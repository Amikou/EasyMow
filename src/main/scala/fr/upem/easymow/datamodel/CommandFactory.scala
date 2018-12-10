package fr.upem.easymow.datamodel

import fr.upem.easymow.datamodel.Command.{Command, CommandAvancer, CommandRotateD, CommandRotateG}

object CommandFactory {

  def buildCommand(s : Char) : Option[Command] = s match {
      case CommandRotateG.command => Some(CommandRotateG);
      case CommandRotateD.command => Some(CommandRotateD);
      case CommandAvancer.command => Some(CommandAvancer);
      case _ => None;
  }
}
