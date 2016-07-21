package poc

import akka.actor.ActorSystem
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

object Outside extends App with Config with ProducerSettings {

  implicit val system = ActorSystem("outside-actor-system")
  implicit val materializer = ActorMaterializer()

  val numberOfMessages = {
    if (args.length == 0) {
      throw new IllegalArgumentException("You need to provide the number of messages as program argument.")
    }
    args(0).toInt
  }
}
