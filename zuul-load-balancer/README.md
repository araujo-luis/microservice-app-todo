# Docker run

```
docker run -d -p {zuul.port}:{zuul.port} -e "spring.cloud.config.uri=http://{config-server-private-ip}:{config-server-port}" l222p/zuul-gateway
```

Example
```
docker run -d -p 8011:8011 -e "spring.profiles.active=prod" -e "spring.cloud.config.uri=http://172.31.38.138:8083" l222p/zuul-gateway 
```