import fr.upem.easymow.datamodel._
import fr.upem.easymow.services.PrintService
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}


class PrintTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "print" should "print {x} {y} {direction}" in {
    forAll(Gen.choose(0, 10), Gen.choose(0, 10)) { (x: Int, y:Int)  =>
     // val tondeuse = new Tondeuse(new Position(x, y), new CardinalNord(), new Field(x+1,y+1));
      val tondeuse = TondeuseFactory.buildTondeuse(PositionFactory.buildPosition(x,y, FieldFactory.buildField(x+1,y+1)), CardinalFactory.build('N'), List.empty)

      PrintService.print(tondeuse) should be(s"${tondeuse.get.position.get.x} ${tondeuse.get.position.get.y} ${tondeuse.get.orientation.get.direction}")
    }
  }

 /* "print" should "print a negative bank account" in {
    forAll { n: String =>
      val balance = -100
      val account = Bank.Account(Account.Number(n), balance)

      Print.print(account) should be(s"Account number $n has negative balance $balance")
    }
  }

  "print" should "print a zero bank account" in {
    forAll { n: String =>
      val balance = 0
      val account = Bank.Account(Account.Number(n), balance)

      Print.print(account) should be(s"Account number $n is empty")
    }
  }

  "print" should "print none account" in {
    Print.print(Option.empty[Account]) should be(s"Nothing here")
  }

  "print" should "print a positive (some) bank account" in {
    forAll { n: String =>
      val balance = 100
      val account = Option(Bank.Account(Account.Number(n), balance))

      Print.print(account) should be(s"Something inside : Account number $n has positive balance $balance")
    }
  }
*/
}
