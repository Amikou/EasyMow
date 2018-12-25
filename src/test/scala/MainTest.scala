import fr.upem.easymow.services.{ApplyCommandService, LoaderService, PrintService}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class MainTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA" should "print 1 3 N\n5 1 E " in {

    val str: String = s"5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA";
    val result = LoaderService.loadHubFromString(str).get.tondeuses.map(t => PrintService.print(ApplyCommandService.apply(t)().get));
    result(0) should be("Mow 1 3 N");
    result(1) should be("Mow 5 1 E");

  }
}