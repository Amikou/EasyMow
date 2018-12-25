import fr.upem.easymow.factories.{CardinalFactory, FieldFactory, PositionFactory, TondeuseFactory}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class TondeuseTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  //Tests for TondeuseFactory. Illegal position argument should be fixed by the program itself, no human interaction needed.

  "Position.x <= 0 and Position.y <= 0" should "be 0 and 0" in {
    forAll(Gen.choose(-10, 0), Gen.choose(-10, 0)) { (n1, n2) =>


      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(n1)(n2)(FieldFactory.buildField(1)(1))())(CardinalFactory.build('N'))(List.empty);

      tondeuse.get.position.x should be(0);
      tondeuse.get.position.y should be(0);
    }
  }

  "Position.x >= max and Position.y >= max" should "be max and max" in {
    val max: Int = 10;
    forAll(Gen.choose(max, 10 + max), Gen.choose(max, 10 + max)) { (n1, n2) =>


      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(n1)(n2)(FieldFactory.buildField(max)(max))())(CardinalFactory.build('N'))(List.empty);

      tondeuse.get.position.x should be(max);
      tondeuse.get.position.y should be(max);
    }

  }

  "Position.x <= 0 and 0<=Position.y<max " should "be 0 and y" in {
    val max: Int = 10;
    forAll(Gen.choose(-10, 0), Gen.choose(0, max)) { (n1, n2) =>


      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(n1)(n2)(FieldFactory.buildField(max)(max))())(CardinalFactory.build('N'))(List.empty);

      tondeuse.get.position.x should be(0);
      tondeuse.get.position.y should be(n2);
    }

  }

  "Position.y <= 0 and 0<=Position.x<max " should "be 0 and x" in {
    val max: Int = 10;
    forAll(Gen.choose(-10, 0), Gen.choose(0, max)) { (n2, n1) =>


      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(n1)(n2)(FieldFactory.buildField(max)(max))())(CardinalFactory.build('N'))(List.empty);

      tondeuse.get.position.x should be(n1);
      tondeuse.get.position.y should be(0);
    }

  }

  "0<=Position.x<max and 0<=Position.y<max " should "be x and y" in {
    val max: Int = 10;
    forAll(Gen.choose(0, max), Gen.choose(0, max)) { (n1, n2) =>


      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(n1)(n2)(FieldFactory.buildField(max)(max))())(CardinalFactory.build('N'))(List.empty);

      tondeuse.get.position.x should be(n1);
      tondeuse.get.position.y should be(n2);
    }

  }


}
