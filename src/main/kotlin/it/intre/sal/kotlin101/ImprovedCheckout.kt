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

        val quantities = mutableMapOf<String, Int>()

        for (item in items) {
            quantities[item] = 1 + (quantities[item] ?: 0)
        }

        for ((item, offer) in offers) {
            val (offerQuantity, offerPrice) = offer
            val quantity = quantities[item] ?: 0
            when (item) {
                APPLE -> {
                    if (quantity >= offerQuantity) {
                        res += offerPrice
                    }
                    quantities[item] = quantity - offerQuantity
                }
                PEAR -> {
                    if (quantity >= offerQuantity) {
                        res += offerPrice
                    }
                    quantities[item] = quantity - offerQuantity
                }
                PINEAPPLE -> {
                    if (quantity >= offerQuantity) {
                        res += offerPrice
                    }
                    quantities[item] = quantity - offerQuantity
                }
                BANANA -> {
                    if (quantity >= offerQuantity) {
                        res += offerPrice
                    }
                    quantities[item] = quantity - offerQuantity
                }
            }
        }

        for ((item, price) in prices) {
            val quantity = quantities[item] ?: 0
            res += quantity * price
        }

        return res
    }

}