import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._
import fr.upem.easymow.services.{LoaderService, PrintService}
import org.scalacheck.Gen
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class LoaderTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "10 10\n1 3 N\nA" should "build Tondeuse 1 3 N with field 10 10 and instruction CommandAvancer" in {
    forAll(Gen.choose(10, 20), Gen.choose(0, 10), Gen.choose(0, 10)) { (field: Int, x: Int, y: Int) =>
      // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val str : String = s"${field} ${field}\n${x} ${y} N\nA";
      val tondeuseFromStr : List[Option[Tondeuse]] = LoaderService.loadFromString(str);
      val l : List[Option[Command]] = List(CommandFactory.buildCommand('A'));
      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(x, y, FieldFactory.buildField(field, field)), CardinalFactory.build('N'), l);
      tondeuseFromStr(0) should equal(tondeuse);
      tondeuseFromStr(0).size should be(1);
    }
  }
}