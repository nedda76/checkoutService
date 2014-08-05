import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.libs.ws.WS
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

/**
 * Test the application API
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  val maxWaitPeriod = DurationInt(5).seconds

  "Application" should {

    "checkout successfully and return the correct amount given an apple and an orange" in new WithServer(app = FakeApplication()) {
      val items = "Orange,Apple"

      val response = Await.result(WS.url(s"http://localhost:$port/total/$items").get(), maxWaitPeriod)

      response.status must equalTo(OK)

      response.body mustEqual "0.85"
    }
  }
}
