package exercise6

object Sets {
  type Set = Int => Boolean

  val negative: Set =
    (x: Int) => x < 0

  val even: Set =
    (x: Int) => x % 2 == 0

  def contains(s: Set, elem: Int): Boolean =
    s(elem)

  // Implement these yourself!

  def singletonSet(elem: Int): Set = ???

  def union(s: Set, t: Set): Set = ???

  def intersect(s: Set, t: Set): Set = ???

  def diff(s: Set, t: Set): Set = ???

}
