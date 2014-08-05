package controllers

import play.api._
import play.api.mvc._
import services.PriceCalculator


object Application extends Controller with PriceCalculator {

  def checkout(fruitItems: String) = Action {

    // Convert to List
    val fruitList = fruitItems.toUpperCase().split(",").toList

    // Calculate the individual item totals
    val orangesTotal = calculateItemTotal(fruitList.filter(_ startsWith "O").length, priceForOranges)

    val applesTotal = calculateItemTotal(fruitList.filter(_ startsWith "A").length, priceForApples)

    // Combined total
    val total = orangesTotal+applesTotal

    // Return the total checkout amount as a String
    Ok(total.toString)
  }

}