import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._
import fr.upem.easymow.services.LoaderService
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class LoaderTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "10 10\n1 3 N\nA" should "build Tondeuse 1 3 N with field 10 10 and instruction CommandAvancer" in {
    forAll(Gen.choose(10, 20), Gen.choose(0, 10), Gen.choose(0, 10)) { (field: Int, x: Int, y: Int) =>
      // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val str: String = s"${field} ${field}\n${x} ${y} N\nA";
      val tondeuseFromStr: Option[TondeuseHub] = LoaderService.loadHubFromString(str);
      val l: List[Command] = List(CommandFactory.buildCommand('A').get);
      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(x)(y)(FieldFactory.buildField(field)(field))())(CardinalFactory.build('N'))(l).get;
      tondeuseFromStr.get.tondeuses(0) should equal(tondeuse);
      tondeuseFromStr.get.tondeuses.size should be(1);


    }
  }

  "10 10 a\n1 3 N\nA" should "not build Tondeuse" in {
    forAll(Gen.choose(10, 20), Gen.choose(0, 10), Gen.choose(0, 10)) { (field: Int, x: Int, y: Int) =>
      // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val str: String = s"${field} ${field}  a\n${x} ${y} N\nA";
      (LoaderService.loadHubFromString(str)) should equal(None);
    }
  }

  "10 10\n1 3 N a\nA" should "not build Tondeuse" in {
    forAll(Gen.choose(10, 20), Gen.choose(0, 10), Gen.choose(0, 10)) { (field: Int, x: Int, y: Int) =>
      // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val str: String = s"${field} ${field}\n${x} ${y} N  a\nA";
      (LoaderService.loadHubFromString(str)) should equal(None);
    }
  }

  "read an unknown file" should "return None" in {
    LoaderService.loadHubFromFile("kaka.kaka") should be(None);
  }

}