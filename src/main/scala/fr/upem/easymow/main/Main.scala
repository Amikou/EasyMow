package fr.upem.easymow.main

import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}

object Main extends App {

  override def main(args: Array[String]) = {
    // Welcome to my main class! Only one line to run it and print results ! Enter your file path ! . = Base directory = Project's directory :)

    // Note : Les erreurs de position de la tondeuse sont automatiquement rattrapées par le logiciel :
    //  Un dépassement de la carte? une tondeuse crée dans le néant? Le logiciel la replace pour vous aux valeurs les plus proches des limites du terrain!
    LoaderService.loadFromFile("./src/ressources/data.txt").map(t => ApplyCommandService.apply(t)).foreach(t => System.out.println(PrintService.print(t)));
  }
}
