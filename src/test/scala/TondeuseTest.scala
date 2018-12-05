import fr.upem.easymow.datamodel.{CardinalNord, Field, Position, Tondeuse}
import fr.upem.easymow.services.PrintService
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.Gen

class TondeuseTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

 /* "Position.x <= 0 and Position.y <= 0" should "be 0 and 0" in {
    forAll(Gen.choose(-10, 0), Gen.choose(-10, 0)) { (n1,n2) =>


      val tondeuse = new Tondeuse(new Position(n1, n2), new CardinalNord(), new Field(1, 1));

      tondeuse.position should be(new Position(0, 0));
    }
  }

  "Position.x >= max and Position.y >= max" should "be max and max" in {
    val max : Int = 5
    forAll( Gen.choose(5, 10), Gen.choose(5, 10)) { case (n1 : Int ,n2 : Int ) =>

      System.out.println(n1);
      System.out.println(n2);
      val tondeuse = new Tondeuse(new Position(n1, n2), new CardinalNord(), new Field(max+1, max+1));

      tondeuse.position should be(new Position(max,max));
    }
  }

  "Position.x <= 0 and 0<=Position.y<max " should "be 0 and y" in {
    val max : Int = 5
    forAll(Gen.choose(-10, 0), Gen.choose(0, max-1)) { (n1,n2) =>


      val tondeuse = new Tondeuse(new Position(n1, n2), new CardinalNord(), new Field(1, max+1));

      tondeuse.position should be(new Position(0,n2));
    }

  }

*/
}
