package poc

import java.nio.file.{Paths, StandardOpenOption}

import akka.actor.ActorSystem
import akka.kafka.Subscriptions
import akka.kafka.scaladsl.{Consumer, Producer}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.FileIO
import akka.util.ByteString

object Filter extends App with Config with ConsumerSettings with ProducerSettings {

  implicit val system = ActorSystem("filter-actor-system")
  implicit val materializer = ActorMaterializer()

  Consumer.committableSource(consumerSettings("filter-client"), Subscriptions.topics(Incoming))
    .map(asTransformedProducerMessage(Unverified))
    .to(Producer.commitableSink(producerSettings))
    .run()

  Consumer.committableSource(consumerSettings("filter-client"), Subscriptions.topics(Verified))
    .map { msg =>
      msg.committableOffset.commitScaladsl
      ByteString.fromString(msg.value + "\n")
    }
    .to(FileIO.toPath(Paths.get("/tmp/filter/verified.txt"), Set(StandardOpenOption.APPEND)))
    .run()
}

