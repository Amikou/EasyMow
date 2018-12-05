package fr.upem.easymow.datamodel

abstract class Command {

  def execute(tondeuse : Option[Tondeuse]) : Option[Tondeuse];
}
