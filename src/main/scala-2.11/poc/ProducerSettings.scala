package poc

import akka.actor.ActorSystem
import akka.kafka.{ProducerSettings => KProducerSettings}
import org.apache.kafka.common.serialization.{StringSerializer, ByteArraySerializer}

trait ProducerSettings {
  self: Config =>

  def producerSettings(implicit system: ActorSystem) = KProducerSettings(system, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers(bootstrapServers)
}
