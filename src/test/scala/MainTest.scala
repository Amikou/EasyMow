import fr.upem.easymow.datamodel.Command.Command
import fr.upem.easymow.datamodel._
import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}
import org.scalacheck.Gen
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class MainTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA" should "print 1 3 N\n5 1 E " in {

    val str : String = s"5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA";
    val result = LoaderService.loadFromString(str).map(t => PrintService.print(ApplyCommandService.apply(t)));
    result(0) should be("1 3 N");
    result(1) should be("5 1 E");

  }
}