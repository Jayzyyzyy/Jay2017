# 必须掌握的Linux命令

##1.执行查看帮助命令

	命令名称 [命令参数] [命令对象]

	man----format and display the on-line manual pages

##2.常用系统工作命令

###(1) echo 打印输出
	echo 字符串-------echo linuxprobe.com---->linuxprobe.com
	echo $变量--------echo $SHELL----->/bin/bash (读取变量值并输出)
###(2) date 时间
	date ---> 2018年 01月 23日 星期二 20:16:48 CST
	date "+%Y-%m-%d %H:%M:%S"  ---> 2018-01-23 20:18:03  (加参数)
	date "+%j"  ---> 23   (一年中的第几天)
###(3) reboot/poweroff
	关闭Linux系统
###(4) wget 获取网络文件
	wget http://www.linuxprobe.com/docs/LinuxProbe.pdf ---> 获取LinuxProbe.pdf
	wget -r -p http://www.linuxprobe.com/ ---> 递归下载该地址下的所有页面数据及文件
###(5) ps 查看系统进程的状态

	1.命令参数
	-a 显示所有进程
	-u 用户以及其他详细信息
	-x 显示没有控制终端的进程
	
	2.进程状态
	R---运行  S---中断  D---不可中断 Z---僵死 T---停止
	
	3.进程状态实例(ps aux)
	
[![pIOeAI.md.png](https://s1.ax1x.com/2018/01/23/pIOeAI.md.png)](https://imgchr.com/i/pIOeAI)

###(6) top 	 动态监视进程活动与系统负载

###(7) pidof 查询进程的pid号

	pidof init ---> 1(pid号)

###(8) kill 终止指定pid号的进程

	pidof  服务进程名 ---> pid号
	kill   pid号 ---> 终止

	killall 服务进程名 ---> 终止某个服务程序带有的全部进程

##3.系统状态检测命令
###(1) ifconfig 获取网卡与网络状态等信息
	
###(2) uname 查看系统内核与系统版本信息

	Linux Jay 2.6.32-504.el6.x86_64 #1 SMP Wed Oct 15 04:27:16 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux

###(3) uptime 查看系统负载
	20:16:17 up 51 min,  2 users,  load average: 0.00, 0.00, 0.00 
	load average(平均负载): 最近1、5、15分钟内的压力情况，尽量不要长期超过1，生产环境中不能超过5.

###(4) free 内存使用量信息
	
###(5) who 查看当前登录主机的用户终端信息
	用户名   终端设备       登录时间
	Jay      tty1         2018-01-24 19:26 (:0)
	Jay      pts/0        2018-01-24 19:27 (192.168.209.1)

###(6) last 