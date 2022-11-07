<!-- .slide: data-background-color="#6a1520" -->
# Scala 101 - Introduction Training
## Session 2

==
## Session 2
- Functional programming
- Higher order functions
- For-comprehensions
- More Scala collections

===
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Homework - exercise 4 
* Questions/remarks
* Showcase solutions

==
<!-- .slide: data-background-color="#002b00" -->
<img src="/scala101/images/exercise.png" class="slideLabel"/>

## Excercise 4: todo.txt - possible solution
For example

```scala
import todo.TodoParser
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TaskTest extends AnyFlatSpec with Matchers {
  it should "parse a simple task" in {
    val task = parse("(A) Call mom")
    
    task.isSuccess shouldBe true
    task.get.priority shouldBe Some("A")
    task.get.description shouldBe Some("Call mom")
  }
  
  private def parse(input: String) = {
    val parser = TodoParser(input)
    parser.task.run()
  }
}

```
