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
        var total = 0

        prices.forEach { item, price ->
            val quantity = quantities[item] ?: 0
            val offer = offers[item]
            if (offer != null) {
                val appliedOffer = offer buying quantity
                total += appliedOffer.price
                total += (quantity - appliedOffer.quantity) * price
            } else {
                total += quantity * price
            }
        }

        return total
    }

}

data class Offer(val quantity: Int, val price: Int) {
    operator fun times(repeat: Int) = Offer(repeat * quantity, repeat * price)

    infix fun buying(quantity: Int): Offer {
        val repeat = quantity / this.quantity
        return this * repeat
    }
}