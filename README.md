# Dockerized producer and consumer in Scala 2.12

Docker images to:
* Start a simple Kafka producer which pushes 1000 records
* Start a simple Kafka consumer process the incoming events and perform sum aggregation and output it to the console

### Pre-Requisites
* docker
* docker-compose
* Java 8
* Scala 2.x

#### Purpose of sub projects 
1. scripts - Supporting shell files for Kafka,Zk and Producer , Consumer services 
2. transaction-producer - Producer pushes 1000 random records to `transaction-logs` topic 
2. transaction-consumer - Consumes the logs, sums them by account and output it to console 

#### Purpose of base files
1. build.sbt - SBT file to build docker images with Docker using assembly plugin 
2. docker-compose.yml - Docker composition file defining Services(Producer and Consumer)

```shell script
 
#Goto prject base folder 
cd transaction-stream-docker

#build docker image using SBT 
sbt docker

#Start the docker image for Kafka Producer and Consumer
docker-compose up
```