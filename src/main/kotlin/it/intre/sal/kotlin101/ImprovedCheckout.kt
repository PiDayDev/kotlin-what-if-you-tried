package it.intre.sal.kotlin101

const val APPLE = "apple"
const val PEAR = "pear"
const val PINEAPPLE = "pineapple"
const val BANANA = "banana"

class ImprovedCheckout : Checkout {

    private val map = mapOf(
        APPLE to 50,
        PEAR to 30,
        PINEAPPLE to 220,
        BANANA to 60
    )

    override fun pay(items: List<String>, offers: Map<String, Map.Entry<Int, Int>>) = 0

}