import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import services.PriceCalculator

/**
 * Unit test functionality of the checkout service application.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification with PriceCalculator {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "calculate the correct total of 1.70 for 2 oranges and 2 apples" in new WithApplication {
      // Input list
      val fruitItems = "Orange,apple,Apple,orange"

      // Convert to List
      val fruitList = fruitItems.toUpperCase().split(",").toList

      // Calculate the individual item totals
      val orangesTotal = calculateItemTotal(fruitList.filter(_ startsWith "O").length, priceForOranges)

      val applesTotal = calculateItemTotal(fruitList.filter(_ startsWith "A").length, priceForApples)

      orangesTotal+applesTotal mustEqual 1.70
    }

    "calculate item total of 6.0 for 10 apples at 0.60p" in new WithApplication {
      val price = priceForApples
      price mustEqual 0.60
      calculateItemTotal(10, price) mustEqual 6.0
    }

    "calculate item total of 2.50 for 10 oranges at 0.25p" in new WithApplication {
      val price = priceForOranges
      price mustEqual 0.25
      calculateItemTotal(10, price) mustEqual 2.50
    }

    "apply correctly 3 for 2 discount for 5 oranges" in new WithApplication() {
      val fruitItems = "Orange,Apple,Orange,Orange"
      val fruitList = fruitItems.split(",").toList
      val oranges = fruitList.filter(_ startsWith "O")

      val orangesTotal = calculateItemTotal(threeForTwo(oranges.length), priceForOranges)

      orangesTotal mustEqual 0.50
    }

    "apply correctly 2 for 1 discount for 4 apples" in new WithApplication() {
      val fruitItems = "Apple,Apple,Apple,apple,Orange"
      val fruitList = fruitItems.split(",").toList
      val apples = fruitList.filter(_ startsWith "A")

      val applesTotal = calculateItemTotal(twoForOne(apples.length), priceForApples)

      applesTotal mustEqual 1.20
    }

  }
}
