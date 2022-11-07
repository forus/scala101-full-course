<!-- .slide: data-background-color="#6a1520" -->
# Scala 101 - Introduction Training
## Session 1

===
## Welcome
- Experience and expectations
- Objectives
  - Scala syntax
  - Object-oriented concepts in Scala
  - Basics of functional programming
  - Using Scala's Collections
  - Working with SBT and ScalaTest

Notes: Provide some background on experience<br/>
Ask about audience experience and expectations

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

notes: Due to the type safety, a compiling program is a strong indication of a correct program.<br/>
Compared to untyped languages where you can pass an int when a string is expected. Scala does this even better than Java, where
types are often lost and you are left with just an `Object`

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

notes: Complexity: familiar does not imply easy<br/>
Type system: Some parts of the type system can be inaccessible. Usually this is no issue, unless you are developing a framework or library<br/>
The type system is Turing Complete, which means it is very powerful but has caveats too.<br/>
Paradigm shift: there are multiple ways to get things done. This makes it harder to learn the differences
