import cats.kernel.Eq
import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._
import fr.upem.easymow.services.{ApplyCommandService, LoaderService}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class CollisionTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "tondeuse 1 1 N executing command A with an existing tondeuse 1 2 E on field" should "None" in {
    val l: List[Option[Command]] = List(CommandFactory.buildCommand('A'));
    val tondeuses : List[Option[Tondeuse]] = List(TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(2)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('S'))(l));
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('N'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse)(tondeuses);
    newtondeuse should be(None);
  }

  "Loading antoher tondeuse on an existing tondeuse's position" should "not be None" in {
    forAll(Gen.choose(10, 20), Gen.choose(0, 10), Gen.choose(0, 10)) { (field: Int, x: Int, y: Int) =>
      val str: String = s"${field} ${field}\n${x} ${y} N\nA\n${x} ${y} E\nD";
      val tondeuseFromStr: Option[TondeuseHub] = LoaderService.loadHubFromString(str);
      tondeuseFromStr.get.tondeuses.size should equal(2);
      tondeuseFromStr.get.tondeuses(1) should equal(None)
    }
  }


}