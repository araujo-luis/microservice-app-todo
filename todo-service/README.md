# docker run

Mysql
```
docker run -d -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=root -e MYSQL_DATABASE=todo_service -v /var/lib/mysql:/var/lib/mysql mysql:latest
```