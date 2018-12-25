package fr.upem.easymow.services

import fr.upem.easymow.datamodel.Cardinal._
import fr.upem.easymow.datamodel.{Field, Position, Tondeuse}

object PrintService {

  def print[A](a: A)(implicit ev: Show[A]): String = ev.show(a)

  implicit def positionShow: Show[Position] = new Show[Position] {
    override def show(position: Position): String = s"${position.x} ${position.y}"
  }

  implicit def fieldShow: Show[Field] = new Show[Field] {
    override def show(field: Field): String = s"Field ${field.length} ${field.width}"
  }

  implicit def cardinalShow: Show[Cardinal] = new Show[Cardinal] {
    override def show(cardinal: Cardinal): String = s"${cardinal.orientation}";
  }

  implicit def tondeuseShow(implicit showP: Show[Position], showC: Show[Cardinal]): Show[Tondeuse] = new Show[Tondeuse] {
    override def show(tondeuse: Tondeuse): String = s"Mow ${showP.show(tondeuse.position)} ${showC.show(tondeuse.orientation)}"
  }

  trait Show[S] {
    def show(s: S): String
  }

}
