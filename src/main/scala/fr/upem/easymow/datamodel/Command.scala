package fr.upem.easymow.datamodel

import fr.upem.easymow.datamodel.Cardinal.{CardinalEst, CardinalNord, CardinalOuest, CardinalSud}
import fr.upem.easymow.factories.{PositionFactory, TondeuseFactory}

object Command {

  sealed trait Command {
    val command: Char

    def execute(tondeuse: Option[Tondeuse])(tondeusehub : List[Option[Tondeuse]] = List.empty): Option[Tondeuse];

  }


  case object CommandRotateD extends Command {
    override val command: Char = 'D';

    override def execute(tondeuse: Option[Tondeuse])(tondeusehub : List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalEst), instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalNord), instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalOuest), instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalSud), instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }
  }

  case object CommandRotateG extends Command {
    override val command: Char = 'G'

    override def execute(tondeuse: Option[Tondeuse])(tondeusehub : List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalOuest), instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalSud), instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalEst), instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.get.copy(orientation = Some(CardinalNord), instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }
  }

  case object CommandAvancer extends Command {
    override val command: Char = 'A'

    override def execute(tondeuse: Option[Tondeuse])(tondeusehub : List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
      case a if a.isEmpty => None;
      case b => b.get.orientation match {
        case Some(CardinalNord) => TondeuseFactory.buildTondeuse(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x)(b.get.position.get.y + 1)(b.get.position.get.field)(tondeusehub), instructions = b.get.instructions.drop(1)));
        case Some(CardinalOuest) => TondeuseFactory.buildTondeuse(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x - 1)(b.get.position.get.y)(b.get.position.get.field)(tondeusehub), instructions = b.get.instructions.drop(1)));
        case Some(CardinalSud) => TondeuseFactory.buildTondeuse(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x)(b.get.position.get.y - 1)(b.get.position.get.field)(tondeusehub), instructions = b.get.instructions.drop(1)));
        case Some(CardinalEst) => TondeuseFactory.buildTondeuse(b.get.copy(position = PositionFactory.buildPosition(b.get.position.get.x + 1)(b.get.position.get.y)(b.get.position.get.field)(tondeusehub), instructions = b.get.instructions.drop(1)));
        case _ => b;
      }
    }
  }


}
