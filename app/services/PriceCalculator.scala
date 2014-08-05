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

}
