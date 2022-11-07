<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 4
### Collections

===
## Collections
* Immutable by default
* Mutable if you need it

==
## Immutable collections

```scala
val xs = List(1, 2, 3)
```

<img src="/scala101/images/list1.png" class="center">

==
<!-- .slide: class="fragmented-lists" -->
## Immutable collections

```scala
val xs = List(1, 2, 3)
val ys = 0 :: xs
```

<img src="/scala101/images/list2.png" class="center">

* Immutable collections can be implemented efficiently by re-using parts of existing collections
* In a linked list, pre-pending simply re-uses the existing list, and the old pointer remains valid

==
<!-- .slide: class="fragmented-lists" -->
## Immutable collections
This technique is called _persistent collections_
* Has nothing to do with databases
<br>
<br>
<br>
* Demonstrated addition to a linked list
* Other collections are similarly implemented
* Other operations (like remove) are also similarly implemented
* Results in very performant operations
  * But you must understand the characteristics

Note: persistent means that the original version of the collection is preserved<br>
Result of immutability: concurrency becomes much easier (but other executing code sees the original version)

===
## Lists
<div class="colLeft" data-markdown>

```scala
val xs = List(1, 2, 3)
val ys = 4 :: 5 :: 6 :: Nil  
val zs = xs ++ ys

zs.head
zs.tail

zs(2)
zs take 3
zs drop 2

zs.size
zs.isEmpty                   
```
</div><div class="fragment colRight" data-markdown>

```text
val xs: List[Int] = List(1, 2, 3)
val ys: List[Int] = List(4, 5, 6)
val zs: List[Int] = List(1, 2, 3, 4, 5, 6)

val res5: Int = 1
val res6: List[Int] = List(2, 3, 4, 5, 6)

val res7: Int = 3
val res8: List[Int] = List(1, 2, 3)
val res9: List[Int] = List(3, 4, 5, 6)

val res10: Int = 6
val res11: Boolean = false
```
</div>

Note: symbolic methods like ++ and ::<br>
Infix notation<br>
List(1, 2, 3) and zs(3) use apply

==
## Lists
<div class="colLeft" data-markdown>

```scala
zs mkString ","
zs.reverse

zs.min
zs.max
zs.sum

xs zip ys

val rs = List(2, 4, 1, 3)
rs.zipWithIndex
rs.sorted
```
</div><div class="fragment colRight" data-markdown>

```text
val res12: String = 1,2,3,4,5,6
val res13: List[Int] = List(6, 5, 4, 3, 2, 1)

val res14: Int = 1
val res15: Int = 6
val res16: Int = 21

val res17: List[(Int, Int)] = List((1,4), (2,5), (3,6))

val rs: List[Int] = List(2, 4, 1, 3)
val res18: List[(Int, Int)] = List((2,0), (4,1), (1,2), (3,3))
val res19: List[Int] = List(1, 2, 3, 4)
```
</div>

Note: List is also useful for pattern matching and recursive processing

===
## Collections
* `List` is not the only collection

<div class="r-stack">
  <img src="/scala101/images/collections-diagram-213.svg" class="fragment current-visible">
  <img src="/scala101/images/collections-immutable-diagram-213.svg" class="center fragment fade-in-then-out">
  <img src="/scala101/images/collections-mutable-diagram-213.svg" class="center fragment fade-in">
</div>


<img src="/scala101/images/collections-legend-diagram.svg">

* Images from https://docs.scala-lang.org/overviews/collections-2.13/overview.html

==
## Collections
Scala defines several collection classes:

* Base Traits
  * `Iterable` (collections you can iterate on)
  * `Seq` (ordered sequences)
  * `Set` (unordered without duplicates)
  * `Map` (lookup data structure)
* Immutable Collections
  * `List` (linked list, provides fast sequential access)
  * `Stream` (same as List, except that the tail is evaluated only on demand)
  * `Vector` (array-like type, implemented as tree of blocks, provides fast random access)
  * `Range` (ordered sequence of integers with equal spacing)
  * `String` (Java type, implicitly converted to a character sequence, so you can treat every string like a `Seq[Char]`)
  * `Map` (collection that maps keys to values)
  * `Set` (collection without duplicate elements)

==
## Collections
Scala defines several collection classes:

* Mutable Collections
  * `Array` (Scala arrays are native JVM arrays at runtime, therefore they are very performant)
  * Scala also has mutable maps and sets
<br>
<br>
<div class="fragment" data-markdown>

Be aware: the *contents* of a Scala Array is mutable, even if you declare a `val ar: Array[Int] = ???`
</div>

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Consider `List`
  * for pattern matching
  * for recursion
* Consider `Vector`
  * If you need to index elements
  * It is like Java's `ArrayList` (*NOT* like Java's `Vector`)

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## Collection performance characteristics
* https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html

<div class="r-stack">
  <img src="/scala101/images/performance-sequence.png" class="fragment current-visible">
  <img src="/scala101/images/performance-set-map.png" class="center fragment fade-in-then-out">
  <img src="/scala101/images/performance-operations.png" class="center fragment fade-in-then-out">
  <img src="/scala101/images/performance-operations2.png" class="center fragment fade-in">
</div>

<img src="/scala101/images/performance-legend.png" width="400px">

===
## Sets
* An unordered collection of items, without duplicates

<div class="colLeft" data-markdown>

```scala
import scala.collection.immutable.SortedSet

val s = Set(3, 1, 2, 1)
val t = SortedSet(3, 5, 4)

t + 42
t - 3

t.size
t.isEmpty
t contains 5

t intersect s
t union s
t diff s 
```
</div>
<div class="fragment colRight" data-markdown>

```text
import scala.collection.immutable.SortedSet

val s: Set[Int] = Set(3, 1, 2)
val t: SortedSet[Int] = TreeSet(3, 4, 5)

val res0: SortedSet[Int] = TreeSet(3, 4, 5, 42)
val res1: SortedSet[Int] = TreeSet(4, 5)

val res2: Int = 3
val res3: Boolean = false
val res4: Boolean = true

val res5: SortedSet[Int] = TreeSet(3)
val res6: SortedSet[Int] = TreeSet(1, 2, 3, 4, 5)
val res7: SortedSet[Int] = TreeSet(4, 5)
```
</div>

===
## Maps
* Collection to store Key-Value pairs

<div class="colLeft" data-markdown>

```scala
val m = Map(1 -> "a", 2 -> "b", 3 -> "c")

m + (4 -> "d")
m - 1
m.updated(3, "newValue")

m(2)
m(5)
m.get(2)
m.get(5)
m.getOrElse(5, "WTF")

m.isEmpty
m contains 5

m.keys
m.unzip
```
</div>
<div class="fragment colRight" data-markdown>

```text
val m: Map[Int,String] = Map(1 -> a, 2 -> b, 3 -> c)

val res8: Map[Int,String] = Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)
val res9: Map[Int,String] = Map(2 -> b, 3 -> c)
val res10: Map[Int,String] = Map(1 -> a, 2 -> b, 3 -> newValue)

val res11: String = b
java.util.NoSuchElementException: key not found: 5
val res13: Option[String] = Some(b)
val res14: Option[String] = None
val res15: String = WTF

val res16: Boolean = false
val res17: Boolean = false

val res18: Iterable[Int] = Set(1, 2, 3)
val res19: (Iterable[Int], Iterable[String])
 = (List(1, 2, 3),List(a, b, c))
```
</div>

===
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 3: Shakespeare

Write code that takes the following input and produces the output listed below.

```scala
val phrase = "brevity is the soul of wit"
```

Target:
```text
(brevity,0)
(is,1)
(of,4)
(soul,3)
(the,2)
(wit,5)
```

The words are sorted alphabetically, and show their position in the original string

<span class="fragment" data-markdown>

Open `exercises/src/main/scala/exercise3/Brevity.scala` and add/update the code to make the program work as expected.

Exercises available at https://github.com/code-star/scala101-full-course.git
</span>

<span class="fragment" data-markdown>

One possible solution:

```scala
phrase.split(" ").zipWithIndex.sorted.mkString("\n")
```
</span>

===
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 4: todo.txt (homework assignment)

* The assignment: write some unit tests for todo.txt
  * Source code available in module `appTodo`
  * The readme (`appTodo/README.md`) has a link to the format specification
* Hints
  * Add ScalaTest to SBT
  * Use the parser like this:

```scala
val input = "(A) Call mom"
val parser = TodoParser(input)
val task: Try[Task] = parser.task.run()
```

* Try is like Option:
  * `if (task.isSuccess) println(task.get) else println(":(")`

Exercises available at https://github.com/code-star/scala101-full-course.git

==
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 4: todo.txt (homework assignment)
* Note
  * For a single task: `parser.task.run()`
  * For a list of tasks, separated by newlines: `parser.tasks.run()`
* This is homework
  * We'll discuss it in the next session
* If you get stuck
  * Google is your friend
  * The instructor(s) - and your teammates - are your friend
    * you can contact them