# Docker run

```
docker run -d -p {eureka.port}:{eureka.port} -e "spring.cloud.config.uri=http://{config-server-ip}:{config-server-port}" l222p/eureka-server-discovery
```

Example
```
docker run -d -p 8010:8010 -e "spring.profiles.active=prod" -e "spring.cloud.config.uri=http://172.31.38.138:8083" l222p/eureka-server-discovery 
```