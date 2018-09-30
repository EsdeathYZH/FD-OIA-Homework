## Borg(Google)
#### Author: wxw
#### Last modify time: 2018-09-29 20:26

---
---

+ ### 简介
#### &ensp;&ensp;Google的Borg系统是一个集群管理工具，运行着来自不同应用的job，并且跨越很多集群，每个集群又由大量的机器构成。
#### &ensp;&ensp;Borg通过组合准入控制、高效的任务打包，超额负载以及基于进程级别性能隔离的机器共享实现高利用率。它支持那些高可用的应用，运行时特性能够最小化错误恢复时间，它们的调度策略降低了相关错误发生的可能性。
#### &ensp;&ensp; Borg的集群管理系统确认，调度，启动，重启以及监视Google运行的所有应用，是少数能在这么大规模处理这些问题并且还能达到这样弹性和完整性的系统之一。

---

+ ### Borg的优点
#### &ensp;&ensp; 1. 隐藏了资源管理以及错误处理，因此用户能集中精力开发应用
#### &ensp;&ensp; 2. 具有非常高的可靠性和可用性，从而能够支持具有这些特性的应用
#### &ensp;&ensp; 3. 能够让我们跨越数以千计的机器有效地运行负载。
![avatar](https://images2015.cnblogs.com/blog/685359/201604/685359-20160408145529281-465318185.png)

---
+ ### 用户视角 The user perspective
### 1. The workload
#### &ensp;&ensp; Borg上运行的负载主要有两类：第一类是长时间运行、要求快速处理的、延迟敏感的请求(Gmail、Google、Docs、web等)；第二类是需要几秒到几天来完成的批处理，对时间波动不敏感。多数的长时间运行服务是高优先级的。

### 2. Clusters and cells
#### &ensp;&ensp; 一个cell中的机器通常属于单个集群，并且由数据中心规模的高性能网络结构连接起来，多个数据中心的集合构成了一个site。
#### &ensp;&ensp; 一个集群通常包含一个大的cell，也许其中还有一些小规模用于测试或者其他特殊目的的cell。我们总是极力避免单点故障发生。
#### &ensp;&ensp; ps: 所以这次交大断网算是一次单点故障吗？

### 3. Jobs and tasks
#### &ensp;&ensp; Borg的一个job的属性通常包括它的名字，所有者以及它拥有的task的名字。job可以存在一定的约束。一个job的运行可以推迟到上一个结束之后才开始并且一个job只能运行在一个cell中。
#### &ensp;&ensp; 每个task代表了运行在一个容器或者一个物理机器内的一系列Linux进程。Borg的大部分负载并不会运行在虚拟机中，因为我们不想承担虚拟化带来的开销。

### 4. allocs
#### &ensp;&ensp; Borg的alloc操作是指在一台机器上预留一些资源，从而能够在其上运行一个或者多个资源；这些资源不管是否被使用都是保持被分配状态的。
#### &ensp;&ensp; 一个alloc集就像是一个job：它是一系列的alloc操作用于在多台机器上预留资源。一旦一个alloc操作被创建，一个或多个job就能被提交并且运行在它之上。

### 5. Priority quota and admission control
### 6. Naming and monitoring

---

+ ### Borg架构
#### &ensp;&ensp; 一个Borg的cell由一系列的机器组成，通常在cell运行着一个逻辑的中央控制器叫做Borgmaster，在cell中的每台机器上则运行着一个叫Borglet的代理进程。
### 1. Borgmaster
#### &ensp;&ensp; 每个cell的Borgmaster主要由两个进程组成：一个主Borgmaster进程以及一个分离的调度器。主Borgmaster进程用于处理各种客户的RPC请求，。它还用于管理系统中各个对象（机器，task，alloc等）的状态机，和Borglets之间的交互以及提供一个web的UI作为Sigma的备份。

### 2. 调度
#### &ensp;&ensp; 当一个job被提交的时候，Borgmaster会将它持续性地记录在Paxos中，并且将该job中的task都加入挂起队列中。这些都是由调度器异步扫描完成的，它会在有足够资源并且符合job的限制条件的时候将task部署到机器上。。扫描根据优先级从高到底进行，在同一优先级内按照轮转法进行调节从而确保各用户间的公平性并且避免大型job的头端阻塞。

### 3. Borglet
#### &ensp;&ensp; Borglet是一个本地的Borg代理，它会出现在cell中的每一台机器上。它启动，停止task；在task失败的时候重启它们，通过控制操作系统内核设置来管理本地资源以及向Borgmaster和其他监视系统报告机器状态。
#### &ensp;&ensp; Borgmaster每过几分钟就轮询每个Borglet获取机器的当前状态，同时向它们发送外部的请求。这能够让Borgmaster控制交互的速率，避免了显示的流量控制和恢复风暴。

### 4. 可扩展性
#### &ensp;&ensp; 早期的Borgmaster只有一个单一的，同步的循环用于接收请求，调度task以及和Borglet进行通信。为了应付大型的cell，我们将调度器分配到一个独立进程中，从而使它能够和其他用于异常处理的Borgmaster函数并行工作。为了提高响应时间，我们添加了额外的线程用于和Borglet的交互以及响应只读的RPC。

---

+ ## 引用及参考文献
#### Abhishek Verma, Luis Pedrosa, Madhukar Korupolu, David Oppenheimer, Eric Tune, John Wilkes . Large-scale cluster management at Google with Borg[J].Google Inc. 1-17
