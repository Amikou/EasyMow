package fr.upem.easymow.factories

import fr.upem.easymow.datamodel.{Field, Position, Tondeuse}

object PositionFactory {

  def buildPosition(x: Int)(y: Int)(field: Option[Field])(tondeusehub: List[Tondeuse] = List.empty): Option[Position] = field match {
    case z if z.isEmpty => None;
    case _ => Position(x, y, field.get) match {
      case w if tondeusehub.map(t => (t.position.x, t.position.y)).count(position => position == (w.x, w.y)) >= 1 => None; // Tondeuse imploded due to a collision :(
      case a if a.x < 0 && a.y < 0 => Some(a.copy(0, 0));
      case b if b.x < 0 && b.y > field.get.width => Some(b.copy(0, field.get.width));
      case c if c.x > field.get.length && c.y < 0 => Some(c.copy(field.get.length, 0));
      case d if d.x > field.get.length && d.y > field.get.width => Some(d.copy(field.get.length, field.get.width));
      case e if (e.x >= 0 && e.x <= field.get.length) && (e.y < 0) => Some(e.copy(e.x, 0));
      case f if (f.y >= 0 && f.y <= field.get.width) && (f.x < 0) => Some(f.copy(0, f.y));
      case g if (g.x >= 0 && g.x <= field.get.length) && (g.y > field.get.width) => Some(g.copy(g.x, field.get.width));
      case h if (h.y >= 0 && h.y <= field.get.width) && (h.x > field.get.length) => Some(h.copy(field.get.length, h.y));
      case i => Some(i.copy(i.x, i.y));
    }
  }


}
