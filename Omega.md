# OMEGA
### 1. Characteristics
A brief introduction:
>Omega is a parallel scheduler architecture built around shared state, using lock free optimistic concurrency control, to achieve both implementation extensibility and performance scalability.

Omega has several important characteristics:

- **divide and conquer**

There is no central resource allocator in Omega, like the two-level architecture, Omega has many schedulers to schedule different types of jobs, and different scheduler policies is delegated to each scheduler to complete their work.

- **optimistic concurrency control**

Compared to traditional Monolithic architecture, Omega supports concurrent scheduling, 
But the shared things that need concurrency control are just some shared state, so it uses optimistic concurrency control which makes Omega has a better parallelism.

- **full access of each scheduler**

Omega grant each scheduler full access to the entire cluster, allow them to complete in a free-for-all manner. This eliminates the restriction of resource visibility. The scheduler can see the entire state of the resource allocations in the cluster. Each scheduler is responsible for self-management and control.

- **high scalability**

Omega model can scale to a high batch workload while still providing good behavior for service jobs. It offers competitive, scalable performance with little interference at realistic operating points.

### 2. Pros & Cons
- ***Pros***

1.High concurrency, Omega uses optimistic concurrency control. Compare to two-level architecture, Omega has more concurrency which improve its performance obviously.

2.Each scheduler can view the current resource state of clusters, this feature provide potential optimization space. The schedulers complete work in a free-for-all manner.

3.High scalability, Omega delegates different scheduler policies to schedulers, the allocation granularity is reduced to per scheduler policy.

- ***Cons***

1.When the optimistic concurrency assumptions are incorrect, there may be potential cost of redoing work.

 2.Conficts may occur when schedulers want to allocate resources, if there are more conflicts occur, the performance of the system will decrease. 
 
###  3. My comment

Omega is described as \"Google\'s next-generation cluster management platform\".
The paper compare three architectures: monolithic, two-level, Omega. Under the pratical workload, Omega improve some cons of monolithic and two-level. 

But with a high concurrency architecture, a tradeoff occur: there is potential cost of redoing work when the optimistic concurrency assumptions are incorrect. The whole paper involves this tradeoff. 
Evakuation experiments prove the overhead is acceptable at reasonable operating points, so Omega gets a better scalability and concurrency through above mechanisms.

If we want to improve the concurrency of new system, we may sacrificed some safety and consistency. And we usually do some experiments to evaluate whether the overhead is accaptable and indeed improves our new system performance. 

I think there are many problems to be solved, such as fairness, starvation and so on. Through Omega improve the performance and scalability, how to reduce the effects of interference is wait to be solved.

Because the design details of Omega are not open-source, I look forward to a more detailed understanding of Omega.
