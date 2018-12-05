package fr.upem.easymow.services

import fr.upem.easymow.datamodel.{Cardinal, Position, Tondeuse}

object PrintService {

  // TODO Define typeclass Show
  trait Show[S] {
    def show(s: S): String
  }

  // TODO Implement instances of Show typeclass
  implicit def positionShow : Show[Option[Position]] = new Show[Option[Position]] {
    override def show(position : Option[Position]): String = s"${position.get.x} ${position.get.y}"
  }

  implicit def cardinalnShow: Show[Option[Cardinal]] = new Show[Option[Cardinal]] {
    override def show(cardinal: Option[Cardinal]): String = s"${cardinal.get.direction}"
  }

  implicit def tondeuseShow (implicit showP: Show[Option[Position]], showC : Show[Option[Cardinal]] ): Show[Option[Tondeuse]] = new Show[Option[Tondeuse]] {
    override def show(tondeuse: Option[Tondeuse]): String = tondeuse match {
      case None => "Nothing here"
      case _ => s"${showP.show(tondeuse.get.position)} ${showC.show(tondeuse.get.orientation)}"
    }
  }

  // TODO Implement print function
  def print[A](a: A)(implicit ev: Show[A]): String = ev.show(a)

}
