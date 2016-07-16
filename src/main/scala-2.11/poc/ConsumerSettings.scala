package poc

import akka.actor.ActorSystem

trait ConsumerSettings {
  def consumerSettings(groupId: String)(implicit system: ActorSystem) = {}
}
