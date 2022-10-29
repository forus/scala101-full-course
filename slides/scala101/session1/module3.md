<!-- .slide: data-background-color="#6a1520" -->
## Scala 101 - Module 3
### Tools

===
## SBT
* https://www.scala-sbt.org/
* Scala Build Tool
* Simple Build Tool
* Interactive Build Tool

==
## SBT
* You can also use ant, gradle, make, maven, ...

<br/>

* Most Scala projects use SBT
  * It's faster for Scala code
  * It has an interactive mode
  * It has good support for cross-compiling

Note: cross compiling is an advanced feature, that allows developers to build the same code base for multiple scala versions

==
## build.sbt

```sbt
name := "My Cool Project"
version := "1.0"
scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.6",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test
)

```

* `% Test` is the scope in which you want this dependency to appear

==
## build.sbt
* Some dependencies are available for different scala versions
  * org.scalatest:scalatest_2.12:3.2.14
  * org.scalatest:scalatest_2.13:3.2.14
* Dependency in build.sbt
  * Use `%%` to make SBT choose the appropriate minor version based on `scalaVersion` 
  * "org.scalatest" <span class="fragment highlight-green">%</span> "scalatest<span class="fragment highlight-green">_2.13</span>" % "3.2.14" % Test
  * "org.scalatest" <span class="fragment highlight-green">%%</span> "scalatest" % "3.2.14" % Test

==
## project/build.properties
* It must contain the version of SBT you want to use in this project
```scala
sbt.version=1.7.2
```

<br/>

* This *must* be present, or weird errors may happen
* It helps keeping your build stable across different installations 

==
## Running sbt
* sbt clean
* sbt compile
* sbt test
* sbt
  * starts the interactive prompt

==
## Running sbt interactively
```shell
$ sbt
[info] welcome to sbt 1.7.2 (Oracle Corporation Java 11.0.2)
[info] loading global plugins from /Users/jml/.sbt/1.0/plugins
[info] loading project definition from /Users/jml/work/ordina/training/scala101-full-course/project
[info] loading settings for project root from build.sbt ...
[info] set current project to Scala 101 Full Course (in build file:/Users/jml/work/ordina/training/scala101-full-course/)
[info] sbt server started at local:///Users/jml/.sbt/1.0/server/1222e06f84b0a561587e/sock
[info] started sbt server
sbt:Scala 101 Full Course> _
```

==
## Running sbt interactively
```shell [1]
sbt:Scala 101 Full Course> compile
[info] compiling 4 Scala sources to /Users/jml/work/ordina/training/scala101-full-course/exercises/target/scala-2.13/classes ...
[error] /Users/jml/work/ordina/training/scala101-full-course/exercises/src/main/scala/exercise2/Hamlet.scala:13:21: not found: value Author
[error]   val shakespeare = Author("William", "Shakespeare")
[error]                     ^
[error] /Users/jml/work/ordina/training/scala101-full-course/exercises/src/main/scala/exercise2/Hamlet.scala:14:16: not found: value Book
[error]   val hamlet = Book("Hamlet", shakespeare, 1603)
[error]                ^
[error] two errors found
[error] (exercises / Compile / compileIncremental) Compilation failed
[error] Total time: 2 s, completed 29 Oct 2022, 11:56:56
sbt:Scala 101 Full Course> _
```

==
## Running sbt interactively
```shell [1|12-13]
sbt:Scala 101 Full Course> ~compile
[info] compiling 4 Scala sources to /Users/jml/work/ordina/training/scala101-full-course/exercises/target/scala-2.13/classes ...
[error] /Users/jml/work/ordina/training/scala101-full-course/exercises/src/main/scala/exercise2/Hamlet.scala:13:21: not found: value Author
[error]   val shakespeare = Author("William", "Shakespeare")
[error]                     ^
[error] /Users/jml/work/ordina/training/scala101-full-course/exercises/src/main/scala/exercise2/Hamlet.scala:14:16: not found: value Book
[error]   val hamlet = Book("Hamlet", shakespeare, 1603)
[error]                ^
[error] two errors found
[error] (exercises / Compile / compileIncremental) Compilation failed
[error] Total time: 0 s, completed 29 Oct 2022, 11:58:59
[info] 1. Monitoring source files for root/compile...
[info]    Press <enter> to interrupt or '?' for more options.
```

===
## Sbt with IntellIJ
* IntellIJ has support for scala and sbt through the Scala plugin
  * provided by JetBrains
* Open project by opening the `build.sbt`

===
## Sbt with VSCode
TODO: Add info on setup

===
## ScalaTest
* Most used testing framework for Scala
* Beautifully designed DSLs

==
## ScalaTest
The problem with JUnit: everything has to be a method

```java
@Test public void twoIntsAddedWithPlusShouldBeTheSumOfTwoInts(){ ... }

@Test public void plus() { ... }
```

Test names typically are long and hard to read, or short and unclear

==
## ScalaTest
The solution in ScalaTest:

```scala
test("2 ints added with + should be the sum of 2 ints"){ ... }
```

This also works well with test tooling showing the results:

```shell
sbt:Scala 101 Full Course> test
...
[info] exampleSpec:
[info] 2 ints added with +
[info] - should be the sum of 2 ints
```

<img src="/scala101/images/testresult.png" class="fragment colRight" style="width:600px;"/>

