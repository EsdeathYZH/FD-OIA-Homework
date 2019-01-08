from locust import HttpLocust, TaskSet, task
from faker import Faker
from random import randint
import json

myfake = Faker("zh_CN")

class MyTaskSet(TaskSet):
    @task
    def index(self):
        self.client.get("/")

    @task(1)
    def about(self):
        self.client.get("/about/")

    @task(10)
    def get_all_person(self):
        self.client.get("/api/persons/page=&")

class UpdateTask(TaskSet):
    @task(3)
    def updatePersonInfo(self):
        j = randint(1, 14);
        body = { "phone": myfake.phone_number(), "zone": myfake.city() + myfake.city_suffix() }
        headers = {'Content-Type' : 'application/json'}
        self.client.put("/api/persons/detail/%i" % j, data=json.dumps(body), headers=headers, name="/api/persons/detail/[id]")

    @task(5)
    def stop(self):
        self.interrupt()

class QueryTasks(TaskSet):
    tasks = {UpdateTask: 2}

    @task(10)
    def queryAll(self):
        for i in range(3): 
            self.client.get("/api/persons?page=%i&sex=&email=" % (i + 1), name="/api/persons?page=[page]");

    @task(7)
    def queryBySex(self):
        for sex in ["male", "female"]:
            self.client.get("/api/persons?sex=%s&email=" % sex, name="/api/persons?sex=[sex]");

    @task(5)
    def queryById(self):
        for id in range(15):
            self.client.get("/api/persons/detail/%i" % id, name="/api/persons/detail/[id]");

class MyLocust(HttpLocust): 
    task_set = QueryTasks
    # task_set = [(QueryTasks, 3), (UpdateTask, 1)]
    host = "http://129.211.119.51:31001"
    min_wait = 300
    max_wait = 6000