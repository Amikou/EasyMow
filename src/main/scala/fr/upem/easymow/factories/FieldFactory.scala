package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.Field

object FieldFactory {

  def buildField(lenght: Int, width: Int): Option[Field] = Field(lenght, width) match {
    case a if a.width <= 0 || a.length <= 0 => None;
    case b => Some(b);
  }
}
