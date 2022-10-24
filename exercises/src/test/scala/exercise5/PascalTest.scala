package exercise5

import org.scalatest.{FlatSpec, Matchers}

class PascalTest extends FlatSpec with Matchers {
  import Pascal._

  behavior of "Pascal's Triangle"

  it should "calculate that pascal(0, 2) == 1" in {
    pascal(0, 2) should be (1)
  }

  it should "calculate that pascal(1, 2) == 2" in {
    pascal(1, 2) should be (2)
  }

  it should "calculate that pascal(1, 3) == 3" in {
    pascal(1, 3) should be (3)
  }

  it should "calculate that pascal(2, 4) == 6" in {
    pascal(2, 4) should be (6)
  }

  it should "calculate something crazy big" in {
    pascal(42, 50) should be (536878650)
  }

}
