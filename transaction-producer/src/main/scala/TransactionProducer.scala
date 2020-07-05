import java.util.{Properties, UUID}

import kafka.serde.TransactionSerializer
import model.Transaction
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.util.Random

object TransactionProducer extends App {

  val BROKER_LIST = "kafka:9092" //change it to localhost:9092 if not connecting through docker
  val TOPIC = "transaction-logs"

  val props = new Properties()
  props.put("bootstrap.servers", BROKER_LIST)
  props.put("client.id", "KafkaTransactionProducer")

  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[TransactionSerializer])

  val producer = new KafkaProducer[String, Transaction](props)

  def startTransactionStream() {
    val random = new Random()
    (0 to 1000) foreach { a =>
      val account_num = random.nextInt(20)
      val transaction_amount = random.nextDouble() * 100
      val transact = Transaction(UUID.randomUUID().toString,
        account_num.toString,
        UUID.randomUUID().toString,UUID.randomUUID().toString,
        transaction_amount)
      val data = new ProducerRecord[String, Transaction](TOPIC,UUID.randomUUID().toString, transact)
      producer.send(data)
    }
  }

  startTransactionStream()


}

