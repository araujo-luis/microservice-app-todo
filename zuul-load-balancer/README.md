#Run zuul-gateway docker

docker run -e "spring.cloud.config.uri=http://<CONFIG SERVER PRIVATE IP>:<CONFIG SERVER PORT>" -e "eureka.client.service-url.defaultZone=http://<EUREKA USER>:<EUREKA PASSWORD>@<EUREKA IP>:<EUREKA PORT>/eureka" -d -p 8011:8011 l222p/zuul-gateway


docker run -e "spring.cloud.config.uri=http://172.31.38.138:8083" -e "eureka.client.service-url.defaultZone=http://admin:{cipher}1284523bdd9f9ff301d50f309f4e6987a839f43c726573a27ba1bde316cc779f@172.31.34.32:8010/eureka" -d -p 8011:8011 l222p/zuul-gateway

eureka.password={cipher}1284523bdd9f9ff301d50f309f4e6987a839f43c726573a27ba1bde316cc779f
eureka.client.service-url.defaultZone=http://admin:${eureka.password}@localhost:8010/eureka