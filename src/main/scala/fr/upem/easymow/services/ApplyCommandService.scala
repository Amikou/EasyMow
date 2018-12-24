package fr.upem.easymow.services

import fr.upem.easymow.datamodel.{Tondeuse, TondeuseHub}

object ApplyCommandService {

  def apply(tondeuse: Option[Tondeuse])(tondeusehub : List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b => b.get.instructions.foldLeft(b) { (b, f) => {
      f match {
        case a if f.isDefined => f.get.execute(b)(tondeusehub);
        case _ => Some(b.get.copy(instructions = b.get.instructions.drop(1)));
      }
    }
    }
  }
}
