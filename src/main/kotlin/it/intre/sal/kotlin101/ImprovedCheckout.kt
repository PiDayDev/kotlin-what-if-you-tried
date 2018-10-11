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
        var apple = 0
        var pear = 0
        var pineapple = 0
        var banana = 0

        for (item in items) {
            when (item) {
                APPLE -> apple++
                PEAR -> pear++
                PINEAPPLE -> pineapple++
                BANANA -> banana++
            }
        }

        for ((item, offer) in offers) {
            val (offerQuantity, offerPrice) = offer
            when (item) {
                APPLE -> {
                    if (apple >= offerQuantity) {
                        res += offerPrice
                    }
                    apple -= offerQuantity
                }
                PEAR -> {
                    if (pear >= offerQuantity) {
                        res += offerPrice
                    }
                    pear -= offerQuantity
                }
                PINEAPPLE -> {
                    if (pineapple >= offerQuantity) {
                        res += offerPrice
                    }
                    pineapple -= offerQuantity
                }
                BANANA -> {
                    if (banana >= offerQuantity) {
                        res += offerPrice
                    }
                    banana -= offerQuantity
                }
            }
        }

        for ((item, price) in prices) {
            when (item) {
                APPLE -> res += apple * price
                PEAR -> res += pear * price
                PINEAPPLE -> res += pineapple * price
                BANANA -> res += banana * price
            }
        }

        return res
    }

}