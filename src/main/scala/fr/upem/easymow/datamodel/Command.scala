package fr.upem.easymow.datamodel

import fr.upem.easymow.datamodel.Cardinal.{CardinalEst, CardinalNord, CardinalOuest, CardinalSud}
import fr.upem.easymow.factories.{PositionFactory, TondeuseFactory}

object Command {

  sealed trait Command {
    val command: Char

    def execute(tondeuse: Tondeuse)(tondeusehub: List[Option[Tondeuse]] = List.empty): Option[Tondeuse];

  }


  case object CommandRotateD extends Command {
    override val command: Char = 'D';

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalEst), instructions = b.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalNord), instructions = b.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalOuest), instructions = b.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalSud), instructions = b.instructions.drop(1)));
        case _ => Some(b);
      }
    }
  }

  case object CommandRotateG extends Command {
    override val command: Char = 'G'

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalOuest), instructions = b.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalSud), instructions = b.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalEst), instructions = b.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.copy(orientation = Some(CardinalNord), instructions = b.instructions.drop(1)));
        case _ => Some(b);
      }
    }
  }

  case object CommandAvancer extends Command {
    override val command: Char = 'A'

    override def execute(tondeuse: Tondeuse)(tondeusehub: List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case b => b.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.copy(position = PositionFactory.buildPosition(b.position.get.x)(b.position.get.y + 1)(b.position.get.field)(tondeusehub), instructions = b.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.copy(position = PositionFactory.buildPosition(b.position.get.x - 1)(b.position.get.y)(b.position.get.field)(tondeusehub), instructions = b.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.copy(position = PositionFactory.buildPosition(b.position.get.x)(b.position.get.y - 1)(b.position.get.field)(tondeusehub), instructions = b.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.copy(position = PositionFactory.buildPosition(b.position.get.x + 1)(b.position.get.y)(b.position.get.field)(tondeusehub), instructions = b.instructions.drop(1)));
        case _ => Some(b);
      }
    }
  }


}
