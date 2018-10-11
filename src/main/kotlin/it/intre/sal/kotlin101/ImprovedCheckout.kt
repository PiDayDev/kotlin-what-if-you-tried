package it.intre.sal.kotlin101

import kotlin.collections.Map.Entry

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

    override fun pay(items: List<String>, offers: Map<String, Entry<Int, Int>>): Int {
        var res = 0

        val quantities = items
                .groupingBy { it }
                .eachCount()
                .toMutableMap()

        offers.forEach { (item, offer) ->
            val (offerQuantity, offerPrice) = offer
            val quantity = quantities[item]
            if (quantity != null && item in prices.keys) {
                if (quantity >= offerQuantity) {
                    res += offerPrice
                }
                quantities[item] = quantity - offerQuantity
            }
        }

        res += quantities.entries
                .sumBy { (item, quantity) -> quantity * (prices[item] ?: 0) }

        return res
    }

}