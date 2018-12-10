package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.Cardinal.Cardinal
import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel.{Position, Tondeuse}

object TondeuseFactory {

  def buildTondeuse(position: Option[Position], orientation: Option[Cardinal], instructions: List[Option[Command]]): Option[Tondeuse] = Tondeuse(position, orientation, instructions) match {
    case a if a.orientation.isEmpty => None;
    case c if c.position.isEmpty => None;
    case e => Some(e);
  }


}
