package fr.upem.easymow.main

import fr.upem.easymow.datamodel.Tondeuse
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}

object Main extends App {

  override def main(args: Array[String]) = {
    // Implement a program using the methods above, to filter only the independent countries
    // Feel free to use a JSON parsing library (tips : "Play Json" is nice)
    // If you finish this one, I've got couples of ideas before leaving the room ;)
   /* c : Position;
    val Tondeuse = new Tondeuse(new Position(1, 7), new CardinalNord(), new Field(3,5));
    System.out.println(PrintService.print(Tondeuse));
    System.out.println(Tondeuse.field.length);
    val zip1 = Source.fromFile("./src/ressources/test.txt").getLines().filter(s => s.contains(" "));
    val zip2 = Source.fromFile("./src/ressources/test.txt").getLines().filter(s => !s.contains(" "));
    val zip3 = zip1 zip zip2;
    zip3.foreach(s=>System.out.println(s));
    zip3.map(s => s.toString());
    zip3.map{case(s1,s2) => ("","")};*/
   //val fi =  zip3.map{case (s1,s2)  => new Tondeuse(new Position(Integer.valueOf(s1),Integer.valueOf(s2)), new CardinalNord())};

    /*LoaderService.loadFromFile("./src/ressources/test.txt").foreach(t => {
      t.get.instructions.foreach(i => t=i.get.execute(t));
      System.out.println(PrintService.print(t));
    }
    );*/
    LoaderService.loadFromFile("./src/ressources/test.txt").map(t => ApplyCommandService.apply(t)).foreach(t => System.out.println(PrintService.print(t)));



  }
}
