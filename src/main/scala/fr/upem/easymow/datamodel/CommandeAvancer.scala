package fr.upem.easymow.datamodel

class CommandeAvancer extends Command {
  override def execute(tondeuse : Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b if b.get.orientation.get.direction.equals(CardinalFactory.build('N').get.direction) => Some(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x, b.get.position.get.y+1, FieldFactory.buildField(b.get.position.get.field.get.length,b.get.position.get.field.get.width ))));
    case c if c.get.orientation.get.direction.equals(CardinalFactory.build('W').get.direction) => Some(c.get.copy(position = PositionFactory.buildPosition(c.get.position.get.x-1, c.get.position.get.y, FieldFactory.buildField(c.get.position.get.field.get.length,c.get.position.get.field.get.width ))));
    case d if d.get.orientation.get.direction.equals(CardinalFactory.build('S').get.direction) => Some(d.get.copy(position = PositionFactory.buildPosition(d.get.position.get.x, d.get.position.get.y-1, FieldFactory.buildField(d.get.position.get.field.get.length,d.get.position.get.field.get.width ))));
    case e if e.get.orientation.get.direction.equals(CardinalFactory.build('E').get.direction) => Some(e.get.copy(position = PositionFactory.buildPosition(e.get.position.get.x+1, e.get.position.get.y, FieldFactory.buildField(e.get.position.get.field.get.length,e.get.position.get.field.get.width ))));
    case _ => None;
  }
  //TondeuseFactory.
}
