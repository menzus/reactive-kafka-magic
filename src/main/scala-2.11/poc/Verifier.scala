package poc

import akka.actor.ActorSystem
import akka.kafka._
import akka.kafka.scaladsl._
import akka.stream.ActorMaterializer

import scala.util.Random

object Verifier extends App with Config with ConsumerSettings with ProducerSettings {

  implicit val system = ActorSystem("verifier-actor-system")
}
