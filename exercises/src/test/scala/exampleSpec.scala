import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class exampleSpec extends AnyFlatSpec with Matchers {
  "2 ints added with + " should "be the sum of 2 ints" in {
    2 + 2 shouldBe 4
  }

}
