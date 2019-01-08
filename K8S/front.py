from locust import HttpLocust, TaskSet, task

class MyTaskSet(TaskSet):
    @task
    def index(self):
        self.client.get("/")

class MyLocust(HttpLocust):
    task_set = MyTaskSet
    host="http://129.211.119.51:31002"
    min_wait=1000
    max_wait=10000
