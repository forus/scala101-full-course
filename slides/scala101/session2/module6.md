<!-- .slide: data-background-color="#6a1520" -->
## Module 6 - Higher order functions

===
## Higher order functions

```scala[1|3|5|7]
def applyFunction(n: Int, f: Int => Int): Int = f(n)

applyFunction(2, n => n + 1)                // returns 3

applyFunction(2, _ + 1)                     // also returns 3

applyFunction(3, twice)                     // returns 6
```

Note: this is an example of the Strategy pattern

===
## Higher order functions - collections
Collections have many higher order functions that allow you to perform operations on their elements

```scala[1|1-3|1,5|1,7]
val languages = List("Scala", "Java", "C#", "Python")

languages.filter(s => s.contains("a"))      // List(Scala, Java)

languages.map(_.toUpperCase)                // List(SCALA, JAVA, C#, PYTHON)

languages.sortBy(_.length)                  // List(C#, Java, Scala, Python)

```

==
## Higher order functions - collections
Alternative notations

```scala[1|1-3|1,5|1,7]
val languages = List("Scala", "Java", "C#", "Python")

languages filter { s => s.contains("a") }   // List(Scala, Java)

languages map { _.toUpperCase }             // List(SCALA, JAVA, C#, PYTHON)

languages sortBy { _.length }               // List(C#, Java, Scala, Python)

```

==
<!-- .slide: class="fragmented-lists" -->
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Method call notation
  * `languages.filter(_.contains("a")).map(_.toUpperCase)`
* Infix notation
  * `languages filter { _.contains("a") } map { _.toUpperCase }`
* Do not mix the notations, that makes it confusing

===
## Higher order collection functions
```scala[1|1,3-5|1,7-8|1,10-11|1,13-14]
val xs = List(1, 2, 3, 4, 5)

xs takeWhile { n => n < 3 }                 // List(1, 2)
xs dropWhile { n => n < 3 }                 // List(3, 4, 5)
xs find { n => n > 3}                       // Some(4)

xs exists { n => n > 10 }                   // false
xs forall { n => n < 10 }                   // true

xs maxBy { n => -n }                        // -1
xs minBy { n => -n }                        // -5

xs sortWith { (n, m) => n > m }             // List(5, 4, 3, 2, 1)
xs groupBy { n => n % 2 }                   // Map(0 -> List(2,4), 1 -> List(1,3,5))
```

==
## Higher order collection functions
```scala[1|1-3|1,5|1,7-8]
val xs = List(1, 2, 3, 4, 5)

xs.reduce((acc: Int, cur: Int) => acc + cur)          // 15

xs.foldLeft(5) { (acc: Int, cur: Int) => acc + cur}   // 20

xs.foldLeft("") { (acc: Int, cur: Int) => 
    acc + cur.toString }                              // "12345"
```

* acc = accumulator
* cur = current element

Note: foldLeft uses currying - multiple parameter lists (explain only if you get questions, it's an advanced topic)

==
## Higher order collection functions
```scala
val xs = List(1, 2, 3, 4, 5)

xs.reduce((acc, cur) => acc + cur)                    // 15

xs.foldLeft(5) { (acc, cur) => acc + cur}             // 20

xs.foldLeft("") { (acc, cur) => 
    acc + cur.toString }                              // "12345"
```

* types are inferred by compiler, no need to specify them

==
## Higher order collection functions
```scala
val xs = List(1, 2, 3, 4, 5)

xs.reduce(_ + _)                                      // 15

xs.foldLeft(5) { _ + _ }                              // 20

xs.foldLeft("") { _ + _.toString }                    // "12345"
```

* using each parameter exactly once, no need to name them

===
## Higher order collection functions: flatMap
```scala[1-3|1-5|1,7]
val xs = List(1, 2, 3, 4, 5)

val ys = xs.map(el => List(el, el))                   // List(List(1,1),List(2,2),List(3,3),List(4,4),List(5,5))

ys.flatten                                            // List(1,1,2,2,3,3,4,4,5,5)

xs.flatMap(el => List(el, el))                        // List(1,1,2,2,3,3,4,4,5,5)
```

==
## Higher order collection functions: flatMap
```scala[1-4|1-6|1-4,8]
val xs = List(1, 2, 3, 4, 5)

def even(n: Int): Option[Int] =
    if (n % 2 == 0) Some(n) else None
    
xs map even                                           // List(None, Some(2), None, Some(4), None)

xs flatMap even                                       // List(2, 4)

```

===
<!-- .slide: data-background-color="#002b00" class="fragmented-lists" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Exercise 7: todo.txt, part II
* Revisit the todo.txt exercise
* How would you
  * Get all tasks in project GarageSale?
  * Get all priority A phone calls?
  * Turn all priority A tasks into priority B tasks?

