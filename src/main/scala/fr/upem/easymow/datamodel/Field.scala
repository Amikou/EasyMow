package fr.upem.easymow.datamodel

import cats.Eq

case class Field private[datamodel](val length : Int, val width: Int /*, val tondeuses : List[Tondeuse][Cardinal]*/ ){
}
