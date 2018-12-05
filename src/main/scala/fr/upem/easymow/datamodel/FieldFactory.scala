package fr.upem.easymow.datamodel

object FieldFactory {

  def buildField(lenght : Int, width : Int) : Option[Field] = Field(lenght,width) match {
      case a if a.width <= 0 || a.length  <=0 => None;
      case b => Some(b);
  }
}
