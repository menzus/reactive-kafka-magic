package poc

import akka.actor.ActorSystem
import akka.kafka.Subscriptions
import akka.kafka.scaladsl.{Consumer, Producer}
import akka.stream.ActorMaterializer

object Filter extends App with Config with ConsumerSettings with ProducerSettings {

  implicit val system = ActorSystem("filter-actor-system")
  implicit val materializer = ActorMaterializer()

  Consumer.committableSource(consumerSettings("filter-client"), Subscriptions.topics(Incoming))
    .map(asTransformedProducerMessage(Unverified))
    .to(Producer.commitableSink(producerSettings))
    .run()

  Consumer.committableSource(consumerSettings("filter-client"), Subscriptions.topics(Verified))
    .map(asTransformedProducerMessage(Approved))
}

