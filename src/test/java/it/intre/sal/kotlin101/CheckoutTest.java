package it.intre.sal.kotlin101;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {

    private Map<String, Entry<Integer, Integer>> withOffers(int quantity, String fruit, int offerPrice) {
        Map<String, Entry<Integer, Integer>> offers = new HashMap<>();
        offers.put(fruit, new SimpleEntry<>(quantity, offerPrice));
        return offers;
    }

    private List<String> forFruits(String... fruits) {
        return Arrays.asList(fruits);
    }

    private Map<String, Entry<Integer, Integer>> withNoOffers = new HashMap<>();

    @ParameterizedTest
    @ArgumentsSource(Checkouts.class)
    void shouldSellAnApple(Checkout checkout) {
        int expectedPrice = 50;
        assertEquals(expectedPrice, checkout.pay(forFruits("apple"), withNoOffers));
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts.class)
    void aPineappleCosts220(Checkout checkout) {

        String pineapple = "pineapple";

        int expectedPrice = 220;
        assertEquals(expectedPrice, checkout.pay(forFruits(pineapple),
                withOffers(1, pineapple, 220)));
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts.class)
    void aBananaCosts60(Checkout checkout) throws Exception {
        String banana = "banana";

        int expectedPrice = 60;
        assertEquals(expectedPrice, checkout.pay(forFruits(banana), withOffers(1, banana, 60)));
    }

    @ParameterizedTest
    @ArgumentsSource(Checkouts.class)
    void fruits(Checkout checkout) {
        String apple = "apple";
        String pear = "pear";
        // TODO: Do we sell lychee?
        String lychee = "lychee";
        String pineapple = "pineapple";
        String banana = "banana";

        Map<String, Entry<Integer, Integer>> ll = withOffers(3, apple, 130);
        ll.put(pear, new SimpleEntry<>(2, 45));

        int expectedPrice = 455;
        assertEquals(expectedPrice, checkout.pay(
                forFruits(
                        apple, pear, apple, pear, lychee, apple, banana, pineapple), ll));
    }

    static class Checkouts implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(new UglyCheckout())
                    .map(Arguments::of);
        }
    }
}