package fr.upem.easymow.datamodel

import cats.kernel.Eq
import fr.upem.easymow.datamodel.Cardinal._
import fr.upem.easymow.datamodel.Command.Command

case class Tondeuse (position: Position, orientation: Cardinal, instructions: List[Command]) {
}

object Tondeuse {
  lazy implicit val eq: Eq[Tondeuse] = Eq.fromUniversalEquals
}

