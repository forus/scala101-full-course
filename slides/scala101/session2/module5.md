<!-- .slide: data-background-color="#6a1520" -->
## Module 5 - Functional Programming

===
## Why Functional Programming?
<img src="/scala101/images/42-years-of-microprocessor-trend-data.png" class="colRight" />

* The free lunch is over
* Computers (CPU's) do not get faster anymore
* To speed up you need to scale
    * use more cores
    * use more servers
* To be able to scale you need to distribute your data
    * distributing *immutable* data is infinitely easier

Notes: processors are now multi-core, meaning that even in a single processor you have multiple threads that execute concurrently

===
## Object-Oriented versus Functional Programming
* OO versus FP makes no sense
    * They are *NOT* mutually exclusive
      <br>
      <br>
* OO can be combined with
    * imperative programming, like Java, C#, C++
    * functional programming, like Scala, F#, OCaml

===
<!-- .slide: class="fragmented-lists" -->
## Imperative versus Functional
* Imperative programming
    * executing instructions and updating state
    * first do X, then do Y
    * X and Y have side effects
    * Z is updated
* Functional programming
    * transforming data and calling functions
    * Call Y with the result of X
    * X and Y have no side effects
    * Z is immutable, transforming it will result in another data-object (Z')

===
## Referential transparency
An expression is said to be referentially transparent if it can be replaced with its value without changing the behaviour of the application

```scala
val a = 1 + 1

// is identical to

val a = 2
```

This is true for any expression that only contains function calls to functions that have no side effects
<!-- .element: class="fragment" -->

==
## `val` versus `var`
Functional programming is about transforming *immutable* data

```scala
val i = 0
i = i + 1 // does not compile

var j = 0
j = j + 1 // OK
```

==
<!-- .slide: class="fragmented-lists" -->
## Single computation
* An immutable value only needs to be computed once
    * Calculation happens when the value comes into scope
* The `lazy` keyword indicates to the compiler that the evaluation should be done at the first actual use of the `val`
*
```scala
lazy val pi = veryExpensiveComputation
```

===
<!-- .slide: class="fragmented-lists" -->
## Everything is an expression

```scala
val discount = if (totalPrice > 100) totalPrice * 0.05 else 0.0

val x: Unit = prinln("Hello world")
println(x)

// Hello world
// ()

```

* Each statement in Scala can be treated as a value
* It has a type
    * Type can be `Unit`
* The last statement in each block (`{ ... }`) is treated as the value of that block

===
## Iteration
```scala
def sum(xs: List[Int]): Int = {
  var result = 0
  var i = 0
  while (i < xs.size) {
    result += xs(i)
    i += 1
  }
  result
}
```
===
## Recursion
```scala
def sum(xs: List[Int]): Int = {
  if (xs.isEmpty)
    0
  else
    xs.head + sum(xs.tail) 
}
```
Note: Nice and simple, but might result in stack overflow for long lists

===
<!-- .slide: class="fragmented-lists" -->
## Tail Recursion
```scala
def sum(xs: List[Int]): Int = {
  @tailrec
  def go(ys: List[Int], result: Int = 0): Int =
    if (ys.isEmpty)
      result
    else
      go(ys.tail, result + ys.head)
      
  go(xs)
}

```

* Tail recursion is rewritten to a regular iteration under the hood. That makes it fast and stack-friendly.
* Not all recursive functions can be rewritten into tail recursive functions.
* Truly tail-recursive functions are not that easy to write, look for more examples on http://alvinalexander.com/scala/scala-recursion-examples-recursive-programming

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Always use the `@tailrec` annotation when you expect the function to be tail recursive
  * You get a compiler warning if it is not

===
<!-- .slide: class="fragmented-lists" -->
## Pattern Matching
```scala
def intToString(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}
```

* The `_` acts as the default clause

==
## Pattern Matching
```scala
def maybeIntToString(x: Option[Int]): String = x match {
  case None => "I don't know"
  case Some(i) => i.toString 
}
```

==
<!-- .slide: class="fragmented-lists" -->
## Pattern Matching
```scala
case class Person(firstName: String, lastName: String)

def isAwesome(p: Person): Boolean = p match {
  case Person("Martin", "Odersky") => true
  case Person(fn, _) if fn endsWith "an" => true
  case _ => false
}
```

* You can use "constants" in the Person pattern
* You can add guards to refine the match (`... if ...`)
* You can use `_` in the pattern for "don't care" parts (everything will match)
* This works because case classes have an `unapply` method that allow you to decompose

Note: it is possible to write your own `unapply` method for non case classes

==
<!-- .slide: class="fragmented-lists" -->
## Pattern Matching
```scala
case class Person( firstName: String, lastName: String)

def areAwesome(team: List[Person]): Boolean = team match {
  case Nil => true
  case member :: Nil => isAwesome(member)
  case head :: tail => isAwesome(head) && areAwesome(tail)
}
```

* `Nil` is the empty list
* The `::` operator divides the list between the head element and the remainder (tail)
* Order matters in pattern matching: first match wins
* The method is tail recursive
  * Possible because of Scala's short-circuiting of boolean expressions

===
## Lambdas

```scala [1|3|5]
def twice(n: Int): Int = n * 2

val twice: Int => Int = (n: Int) => n * 2

val twice: Int => Int = _ * 2 

```

Note: see https://alvinalexander.com/scala/fp-book-diffs-val-def-scala-functions/ for an explanation of the subtle differences between
the def-based and val-based methods.

==
<!-- .slide: class="fragmented-lists" -->
## Lambdas

* The `Int => Int` is the type: a function from Int to Int
* The `(n: Int) => n * 2` is the function parameter list and function body
* The `_` is used as placeholder for the first parameter
* When functions have multiple parameters, you can use multiple `_`
  * Be careful to keep it legible, the `(a: Int, b: Int) => ???` syntax is usually preferred

===
<!-- .slide: class="fragmented-lists" -->
## Type Alias

* Useful when you define lambdas
  * `type Twice = Int => Int`
  * `val twice: Twice = _ * 2`
* But also for very long or complex types
  * `type MultiMap[K, V] = HashMap[K, List[V]]`

===
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 5: Pascal's Triangle

```text
                             1
                           1   1
                         1   2   1
                       1   3   3   1
                     1   4   6   4   1
                   1   5  10  10   5   1
```
* This pattern of numbers is called *Pascal's Triangle*
* The numbers on the 2 sides are always 1
* Each number inside the triangle is the sum of the numbers above it
* Useful because they give the factors of `$$ (a+b)^p $$`

==
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 5: Pascal's Triangle

* p = 0: `$$ (a+b)^0 = 1 $$`
* p = 1: `$$ (a+b)^1 = 1a + 1b $$`
* p = 2: `$$ (a+b)^2 = 1a^2 + 2ab + 1b^2 $$`
* p = 3: `$$ (a+b)^3 = 1a^3 + 3a^2 b + 3ab^2 + 1b^3 $$`
* ...

==
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 5: Pascal's Triangle

* Implement the `pascal` function, which takes a column `c` and a row `r` (counting from 0)
* The function returns the number at that spot in the triangle
* For example
  * pascal(0, 2) = 1
  * pascal(1, 2) = 2
  * pascal(1, 3) = 3

<div class="fragment" data-fragment-index="2">

Open `exercises/src/main/scala/exercise5/Pascal.scala` and add/update the code to make the program work as expected.

Exercises available at https://github.com/code-star/scala101-full-course.git

</div>

==
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 5: Pascal's Triangle - possible solution
```scala
def pascal(c: Int, r: Int): Int = {
  assert (0 <= c && c <= r)
  if (c == 0 || c == r)
    1
  else
    pascal(c - 1, r - 1) + pascal(c, r - 1)
}
```
The assert is used to prevent stack overflow on illegal c and r values

===
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 6: Sets of integers
* How could we represent sets of integers
  * For example: the set with all negative integers
  * infinite number of elements -> we cannot list them
* One possible way:
  * If you give me an integer, I'll say if it's in the set
  * For 3, I'll say "no"
  * For -4, I'll say "yes"
* We make a function for that

==
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 6: Sets of integers
* We define "characteristic functions" for sets
* All negative integers
  * `val negative: Int => Boolean = (x: Int) => x < 0`
* All even integers
  * `val even: Int => Boolean = (x: Int) => x % 2 == 0`

Note: In Math a characteristic function is a function that takes an element and returns a boolean that indicates if the element is in the set

==
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 6: Sets of integers


```scala
val negative: Int => Boolean = 
  (x: Int) => x < 0
  
val even: Int => Boolean = 
  (x: Int) => x % 2 == 0
```

<div class="fragment fade-in">

```scala
type Set = Int => Boolean 

val negative: Set = 
  (x: Int) => x < 0
  
val even: Set = 
  (x: Int) => x % 2 == 0
```
</div>
<div class="fragment fade-in">

```scala
// a function that tests for the presence of a value in a set
def contains(s: Set, elem: Int): Boolean = s(elem)
```
</div>

==
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 6: Sets of integers
* We need more functions to work effectively with sets
* Your task is to define them
* Hint: return lambdas

<div class="fragment" data-fragment-index="20">

Open `exercises/src/main/scala/exercise6/Sets.scala` and add/update the code to make the program work as expected.

Exercises available at https://github.com/code-star/scala101-full-course.git

</div>

```scala
def singletonSet(elem: Int): Set = ???    // set with only 1 element


def union(s: Set, t: Set): Set = ???      // set with all elements from s and t


def intersect(s: Set, t: Set): Set = ???  // set with elements that are in s and in t


def diff(s: Set, t: Set): Set = ???       // set with elements from s that are not in t
```
<!-- .element: class="fragment" -->


==
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 6: Sets of integers - possible solution

```scala [1-2|4-5|7-8|10-11]
def singletonSet(elem: Int): Set =        // set with only 1 element
  (x: Int) => x == elem

def union(s: Set, t: Set): Set =          // set with all elements from s and t
  (x: Int) => contains(s, x) || contains(t, x)

def intersect(s: Set, t: Set): Set =      // set with elements that are in s and in t
  (x: Int) => contains(s, x) && contains(t, x)

def diff(s: Set, t: Set): Set =           // set with elements from s that are not in t
  (x: Int) => contains(s, x) && !contains(t, x)
```
