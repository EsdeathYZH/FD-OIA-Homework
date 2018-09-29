# A brief introduction to Apollo

## What is Apollo
Apollo is a highly scalable and coordinated scheduling framework, which has been deployed on production clusters at Microsoft to schedule thousands of computations with millions of tasks efficiently and effectively on tens of thousands of machines daily.

## The Performance of Apollo
Apollo schedules over 20,000 tasks per second in a production cluster with over 20,000 machines. It also delivers high scheduling quality, with 95% of regular tasks experiencing a queuing delay of under 1 second, while achieving consistently high (over 80%) and balanced CPU utilization across the cluster.

## The Archeticture of Apollo
<img src = "https://github.com/EsdeathYZH/FD-OIA-Homework/blob/apollo-pzy/architecture.PNG?raw=true">

* A Job Manager (JM),which is implemented in a master-slave configuration using Paxos is assigned to manage the life cycle of each job. The global cluster load information used by each JM is provided through the cooperation of two additional entities in the Apollo framework: a Resource Monitor (RM) for each cluster and a Process Node (PN) on each server.
* A PN process running on each server is responsible for managing the local resources on that server and performing local scheduling
* the RM aggregates load information from PNs across the cluster continuously, providing a global view of the cluster status for each JM to make informed scheduling decisions.
* a local queue of tasks is maintained by each PN to better predict resource utilization in the near future and to optimize scheduling quality. It advertises its future resource availability in the form of a wait-time matrix inferred from the queue

## The Technical Contribution of Apollo
1. To balance scalability and scheduling quality, Apollo adopts a distributed and (loosely) coordinated scheduling framework.
2. To achieve high-quality scheduling decisions, Apollo schedules each task on a server that minimizes
the task completion time. 
3. To supply individual schedulers with cluster information, Apollo introduces a lightweight hardwareindependent mechanism to advertise load on
servers.
4. To cope with unexpected cluster dynamics, Apollo is made robust through a series of correction mechanisms that dynamically adjust and rectify suboptimal decisions at runtime.
5. To drive high cluster utilization while maintaining low job latencies, Apollo
ensures low latency for regular tasks, while using the opportunistic tasks for high utilization to fill in the slack left by regular tasks.
6. To ensure no service disruption, Apollo is designed to support staged rollout to production clusters
and validation at scale.

## Prons
1. Apollo adopts a distributed and loosely coordinated scheduling architecture that scales well without sacrificing scheduling quality.
2. Each Apollo scheduler considers various factors holistically and performs estimationbased scheduling to minimize task completion time
3. By maintaining a local task queue on each server, Apollo enables each scheduler to reason about future resource availability and implement a deferred correction mechanism to effectively adjust suboptimal decisions dynamically.
4. To leverage idle system resources gracefully, opportunistic scheduling is used to maximize the overall system utilization. 
5. Apollo is robust, with means to cope with unexpected system dynamics, and can take advantage of idle system resources  gracefully while supplying guaranteed resources when needed.

## Crons
1. Apollo is not open-source and can't make secondary development.
2. Apollo is too heavy and complicated, it's hrad for people to master it.
3. Apollo is not rich in the kinds of jobs, such as k8s's deployment, service, cronjob, batchjob and so on.

------
## Reference
1. Eric Boutin, Jaliya Ekanayake, Wei Lin, Bing Shi, and Jingren Zhou, Microsoft  "Apollo: Scalable and Coordinated Scheduling for Cloud-Scale Computing." (2014) 
