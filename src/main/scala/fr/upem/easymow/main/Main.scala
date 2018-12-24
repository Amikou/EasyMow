package fr.upem.easymow.main

import java.util.logging.Logger

import fr.upem.easymow.datamodel.{Tondeuse, TondeuseHub}
import fr.upem.easymow.factories.{PositionFactory, TondeuseFactory}
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}

object Main extends App {

  override def main(args: Array[String]) = {
    // Welcome to the main class!  Enter your file path ! . = Base directory = Project's directory :)

    // Note : Les erreurs de position de la tondeuse sont automatiquement rattrapées par le logiciel :
    // Un dépassement de la carte? une tondeuse crée dans le néant? Le logiciel la replace pour vous aux valeurs les plus proches des limites du terrain!
    // Note : Nos tondeuses volent et s'executent l'une après l'autre permettant de ne pas se soucier d'evntuelles collisions entre tondeuses :)
    // Note : Traitement de l'exception lancée si on ne trouve pas le fichier. Aucune exception pour le reste !


    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
    mainDef("./src/ressources/data.txt"); // Magic Line ! Use it ! Move your mows !
  }

  def mainDef(filePath: String)(implicit load: Option[TondeuseHub] = LoaderService.loadHubFromFile(filePath)) = load match {
    case a if load.isEmpty => Logger.getLogger("EasyMow").severe("File unreachable");

    case b => load.get.tondeuses.foldLeft(List.empty[Option[Tondeuse]]) { (list, tondeuse) =>
      val newtondeuse = tondeuse match {
        case a if a.isDefined => TondeuseFactory.buildTondeuse(tondeuse.get.copy(position = PositionFactory.buildPosition(tondeuse.get.position.get.x)(tondeuse.get.position.get.y)(tondeuse.get.position.get.field)(list)))
        case _ => None
      }
      val appliedtondeuse = ApplyCommandService.apply(newtondeuse)(list);
      tondeuse match {
        case _ => Logger.getLogger("EasyMow").info(s"Loaded : ${PrintService.print(tondeuse)}");
          Logger.getLogger("EasyMow").info(s"Result : ${PrintService.print(appliedtondeuse)}");
          list :+ appliedtondeuse;

      }
    }.foreach(t => Logger.getLogger("").info(PrintService.print(t)));
  }
}


