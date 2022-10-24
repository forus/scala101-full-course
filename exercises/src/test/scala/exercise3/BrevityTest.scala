package exercise3

import org.scalatest.{FlatSpec, Matchers}

class BrevityTest extends FlatSpec with Matchers {

  behavior of "brevity"

  it should "be the soul of wit" in {
    Brevity.output should be ("(brevity,0)\n(is,1)\n(of,4)\n(soul,3)\n(the,2)\n(wit,5)")
  }
}
