package fr.upem.easymow.main

import java.util.logging.Logger

import fr.upem.easymow.datamodel.Tondeuse
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}

object Main extends App {

  override def main(args: Array[String]) = {
    // Welcome to the main class!  Enter your file path ! . = Base directory = Project's directory :)

    // Note : Les erreurs de position de la tondeuse sont automatiquement rattrapées par le logiciel :
    // Un dépassement de la carte? une tondeuse crée dans le néant? Le logiciel la replace pour vous aux valeurs les plus proches des limites du terrain!
    // Note : Nos tondeuses volent et s'executent l'une après l'autre permettant de ne pas se soucier d'evntuelles collisions entre tondeuses :)
    // Note : Traitement de l'exception lancée si on ne trouve pas le fichier. Aucune exception pour le reste !


    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
    mainDef("./src/ressources/data.txt");
  }

  def mainDef(filePath: String)(implicit load: Option[List[Option[Tondeuse]]] = LoaderService.loadFromFile(filePath)) = load match {
    case a if load.isEmpty => Logger.getLogger("EasyMow").severe("File unreachable");
    case _ => load.get.map(t => {
      Logger.getLogger("EasyMow").info(s"Loaded : ${PrintService.print(t)}"); ApplyCommandService.apply(t);
    }).foreach(t => Logger.getLogger("EasyMow").info(s"Result : ${PrintService.print(t)}"))
  }
}
