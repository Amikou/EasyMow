package fr.upem.easymow.services

import fr.upem.easymow.datamodel.Tondeuse

object ApplyCommandService {

  def apply(tondeuse: Option[Tondeuse]): Option[Tondeuse] = tondeuse match {
    case a if a.isEmpty => None;
    case b => b.get.instructions.foldLeft(b) { (b, f) => {
      if (f.isDefined) f.get.execute(b); else b;
    }
    }
  }
}
