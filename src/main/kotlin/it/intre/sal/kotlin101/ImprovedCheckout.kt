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

        val offersMap = offers.mapValues { (_, v) -> GroupOffer(quantity = v.first, price = v.second) }
        return pay(quantities, offersMap)
    }

    private fun pay(quantities: MutableMap<String, Int>, offers: Map<String, Offer>) =
            prices.entries.sumBy { (item, price) ->
                payItem(quantities[item] ?: 0, price, offers[item] ?: NoOffer)
            }

    private fun payItem(quantity: Int, price: Int, offer: Offer): Int {
        val appliedOffer = quantity / offer
        val residual = quantity - appliedOffer.quantity
        return appliedOffer.price + residual * price
    }

}

sealed class Offer

data class GroupOffer(val quantity: Int, val price: Int) : Offer() {
    operator fun times(repeat: Int) = GroupOffer(repeat * quantity, repeat * price)
}

object NoOffer : Offer()


operator fun Int.div(offer: Offer) =
        when (offer) {
            is GroupOffer -> offer * (this / offer.quantity)
            NoOffer -> GroupOffer(0, 0)
        }
