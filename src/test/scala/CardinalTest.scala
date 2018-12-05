import fr.upem.easymow.datamodel._
import fr.upem.easymow.services.PrintService
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardinalTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks{

  "CardinalNord direction" should "be N" in {
      CardinalFactory.build('N').get.direction should be(s"N")
  }

  "CardinalEst direction" should "be E" in {
    CardinalFactory.build('E').get.direction should be(s"E")
  }

  "CardinalSud direction" should "be S" in {
    CardinalFactory.build('S').get.direction should be(s"S")
  }

  "CardinalOuest direction" should "be W" in {
    CardinalFactory.build('W').get.direction should be(s"W")
  }
}
