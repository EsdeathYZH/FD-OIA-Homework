## Experiment Report
#### author : 王鑫伟

### <hr>

## 1. Requirement :
Design an experiment to measure the metrics of your computer's storage and network

 <br>

## 2. Measure computer's storage

### - 测试指标：

OSD Throughput, Write Latency, Data Distribution and Scalability

Metadata Update Latency, Metadata Read Latency, Metadata Scaling

### - 本机存储的参数信息：

通过设备管理器查找硬盘的型号，本机的硬盘牌子是三星，具体型号是SAMSUNG MZNTY256HDHP-000L2

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/disk.png?raw=true)

从三星官网上查找这种硬盘的具体参数，不过三星官网给的东西不是很多

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/diskParas.png?raw=true)

### - 使用测试工具

#### &ensp;&ensp; 1. AS SSD Benchmark

这种测试工具测试了硬盘在4种常见情况情况下的读写速率，分别是：

Seq表示连续大文件存储，例如大音频，大视频; 

4k单线程，普通的进程的量级; 

4k-64线程表示每个client使用量不大，但并发量很大的情况; 

最后还有access time，最后并给出评分。

可以看出我的电脑硬盘性能确实一般，毕竟比较便宜

![avatar](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/ASSSDBenchmark.png?raw=true)

#### &ensp;&ensp; 2. Crystal DiskMark

这个工具其实和上一个差不多

![avatar](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/CrystalDiskMark.png?raw=true)

#### &ensp;&ensp; 3. ATTO_Disk_Benchmark

![avatar](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/ATTO_Disk_Benchmark.png?raw=true)

#### &ensp;&ensp; 4. HDTunePro

- 基准检测测试

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/HDTunePro_basic.png?raw=true)

- 随机存取测试

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/HDTunePro_random.png?raw=true)

- 文件存取测试

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/HDTunePro_file.png?raw=true)


 <br>

## 3. Measure computer's network

### - 测试指标：

bit rate, bandwidth, throughput, latency/delay, RTT, utilization

### - 本机网卡的参数信息：

一样是从设备管理器中获取网络适配器的信息

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/netParas.png?raw=true)

### - 使用测试工具

这是一个网上博客的方法，用了一个叫iperf的工具

#### &ensp;&ensp; 1. 测试单线程和多线程TCP的性能

开启两个终端，分别输入：

iperf -s -p [server-port] -i 1

iperf -c [server-ip] -p [server-port] -i 1 -t [second(s)] -w 20K

两次测试中修改了一下second参数

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/testTCP.jpg?raw=true)

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/testTCP2.png?raw=true)

#### &ensp;&ensp; 2. 测试单线程和多线程UDP的性能

开启两个终端，分别输入：

iperf -s -u -p [server-port] -i 1

iperf -c [server-ip] -p [server-port] -u -i 1 -t [second(s)]

跟TCP的区别只是在于参数是-u还是-t

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/testUDP.jpg?raw=true)

![avator](https://github.com/EsdeathYZH/FD-OIA-Homework/blob/master/Homework3/A_ExperimentReport/picture/testUDP2.jpg?raw=true)









