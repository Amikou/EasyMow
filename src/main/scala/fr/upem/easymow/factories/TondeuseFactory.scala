package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.Cardinal.Cardinal
import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel.{Position, Tondeuse}

object TondeuseFactory {

  def buildTondeuse(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isDefined => buildTondeuse(a.get)
    case _ => None
  }

  def buildTondeuse(tondeuse: Tondeuse): Option[Tondeuse] = Some(Tondeuse(tondeuse.position, tondeuse.orientation, tondeuse.instructions))


  def buildTondeuse(position: Option[Position])(orientation: Option[Cardinal])(instructions: List[Command]): Option[Tondeuse] = this match {
    case a if position.isEmpty => None
    case c if orientation.isEmpty => None
    case _ => Some(Tondeuse(position.get, orientation.get, instructions))
  }


}
