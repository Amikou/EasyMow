package fr.upem.easymow.datamodel

import fr.upem.easymow.datamodel.Cardinal.{CardinalEst, CardinalNord, CardinalOuest, CardinalSud}
import fr.upem.easymow.factories.{PositionFactory, TondeuseFactory}

object Command {

  sealed trait Command {
    val command: Char

    def execute(tondeuse: Tondeuse)(tondeusehub: List[Tondeuse] = List.empty): Option[Tondeuse]

  }


  case object CommandRotateD extends Command {
    override val command: Char = 'D'

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Tondeuse] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case CardinalNord => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalEst, instructions = b.instructions.drop(1)));
        case CardinalOuest => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalNord, instructions = b.instructions.drop(1)));
        case CardinalSud => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalOuest, instructions = b.instructions.drop(1)));
        case CardinalEst => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalSud, instructions = b.instructions.drop(1)));
        case _ => Some(b);
      }
    }
  }

  case object CommandRotateG extends Command {
    override val command: Char = 'G'

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Tondeuse] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case CardinalNord => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalOuest, instructions = b.instructions.drop(1)));
        case CardinalOuest => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalSud, instructions = b.instructions.drop(1)));
        case CardinalSud => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalEst, instructions = b.instructions.drop(1)));
        case CardinalEst => TondeuseFactory.buildTondeuse(b.copy(orientation = CardinalNord, instructions = b.instructions.drop(1)));
        case _ => Some(b);
      }
    }
  }

  case object CommandAvancer extends Command {
    override val command: Char = 'A'

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Tondeuse] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case CardinalNord => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(b.position.x)(b.position.y + 1)(Some(b.position.field))(tondeusehub))(Some(b.orientation))(b.instructions.drop(1));
        case CardinalOuest => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(b.position.x - 1)(b.position.y)(Some(b.position.field))(tondeusehub))(Some(b.orientation))(b.instructions.drop(1));
        case CardinalSud => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(b.position.x)(b.position.y - 1)(Some(b.position.field))(tondeusehub))(Some(b.orientation))(b.instructions.drop(1));
        case CardinalEst => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(b.position.x + 1)(b.position.y)(Some(b.position.field))(tondeusehub))(Some(b.orientation))(b.instructions.drop(1));
        case _ => Some(b);
      }
    }
  }


}
