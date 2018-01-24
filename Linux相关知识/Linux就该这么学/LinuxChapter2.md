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
###(6) top 	
