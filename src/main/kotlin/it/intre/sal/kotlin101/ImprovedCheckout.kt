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
                APPLE -> a++
                PEAR -> p++
                PINEAPPLE -> ananas++
                BANANA -> b++
            }
        }

        // TODO offers

        for ((key, value) in map) {
            when (key) {
                APPLE -> res += a * value
                PEAR -> res += p * value
                PINEAPPLE -> res += ananas * value
                BANANA -> res += b * value
            }
        }

        return res
    }

}