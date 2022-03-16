# Spring Boot Data Redis Exemple

See article [Spring Boot + Spring Data Redis (Remote Directory Server) with HashOperations + Rest API + Example](https://thebasictechinfo.com/java-8/spring-boot-rest-api-spring-data-redis-remote-directory-server-with-hashoperations-example/)

# Install Redis (Ubuntu)

https://www.digitalocean.com/community/tutorials/how-to-install-and-secure-redis-on-ubuntu-18-04

* Make sure redis is up and running:

```
$ systemctl status redis
● redis-server.service - Advanced key-value store
   Loaded: loaded (/lib/systemd/system/redis-server.service; enabled; vendor preset: enabled)
   Active: active (running) since Tue 2022-03-15 11:59:27 EDT; 2h 53min ago
     Docs: http://redis.io/documentation,
           man:redis-server(1)
  Process: 40508 ExecStop=/bin/kill -s TERM $MAINPID (code=exited, status=0/SUCCESS)
  Process: 40511 ExecStart=/usr/bin/redis-server /etc/redis/redis.conf (code=exited, status=0/SUCCESS)
 Main PID: 40537 (redis-server)
    Tasks: 4 (limit: 19141)
   CGroup: /system.slice/redis-server.service
           └─40537 /usr/bin/redis-server 127.0.0.1:6379

Mar 15 11:59:27 ubuntu systemd[1]: Starting Advanced key-value store...
Mar 15 11:59:27 ubuntu systemd[1]: redis-server.service: Can't open PID file /var/run/redis/redis-server.pid (yet?) after start: No such file or directory
Mar 15 11:59:27 ubuntu systemd[1]: Started Advanced key-value store.
```

* RedisInsight: Redis GUI - Get Started
  https://docs.redis.com/latest/ri/installing/install-redis-desktop/

```
chmod +x redisinsight-linux64-<version>
```

* Start RedisInsight (Redis GUI)

```
./redisinsight-linux64-<version>
```

To access your RedisInsight GUI, open a web browser and navigate to http://127.0.0.1:8001

# Initialise demo application

* [Spring Initializr](https://start.spring.io/)

**NOTE**: I use Lettuce connection factory which is included with spring-boot-starter-data-redis instead of Jedis used in
the article

* Add `application.yaml`

* Implement configuration, model, repository and controller

* Run spring boot application and access endpoints: 

```
http://localhost:8080/springbootdataredisdemo/save
...
```