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

    override fun pay(items: List<String>, offers: Map<String, Map.Entry<Int, Int>>): Int {
        var res = 0
        var a = 0
        var p = 0
        var ananas = 0
        var b = 0

        for (item in items) {
            when (item) {
                "apple" -> a++
                "pear" -> p++
                "pineapple" -> ananas++
                "banana" -> b++
            }
        }

        // TODO offers

        for ((key, value) in map) {
            when (key) {
                "apple" -> res += a * value
                "pear" -> res += p * value
                "pineapple" -> res += ananas * value
                "banana" -> res += b * value
            }
        }

        return res
    }

}