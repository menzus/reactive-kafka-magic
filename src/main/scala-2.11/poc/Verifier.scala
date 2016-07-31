package poc

import akka.actor.ActorSystem
import akka.kafka._
import akka.kafka.scaladsl._
import akka.stream.ActorMaterializer

import scala.util.Random

object Verifier extends App with Config with ConsumerSettings with ProducerSettings {

  implicit val system = ActorSystem("verifier-actor-system")
  implicit val materializer = ActorMaterializer()

  Consumer.committableSource(consumerSettings("verifier-client"), Subscriptions.topics(Unverified))
    .map(asTransformedProducerMessage(Verified))
    .to(Producer.commitableSink(producerSettings))
    .run()

  def isVerified(msg: String): Boolean = Random.nextBoolean()
}
