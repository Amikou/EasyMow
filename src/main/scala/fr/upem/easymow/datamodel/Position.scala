package fr.upem.easymow.datamodel

import cats.Eq

case class Position private[datamodel](x : Int, y : Int, field : Option[Field]) {

}

