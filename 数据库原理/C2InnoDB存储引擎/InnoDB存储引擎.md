# InnoDB存储引擎 #

InnoDB是**事务安全**的MySQL存储引擎，是**OLTP**应用中**核心表的首选存储引擎。**

## 一、InnoDB存储引擎概述 ##
特点：


> 行锁设计、全文索引、支持MVCC、支持外键、提供一致性非锁定读。

## 二、InnoDB体系架构 ##

![](https://jockchou.gitbooks.io/jockchou/content/img/innodb-1.png)

![](http://www.nathanyan.com/img/innodb_56_architecture.png)


InnoDB存储引擎有多个内存块，这些内存块组成了一个大的内存池。负责如下工作：①维护所有进程/线程需要访问的多个内部数据结构。②缓存磁盘上的数据，方便快速的读取，同时在对磁盘文件的数据修改之前在这里进行缓存。③重做日志缓冲等。

**后台线程的主要作用**是负责刷新内存池中的数据，保证缓冲池中的内存缓存的是最近的数据，另外，将已修改的数据文件刷新到磁盘文件，同时，保证在数据库发生异常的情况下，InnoDB能恢复到正常运行状态。

### Ⅰ.后台线程 ###
InnoDB是多线程模型，不同的线程用于处理不同的任务。

1. `Master Thread` 
 
	Master Thread是核心后台线程，主要负责将缓冲池中的数据异步刷新到磁盘，包括脏页的刷新，合并插入缓冲(insert buffer)，undo页的回收等。

2. `IO Thread`
 
	IO Thread负责IO请求的回调。主要有`read, write, insert buffer`和`log IO Thread`。其中read thread和write thread分别使用`innodb_read_io_threads`和`innodb_write_io_threads`参数进行设置。可以通过命令`show engine innodb status`来查看InnoDB中的IO Thread.

3. `purge thread`

	事务被提交后，其所使用的undolog可能不再需要，Purge Thread负责回收undo页。在InnoDB1.1版本之前，purge操作仅在Master Thread中完成。InnoDB1.1开始，purge操作可以独立在Purge Thread中进行。通过如下配置来启用独立的Purge Thread:

	`innodb_purge_threads=1`

	InnoDB1.2版本开始支持多个Purge Thread，加快undo页的回收。由于是离散地读取undo页，也能更进一步利用磁盘的随机读取性能。

4. `Page Cleaner Thread`

	InnoDB1.2版本引入了Page Cleaner Thread。作用是将脏页刷新操作都放到单独的线程中来完成，从而减轻Master Thread的工作。

### Ⅱ.内存 ###

**1.缓冲池**

(a)简介
> 1.InnoDB引擎是基于磁盘存储的，将**数据记录按照页的方式进行管理**，可视为基于磁盘的数据库系统。由于CPU与磁盘速度之间的差异，基于磁盘的数据库系统使用**缓冲池技术**提高数据库的整体性能。
> 
> 2.(Read)在数据库中进行读取页的操作时，首先将从磁盘读到的页存放在缓冲池中(将页"FIX"到缓冲池)。下次再读到相同的页时，首先判断该页是否在缓冲池中，若在缓冲池，则该页在缓冲池被命中，直接读取该页；否则，读取磁盘上的页。
> 
> 3.(Update)对数据库页的修改操作，首页修改缓冲池中的页，然后再按照某种频率刷新到磁盘上，这种频率的控制是通过一种称为**Checkpoint**的机制来实现。
> 
> 4.缓冲池的大小直接影响到数据库的整体性能，其通过参数`innodb_buffer_pool_size`来设置，额外内存池通过参数`innodb_additional_mem_pool_size`来设置。

> 5.InnoDB缓冲池包含的数据页类型有：**索引页，数据页**，undo页，插入缓冲（insert buffer)，自适应哈希索引，InnoDB存储的锁信息，数据字典信息等。

(b)InnoDB存储引擎内存结构

![](https://jockchou.gitbooks.io/jockchou/content/img/innodb-2.jpg)

InnoDB1.0.X版本开始，允许有多个缓冲池实现。page根据哈希平均分配到不同的缓冲池实例中，用来减少资源竞争，提高并发。可以通过参数`innodb_buffer_pool_instances`进行配置。

**2.LRU List/Free List/Flush List**

**<1>LRU List**

1.InnoDB缓冲池是通过LRU(最近最少使用)算法来管理page的。频繁使用的page放在LRU列表的前端，最少使用的page在LRU列表的尾端，缓冲池满了的时候，优先淘汰尾端的page。

2.InnoDB中的LRU结构

InnoDB引擎中page的默认大小为16KB(每页)，InnoDB对传统的LRU算法做了一些优化。在InnoDB存储引擎中，LRU 列表中加入了`midpoint`位置。新读取到的页放入列表的midpoint位置，而不是列表的首部，这种策略叫做`midpoint insertion strategy`。

![](https://jockchou.gitbooks.io/jockchou/content/img/innodb-3.png)

LRU列表被分成两部分，**midpoint点之前**的部分称为**new列表**，**之后**的部分称为**old列表**，new列表中的页都是**最为活跌的热点数据**。midpoint的位置通过参数innodb_old_blocks_pct来设置。

参数innodb_old_blocks_pct默认值为37，表示**新读取的page将被插入到LRU列表左侧的37%**（差不多3/8的位置）。

3.不采用传统的LRU算法的原因

若直接将读取到的page放到LRU的首部，那么**某些SQL操作可能会使缓冲池中的page被刷出**。常见的这类操作为**索引或数据的扫描操作**。这类操作访问表中的许多页，而这些页通常只是在这次查询中需要，并不是活跃数据。如果放入到LRU首部，那么非常可能将真正的热点数据从LRU列表中移除，在下一次需要时，InnoDB需要<font color=red>重新访问磁盘</font>读取，这样性能会低下。

InnoDB进一步引入了另一个参数来管理LRU列表，这个参数就是`innodb_old_blocks_time`，用于表示page放到midpoint位置后需要等待多久才会被加入到LRU列表的new端成为热点数据。

**<2>Free List(LRU中Page的变化)**

1.数据库启动时，LRU列表是空的，即没有任何page，这时page都存放在Free列表中。当需要从缓冲池中分页时，首先从Free列表中查找是否有可用的空闲页，若有则将page从Free中删除，放入到LRU中。否则，根据LRU算法，淘汰LRU列表末尾的页分配给新的页。

2.当页从old部分进入到new部分时，此时发生的操作为`page made young`。因为`innodb_old_blocks_time`参数导致page没有从old移动到new部分称为`page not made young`。

3.实例

	----------------------
	BUFFER POOL AND MEMORY
	----------------------
	Total memory allocated 4395630592; in additional pool allocated 0
	Dictionary memory allocated 28892957
	Buffer pool size   262143 //
	Free buffers       0
	Database pages     258559
	Old database pages 95424
	Modified db pages  36012
	Pending reads 0
	Pending writes: LRU 0, flush list 0, single page 0
	Pages made young 72342127, not young 0
	8.82 youngs/s, 0.00 non-youngs/s
	Pages read 72300801, created 339791, written 13639066
	8.56 reads/s, 0.35 creates/s, 3.79 writes/s
	Buffer pool hit rate 1000 / 1000, young-making rate 0 / 1000 not 0 / 1000
	Pages read ahead 0.00/s, evicted without access 0.00/s, Random read ahead 0.00/s
	LRU len: 258559, unzip_LRU len: 0
	I/O sum[459]:cur[1], unzip sum[0]:cur[0]

- Buffer pool size表示**缓冲池共有262143个page**，即262143 * 16K，约为4GB
- Free buffers表示**当前Free列表中page的数量**
- Database pages表示**LRU列表中page的数量**
- Old database pages表示**LRU列表中old部分的page数量**
- Modified db pages表示的是**脏页(dirty page)的数量**
- Pages made young表示**LRU列表中page移动到new部分的次数**
- youngs/s, non-youngs/s表示**每秒这两种操作的次数**
- Buffer pool hit rate表示**缓冲池的命中率，该值若小于95%，需要观察是否全表扫描引起LRU污染**
- LRU len表示**LRU中总page数量**

可以看到Free buffers与Database pages的和不等于Buffer pool size，这是因为**缓冲池中的页还会被分配给自适应哈希索引，Lock信息，Insert Buffer等页**，这部分页不需要LRU算法维护。

**<3>压缩页`unzip_LRU`**

(a)InnoDB支持将原本16KB的页压缩为1KB、2KB、4KB、8KB。对于非16KB的页，通过`unzip_
LRU`列表进行管理。

(b)`unzip_LRU`如何让从缓冲池中分配内存

1. 首先在unzip_LRU列表中对不同压缩页大小的页进行分别管理。
2. 其次，通过伙伴算法进行内存的分配，例如对**需要从缓冲池中申请页为4KB的大小**
	1. 检查4KB的unzip_LRU列表，检查是否有可用的空闲页。
	2. 若有，则直接使用；
	3. 否则，检查8KB的unzip_LRU列表；
	4. 若能够得到空闲页，将页分成2个4KB的页，存放到4KB的unzip_LRU列表中；
	5. 若不能得到空闲页，从LRU列表中申请一个16KB的页，将页分成1个8KB的页、2个4KB的页，分别存放到对应的unzip_LRU列表中。

**<4>Flush List**

LRU列表中的page被修改后，称该页为脏页，即缓冲池中的页和磁盘上的页的数据产生了不一致。这时InnoDB通过Checkpoint机制将脏页刷新回磁盘。而Flush列表中的页即为脏页列表。脏页既存在于LRU列表中，又存在于Flush列表中，二者互不影响。**Modified db pages显示的就是脏页的数量。**

**3.重做日志缓冲**

InnoDB引擎首先将重做日志信息先放到重做日志缓冲区(`redo log buffer`)，然后按一定频率刷新到重做日志文件。重做日志缓冲不需要设置很大，一般每一秒都会刷新`redo log buffer`，配置的大小只需要保证每秒产生的事务在这个缓冲区大小之内即可。

在下列情况下会将重做日志缓冲中的内容刷新到磁盘重做日志文件中：

- Master Thread每一秒中刷新一次
- 每个事务提交时会刷新
- 当重做日志缓冲区空间小于1/2时

**4.额外的内存池**

额外的内存池用来对一些数据结构本身的内存进行分配，例如每个缓冲池对应的缓冲控制对象(buffer control block)记录LRU，锁，等待等信息，这些对象的内存需要从额外的内存池申请。在申请了很大的InnoDB的缓冲池，也应考虑相应的增加这个值。

**额外的内存池不够时会从缓冲池中进行申请。**

## 三 、```Checkpoint技术(InnoDB脏页刷新机制)``` ##

> InnoDB采用`Write Ahead Log`策略来防止宕机数据丢失，即事务提交时，先写重做日志，再修改内存数据页。当由于发生宕机而导致数据丢失时，通过重做日志来完成数据恢复。

当数据库宕机时，数据库不需要重做所有的日志，只需要执行上次刷入点之后的日志。这个点就叫做`Checkpoint`.这个检查点解决了这些问题：

- 缩短数据库恢复时间
	
	数据库只需对Checkpoint后的重做日志进行恢复。CHeckpoint之前的页已经刷新回磁盘。
- 缓冲池不够用时，将脏页刷新到磁盘

	当缓冲池不够用时，根据LRU算法会溢出最近最少使用的页，若此页为脏页，需要强制执行Checkpoint，将脏页刷新回磁盘。
- 重做日志不可用时，刷新脏页

	重做日志被设计成可循环使用，当日志文件写满时，重做日志中对应数据已经被刷新到磁盘的那部分不再需要的日志可以被覆盖重用。数据库恢复时，如果不需要，即可被覆盖；如果需要，必须强制执行checkpoint，将缓冲池中的页至少刷新到当前重做日志的位置。
 
1.InnoDB引擎通过LSN(`Log Sequence Number`8字节)来标记版本，LSN是日志空间中每条日志的结束点，用字节偏移量来表示。每个page有LSN，redo log也有LSN，Checkpoint也有LSN。

2.在InnoDB存储引擎中饭有两种Checkpoint，分别为:

	1. Sharp Checkpoint(全部刷新)
		Sharp Checkpoint发生在关闭数据库时，将所有脏页刷回磁盘。这是默认的工作方式，即参数：innodb_fast_shutdown=1.
	
	2. Fuzzy Checkpoint(只刷新部分脏页)
		
3.发生Fuzzy Checkpoint的情况

(1).**异步刷新**，每秒或没10秒从缓冲池脏页列表刷新一定比例的页回磁盘。异步刷新，即此时InnoDB存储引擎可以进行其他操作，**用户查询线程不会受阻**。<br>
(2).**`FLUSH_LRU_LIST Checkpoint`**<br>
InnoDB存储引擎需要保证LRU列表中差不多有100个空闲页可供使用。在InnoDB 1.1.x版本之前，用户查询线程会检查LRU列表是否有足够的空间操作(阻塞)。如果没有，根据LRU算法，溢出LRU列表尾端的页，如果这些页有脏页，需要进行checkpoint。因此叫：`flush_lru_list checkpoint`<br>

mysql5.6之后，也就是Innodb1.2.x开始，这个检查放在了单独的进程（**Page Cleaner**）中进行。**好处：1.减少master Thread的压力 2.减轻用户线程阻塞。**

(3).**Async/Sync Flush Checkpoint**

指重做日志不可用的情况，需要强制刷新页回磁盘，此时的页时脏页列表选取的。这种情况是保证重做日志的可用性，说白了就是，重做日志中可以循环覆盖的部分空间太少了，换种说法，就是极短时间内产生了大量的redo log。

写入日志的LSN:`redo_lsn`<br>
刷新回磁盘的最新页LSN:`checkpoint_lsn`<br>
定义:<br>

  - `checkpoint_age = redo_lsn - checkpoint_lsn`
  - `async_water_mark = 75% * total_redo_file_size`
  - `sync_water_mark = 90% * total_redo_file_size`

![](http://images2015.cnblogs.com/blog/1063598/201703/1063598-20170320170146143-2098588440.png)

关于用户阻塞的问题：<br>
在InnoDB1.2.x版本之前，**Async Flush Checkpoint 会阻塞发现问题的用户查询线程；Sync Flush Checkpoint会阻塞所有用户查询线程**。<br>
在InnoDB1.2.x版本开始，也就是MySQL5.6，这部分的刷新操作放在了单独的线程中：**Page Cleaner Thread**中，故不会阻塞用户查询线程。

(4).Dirty Page too much

  即脏页太多，强制checkpoint.保证缓冲池有足够可用的页。 参数设置：innodb_max_dirty_pages_pct = 75 表示：当缓冲池中脏页的数量占75%时，强制checkpoint。1.0.x之后默认75.

## 四、Master Thread工作方式 ##


