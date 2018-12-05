package fr.upem.easymow.services

import fr.upem.easymow.datamodel._
import fr.upem.easymow.services.PrintService.Show

import scala.io.Source

object LoaderService {


  // TODO Implement instances of Show typeclass
 // private def loadTondeuse(tondeuse : String)

  //Crée une liste de tondeuse à partir d'une liste d'un tuple de 2 string formaté : <"position_tondeuse_x position_tondeuse_y orientation_tondeuse",List<Char> représentant des instructions<
  def loadTondeuses(data : List[(String, String)], field : Option[Field]) : List[Option[Tondeuse]] = data.map{case (s1,s2)  => TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(Integer.valueOf(s1.split(" ")(0)),Integer.valueOf(s1.split(" ")(1)), field), CardinalFactory.build(s1.split(" ")(2).charAt(0)), s2.toCharArray.map(c => CommandFactory.buildCommand(c)).toList)};

  //Crée une liste d'un tuple de 2 string à utiliser pour loadTondeuse à partir d'une liste de string où chaque string est soit les infos de la tondeuse
  // soit les instructions pour cette tondeuse. Mieux vaut qu'il y ait des instructions pour chaque tondeuse ...
  def loadTondeusesFromStringFile(data : List[String]) : List[(String, String)] = data.filter(s => s.contains(" ")) zip data.filter(s => !s.contains(" "));


  //Crée un Field à partir d'une string fromatée : "lenght width"
  def loadFieldFromStringFile(data : String) : Option[Field] = FieldFactory.buildField(Integer.valueOf(data.split(" ")(0)), Integer.valueOf(data.split(" ")(1)))

  def loadFromString(data : List[String]) : List[Option[Tondeuse]] = loadTondeuses(loadTondeusesFromStringFile(data.drop(1)), loadFieldFromStringFile(data(0)))

  def loadFromFile(filepath : String) : List[Option[Tondeuse]] = loadFromString(Source.fromFile(filepath).getLines().toList);





  // TODO Implement print function
 // def loadField(file: String)  : Field = new Field(Source.fromFile(file).mkString)

  }
