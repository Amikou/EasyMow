package fr.upem.easymow.services

import fr.upem.easymow.datamodel.Tondeuse

object ApplyCommandService {

  def apply(tondeuse: Tondeuse)(tondeusehub: List[Tondeuse] = List.empty): Option[Tondeuse] = tondeuse.instructions.foldLeft(Option(tondeuse)) { (b, f) => {
    b match {
      case a if a.isDefined => f.execute(a.get)(tondeusehub)
      case _ => return None
    }
  }
  }
}
