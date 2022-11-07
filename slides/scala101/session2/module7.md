<!-- .slide: data-background-color="#6a1520" -->
## Module 7 - For comprehensions

===
## For comprehensions - side effects

```scala
for (i <- 1 to 10) println(i)
```

==
## For comprehensions - side effects

```scala
for (i <- 1 to 10) println(i)

// is equivalent to 
(1 to 10) foreach { i => println(i) }

// is equivalent to 
(1 to 10) foreach { println(_) }

// is equivalent to 
1 to 3 foreach println
```

===
## For comprehensions - functional

```scala
val xs = List(1, 2, 3)

val ys = for{
  x <- xs
} yield (x + 1)

// is equivalent to
val ys = xs.map(_ + 1)
```

==
<!-- .slide: class="fragmented-lists" -->
## For comprehension - how it works
Simplified general form
```scala
for {
  p1 <- g1
    ...
  pn <- gn  
} yield result(p1, ..., pn)
```

* the for comprehension is a sequence of generators and filters
* `p1 <- g1` is a generator
* if there are several generators
  * equivalent of a nested loop
  * the last generator varies faster than the first
  * `result(p1, ..., pn)` computes 1 element of the resulting collection

==
<!-- .slide: class="fragmented-lists" -->
## For comprehension - how it works
Complete general form
```scala
for {
  p1 <- g1 if f1(p1)
    ...
  pn <- gn if fn(pn)
} yield result(p1, ..., pn)
```

* `if f1(p1)` is a filter
* A value is produced by the generator only if the filter evaluates to `true`

===
<!-- .slide: class="fragmented-lists" -->
## For comprehension - translation rules
The for comprehension is called "syntactic sugar", because it is converted into other Scala constructs before compiling

```scala
for (x <- e1) yield e2

// is translated to

e1.map(x=> e2)
```

* A for-comprehension looks like a traditional for-loop, but works differently
* You can use it with your own types, as long as they provide `map`, `flatMap`, and `filter`
  * With the proper behaviour that is expected from these methods

==
<!-- .slide: class="fragmented-lists" -->
## For comprehension - translation rules
The for comprehension is called "syntactic sugar", because it is converted into other Scala constructs before compiling

```scala
for (x <- e1 if f) yield e2

// is translated to

for (x <- e1.filter(x => f)) yield e2

// is translated to
(e1.filter(x => f)).map(x => e2)
```

==
<!-- .slide: class="fragmented-lists" -->
## For comprehension - translation rules
The for comprehension is called "syntactic sugar", because it is converted into other Scala constructs before compiling

```scala
for (x <- e1; y <- e2) yield e3

// is translated to

e1.flatMap(x => for (y <- e2) yield e3)
```

* and then the inner `for` is translated again...

==
## For comprehension - translation rules
Listing all combinations of numbers x and y, where x is drawn from 1 to m, and y is drawn from 1 to n

```scala
for (x <- 1 to m; y <- 1 to n) yield (x, y)

// is equivalent to 

(1 to m).flatMap(x => (1 to n) map (y => (x, y)))
```

===
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 8: Shakespeare, part II
Implement the functions marked `???` in `functions.scala`

<div class="fragment" data-fragment-index="20">

Open `appHamlet/src/main/scala/words/fuinctions.scala` and add/update the code to make the program work as expected.

Exercises available at https://github.com/code-star/scala101-full-course.git

</div>
