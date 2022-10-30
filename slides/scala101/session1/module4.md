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
