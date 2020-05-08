# docker run

Mysql
```
docker run -d -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=root -e MYSQL_DATABASE=todo_service -v /var/lib/mysql:/var/lib/mysql mysql:latest
```
Todo Service
```
docker run -d --network host -e "spring.profiles.active=prod" -e "spring.cloud.config.uri=http://172.31.38.138:8083" -e "logging.file=/todo-logs/todo-service.log" -v /home/ec2-user/todo-logs:/todo-logs l222p/todo-service
```

Note: check mysql connection string in spring config server github.