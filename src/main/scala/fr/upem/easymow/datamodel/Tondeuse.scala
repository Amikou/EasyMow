package fr.upem.easymow.datamodel

import cats.kernel.Eq
import fr.upem.easymow.datamodel.Cardinal._
import fr.upem.easymow.datamodel.Command.Command

case class Tondeuse private[datamodel](val position : Option[Position], orientation : Option[Cardinal], instructions : List[Option[Command]]) {
}

object Tondeuse {
  lazy implicit val eq: Eq[Tondeuse] = Eq.fromUniversalEquals
}

