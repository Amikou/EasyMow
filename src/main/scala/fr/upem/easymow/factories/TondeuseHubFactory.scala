package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.{Tondeuse, TondeuseHub}

object TondeuseHubFactory {

  def buildTondeuseHub(tondeuses: Option[List[Option[Tondeuse]]]): Option[TondeuseHub] = tondeuses match {
    case a if a.isEmpty => Some(TondeuseHub(List.empty));
    case e => Some(TondeuseHub(e.get));
  }


}
