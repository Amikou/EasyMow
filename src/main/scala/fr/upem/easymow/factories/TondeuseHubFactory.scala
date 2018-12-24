package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.{Tondeuse, TondeuseHub}

object TondeuseHubFactory {

  def buildTondeuseHub(tondeuses: Option[List[Option[Tondeuse]]]): Option[TondeuseHub] = tondeuses match {
    // case a if a.tondeuses.isEmpty => None; If list is empty then map/foreach/etc do nothing so we don't need to manage this case
    case a if a.isEmpty => Some(TondeuseHub(List.empty));
    case e => Some(TondeuseHub(e.get));
  }


}
