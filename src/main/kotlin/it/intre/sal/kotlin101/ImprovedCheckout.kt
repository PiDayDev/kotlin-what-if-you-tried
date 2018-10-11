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

        var offerTotal = 0
        offers.forEach { (item, offer) ->
            val (offerQuantity, offerPrice) = offer
            val quantity = quantities[item]
            if (quantity != null && item in prices.keys) {
                val repeat = quantity / offerQuantity
                offerTotal += repeat * offerPrice
                quantities[item] = quantity - repeat * offerQuantity
            }
        }

        return offerTotal +
                quantities.entries.sumBy { (item, quantity) -> quantity * (prices[item] ?: 0) }
    }

}