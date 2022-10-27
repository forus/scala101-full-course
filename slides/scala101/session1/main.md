<!-- .slide: data-background-color="#6a1520" -->
# Scala 101 - Introduction Training
## Session 1

===
## Welcome
- experience and expectations
- objectives
  - Scala syntax
  - Object-oriented concepts in Scala
  - Basics of functional programming
  - Using Scala's Collections
  - Working with SBT and ScalaTest
<aside class="notes">
Provide some background on experience<br/>
Ask about audience experience and expectations
</aside>
==
## Format of the training

```scala
repeat {
  I.explain(Some("Scala features"))
  you.do(exercises)
  we.interact()
}
```

===
## Session 1
- Cleaner Java
- Object-oriented programming
- Tools
  - SBT
  - ScalaTest
- Scala Collections
==
## What is Scala
> Scala combines object-oriented and functional programming in one concise, high-level language. 
> Scala's static types help avoid bugs in complex applications, and its JVM and JavaScript runtimes 
> let you build high-performance systems with easy access to huge ecosystems of libraries.

Source: https://www.scala-lang.org/
==
## What is Scala
* Functional _and_ Object-Oriented
* Fully compatible with Java
  * But more concise
* Strongly Typed
  * With type inference
* Proven in production
==
## Scala's strong points
* Concurrency
* Programs correct by definition
* Good support for Domain Specific Languages (DSLs)
* All the things that Java is good at

<aside class="notes">
Due to the type safety, a compiling program is a strong indication of a correct program.<br/>
Compared to untyped languages where you can pass an int when a string is expected. Scala does this even better than Java, where
types are often lost and you are left with just an `Object`
</aside>

==
## Scala's challenges
* Complexity
  * Syntax
  * Type system
  * Operator overloading
* Paradigm shift
  * Object-Oriented vs Functional
* Concurrency
  * Mutability is still possible

<aside class="notes">
Complexity: familiar does not imply easy<br/>
Type system: Some parts of the type system can be inaccessible. Usually this is no issue, unless you are developing a framework or library<br/>
The type system is Turing Complete, which means it is very powerful but has caveats too.<br/>
Paradigm shift: there are multiple ways to get things done. This makes it harder to learn the differences
</aside>

===
<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 1
### A cleaner Java

==
## Look mama, no semicolons
```scala
println("Hello World!")
```

==
## Type inference
Types are given _after_ the identifier (as opposed to Java where they are placed in front of the identifier)
```scala
val str: String = "Hello World!"
```
is equivalent to 
```scala
val str = "Hello World!"
```

The compiler infers the type of `str` from the type of the assignment expression

==
## String interpolation
Given
```scala
val hello = "Hello"
val world = "world"
```
This assignment
```scala

val greeting = hello + ", " + world + "!"
```
is equivalent to
```scala
val greeting = s"$hello, $world!"
```

The string interpolation is compiled as a sequence of operations on a `StringBuffer`

==
## String escaping
Triple quotes allow you to use quote literals in your string expressions without escaping them:
```scala
val str = "Hello, \"world\"!"
```
is equivalent to 
```scala
val str = """Hello, "world"!"""
```
<br/>

The triple-quote strings also support string interpolation (`s"""Hello $name"""`)

<br/>

You can use the `.stripMargin` operator to create a long string with embedded newlines (`\n`):

```scala
val longString = """
    |This is a pretty
    |long string that spans multiple
    |lines""".stripMargin
```
is equivalent to
```scala
val longString = "This is a pretty\nlong string that spans multiple\nlines" 
```

===
## Java interoperability
What differences do you spot compared to Java?

```scala
import java.util.ArrayList

val dates = new ArrayList[LocalDate]

dates.add( new LocalDate(2022, 10, 25))
dates.add( new LocalDate(1999, 12, 31))

println(dates.size)
```

<aside class="notes'>
Ask audience what differences they spot<br/>
No semicolons<br/>
[] instead of <> for generics <br/>
No () after new ArrayList<br/>
</aside>

==
## A note about style
* Parentheses (`()`) can often be omitted
* Convention is to 
  * keep them if the call has side effects
    * `dates.clear()`
    * `control.fireTheMissiles()`
  * omit them if the call has *NO* side effects
    * `room.getTemperature`

===
## Infix notation
* Functions that take exactly 1 parameter can be called in different ways
* Depending on the context this can greatly improve readability

```scala
val str = "hello"

str.contains("e")   // normal function call
s contains "e"      // infix notation of same call

```

==
## A note about style
* Never use infix notation for side-effecting calls

```scala
dates add new LocalDate(2002, 9, 11) // DON'T DO THIS

dates.add(new LocalDate(2022, 10, 25)) // Correct
```

==
## Infix notation
```scala
// method call on Integer
1.to(3)

// can be written as
1 to 3

```

===

## Symbolic methods

Symbols can be powerful conveyors of meaning when used appropriately.

```scala
val s = "Hello"
val t = "world"

// Do not use this Java style
val same = s.equals(t)
val hash = s.hashCode

// Use this Scala style
val same = s == t
val hash = s.##
```

==
## Symbolic methods

Scala allows you to (re)define methods with symbols in their name

```scala
// + is just a function name
1.+(3)

// can be written as
1 + 3

```

==
## Symbolic methods
Some more examples:
```scala
// operators on Types
bigDecimal1 + bigDecimal2

// send a message to an actor
actor ! "message"

val todo = ???
```

==
## ???
* The `???` can be used to tell the scala compiler that this part is not yet written
* Compiler will treat this as valid in any context where it is used
* Throws a `scala.NotImplementedError` exception when code is reached at runtime
* From the scala source code:

```scala
  /** `???` can be used for marking methods that remain to be implemented.
   *  @throws NotImplementedError when `???` is invoked.
   *  @group utilities
   */
  def ??? : Nothing = throw new NotImplementedError

```

==
## A note about style
* Do not go overboard with inventing symbolic operators. It poses a strain on developers that need to learn the meaning
* `x :-| y`

<img src="/scala101/images/dispatch.png" class="center" style="width:600px;"/>

===
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 1: the basics
* Open a new Scala worksheet
  * File->New->Scala Worksheet
* Experiment!
* Press the green arrow to "run" the worksheet
* Observe the results

==
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Extra
* Open https://scala-lang.org/api/current
* Find `RichInt.to`
* What parameters does it accept?
* What does it return?
* What about `RichInt.until`?

===
<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 2
### Object-Oriented programming

==
## Classes
Initialiser values are private by default
```scala
class Shape(area: Double){
  // area is private
}

val shape = new Shape(1.0)
```

==
## Classes
Public members can be added
```scala
class Shape(a: Double){
  // a is private, area is public
  val area = a
}

val shape = new Shape(1.0)
println(shape.area)
```

The `val` can also be used in the class definition:
<!-- .element: class="fragment" --> 

```scala
class Shape(val area: Double){
  // area is public
}
```
<!-- .element: class="fragment" -->

===
## Type hierarchy
<img src="/scala101/images/unified-types-diagram.svg" class="center"/>

===
<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 3
### Tools

===
<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 4
### Collections

