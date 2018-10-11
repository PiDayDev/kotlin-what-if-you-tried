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
            when (item) {
                APPLE -> {
                    val a1 = offer.key
                    if (apple >= a1) {
                        res += offer.value
                    }
                    apple -= a1
                }
                PEAR -> {
                    val a2 = offer.key
                    if (pear >= a2) {
                        res += offer.value
                    }
                    pear -= a2
                }
                PINEAPPLE -> {
                    val a3 = offer.key
                    if (pineapple >= a3) {
                        res += offer.value
                    }
                    pineapple -= a3
                }
                BANANA -> {
                    val a4 = offer.key
                    if (banana >= a4) {
                        res += offer.value
                    }
                    banana -= a4
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