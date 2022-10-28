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
===
## Type hierarchy
<img src="/scala101/images/unified-types-diagram.svg" class="center"/>
