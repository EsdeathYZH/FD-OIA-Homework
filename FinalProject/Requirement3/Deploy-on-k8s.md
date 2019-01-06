# 在Kubernete上部署Web app
+ 当Web app通过CICD平台Drone打包成镜像之后，我们需要通知kubeadm拉取镜像部署Web app。

+ 这个步骤是在Drone的deploy阶段做的，命令很简单，就是通知K8S重新拉镜像部署
```
#! /bin/bash
/root/rke_deploy/kubernetes/client/bin/kubectl delete -f /root/web-application/web-frontend-deploy.yaml
/root/rke_deploy/kubernetes/client/bin/kubectl create -f /root/web-application/web-frontend-deploy.yaml
```

+ 创建deployment.yaml文件，下面列出前端的部署yaml
```
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: frontend
spec:
  replicas: 1
  template:
    metadata:
      labels:
        frontend: frontend
    spec:
      containers:
        - name: frontend
          image: registry.cn-shenzhen.aliyuncs.com/pipipan/frontend:master
          imagePullPolicy: Always
          resources:
          # keep request = limit to keep this container in guaranteed class
            limits:
              cpu: 500m
              memory: 500Mi
            requests:
              cpu: 100m
              memory: 100Mi
          ports:
            - containerPort: 8080

---
kind: Service
apiVersion: v1
metadata:
  name: frontend
spec:
  selector:
    frontend: frontend
  type: NodePort
  ports:
  - port: 8080
    targetPort: 8080
    nodePort: 31002
```

+ 部署Web App，通过浏览器访问成功，部署成功。
