package fr.upem.easymow.datamodel

class CommandRotateD private[datamodel]() extends Command {
  override def execute(tondeuse : Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b if b.get.orientation.get.direction.equals(CardinalFactory.build('N').get.direction) => Some(b.get.copy(orientation = CardinalFactory.build('E')));
    case c if c.get.orientation.get.direction.equals(CardinalFactory.build('E').get.direction) => Some(c.get.copy(orientation = CardinalFactory.build('S')));
    case d if d.get.orientation.get.direction.equals(CardinalFactory.build('S').get.direction) => Some(d.get.copy(orientation = CardinalFactory.build('W')));
    case e if e.get.orientation.get.direction.equals(CardinalFactory.build('W').get.direction) => Some(e.get.copy(orientation = CardinalFactory.build('N')));
  }
}
