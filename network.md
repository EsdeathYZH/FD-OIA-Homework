# Neutron——虚拟化网络解决方案
## 从物理网络到虚拟网络
在虚拟机还并不流行的时候，网络就已经是很繁杂且难以处理的一项事物了，从网络边缘到NP内部路由再到NP间的路由，从ARP协议到BRP协议再到HTTP协议，从物理层到链路层最后在到应用层的OSI七层协议，整个网络栈已经是个庞然大物了，但是其复杂度依然还是在可控的范围内，对于二层网络无非是交换机或者是VLAN，对于三层网络无非是交换机，有时为了联通两个内网可能要用类似隧道，VXLAN这样的操作。

但是这一切同虚拟网络比起来却又是小巫见大巫了，随着虚拟机的流行，因为每一个namespace都有自己独有的网络栈，整个虚拟网络的复杂度也就随之再上了一个档次，除了物理机与物理机之间的通信，更多需要处理的是不同宿主机上虚拟机之间的通信，尤其是当虚拟机在同一网段却不在同一个宿主机上时，问题又该怎么解决呢？随之出现的解决方案有Linux自带的Bridge，有Open VSWitch，也有Calico这样基于三层路由的SDN，而今天需要讨论的就是OpenStack使用的处理虚拟网络的技术——Neutron，当然除了作为OpenStack网络的核心解决方案之外，它还是SDN的一个解决方案。
## 什么是Neutron
首先，Neutron是OpenStack的网络服务项目。

其次，这是一个python的软件项目。

再来进一步看，OpenStack Neutron是一个多进程运行的项目。常见的进程包括了neutron-server和neutron-xxx-agent们。通常，Neutron是一个分布式部署的模式，也就是说不同的进程部署在不同的操作系统里面。进程间同步通过AMQP，通常是rabbitmq。

总的来说：Neutron 是一个用python写的分布式软件项目，用来实现OpenStack中的网络服务。
## Neutron功能介绍
除了在分布式环境下面给虚拟机提供网卡，此外，Neutron还要虚拟化网络功能的其它元件，比如抽象交换机，抽象负载均衡器，抽象防火墙，抽象NAT。

Neutron在实现虚拟化的时候使用到的已有的软件或者模块，如下表所示：

|     组件名称      |         实现方式           |
| -------------- | --------------------- |
|  网卡 |      Open VSwitch       |
|   防火墙  |     iptables           |
|    交换机     |  Open VSwitch or Linux Bridge |
|   路由器   |     ip协议栈和iptables      |
| 负载均衡器 |           haproxy            |


## Neutron虚拟网络定义类型
#### 1.Local

只允许在本服务器内通信的虚拟网络，不能跨服务器通信，通常用于单节点测试

#### 2.Flat

所有租户都在一个网络内，没有进行网络隔离，容易产生广播风暴，一般用于提供者网络

#### 3.Vlan

基于物理Vlan网络实现的虚拟网络，共享同一个物理网络的多个Vlan网络是相互隔离的，甚至可以使用重叠的IP地址空间。每个支持Vlan network的物理网络可以被视为一个分离的Valn trunk，它使用一组独占的Vlan ID，有效的VLAN ID范围是1到4094
一般只用于提供者网络

#### 4.GRE（Generic Routing Encapsulation）

通用路由协议封装协议，是一种Ip-over-IP的隧道
GRE是L3层的隧道技术，本质是在隧道的两端的L4层建立UDP连接传输重新包装的L3层包头，在目的地再取出包装后的包头进行解析，GRE封装的数据包基于路由表来进行路由，因此GRE network不和具体的物理网络绑定，一般只适用于租户网络

## Neutron架构
和OpenStack 其他服务一样，Neutron 采用的是分布式架构，包括 Neutorn Server、各种 plugin/agent、database 和 message queue，

其具体功能如下：

|      服务      |         功能          |
| -------------- | --------------------- |
| Neutron server |     接收 api 请求     |
|  plugin/agent  |       实现请求        |
|    database    | 保存 neutron 网络状态 |
| message queue  |   实现组件之间通信    |

如果我们将 Neutron架构更加详细地展开，我们可以得到一个更加细致的模型：

Neutron 通过 plugin 和 agent 提供的网络服务。

plugin 位于 Neutron server，包括 core plugin 和 service plugin。

agent 位于各个节点，负责实现网络服务。

core plugin 提供 L2 功能，ML2 是推荐的 plugin。

使用最广泛的 L2 agent 是 linux bridage 和 open vswitch。

service plugin 和 agent 提供扩展功能，包括 dhcp, routing, load balance, firewall, vpn 等。
## Neutron简评
不可否认的，Neutron作为一个SDN解决方案很好的解决了虚拟机之间的通信方式，通过分布式系统的方式将各个组件解耦了开来，能做到最大化的灾备以及可扩容化，再通过物理节点，网络节点以及控制节点三种节点的划分，各司其职，使Neutron整体的架构清晰易懂并适合进行二次开发。除此之外，通过在物理实际网络上方搭建一层OverLay网络，使得物理网络透明化，让虚拟机以为自己在一个确确实实的物理网络上，并通过虚拟交换机和虚拟路由器来进行二层以及三层通信。

但是虚拟网络相较于物理网络总归在性能上相差了很多，一个体现在物理硬件设备以及软件虚拟设备对于报文的处理速度的差异可以有2~3个数量级，除此之外，由于Ip-in-Ip这样的隧道技术在虚拟网络中的普及，而这样的报文解析相较于传统的报文解析多了一层链路层以及IP层解析，这也就造成了另外一个性能方面的瓶颈。

总而言之，在物理网络的性能和虚拟网络的多租户隔离之间避免不了适当的trade off，我们需要做的更多的是理清我们的业务思路，并为之选择更加适合我们的解决方案，随着SDN技术的进一步发展，这也终将成为时代的潮流！
