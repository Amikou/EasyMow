package fr.upem.easymow.datamodel

object TondeuseFactory {

  def buildTondeuse(position : Option[Position], orientation : Option[Cardinal], instructions : List[Option[Command]] ) : Option[Tondeuse] = Tondeuse(position, orientation, instructions) match {
      case a if a.orientation.isEmpty => None;
      case c if c.position.isEmpty => None;
      case e => Some(e);
    }



}

