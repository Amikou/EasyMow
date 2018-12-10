package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.Cardinal._

object CardinalFactory {

  // TODO Implement print function
  def build[A](a: A)(implicit ev: Build[A]): Option[Cardinal] = ev.build(a)

  implicit val buildCardinalFromString: Build[String] = new Build[String] {
    override def build(s: String): Option[Cardinal] =
      s match {
        case a if a.equalsIgnoreCase("Nord") => Some(CardinalNord);
        case b if b.equalsIgnoreCase("Sud") => Some(CardinalSud);
        case c if c.equalsIgnoreCase("Est") => Some(CardinalEst);
        case d if d.equalsIgnoreCase("Ouest") => Some(CardinalOuest);
        case _ => None;
      }
  }


  implicit val buildCardinalFromChar: Build[Char] = new Build[Char] {
    override def build(c: Char): Option[Cardinal] = c match {
      case CardinalNord.orientation => Some(CardinalNord);
      case CardinalSud.orientation => Some(CardinalSud);
      case CardinalEst.orientation => Some(CardinalEst);
      case CardinalOuest.orientation => Some(CardinalOuest);
      case _ => None;
    }
  }

  trait Build[S] {
    def build(s: S): Option[Cardinal]
  }


}
