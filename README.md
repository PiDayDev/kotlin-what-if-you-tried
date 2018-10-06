The kata
========
In Jet Supermarket we have a checkout system that only can do one kind of offers, 
based on quantities of the same item (eg. 1 apple for 0.50 cents, 3 apples for 1.20 dollars).  

At the moment, items are priced individually in cents (e.g. 1 apple costs 50 cents), 
while some items are multipriced: buy _x_ of them, and they'll cost you _n_ cents.   

In fact the current prices are:  

|Item       | Unit Price  | Special Price |   
|-----------|-------------|---------------|
| apple     | 50          | 3 for 130     |   
| pear      | 30          | 2 for 45      |   
| pineapple | 220         |               |   
| banana    | 60          |               |   


Our checkout accepts items in any order, so that if we scan a pear, a pineapple, 
and another pear, we'll recognize the two pears and price them at 45 
(for a total price so far of 265).

Because the pricing changes frequently, we pass in a set of pricing rules 
each time we start handling a checkout transaction.  


What are we gonna do?
---------------------
We already have a Java implementation of the checkout system: it makes all our tests pass,
 but the code quality leaves much to be desired.
 
We are going to rewrite it in Kotlin, making it way more concise and clear.

The purpose of this exercise is not to create an impeccable implementation, 
 but to show some of the potentiality of Kotlin: the main goal is to introduce 
 this language in a small example and spur your curiosity.
 
To follow the steps, you can checkout the commits in sequence from the first to the last
 and view the changes introduced in each step.