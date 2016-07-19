package poc

import akka.actor.ActorSystem
import akka.kafka.{ConsumerSettings => KConsumerSettings}
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

trait ConsumerSettings {
  self: Config =>

  def consumerSettings(groupId: String)(implicit system: ActorSystem) = KConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
    .withBootstrapServers(bootstrapServers)
    .withClientId(groupId)
    .withGroupId(groupId)
}
