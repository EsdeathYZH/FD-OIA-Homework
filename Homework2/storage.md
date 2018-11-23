# Storage



## 1. Vendors or types 
### 1.1 SSD：
![avator](
https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=38860e51b31c8701d6b6b5e01f44f912/e1fe9925bc315c60225ddb5a8db1cb13485477be.jpg)
#### &ensp;&ensp;SSD即固态硬盘，是用固态电子存储芯片阵列而制成的硬盘，由控制单元和存储单元（FLASH芯片、DRAM芯片）组成。它的优点是速度快，日常的读写比机械硬盘快几十倍上百倍。缺点是单位成本高，不适合做大容量存储。
#### &ensp;&ensp;目前SSD可以达到的最高容量为6TB左右，价格约40000RMB，具有1000~6000MB的高IO读写速率,但使用最为广泛的SSD容量是256GB和512GB。SSD在速度、减少碎片化、耐用性、可用性、外形、噪音、电池寿命方面都具有更优异的性能

### 1.2 HDD：
![avator](
https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=b99ee28ddd00baa1ba2c40bd7f2bde2f/0d338744ebf81a4c77acc3a8db2a6059252da630.jpg)
#### &ensp;&ensp;HDD即硬盘驱动器，是最基本的电脑存储器，我们电脑中常说的电脑硬盘C盘、D盘为磁盘分区都属于硬盘驱动器。它的优点是单位成本低，适合做大容量存储，但速度远不如SSD。
#### &ensp;&ensp;目前HDD可以达到的最高容量为10TB左右，仅需要3000RMB，但在IO读写速率上仅有100~250MB。

### 1.3 RAID：
![avator](https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=e659d2f47b094b36db921ceb9bf71be4/0b55b319ebc4b745945d9b3dc2fc1e178b8215cb.jpg)
#### &ensp;&ensp; RAID全称Redundant Array of Independent Disks, 即磁盘阵列。磁盘阵列是由很多价格较便宜的磁盘，组合成一个容量巨大的磁盘组，利用个别磁盘提供数据所产生加成效果提升整个磁盘系统效能。利用这项技术，将数据切割成许多区段，分别存放在各个硬盘上。磁盘阵列还能利用同位检查（Parity Check）的观念，在数组中任意一个硬盘故障时，仍可读出数据，在数据重构时，将数据经计算后重新置入新硬盘中。
#### &ensp;&ensp; 磁盘阵列能够提高传输速率，通过在多个磁盘上同时存储和读取数据来大幅提高存储系统的数据吞吐量；同时可以通过数据校验提供容错功能。
####  &ensp;&ensp; 但其也也有一定的缺点，RAID0没有冗余功能，如果一个磁盘（物理）损坏，则所有的数据都无法使用。RAID1磁盘的利用率最高只能达到50%(使用两块盘的情况下)，是所有RAID级别中最低的。

</br>

## 2. Key indicators
#### &ensp;&ensp; 目前Storage在发展上主要面临三个挑战。一是需要快速高效地存储/访问大量的数据集；二是开发商希望能够使用价格低廉的硬件来达到性能指标；三是有大量的数据在不可靠的机器中传播。为此，人们提出了GFS和Ceph两种解决方案。GFS在课堂上详细阐述过了，这里仅介绍Ceph。

### About Ceph

### &ensp; a) Ceph基础介绍
![avator](
https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/icNyEYk3VqGk91oZGzW0jMNv73lKibM81QhjcewG8mWGufpxnich313icS4HGmxNIzYyuIprM5TxmXkO7xjoRbyvog/640?wx_fmt=pngg)
#### &ensp;&ensp; Ceph是一个可靠地、自动重均衡、自动恢复的分布式存储系统，根据场景划分可以将Ceph分为三大块，分别是对象存储、块设备存储和文件系统服务。在虚拟化领域里，比较常用到的是Ceph的块设备存储，比如在OpenStack项目里，Ceph的块设备存储可以对接OpenStack的cinder后端存储、Glance的镜像存储和虚拟机的数据存储，比较直观的是Ceph集群可以提供一个raw格式的块存储来作为虚拟机实例的硬盘。
#### &ensp;&ensp; Ceph相比其它存储的优势点在于它不单单是存储，同时还充分利用了存储节点上的计算能力，在存储每一个数据时，都会通过计算得出该数据存储的位置，尽量将数据分布均衡，同时由于Ceph的良好设计，采用了CRUSH算法、HASH环等方法，使得它不存在传统的单点故障的问题，且随着规模的扩大性能并不会受到影响。

### &ensp; b) Ceph的核心组件
#### &ensp;&ensp; Ceph的核心组件包括Ceph OSD、Ceph Monitor和Cpth MDS。
#### &ensp;&ensp; Ceph OSD的主要功能是存储数据、复制数据、平衡数据、恢复数据等，与其它OSD间进行心跳检查等，并将一些变化情况上报给Ceph Monitor。一般情况下一块硬盘对应一个OSD，由OSD来对硬盘存储进行管理，当然一个分区也可以成为一个OSD。
#### &ensp;&ensp; Ceph Monitor是一个监视器，负责监视Ceph集群，维护Ceph集群的健康状态，同时维护着Ceph集群中的各种Map图。比如当用户需要存储数据到Ceph集群时，OSD需要先通过Monitor获取最新的Map图，然后根据Map图和object id等计算出数据最终存储的位置。
#### &ensp;&ensp; Ceph MDS主要保存的文件系统服务的元数据，但对象存储和块存储设备是不需要使用该服务的。

### &ensp; c) Ceph的基础架构组件
![avatar](https://images2017.cnblogs.com/blog/1302233/201712/1302233-20171223155452631-121429135.jpg)
#### &ensp;&ensp; 从架构图中可以看到最底层的是RADOS，RADOS自身是一个完整的分布式对象存储系统，它具有可靠、智能、分布式等特性，Ceph的高可靠、高可拓展、高性能、高自动化都是由这一层来提供的，用户数据的存储最终也都是通过这一层来进行存储的，RADOS可以说就是Ceph的核心。
#### &ensp;&ensp; RADOS系统主要由两部分组成，分别是OSD和Monitor。基于RADOS层的上一层是LIBRADOS，LIBRADOS是一个库，它允许应用程序通过访问该库来与RADOS系统进行交互，支持多种编程语言，比如C、C++、Python等。基于LIBRADOS层开发的又可以看到有三层，分别是RADOSGW、RBD和CEPH FS。
#### &ensp;&ensp; ADOSGW是一套基于当前流行的RESTFUL协议的网关，并且兼容S3和Swift。RBD通过Linux内核客户端和QEMU/KVM驱动来提供一个分布式的块设备。CEPH FS通过Linux内核客户端和FUSE来提供一个兼容POSIX的文件系统。

### &ensp; d) Ceph IO 算法流程
![avator](
https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/icNyEYk3VqGk91oZGzW0jMNv73lKibM81Q3Vk69XEF5k7AMHI00TtSyj0KcTL0uibaiczgw4z1gAYSxprypZGTVrmQ/640?wx_fmt=png)

</br>

## 3. Our Comment

###  &ensp; 我们认为Ceph架构的优点:
#### &ensp; a) 高性能
#### &ensp;&ensp; 摒弃了传统的集中式存储元数据寻址的方案，采用CRUSH算法，数据分布均衡，并行度高。
#### &ensp;&ensp; 考虑了容灾域的隔离，能够实现各类负载的副本放置规则，例如跨机房、机架感知等。
#### &ensp;&ensp; 能够支持上千个存储节点的规模，支持TB到PB级的数据。

#### &ensp; b) 高可用性
#### &ensp;&ensp; 副本数可以灵活控制 ; 支持故障域分隔，数据强一致性。
#### &ensp;&ensp; 多种故障场景自动进行修复自愈 ; 没有单点故障，自动管理。

#### &ensp; c) 高可扩展性
#### &ensp;&ensp; 去中心化 ; 扩展灵活 ; 随着节点增加而线性增长。

#### &ensp; d) 特性丰富
#### &ensp;&ensp; 支持三种存储接口：块存储、文件存储、对象存储。
#### &ensp;&ensp; 支持自定义接口，支持多种语言驱动。

#

#### author : 王鑫伟