package it.intre.sal.kotlin101

interface Checkout {
    fun pay(items: List<String>, offers: Map<String, Pair<Int, Int>>): Int
}
