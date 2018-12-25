package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.{Tondeuse, TondeuseHub}

object TondeuseHubFactory {

  def buildTondeuseHub(tondeuses: List[Tondeuse]): Option[TondeuseHub] = tondeuses match {
    case a if a.isEmpty => None
    case _ => Some(TondeuseHub(tondeuses))
  }


}
