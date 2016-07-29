import java.time.LocalDateTime

import akka.kafka.ConsumerMessage.CommittableMessage
import akka.kafka.ProducerMessage
import org.apache.kafka.clients.producer.ProducerRecord

package object poc {

  val Incoming = "incoming-4"
  val Verified = "verified-4"
  val Unverified = "unverified-4"

  def asTransformedProducerMessage[A](topic: String)(msg: CommittableMessage[A, String]) = {
    ProducerMessage.Message(
      asTransformedProducerRecord[A](topic)(msg.value),
      msg.committableOffset
    )
  }

  def asTransformedProducerRecord[A](topic: String)(msg: String) = {
    new ProducerRecord[A, String](topic, transform(topic, msg))
  }

  def transform(label: String, msg: String) = {
    s"$label: ${LocalDateTime.now().toString}, $msg"
  }
}
