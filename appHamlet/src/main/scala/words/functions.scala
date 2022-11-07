package words

import Shakespeare._

object Processing {
  implicit class NoLicenseIterator(it: Iterator[String]) {
    /**
     * Strips the initial and final license.
     * Use [[Shakespeare.endOfInitialLicense]] and [[Shakespeare.startOfFinalLicense]].
     */
    def stripLicenses: Iterator[String] = ???
  }

  def toWords(line: String): List[String] = line.split("\\W").toList
}

object InMemory {

  import Processing._

  /** Takes a line iterator and returns a map of words and their count in the text.
    *
    * Process only lines starting from [[Shakespeare.endOfInitialLicense]] and until [[Shakespeare.startOfFinalLicense]].
    *
    * Use the function [[Processing.toWords]] to change a line into a list of words.
    * Use the function [[count]].
    */
  def wordCount(it: Iterator[String]): Map[String, Int] = ???

  /** Takes a list of words and returns a map of words to their word count. */
  def count(words: List[String]): Map[String, Int] =
    words.groupBy(identity) map { case (word, listOfWords) => (word, listOfWords.size) }
}

object Lazy {

  import Processing._

  /** Takes a line iterator and returns a map of words and their count in the text.
    * The function operates lazily. That is; lines that aren't currently being processed are not loaded into memory yet.
    *
    * Process only lines starting from [[Shakespeare.endOfInitialLicense]] and until [[Shakespeare.startOfFinalLicense]].
    * Use the function [[Processing.toWords]] to change a line into a list of words.
    *
    * Use the function [[count]] to combine the incoming words into a single outcome.
    */
  def wordCount(it: Iterator[String]): Map[String, Int] = ???

  /** Given the previous map from word to word count and the words from the current line,
    * returns the updated map from word to word count.
    */
  def count(acc: Map[String, Int], words: List[String]): Map[String, Int] = ???
}

