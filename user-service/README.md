
Mysql
```
docker run -d -p 3307:3306 --name mysql-server-user -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=root -e MYSQL_DATABASE=users_service -v /var/lib/mysql-user:/var/lib/mysql mysql:latest
```
Note: this docker container runs in other port 3307

User Service
```
docker run -d --network host -e "spring.profiles.active=prod" -e "spring.cloud.config.uri=http://172.31.38.138:8083" -e "logging.file=/user-logs/user-service.log" -v /home/ec2-user/user-logs:/user-logs l222p/user-service
```