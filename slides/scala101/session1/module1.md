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
import java.time.LocalDate

val dates = new ArrayList[LocalDate]

dates.add(LocalDate.of(2022, 10, 25))
dates.add(LocalDate.of(1999, 12, 31))

println(dates.size)
```

notes: Ask audience what differences they spot<br/>
1 No semicolons<br/>
2 [] instead of <> for generics <br/>
3 No () after new ArrayList<br/>

==
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

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
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

## A note about style
* Never use infix notation for side-effecting calls

```scala
dates add LocalDate.of(2002, 9, 11) // DON'T DO THIS

dates.add(LocalDate.of(2022, 10, 25)) // Correct
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
notes: This is because equals and hashCode are defined on Object, which is AnyRef. If you compare primitives (AnyVal), the compiler will box it and call equals/hashCode on the Java wrapper object. If you use == or ##, it will call a method on AnyVal without having to box a primitive. For AnyRef, it will simply delegate to equals/hashCode. For consistency's sake, it's a convention to use == and ## everywhere—also Strings.

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
<img src="/scala101/images/do_dont.png" class="slideLabel"/>

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

