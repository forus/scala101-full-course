<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 2
### Object-Oriented programming

===
## Classes
Initialiser values are private by default
```scala
class Shape(area: Double) {
  // area is private
}

val shape = new Shape(1.0)
```

==
## Classes
Public members can be added
```scala
class Shape(a: Double) {
  // a is private, area is public
  val area = a
}

val shape = new Shape(1.0)
println(shape.area)
```

The `val` can also be used in the class definition:
<!-- .element: class="fragment" --> 

```scala
class Shape(val area: Double) {
  // area is public
}
```
<!-- .element: class="fragment" -->
==
## Sub Classes
Scala has support for inheritance, allows you to create specialisations of a class
```scala [5-10]
class Shape(val area: Double) {
  // area is public
}

class Circle(val r: Double) extends Shape(Math.PI * r * r) {
  // ...
}

val circle = new Circle(1.0)
println(circle.area)
```
<!-- .element: class="fragment" -->
==
## Sub Classes
Body of the class definition is the constructor
```scala
class Circle(val r: Double) extends Shape(Math.PI * r * r) {
  require(r > 0)
}
val circle = new Circle(-1.0) // throws!
```
==
## Methods
You can use imperative style and functional style

```scala
class Circle(val r: Double) {
  // imperative style
  def circumference: Double = {
    return 2 * Math.PI * r
  }
  
  // is equivalent to functional style 
  def circumference: Double = 2 * Math.PI * r	
}
```

==
## Methods
```scala
class Circle(val r: Double) {
  
  def draw(c: Canvas): Unit = {
    val box = determineBoundingBox // draw using box

    private def determineBoundingBox = new Rectangle(2 * r, 2 * r)
  }
```

notes: Unit in Scala is same as void in Java <br/>
Definitions are public by default, you can add private <br/>
Return types are optional (but recommended for public members)

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Avoid using explicit return statements
* Always provide a return type for public `def`s

==
## Named parameters
* When calling methods or creating objects, you can explicitly assign to the parameters
* By using the name of the parameter, you are no longer restricted in the order of the arguments
* Very useful for methods with a lot of parameters (that have default values)

```scala
def createQueue( durable: Boolean, exclusive: Boolean, autoAcknowledge: Boolean) = ???

val queue = createQueue(true, false, false) // what do these mean

val queue2 = createQueue( autoAcknowledge = false, exclusive = false, durable = true) // now it is clear

```
<!-- .element: class="fragment"-->
IDE's will help you these days (with parameter hints), but we should not rely on those features too much
<!-- .element: class="fragment"-->

==
## Default parameters
```scala
def createQueue( durable: Boolean, exclusive: Boolean, autoAcknowledge: Boolean = true) = ???

val queue = createQueue(true, false) // uses default value for autoAcknowledge
```

===
## Case Classes
#### Java
<div class="colLeft" data-markdown>

```java
public class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "Point[x=" + x + ", y=" + y + "]";
    }

    
    
```
</div>
<div class="colRight" data-markdown>

```java
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * resuls + y;
        return result;
    }

    @Override
    public boolean equals( Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != objk.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
```
</div>

==
## Case Classes
#### Scala
```scala
case class Point(x: Int, y: Int)
```

Note: parameters are `val`s automatically

==
<!-- .slide: class="fragmented-lists" -->
## Case Classes
Advantages of case classes

* Constructor, `equals`, `hashCode` (also `.##` alias in Scala)
* Immutable
* `copy` method
* Easy serialisation with the `apply` and `unapply` methods
* Put your whole domain in a single short file

notes: val p1 = Point(1, 2)<br/>
val p2 = p1.copy(y=6)<br/><br/>
The unapply method is also used for pattern matching

==
## Case Classes
Improve safety of your application by reducing the need for Stringly typed code

```scala
// Stringly typed
def execute(sql: String): Result
```

Using (case) classes makes the intent more visible and prevents mixups:
<!-- .element: class="fragment"-->

```scala
case class Sql(s: String)
def execute(sql: Sql): Result
```
<!-- .element: class="fragment"-->

<img src="/scala101/images/ccatt.png" class="fragment colRight" style="width:600px;"/>

===
## Singleton object
* Similar to Java static methods

```scala
object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}
```

==
## Singleton object
* You could write Java-like code in Scala:

```scala
class Circle(val r: Double)

object CircleFactory {
  def create(r: Double): Circle = new Circle(r)
}

val c = CircleFactory.create(1.0)
```

==
## Companion object
* In Scala we do not like factory classes
* Companion objects offer easy ways to create instances of a specific class
* Companion objects *must* be in the same file as their class

```scala[3]
class Circle(val r: Double)

object Circle { // notice that object uses the same name as the class
  def create(r: Double): Circle = new Circle(r)
}

val c = Circle.create(1.0)

```

==
## Apply method
* An even more Scala-esque way is to use the `apply` method for object creation

```scala[4]
class Circle(val r: Double)

object Circle {
  def apply(r: Double): Circle = new Circle(r)
}

val c = Circle(1.0)

```

This `apply` method is automatically generated for case classes
<!-- .element: class="fragment"-->

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Do not overuse the `apply` method
* Use it for factory methods in companion objects
  * Provides a nice way to convert objects into other types
* Use it when creating Domain Specific Languages

===
## Traits

```scala
trait Ordered[T] {
  def compareTo(other: T): Int
}

case class Player(skill: Int) extends Ordered[Player] {
  override def compareTo(other: Player): Int =
    skill.compareTo(other.skill)
}

player1.compareTo(player2) < 0
```

Traits are like Java's interfaces

==
## Traits

```scala
trait Ordered[T] {
  def compareTo(other: T): Int
  
  def <(other: T): Boolean = this.compareTo(other) < 0
  def >(other: T): Boolean = this.compareTo(other) > 0
}

case class Player(skill: Int) extends Ordered[Player] {
  override def compareTo(other: Player): Int =
    skill.compareTo(other.skill)
}

player1 < player2
```

* Traits allow you to add implementations
* Traits can be combined `... extends Trait1 with Trait2 with ...`

===
## Enum
```scala
sealed trait Color

object Color {
  case object Red extends Color
  case object Green extends Color
  case object Blue extends Color
}

val color = Color.Blue
```

* Typed enums (no risk of mixing up different enums)
* Pattern matching

Note: sealed means that it can only be implemented in the file that defines it <br/>
the companion object serves as a namespace

===
## Option
* Scala tries to avoid using `null` explicitly
* `Option` is a class that models the presence or absence of a value explicitly

```scala
def findUser(id: Int): Option[Person] = {
  val result = doQuery(id)
  
  if (result == null)
    None
  else
    Some(result)
    
  // shorthand for this if statement:
  // Option(result)
}
```

==
## Option

```scala
val user: Option[Person] = findUser(42)

if (user.isDefined) {
  val u = user.get 
  // do something
} else {
  // do something else
}
```

Note: a better way to deal with Option will be shown later

==
## Option

```scala
val user: Option[Person] = findUser(42)

val u = user.getOrElse(defaultUser)
val a: Option[Address] = user.map(_.address)

Option(null) == None

```

===
## Implicit classes
```scala
implicit class RichInt(val i: Int) extends AnyVal {
  def twice = i * 2
}

println(2.twice)
```

* Allows you to add functionality to existing classes
  * very powerful, so keep in mind: W.G.P.C.G.R :)

Notes: 2 is an Int, which is an object in Scala. It has methods, but without the implicit class the `twice` method does not exist<br/>
The `extends AnyVal` makes sure that no separate objects need to be created (it defines a `static` method)<br/>
It is like extension methods in C#

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Implicits are a controversial Scala feature
* Implicits are an advanced Scala feature
  * API / DSL designers
  * You must be aware that they exist

* Often, implicits make code harder to understand
  * `implicit def` is even more magical, and almost always a bad idea

===
## Rich wrappers
Standard Java types are enhanced by Scala's standard library:

```scala
1.to(3)                             // val res0: scala.collection.immutable.Range.Inclusive = Range 1 to 3
42.toHexString                      // val res1: String = 2a
"42".toInt                          // val res2: Int = 42
"2" * 3                             // val res3: String = 222
"[x=%d, y=%d]".format(2, 3)         // val res4: String = [x=2, y=3]
val regex1 = ".*@.*\\.com".r        // val regex1: scala.util.matching.Regex = .*@.*\.com
val regex2 = """.*@.*\.com""".r     // val regex2: scala.util.matching.Regex = .*@.*\.com
```

===
## Type hierarchy

<img src="/scala101/images/unified-types-diagram.svg" class="center"/>

===
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 2: classes and objects
Given this code

```scala
trait PrettyPrintable {
  def prettyPrint: String
}

def toScreen(p: PrettyPrintable): Unit = ???

val shakespeare = Author("William", "Shakespeare")
val hamlet = Book("Hamlet", shakespeare, 1603)

toScreen(hamlet)
// must print "Hamlet, by William Shakespeare, as published in 1603"
```

How would you implement classes `Author` and `Book`

Exercises available at https://github.com/code-star/scala101-full-course.git

==
## Exercise 2 - one possible solution

```scala
case class Author(firstName: String, lastName: String) extends PrettyPrintable {
  override def prettyPrint: String = s"$firstName $lastName"
}

case class Book(title: String, author: Author, year: Int) extends PrettyPrintable {
  override def prettyPrint: String = s"$title, by ${author.prettyPrint}, as published in $year"
}

def toScreen(p: PrettyPrintable): Unit = println(p.prettyPrint)

```