package it.intre.sal.kotlin101

interface Checkout {
    fun pay(items: List<String>, offers: Map<String, Map.Entry<Int, Int>>): Int
}
