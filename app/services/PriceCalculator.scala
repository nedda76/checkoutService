package services

/**
 * Defines price related calculations
 */
trait PriceCalculator {

  def calculateItemTotal(count: Int, price: Double): Double = {
    count*price
  }

  def priceForApples: Double = {
    0.60
  }

  def priceForOranges: Double = {
    0.25
  }

  def threeForTwo(count: Int): Int = {
    count % 3 + count/3 * 2
  }

  def twoForOne(count: Int): Int = {
    count % 2 + count/2
  }

}
