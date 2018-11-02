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

    override fun pay(items: List<String>, offers: Map<String, Pair<Int, Int>>): Int {
        val quantities = items
                .groupingBy { it }
                .eachCount()
                .toMutableMap()

        val offersMap = offers.mapValues { (_, v) -> Offer(quantity = v.first, price = v.second) }
        return pay(quantities, offersMap)
    }

    private fun pay(quantities: MutableMap<String, Int>, offers: Map<String, Offer>): Int {
        var offerTotal = 0
        offers.forEach { (item, offer) ->
            val quantity = quantities[item]
            if (quantity != null && item in prices.keys) {
                val appliedOffer = quantity / offer
                offerTotal += appliedOffer.price
                quantities[item] = quantity - appliedOffer.quantity
            }
        }

        return offerTotal +
                quantities.entries.sumBy { (item, quantity) -> quantity * (prices[item] ?: 0) }
    }

}

data class Offer(val quantity: Int, val price: Int) {
    operator fun times(repeat: Int) = Offer(repeat * quantity, repeat * price)
}

operator fun Int.div(offer: Offer) = offer * (this / offer.quantity)