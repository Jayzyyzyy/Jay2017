# IOC原理 #

## 一、核心组件 ##
1.Bean组件

包含Bean的定义、创建与解析三部分。

(1)Bean的定义

Bean的定义由BeanDefinition描述。Bean的定义完整描述了在Spring配置文件中定义的<bean/>节点中的所有信息，包括子节点。当Spring解析到一个<Bean/>节点后，在Spring内部就被转换为BeanDefinition对象，以后所有的操作都对这个对象进行。

(2)Bean创建

工厂设计模式，顶层接口是BeanFactory.
![](http://i.imgur.com/uEBbYBb.png)

(3)Bean的解析

Bean的解析主要是对Spring配置文件的解析。

![](http://i.imgur.com/Yzs4u65.png)

2.Context组件

	1. 标示一个应用环境
	2. 利用BeanFactory创建Bean对象
	3. 保存对象关系表
	4. 能够捕获各种事件

Context作为Spring的IOC容器，整合了Spring的 大部分功能，或者是大部分功能的基础。

3.Core组件

Core组件一个重要的功能就是定义了资源的访问方式。

## 二、IOC的工作原理 ##

