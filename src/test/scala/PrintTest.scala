import fr.upem.easymow.factories.{CardinalFactory, FieldFactory, PositionFactory, TondeuseFactory}
import fr.upem.easymow.services.PrintService
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class PrintTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "print" should "print {x} {y} {direction}" in {
    forAll(Gen.choose(0, 10), Gen.choose(0, 10)) { (x: Int, y: Int) =>
      // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(x)(y)(FieldFactory.buildField(x + 1)(y + 1))())(CardinalFactory.build('N'))(List.empty)

      PrintService.print(tondeuse.get) should be(s"Mow ${tondeuse.get.position.x} ${tondeuse.get.position.y} N")
    }
  }
}
