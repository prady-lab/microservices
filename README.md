# Prady's sample application:- Spring Cloud Docker and various frameworks

[![CircleCI](https://circleci.com/gh/pradyb/microservices.svg?style=svg)](https://circleci.com/gh/pradyb/microservices)

This is a POC application, which demonstrates Microservice Architecture Pattern using Spring Cloud, Netflix OSS, Consul, ELK, Docker and monitoring  solution

## Frameworks used 
* [Spring Cloud](http://projects.spring.io/spring-cloud/)
* [Consul](https://www.consul.io/)
* Netflix OSS
  * [Zuul](https://github.com/Netflix/zuul/wiki)
  * [Hystrix](https://github.com/Netflix/Hystrix)
  * [Turbine](https://github.com/Netflix/Turbine)
  * [Ribbon](https://github.com/Netflix/ribbon)
* [Feign](https://github.com/OpenFeign/feign)
* [Sleuth](https://cloud.spring.io/spring-cloud-sleuth/)
* [Zipkin](http://zipkin.io/)
* ELK Stack 
  * [Logstash](https://www.elastic.co/products/logstash) 
  * [Elasticsearch](https://www.elastic.co/products/elasticsearch)
  * [Kibana](https://www.elastic.co/products/kibana) 
* Monitoring 
  * [Prometheus](https://prometheus.io/)
  * [Grafana](http://grafana.org/)
  * [cAdvisor](https://github.com/google/cadvisor)
  * [NodeExporter](https://github.com/prometheus/node_exporter)


### Before you start
- Install Mavan, Docker and Docker Compose.

### Get, Build and Run
* `git clone https://github.com/pradyb/microservices`
* `cd microservices`
* `mvn clean package -DskipTests`
* `docker-compose up --build -d`
* `cd devops-monitoring`
* `docker-compose up --build -d`

#### Test
* `mvn test` 
This test invokes various APIs via Gateway service and the results can be monitor using below URLs  

###### Scale a container
* `docker-compose scale userservice=2`
 
### URLs

| Containers | Use | URL |
| ------ | ------ | ------ |
| Consul | Service Discovery and Configuration | [http://localhost:8500/ui](http://localhost:8500/ui) |
| Kibana | Log Visualize | [http://localhost:5601](http://localhost:5601)|
| Zipkin | Distributed Tracing system | [http://localhost:9411](http://localhost:9411)|
| Hystrix & Turbine | Aggregating streams of Server-Sent Event (SSE)  | [http://localhost:8080/hystrix/](http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8989%2Fturbine.stream)|
| Prometheus | Application Metrics Database | [http://localhost:9090/graph](http://localhost:9090/graph) |
| Grafana | Metrics Visualize | [http://localhost:3000](http://localhost:3000) |
| RabbitMQ | Message Broker | [http://localhost:15672](http://localhost:15672) |

###### Other info
* Grafana: Login with user name **admin** and password **changeme**.
* Kibana: Predefined URL
```
http://10.131.155.127:5601/app/kibana#/discover?_g=(refreshInterval:('$$hashKey':'object:999',display:'5%20seconds',pause:!f,section:1,value:5000),time:(from:now%2Fd,mode:quick,to:now%2Fd))&_a=(columns:!(host,level,appname,logger_name,message,X-B3-ParentSpanId,X-B3-TraceId,thread_name),index:'logstash-*',interval:auto,query:(query_string:(analyze_wildcard:!t,query:'*')),sort:!('@timestamp',desc),uiState:(spy:(mode:(fill:!f,name:!n)),vis:(legendOpen:!f)))
```

***Grafana Dashboard*** 
* Application Transactions
![Application Transactions](https://github.com/pradyb/microservices/blob/master/images/ApplicationTx.png?raw=true)
* Hystrix 
![Hystrix](https://github.com/pradyb/microservices/blob/master/images/Hystrix%20Dashboard.png?raw=true)

***Turbine Dashboard***
![Turbine](https://github.com/pradyb/microservices/blob/master/images/Turbine%20Dashboard.png?raw=true)

***Zipkin***
![Zipkin](https://github.com/pradyb/microservices/blob/master/images/Zipkin%20Dependencies.png?raw=true)
![Zipkin](https://github.com/pradyb/microservices/blob/master/images/Zipkin%20Trace.png?raw=true)


Thanks to **Stefan Prodan** for providing a great setup for monitoring Docker containers
[Stefan Prodan Blog](https://stefanprodan.com/2016/a-monitoring-solution-for-docker-hosts-containers-and-containerized-services/)


#### TODO
- OAuth for Client services