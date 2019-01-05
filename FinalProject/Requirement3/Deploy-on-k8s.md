#在Kubernete上部署Web app
+ 当Web app通过CICD平台Drone打包成镜像之后，我们需要通知kubeadm拉取镜像部署Web app。

+ 创建deployment.yaml文件
```
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: application
spec:
  replicas: 1
  template:
    metadata:
      labels:
        backend: application
    spec:
      containers:
        - name: application
          image: registry.cn-shenzhen.aliyuncs.com/pipipan/meetingroom:master
          imagePullPolicy: Always
          ports:
            - containerPort: 8080

---
kind: Service
apiVersion: v1
metadata:
  name: application
spec:
  selector:
    backend: application
  type: NodePort
  ports:
  - port: 8080
    targetPort: 8080
    nodePort: 31000
```

+ 部署Web App，通过浏览器访问成功，部署成功。