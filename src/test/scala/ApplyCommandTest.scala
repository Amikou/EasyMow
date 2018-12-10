import cats.kernel.Eq
import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}
import org.scalacheck.Gen
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class ApplyCommandTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "tondeuse 1 1 N in field 5 5 on commandAvancer" should "return tondeuse 1 2 N in field 5 5" in {
    val l : List[Option[Command]] = List(CommandFactory.buildCommand('A'));
    val tondeuse : Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    val newtondeuse : Option[Tondeuse] = ApplyCommandService.apply(tondeuse);
    val newtondeuseresult = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,2,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult.get);

  }

  "tondeuse 1 1 E in field 5 5 on commandAvancer" should "return tondeuse 2 1 N in field 5 5" in {
    val l : List[Option[Command]] = List(CommandFactory.buildCommand('A'));
    val tondeuse : Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    val newtondeuse : Option[Tondeuse] = ApplyCommandService.apply(tondeuse);
    val newtondeuseresult = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(2,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult.get);
  }

  "tondeuse 1 1 S in field 5 5 on commandAvancer" should "return tondeuse 1 0 N in field 5 5" in {
    val l : List[Option[Command]] = List(CommandFactory.buildCommand('A'));
    val tondeuse : Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    val newtondeuse : Option[Tondeuse] = ApplyCommandService.apply(tondeuse);
    val newtondeuseresult = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,2,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult.get);
  }

  "tondeuse 1 1 W in field 5 5 on commandAvancer" should "return tondeuse 0 1 N in field 5 5" in {
    val l : List[Option[Command]] = List(CommandFactory.buildCommand('A'));
    val tondeuse : Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    val newtondeuse : Option[Tondeuse] = ApplyCommandService.apply(tondeuse);
    val newtondeuseresult = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(0,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult.get);
  }

  /*"tondeuse 1 1 N in field 5 5 on commandG" should "return tondeuse 1 1 W in field 5 5" in {
    val l : List[Option[Command]] = List(CommandFactory.buildCommand('G'));
    val tondeuse : Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    val newtondeuse : Option[Tondeuse] = ApplyCommandService.apply(tondeuse);
    val newtondeuseresult = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(0,1,FieldFactory.buildField(5,5)), CardinalFactory.build('N'), l);
    newtondeuse should equal(newtondeuseresult);
  }*/
}