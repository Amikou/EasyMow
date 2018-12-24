package fr.upem.easymow.services

import fr.upem.easymow.datamodel.Tondeuse

object ApplyCommandService {

  def apply(tondeuse: Tondeuse)(tondeusehub: List[Option[Tondeuse]] = List.empty): Option[Tondeuse] = Some(tondeuse.instructions.foldLeft(tondeuse) { (b, f) => {
      f match {
        case a if f.isDefined => f.get.execute(b)(tondeusehub) match {
          case b if b.isDefined => b.get
          case _ => return None
         }
        case _ => tondeuse.copy(instructions = tondeuse.instructions.drop(1));
    }
    }
  })
}
