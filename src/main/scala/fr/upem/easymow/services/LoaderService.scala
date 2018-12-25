package fr.upem.easymow.services

import java.nio.file.{Files, Paths}
import java.util.logging.Logger

import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._

import scala.io.Source

object LoaderService {


  // EXPLICATIONS : TondeuseHub a été rajouté APRES avoir fait le sujet initial. Du coup, la gestion des collisions est arrivée seulement après, les tondeuses sont parfaitement autonoms.
  // Cependant, parce qu'une tondeuse est autonome, on a besoin de la recréer en passant la liste des tondeuses pour gérer les collisions.
  // On part d'une liste vide puis on ajoute au fur et à mesure les tondeuses en vérifiant qu'elle n'écrase pas une autre --> la 2ème explose dans ce cas
  def loadHubFromFile(filepath: String): Option[TondeuseHub] = loadFromFile(filepath) match {
    case a if a.isDefined => TondeuseHubFactory.buildTondeuseHub(a.get)
    case _ => None
  }

  private def loadFromFile(filepath: String): Option[List[Tondeuse]] = Files.exists(Paths.get {
    filepath
  }) match {
    case true => Some(loadFromString(Source.fromFile(filepath).getLines().mkString("\n")))
    case false => None
  }

  //Appelle les bonnes def pour renvoyer une List[Option[Tondeuse]] à partir d'une string formatée !
  private def loadFromString(data: String): List[Tondeuse] = loadTondeuses(loadTondeusesFromStringFile(data.split("\n").toList.drop(1)))(loadFieldFromStringFile(data.split("\n").head))

  //Crée une liste de tondeuse à partir d'une liste d'un tuple de 2 string formaté : <"position_tondeuse_x position_tondeuse_y orientation_tondeuse",List<Char> représentant des instructions<
  private def loadTondeuses(data: List[(String, String)])(field: Option[Field]): List[Tondeuse] = data.map { case (s1, s2) => s1.split(" ").length match {
    case 3 => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(Integer.valueOf(s1.split(" ")(0)))(Integer.valueOf(s1.split(" ")(1)))(field)())(CardinalFactory.build(s1.split(" ")(2).charAt(0).toUpper))(s2.toCharArray.map(c => CommandFactory.buildCommand(c)).flatMap(t => t).toList) match {
      case a if a.isDefined => Logger.getLogger("EasyMow").info(s"Loaded : ${PrintService.print(a.get)}")
        a
      case _ => Logger.getLogger("EasyMow").info(s"Loaded : Mow destroyed")
        None
    }
    case _ => None
  }
  }.flatMap(t => t)

  //Crée une liste d'un tuple de 2 string à utiliser pour loadTondeuse à partir d'une liste de string où chaque string est soit les infos de la tondeuse
  // soit les instructions pour cette tondeuse. Mieux vaut qu'il y ait des instructions pour chaque tondeuse ...
  private def loadTondeusesFromStringFile(data: List[String]): List[(String, String)] = data.filter(s => s.contains(" ")) zip data.filter(s => !s.contains(" "));

  //Crée un Field à partir d'une string fromatée : "lenght width"
  private def loadFieldFromStringFile(data: String): Option[Field] = data.split(" ").length match {
    case 2 => FieldFactory.buildField(Integer.valueOf(data.split(" ")(0)))(Integer.valueOf(data.split(" ")(1))) match {
      case a if a.isDefined => Logger.getLogger("EasyMow").info(s"Loaded : ${PrintService.print(a.get)}")
        a
      case b => Logger.getLogger("EasyMow").severe(s"Field invalid")
        b
    }
    case _ => None
  }

  def loadHubFromString(data: String): Option[TondeuseHub] = TondeuseHubFactory.buildTondeuseHub(loadFromString(data)) match {
    case b if b.isDefined => TondeuseHubFactory.buildTondeuseHub(b.get.tondeuses.foldLeft(List.empty[Tondeuse]) { (list, t) => {
      TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(t.position.x)(t.position.y)(Some(t.position.field))(list))(Some(t.orientation))(t.instructions) match {
        case a if a.isDefined => Logger.getLogger("EasyMow").info(s"Constructed : ${PrintService.print(a.get)}")
          list :+ t
        case _ => Logger.getLogger("EasyMow").info(s"Constructed : Mow destroyed :(")
          list
      }
    }
    }
    )
    case _ => None
  }


}
