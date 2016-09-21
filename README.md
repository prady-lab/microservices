# Prady's sample application:- Spring Cloud Netflix OSS ELK Docker

This is a POC application, which demonstrates Microservice Architecture Pattern using Spring Cloud, Netflix OSS, ELK and Docker.

#### Before you start
- Install Docker and Docker Compose.

#### Run the Docker 
- docker-compose up -d

#### Important Commands	

- mvn clean install
- docker-compose up --build -d

- docker-compose stop
- docker-compose rm -f

- docker-compose logs userservice
- docker-compose logs gateway
- docker-compose logs registry 

- docker-compose logs logstash 
- docker-compose logs elasticsearch 

- docker exec -it cloud_registry_1 bash
- docker exec -it cloud_gateway_1 bash
- docker exec -it cloud_logstash_1 bash

- docker-compose scale userservice=2
 
#### Important URLS
- [http://localhost:4000/users](http://localhost:4000/users) - Gatway. For Now, just one API call.
- [http://localhost:8761](http://localhost:8761) - Eureka
- [http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8989%2Fturbine.stream](http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8989%2Fturbine.stream) - Hystrix & Turbine Dashboard
- [http://localhost:15672](http://localhost:15672) - RabbitMq management
- [http://localhost:5601](http://localhost:5601) - Kibana Dashboard. Use below URL for predefined layout
```
http://localhost:5601/app/kibana#/discover?_g=(refreshInterval:(display:'5%20seconds',pause:!f,section:1,value:5000),time:(from:now-15m,mode:quick,to:now))&_a=(columns:!(appname,host,level,caller_class_name,caller_method_name,message),index:'logstash-*',interval:auto,query:(query_string:(analyze_wildcard:!t,query:'*')),sort:!('@timestamp',desc))
```

#### TODO
- Auth using OAuth 
- NoSQL DB for each services