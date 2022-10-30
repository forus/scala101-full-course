import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.immutable.HashMap

class exampleSpec extends AnyFlatSpec with Matchers {
  "2 ints added with + " should "be the sum of 2 ints" in {
    2 + 2 shouldBe 4
  }

  "my HashMap" should "contain 1 as a key" in {
    val map = HashMap((1 -> "a"), (2 -> "b"))

    map should contain key 1
  }

  "division by 0" should "throw an exception" in {
    intercept[ArithmeticException] {
      1 / 0
    }
  }
}
