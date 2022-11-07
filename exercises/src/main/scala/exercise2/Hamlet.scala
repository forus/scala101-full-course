package exercise2

// TODO: Uncomment the lines in the app and add the code needed to make it print
// "Hamlet, by William Shakespeare, as published in 1603" to the screen"

object Hamlet extends App {
  trait PrettyPrintable {
    def prettyPrint: String
  }

  def toScreen(p: PrettyPrintable): Unit = ???

  //val shakespeare = Author("William", "Shakespeare")
  //val hamlet = Book("Hamlet", shakespeare, 1603)

  //toScreen(hamlet)
}
