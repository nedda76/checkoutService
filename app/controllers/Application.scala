package controllers

import play.api._
import play.api.mvc._
import services.PriceCalculator

object Application extends Controller with PriceCalculator {

  def checkout(fruitItems: String) = Action {

    // Convert to List
    val fruitList = fruitItems.toUpperCase().split(",").toList

    val apples = fruitList.filter(_ startsWith "A")
    val oranges = fruitList.filter(_ startsWith "O")

    // Calculate the individual item totals - calculate count based on discount
    val orangesTotal = calculateItemTotal(threeForTwo(oranges.length), priceForOranges)

    val applesTotal = calculateItemTotal(twoForOne(apples.length), priceForApples)

    // Combined total
    val total = orangesTotal+applesTotal

    // Return the total checkout amount as a String
    Ok(total.toString)
  }

}