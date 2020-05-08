# Docker run

```
docker run -d -e "spring.profiles.active=prod" -e "spring.cloud.config.uri=http://172.31.38.138:8083" -e "logging.file=/authentication-logs/authentication-service.log" -v /home/ec2-user/authentication-logs:/authentication-logs --network host l222p/authentication-service
```