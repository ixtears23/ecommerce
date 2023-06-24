# eCommerce Application

---

## Kafka

### Topic 생성
~~~shell
docker exec -it ecommerce-kafka kafka-topics.sh --bootstrap-server localhost:9092 --create --topic testTopic 
~~~

### Producer
~~~shell
docker exec -it ecommerce-kafka kafka-console-producer.sh --topic testTopic --broker-list 0.0.0.0:9092
~~~

### Consumer
~~~shell
docker exec -it ecommerce-kafka kafka-console-consumer.sh --topic testTopic --bootstrap-server localhost:9092
~~~