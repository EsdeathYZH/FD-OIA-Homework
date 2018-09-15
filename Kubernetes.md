# A brief introduction to Kubernete 

## Concepts

### VMs and container

* VM  
    VM (virtual machine) contains a complete operating system. VMs are super resilient

* Container  
    Containers are lightweight. They aren't full VMs. They come from process separation through cgroups and kernel namespaces. They are not resilient, thus they need orchestation to run efficiently and resiliently.

### Applications versus Services

* Applications
    Applications has user interface and performs a lot of different tasks. It is not a must for them to expose API.

* Services
    Services is designed to handle a small number of things.
    Service has no user interface and is invoked solely via some kind of API.

## Kubernetes

Google used to orchestrate and schedule software with Borg. When Docker is first released, Kubernetes is developed with the most useful (and externalizable) bits of the Borg cluster management system.

Kubernetes cluster doesn't manage a fleet of applications but a cluster of services. These services running in containers which is managed by Kubernetes.

The basic Kubernetes layout includes _master_ and _node_. Collections of masters and nodes are known as clusters.

The Master run three items: API Server, Etcd, Scheduler And Controller Manager.

A node usually runs three important processes: Kubelet, Proxy, cAdvisor.

### Pods

A pod contains a collection of containers and volumes which are bundled and scheduled together. The containers in a pod share a common resource - usually a filesystem or IP address.

Usually each container gets its own IP address in standard Docker configuration. But Kubernetes schedules and orchestrates things at the pod level, not container level.