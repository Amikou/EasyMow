package fr.upem.easymow.datamodel

object CommandFactory {

  def buildCommand(s : Char) : Option[Command] = s match {
      case b if b.equals('G') => Some(new CommandRotateG());
      case c if c.equals('D') => Some(new CommandRotateD());
      case d if d.equals('A') => Some(new CommandeAvancer());
      case _ => None;
  }
}
