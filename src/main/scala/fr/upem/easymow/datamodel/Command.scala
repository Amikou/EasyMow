package fr.upem.easymow.datamodel

import cats.Eq
import fr.upem.easymow.datamodel.Cardinal.{CardinalEst, CardinalNord, CardinalOuest, CardinalSud}
import fr.upem.easymow.factories.PositionFactory

object Command {

  sealed trait Command {
    def execute(tondeuse: Option[Tondeuse]): Option[Tondeuse];
    val command: Char

  }


  case object CommandRotateD extends Command {
    override def execute(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => Some(b.get.copy(orientation = Some(CardinalEst),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => Some(b.get.copy(orientation = Some(CardinalNord),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => Some(b.get.copy(orientation = Some(CardinalOuest),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => Some(b.get.copy(orientation = Some(CardinalSud),  instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }

    override val command: Char = 'D';
  }

  case object CommandRotateG extends Command {
    override def execute(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => Some(b.get.copy(orientation = Some(CardinalOuest),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => Some(b.get.copy(orientation = Some(CardinalSud),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => Some(b.get.copy(orientation = Some(CardinalEst),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => Some(b.get.copy(orientation = Some(CardinalNord),  instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }

    override val command: Char = 'G'
  }

  case object CommandAvancer extends Command {
    override def execute(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => Some(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x, b.get.position.get.y + 1, b.get.position.get.field),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => Some(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x - 1, b.get.position.get.y, b.get.position.get.field),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => Some(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x, b.get.position.get.y - 1, b.get.position.get.field),  instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => Some(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x + 1, b.get.position.get.y, b.get.position.get.field),  instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }

    override val command: Char = 'A'
  }

}
