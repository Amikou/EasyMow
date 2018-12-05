package fr.upem.easymow.datamodel

object CardinalFactory {

  trait Build[S] {
    def build(s: S): Option[Cardinal]
  }

  implicit val buildCardinalFromString : Build[String] = new Build[String] {
    override def build(s: String): Option[Cardinal] =
    s match {
      case a if a.equalsIgnoreCase("Nord") => Some(new CardinalNord());
      case b if b.equalsIgnoreCase("Sud") => Some(new CardinalSud());
      case c if c.equalsIgnoreCase("Est") => Some(new CardinalEst());
      case d if d.equalsIgnoreCase("Ouest") => Some(new CardinalOuest());
      case _ => None;
    }
  }



  implicit val buildCardinalFromChar : Build[Char] = new Build[Char]{
    override def build(c: Char) : Option[Cardinal] = c match {
      case a if a.equals(new CardinalNord().direction.charAt(0)) => Some(new CardinalNord());
      case b if b.equals(new CardinalSud().direction.charAt(0)) => Some(new CardinalSud());
      case c_ if c_.equals(new CardinalEst().direction.charAt(0)) => Some(new CardinalEst());
      case d if d.equals(new CardinalOuest().direction.charAt(0)) => Some(new CardinalOuest());
      case _ => None;
    }
  }

  // TODO Implement print function
  def build[A](a: A)(implicit ev: Build[A]): Option[Cardinal] = ev.build(a)
}
