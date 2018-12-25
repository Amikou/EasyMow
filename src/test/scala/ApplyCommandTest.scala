import cats.kernel.Eq
import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories._
import fr.upem.easymow.services.ApplyCommandService
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class ApplyCommandTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  // Tests on CommandAvancer
  "tondeuse 1 1 N in field 5 5 on commandAvancer" should "return tondeuse 1 2 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('A').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('N'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(position = tondeuse.get.position.copy(y = 2), instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);

  }

  "tondeuse 1 1 E in field 5 5 on commandAvancer" should "return tondeuse 2 1 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('A').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('E'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(position = tondeuse.get.position.copy(x = 2), instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 S in field 5 5 on commandAvancer" should "return tondeuse 1 0 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('A').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('S'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(position = tondeuse.get.position.copy(y = 0), instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 W in field 5 5 on commandAvancer" should "return tondeuse 0 1 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('A').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('W'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(position = tondeuse.get.position.copy(x = 0), instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  // Tests on CommandRotateG
  "tondeuse 1 1 N in field 5 5 on commandRotateG" should "return tondeuse 1 1 W in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('G').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('N'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('W').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 W in field 5 5 on commandRotateG" should "return tondeuse 1 1 S in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('G').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('W'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('S').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 S in field 5 5 on commandRotateG" should "return tondeuse 1 1 E in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('G').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('S'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('E').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 E in field 5 5 on commandRotateG" should "return tondeuse 1 1 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('G').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('E'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('N').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  // Tests on CommandRotateD
  "tondeuse 1 1 N in field 5 5 on commandRotateD" should "return tondeuse 1 1 E in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('D').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('N'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('E').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 W in field 5 5 on commandRotateD" should "return tondeuse 1 1 N in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('D').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('W'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('N').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 S in field 5 5 on commandRotateD" should "return tondeuse 1 1 W in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('D').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('S'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('W').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }

  "tondeuse 1 1 E in field 5 5 on commandRotateD" should "return tondeuse 1 1 S in field 5 5" in {
    val l: List[Command] = List(CommandFactory.buildCommand('D').get);
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('E'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(orientation = CardinalFactory.build('S').get, instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }


  "tondeuse 1 1 E in field 5 5 on None command" should "return tondeuse 1 1 E in field 5 5" in {
    val l: List[Command] = List.empty;
    val tondeuse: Option[Tondeuse] = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(1)(1)(FieldFactory.buildField(5)(5))())(CardinalFactory.build('E'))(l);
    val newtondeuse: Option[Tondeuse] = ApplyCommandService.apply(tondeuse.get)();
    val newtondeuseresult = tondeuse.get.copy(instructions = List.empty);
    Eq[Tondeuse].eqv(newtondeuse.get, newtondeuseresult) should be(true);
  }


}