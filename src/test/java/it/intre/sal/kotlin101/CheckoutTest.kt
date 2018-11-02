package it.intre.sal.kotlin101

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class CheckoutTest {

    private val withNoOffers = mutableMapOf<String, Pair<Int, Int>>()

    private fun withOffers(quantity: Int, fruit: String, offerPrice: Int) = mutableMapOf(fruit to (quantity to offerPrice))

    private fun forFruits(vararg fruits: String) = fruits.asList()

    @ParameterizedTest
    @ArgumentsSource(Checkouts::class)
    fun shouldSellAnApple(checkout: Checkout) {
        val expectedPrice = 50
        assertEquals(expectedPrice.toLong(), checkout.pay(forFruits("apple"), withNoOffers).toLong())
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts::class)
    fun aPineappleCosts220(checkout: Checkout) {

        val pineapple = "pineapple"

        val expectedPrice = 220
        assertEquals(expectedPrice.toLong(), checkout.pay(forFruits(pineapple),
                withOffers(1, pineapple, 220)).toLong())
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts::class)
    @Throws(Exception::class)
    fun aBananaCosts60(checkout: Checkout) {
        val banana = "banana"

        val expectedPrice = 60
        assertEquals(expectedPrice.toLong(), checkout.pay(forFruits(banana), withOffers(1, banana, 60)).toLong())
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts::class)
    fun fruits(checkout: Checkout) {
        val apple = "apple"
        val pear = "pear"
        // TODO: Do we sell lychee?
        val lychee = "lychee"
        val pineapple = "pineapple"
        val banana = "banana"

        val ll = withOffers(3, apple, 130)
        ll[pear] = 2 to 45

        val expectedPrice = 455
        assertEquals(expectedPrice.toLong(), checkout.pay(
                forFruits(
                        apple, pear, apple, pear, lychee, apple, banana, pineapple), ll).toLong())
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts::class)
    fun repeatedOffer(checkout: Checkout) {
        val apple = "apple"
        val pear = "pear"

        val offers = withOffers(2, apple, 75)

        val expectedPrice = 2 * 75 + 30
        assertEquals(expectedPrice.toLong(), checkout.pay(
                forFruits(apple, apple, apple, apple, pear),
                offers).toLong())
    }

    internal class Checkouts : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> =
                Stream.of(ImprovedCheckout()).map { Arguments.of(it) }
    }
}