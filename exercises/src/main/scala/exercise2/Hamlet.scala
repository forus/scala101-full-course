package exercise2

object Hamlet extends App {
  trait PrettyPrintable {
    def prettyPrint: String
  }

  // TODO: Uncomment this and add the code needed to make it print
  // "Hamlet, by William Shakespeare, as published in 1603" to the screen

  def toScreen(p: PrettyPrintable): Unit = ???

  //val shakespeare = Author("William", "Shakespeare")
  //val hamlet = Book("Hamlet", shakespeare, 1603)

  //toScreen(hamlet)
}
