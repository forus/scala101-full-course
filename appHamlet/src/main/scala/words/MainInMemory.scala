package words

object MainInMemory extends App {

  InMemory.wordCount(Shakespeare.source).foreach(wc => println(wc))

}
