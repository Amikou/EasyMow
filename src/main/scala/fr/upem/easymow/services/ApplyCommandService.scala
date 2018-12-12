package fr.upem.easymow.services

import fr.upem.easymow.datamodel.Tondeuse

object ApplyCommandService {

  def apply(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b => b.get.instructions.foldLeft(b) { (b, f) => {
      f match {
        case a if f.isDefined => f.get.execute(b);
        case _ => Some(b.get.copy(instructions = b.get.instructions.drop(1)));
      }
    }
    }
  }
}
