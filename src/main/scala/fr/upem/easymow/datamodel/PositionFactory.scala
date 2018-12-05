package fr.upem.easymow.datamodel

object  PositionFactory {

  def buildPosition(x : Int, y : Int, field : Option[Field]) : Option[Position] = Position(x, y, field) match {
    case z if z.field.isEmpty => None;
    case a if a.x < 0 && a.y < 0 => Some(a.copy(0,0));
    case b if b.x < 0 && b.y > field.get.width-1 => Some(b.copy(0, field.get.width-1 ));
    case c if c.x > field.get.length-1 && c.y < 0 => Some(c.copy(field.get.length-1, 0));
    case d if d.x > field.get.length-1 && d.y > field.get.width-1 => Some(d.copy(field.get.length-1, field.get.width-1));
    case e if (e.x >=0 && e.x < field.get.length) &&  (e.y < 0 ) => Some(e.copy(e.x, 0));
    case f if (f.y >=0 && f.y < field.get.width) &&  (f.x < 0 ) => Some(f.copy(0, f.y));
    case g if (g.x >=0 && g.x < field.get.length) &&  (g.y > field.get.width-1 ) => Some(g.copy(g.x, field.get.width-1));
    case h if (h.y >=0 && h.y < field.get.width) &&  (h.x > field.get.length-1 ) => Some(h.copy(field.get.length-1, h.y));
    case i => Some(i.copy(i.x, i.y));
  }

}

