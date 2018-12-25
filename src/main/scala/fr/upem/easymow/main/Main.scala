package fr.upem.easymow.main

import java.util.logging.Logger

import fr.upem.easymow.datamodel.TondeuseHub
import fr.upem.easymow.factories.{PositionFactory, TondeuseFactory}
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}

object Main extends App {

  override def main(args: Array[String]): Unit = {
    // Welcome to the main class!  Enter your file path ! . = Base directory = Project's directory :)

    // Note : Les erreurs de position de la tondeuse sont automatiquement rattrapées par le logiciel :
    // Un dépassement de la carte? une tondeuse crée dans le néant? Le logiciel la replace pour vous aux valeurs les plus proches des limites du terrain!
    // Note : Nos tondeuses volent et s'executent l'une après l'autre permettant de ne pas se soucier d'evntuelles collisions entre tondeuses :)
    // Note : Traitement de l'exception lancée si on ne trouve pas le fichier. Aucune exception pour le reste !


    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n")
    mainDef("./src/ressources/data.txt") // Magic Line ! Use it ! Move your mows !
  }

  def mainDef(filePath: String)(implicit load: Option[TondeuseHub] = LoaderService.loadHubFromFile(filePath)) = load match {
    case a if a.isEmpty => Logger.getLogger("EasyMow").severe("File unreadable")

    case b => b.get.tondeuses.foldLeft(b.get.tondeuses) { (list, tondeuse) =>
      val newlist = list.drop(1)
      val newtondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(tondeuse.position.x)(tondeuse.position.y)(Some(tondeuse.position.field))(newlist))(Some(tondeuse.orientation))(tondeuse.instructions)
      val appliedtondeuse = newtondeuse match {
        case a if a.isDefined => ApplyCommandService.apply(a.get)(newlist)
        case _ => None
      }
      appliedtondeuse match {
        case a if a.isDefined => /*Logger.getLogger("EasyMow").info(s"Loaded : ${PrintService.print(tondeuse)}");*/
          Logger.getLogger("EasyMow").info(s"Result : ${PrintService.print(appliedtondeuse.get)}")
          newlist :+ appliedtondeuse.get
        case _ => Logger.getLogger("EasyMow").info(s"Result : Mow destroyed")
          newlist
      }
    }
  }
}


