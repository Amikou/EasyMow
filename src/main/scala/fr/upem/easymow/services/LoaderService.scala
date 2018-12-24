package fr.upem.easymow.services

import java.nio.file.{Files, Paths}

import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._

import scala.io.Source

object LoaderService {


  // EXPLICATIONS : TondeuseHub a été rajouté APRES avoir fait le sujet initial. Du coup, la gestion des collisions est arrivée seulement après, les tondeuses sont parfaitement autonoms.
  // Cependant, parce qu'une tondeuse est autonome, on a besoin de la recréer en passant la liste des tondeuses pour gérer les collisions.
  // On part d'une liste vide puis on ajoute au fur et à mesure les tondeuses en vérifiant qu'elle n'écrase pas une autre --> la 2ème explose dans ce cas
  def loadHubFromFile(filepath: String): Option[TondeuseHub] = TondeuseHubFactory.buildTondeuseHub(loadFromFile(filepath)) match {
    case a if a.isEmpty => a;
    case b => TondeuseHubFactory.buildTondeuseHub(Some(b.get.tondeuses.foldLeft(List.empty[Option[Tondeuse]]) { (list, t) =>
      t match {
        case a if (t.isDefined) => list :+ TondeuseFactory.buildTondeuse(t.get.copy(position = PositionFactory.buildPosition(t.get.position.get.x)(t.get.position.get.y)(t.get.position.get.field)(list)));
        case _ => list :+ t;
      }
    }));
  }

  private def loadFromFile(filepath: String): Option[List[Option[Tondeuse]]] = Files.exists(Paths.get {
    filepath
  }) match {
    case true => Some(loadFromString(Source.fromFile(filepath).getLines().mkString("\n")));
    case false => None;
  }

  //Appelle les bonnes def pour renvoyer une List[Option[Tondeuse]] à partir d'une string formatée !
  private def loadFromString(data: String): List[Option[Tondeuse]] = loadTondeuses(loadTondeusesFromStringFile(data.split("\n").toList.drop(1)))(loadFieldFromStringFile(data.split("\n").toList(0)))

  //Crée une liste de tondeuse à partir d'une liste d'un tuple de 2 string formaté : <"position_tondeuse_x position_tondeuse_y orientation_tondeuse",List<Char> représentant des instructions<
  private def loadTondeuses(data: List[(String, String)])(field: Option[Field]): List[Option[Tondeuse]] = data.map { case (s1, s2) => s1.split(" ").length match {
    case 3 => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(Integer.valueOf(s1.split(" ")(0)))(Integer.valueOf(s1.split(" ")(1)))(field)())(CardinalFactory.build(s1.split(" ")(2).charAt(0).toUpper))(s2.toCharArray.map(c => CommandFactory.buildCommand(c)).toList);
    case _ => None
  }
  }

  //Crée une liste d'un tuple de 2 string à utiliser pour loadTondeuse à partir d'une liste de string où chaque string est soit les infos de la tondeuse
  // soit les instructions pour cette tondeuse. Mieux vaut qu'il y ait des instructions pour chaque tondeuse ...
  private def loadTondeusesFromStringFile(data: List[String]): List[(String, String)] = data.filter(s => s.contains(" ")) zip data.filter(s => !s.contains(" "));

  //Crée un Field à partir d'une string fromatée : "lenght width"
  private def loadFieldFromStringFile(data: String): Option[Field] = data.split(" ").length match {
    case 2 => FieldFactory.buildField(Integer.valueOf(data.split(" ")(0)))(Integer.valueOf(data.split(" ")(1)))
    case _ => None
  }

  def loadHubFromString(data: String) = TondeuseHubFactory.buildTondeuseHub(Some(loadFromString(data))) match {
    case a if a.isEmpty => a;
    case b => TondeuseHubFactory.buildTondeuseHub(Some(b.get.tondeuses.foldLeft(List.empty[Option[Tondeuse]]) { (list, t) =>
      t match {
        case a if (t.isDefined) => list :+ TondeuseFactory.buildTondeuse(t.get.copy(position = PositionFactory.buildPosition(t.get.position.get.x)(t.get.position.get.y)(t.get.position.get.field)(list)));
        case _ => list :+ t;
      }
    }));
  }


}
