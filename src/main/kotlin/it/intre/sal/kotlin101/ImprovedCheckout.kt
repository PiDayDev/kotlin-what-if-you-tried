package it.intre.sal.kotlin101

const val APPLE = "apple"
const val PEAR = "pear"
const val PINEAPPLE = "pineapple"
const val BANANA = "banana"

class ImprovedCheckout : Checkout {

    private val prices = mapOf(
            APPLE to 50,
            PEAR to 30,
            PINEAPPLE to 220,
            BANANA to 60
    )

    override fun pay(items: List<String>, offers: Map<String, Pair<Int, Int>>) =
            prices.entries.sumBy { (item, price) ->
                val quantity = items.count { it == item }
                val offer = SpecialPrice.from(offers[item])
                offer.payItem(quantity, price)
            }

}

sealed class SpecialPrice {

    companion object {
        fun from(quantityToPrice: Pair<Int, Int>?) =
                when (quantityToPrice) {
                    null -> NoOffer
                    else -> Offer(quantity = quantityToPrice.first, price = quantityToPrice.second)
                }
    }

    abstract fun payItem(quantity: Int, price: Int): Int

}

data class Offer(val quantity: Int, val price: Int) : SpecialPrice() {
    operator fun times(repeat: Int) = Offer(repeat * quantity, repeat * price)

    private infix fun buying(quantity: Int): Offer {
        val repeat = quantity / this.quantity
        return this * repeat
    }

    override fun payItem(quantity: Int, price: Int): Int {
        val appliedOffer = this buying quantity
        return appliedOffer.price + (quantity - appliedOffer.quantity) * price
    }
}

object NoOffer : SpecialPrice() {
    override fun payItem(quantity: Int, price: Int) = quantity * price
}