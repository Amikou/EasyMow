package fr.upem.easymow.datamodel

import cats.Eq

object Cardinal {

  sealed trait Cardinal{
    val orientation : Char
  }

  case object CardinalNord extends Cardinal {
    override val orientation: Char = 'N'
  };
  case object CardinalEst extends Cardinal {
    override val orientation: Char = 'E'
  };
  case object CardinalSud extends Cardinal {
    override val orientation: Char = 'S'
  };
  case object CardinalOuest extends Cardinal {
    override val orientation: Char = 'W'
  };

}
