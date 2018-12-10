import fr.upem.easymow.datamodel.Cardinal.{CardinalEst, CardinalNord, CardinalOuest, CardinalSud}
import fr.upem.easymow.datamodel._
import fr.upem.easymow.factories.CardinalFactory
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardinalTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks{

  "CardinalNord" should "be built from 'N'" in {
       CardinalFactory.build('N').get should be(CardinalNord)
  }

  "CardinalEst" should "be built from 'E'" in {
    CardinalFactory.build('E').get should be(CardinalEst)
  }

  "CardinalSud" should "be built from S" in {
    CardinalFactory.build('S').get should be(CardinalSud)
  }

  "CardinalOuest" should "be built from W" in {
    CardinalFactory.build('W').get should be(CardinalOuest)
  }
}
