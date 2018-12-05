package fr.upem.easymow.datamodel

case class Tondeuse private[datamodel](val position : Option[Position], orientation : Option[Cardinal], instructions : List[Option[Command]]) {

}
