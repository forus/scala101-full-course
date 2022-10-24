package exercise6

import org.scalatest.{FlatSpec, Matchers}

class SetsTest extends FlatSpec with Matchers {
  import Sets._

  behavior of "singletonSet"
  val singleton = singletonSet(42)

  it should "include the value" in {
    singleton(42) should be (true)
  }

  it should "not include another value" in {
    singleton(1337) should be (false)
  }


  behavior of "union"
  val unioned = union(negative, even)

  it should "contain a positve even number" in {
    unioned(4) should be (true)
  }

  it should "contain a negative odd number" in {
    unioned(-3) should be (true)
  }

  it should "not contain a positive odd number" in {
    unioned(5) should be (false)
  }


  behavior of "intersect"
  val intersected = intersect(negative, even)

  it should "contain a negative even number" in {
    intersected(-4) should be (true)
  }

  it should "not contain a negative odd number" in {
    intersected(-3) should be (false)
  }

  it should "not contain a positive even number" in {
    intersected(2) should be (false)
  }


  behavior of "diff"
  val diffed = diff(negative, even)

  it should "contain a negative odd number" in {
    diffed(-3) should be (true)
  }

  it should "not contain a negative even number" in {
    diffed(-4) should be (false)
  }

  it should "not contain a positive even number" in {
    diffed(2) should be (false)
  }
}
