package kafka.serde

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import model.Transaction
import org.apache.kafka.common.serialization.Deserializer

class TransactionDeserializer extends Deserializer[Transaction] {

  override def deserialize(topic:String,bytes: Array[Byte]): Transaction = {
    val byteIn = new ByteArrayInputStream(bytes)
    val objIn = new ObjectInputStream(byteIn)
    val obj = objIn.readObject().asInstanceOf[Transaction]
    byteIn.close()
    objIn.close()
    obj
  }
  override def close():Unit = {

  }

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {

  }
}