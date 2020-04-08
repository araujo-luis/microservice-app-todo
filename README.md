# microservice-app-course

## Docker elasticserach

Elasticsearch

docker network create --driver bridge elk-network   

```
docker run -d -v esdata1:/usr/share/elasticsearch/data --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --network elk-network elasticsearch:7.3.0
```

Kibana
```
docker run -d -p 5601:5601 --network elk-network kibana:7.3.0
```

