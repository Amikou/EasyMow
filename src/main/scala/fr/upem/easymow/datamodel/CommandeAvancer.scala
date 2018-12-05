package fr.upem.easymow.datamodel

class CommandeAvancer extends Command {
  override def execute(tondeuse : Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b if b.get.orientation.get.direction.equals(CardinalFactory.build('N').get.direction) => Some(b.get.copy(position = Some(b.get.position.get.copy(y = b.get.position.get.y+1))));
    case c if c.get.orientation.get.direction.equals(CardinalFactory.build('W').get.direction) => Some(c.get.copy(position = Some(c.get.position.get.copy(x = c.get.position.get.x-1))));
    case d if d.get.orientation.get.direction.equals(CardinalFactory.build('S').get.direction) => Some(d.get.copy(position = Some(d.get.position.get.copy(y = d.get.position.get.y-1))));
    case e if e.get.orientation.get.direction.equals(CardinalFactory.build('E').get.direction) => Some(e.get.copy(position = Some(e.get.position.get.copy(x = e.get.position.get.x+1))));
    case _ => None;
  }
  //TondeuseFactory.
}
