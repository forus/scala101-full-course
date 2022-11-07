package words

object MainLazy extends App {

  for ((word, count) <- Lazy.wordCount(Shakespeare.source)) {
    println(s"$word : $count")
  }

}
