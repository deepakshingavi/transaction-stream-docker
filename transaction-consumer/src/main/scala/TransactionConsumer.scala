import java.util
import java.util.Properties

import kafka.serde.TransactionDeserializer
import model.Transaction
import org.apache.kafka.clients.consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._
import scala.collection.{Map, mutable}

object TransactionConsumer extends App {

  val ZK_HOST = "zookeeper:2181" //change it to localhost:2181 if not connecting through docker
  val BS_HOST = "kafka:9092,kafka:9093" //change it to localhost:2181 if not connecting through docker
  val TOPIC = "transaction-logs"

  private val props = new Properties()
  props.put("group.id", "transaction-consumer")
  props.put("zookeeper.connect", ZK_HOST)
  props.put("bootstrap.servers", BS_HOST)
  props.put("auto.offset.reset", "earliest")
  props.put("consumer.timeout.ms", "120000")
  props.put("zookeeper.connection.timeout.ms", "20000")
  props.put("auto.commit.interval.ms", "10000")
  props.put(consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[TransactionDeserializer])

  //  private val consumerConfig = new ConsumerConfig(props)
  //  private val consumerConnector = Consumer.create(consumerConfig)
  //  private val filterSpec = Whitelist(TOPIC)
  //  private val filterSpecMap = Map[String,Int](TOPIC -> 1)

  def read() = try {

    val kafkaConsumer = new KafkaConsumer[String, Transaction](props)
    kafkaConsumer.subscribe(util.Arrays.asList(TOPIC))
    var accountBalInfo: Map[String, Double] = mutable.Map()
    while (true) {
      val records = kafkaConsumer.poll(10).asScala
      for (transaction <- records.iterator) {
        println(s"Consume successfully transactionId=${transaction.value().account_number}")
        accountBalInfo ++= records.map(_.value()).groupBy(_.account_number).mapValues[Double](_.map(_.amount).sum)
      }
      println(accountBalInfo)
    }

  } catch {
    case ex: Exception =>
      ex.printStackTrace()
  }

  read()


}
