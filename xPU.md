Technical Report of xPU
=======================

## Brief introduction about xPU

Along with the rising of AI, various AI hardware accelerators have been brougout out to the world. 

xPU generally implies these AI hardware accelerator.

However, xPU stands for all kinds of Processing Unit and those yet not named processing unit. The table below shows most of the xPU.

| xPU | full name | brief |
|:---:|:---------:|:------|
| APU | Accelerated Processing Unit | Chip of AMD integrating both CPU and GPU on the same die |
| BPU | Brain Processing Unit | AI chips developed by Horizon Robotics |
| CPU | Central Processing Unit | (We are all familiar with it) |
| DPU | Deep Learning Processing Unit | Chips optimized for CNN workloads and RNN workloads developed by Deephi |
| DPU | Dataflow Processing Unit | Product of Wave Computing, computing is driven by dataflow |
| DPU | Deep Learning Accelerator | An open and standardized architecture by Nvidia to address the computational demands of inference |
| EPU | Emotional Processing Unit | the MCU microchip designed by Emoshape to enable emotional repsonse in AI, robots and consumer electronic devices |
| FPU | Floating Processing Unit | Integrated in most CPU, DSP and GPU to provide float processing |
| GPU | Graphics Processing Unit | (It is as common as CPU nowadays) |
| HPU | Holographic Processing Unit | The specific hardware of Microsoft Hololens |
| IPU | Intelligence Processing Unit | specific for the graph related application by GraphCore |
| IPU | Intelligence Proccing Unit | Product provided by Mythic whose prototype introduces processing-in-memory and performs hybrid digital/analog calculation inside flash arrays |
| IPU | Image Processing Unit | The Pixel Visual Core designed by Google and integrated in Google Pixel 2 to enhance image processing ability |
| NPU | Neural Network Processing Unit | A general name of AI chip |
| SPU | Stream Processing Unit | specialized hardware to process the data streams of video |
| TPU | Tensor Processing Unit | Google’s specialized hardware for neural network |
| VPU | Vision Processing Unit | the specialized chip for computer vision workloads |
| ZPU | Zylin CPU | a small, portable CPU core by a Norwegian company Zylin AS to run supervisory code in electronic systems that include an FPGA |

In this article, I'm going to introduce GPU, NPU and TPU in detail. 

## GPU

> The term GPU was popularized by Nvidia in 1999.  
> A graphics processing unit (GPU) is a specialized electronic circuit designed to rapidly manipulate and alter memory to accelerate the creation of images in a frame buffer intended for output to a display device.

![GPU architecture](https://www.researchgate.net/profile/Liang_Men2/publication/288930312/figure/fig1/AS:408818962976774@1474481444612/Nvidias-Fermi-GPU-Architecture.png)

### Feature

* GPU contains more cores comparing to CPU
* A simple logic control, without branch predicting and data forwarding
* No cache
* Long pipeline

    ![CPU core comparing to CPU](https://www.nvidia.cn/content/tesla/images/cpu-and-gpu.jpg)

### Pros

* High parallel computing
* Low frequency than CPU, low power
* High throughput
* Powerful at graphic processing

### Cons

* Weak in logic control

## NPU

> A neural processor or a neural processing unit (NPU) is a microprocessor that specializes in the acceleration of machine learning algorithms, typically by operating on predictive models such as artificial neural networks (ANNs) or random forests (RFs).

![NPU](http://en.people.cn/NMediaFile/2016/0622/FOREIGN201606221424000501629084965.jpg)

### Features

* NPU has only a few cores, some even only one core so far
* NPU mimic human neural network
* NPU has a different ISA from traditional ISA, which is intended for general computing like arithmetic computing

### Pros

* More efficient at deep learning
* Main frequency is less than CPU
* No less performance comparing to GPGPU
* Less area comparing to GPU
* Less power consumption comparing to GPU

### Cons

* Restricted to a few scenario, like deep learning, AI.
* Not standardized and popularized

## TPU

> Tensor Processing Unit (TPU) is Google first custom accelerator ASIC [application-specific integrated circuit] for machine learning. It is customized to give high performance and power efficiency when running TensorFlow.

![TPU architecture](https://img.technews.tw/wp-content/uploads/2017/04/07111001/first-in-depth-look-at-googles-tpu-architecture-1.jpg)

### Features

* TPUs are customized for machine learning applications using TensorFlow.
* The heart of the TPU is a 65,536 8-bit MAC matrix multiply unit.
* Peak throughput of 92 TeraOps/second (TOPS)
* A large (28 MiB) software-managed on-chip memory
* Support common NN network and TensorFlow framework
* Deterministic execution model
* CISC ISA

### Pros

* 15X - 30X faster than its contemporary GPU or CPU, with TOPS/Watt about 30X - 80X higher
* using the GPU’s GDDR5 memory in the TPU would triple achieved TOPS and raise TOPS/Watt to nearly 70X the GPU and 200X the CPU

### Cons

* Matrix Multiply Unit is specifically optimized for matrix multiply, not fit for general computing

## Measure

To measure the performance of an xPU, it is easy for us to recall benchmark. And we do have some benchmark tools now for CPU and GPU, but not for others like TPU and NPU.

Companies making xPUs takes another metric instead, that is, how much operations the processer can finish in a second, refered to OPS.

As xPU have a really high frequency, the performance metric is often referred to as GOPS or TOPS. 
For example, Google TPU reaches 92 TOPS, meaning the TPU can perform 92 T operations in a second.

Along with TOPS, TOPS/Watt is often referred to. TOPS/Watt stands for how many operations a processor can perform in one second and only with 1 watt energy. This is a no less important metirc for the xPU, which measures its power efficiency.

## Comment

xPU is very  similar to GPU in the way it merges. GPU cones out from CPU as CPU is too heavily burdened with graphic processing. xPU comes out as CPU is too slow for AI. Althougn GPU performs better than CPU for its ablitity to processing massive data, this is based on rather high power consumption. Yet only a few chips for AI have staged, like NPU of Cambrian, Google's TPU, but all shows better performance than CPU and GPU. We can assume that, our computer, mobile phone will no longer contains just CPU and GPU, but more xPUs for various purposes. And some point in the future, these xPUs will be integrated to be a electronic brain, just like human brain. 