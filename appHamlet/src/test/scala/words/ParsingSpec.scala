package words

import org.scalatest._

class ParsingSpec extends FlatSpec with Matchers {
  "Persona parser" should "ignore indefinite article" in {
    Personae.parsePersona("A Hero") should be (None)
  }

  it should "correctly identify the persona in a dramatis line" in {
    Personae.parsePersona("MACBETH, Thane of Glamis and Cawdor, a general in the King's army") should be (Some("MACBETH"))
  }

  "Processing" should "strip the initial and final license" in {
    import Processing._
    for (line <- Shakespeare.source.stripLicenses) {
      val ebook = line.toLowerCase.contains(" ebook")
      if (ebook) println(line)
      ebook should be (false)
    }
  }

  "In memory processing" should "determine correct count for 'bequeath'" in {
    InMemory.wordCount(Shakespeare.source)("bequeath") should be (9)
  }
  
  it should "fail to handle large amounts of data and have your processor make a lot of noise" in {
//    benchmark {
//      InMemory.wordCount(bigIterator.take(Int.MaxValue / 16))
//    }
  }

  "Lazy processing" should "determine correct count for 'bequeath'" in {
    Lazy.wordCount(Shakespeare.source)("bequeath") should be (9)
  }

  it should "handle large amount of data in available memory" in {
    benchmark {
      Lazy.wordCount(bigIterator.take(Int.MaxValue / 16))
    }
  }

  
  private lazy val bigString = (0 to 100).map(_ => "What's in a name? That which we call a rose").mkString
  
  private def bigIterator: Iterator[String] = new Iterator[String] {
    override def next(): String = bigString
    override def hasNext: Boolean = true
  }

  private def benchmark(action: => Unit): Unit = {
    Runtime.getRuntime.gc()
    def free = Runtime.getRuntime.freeMemory()
    val startMem = free
    val startTime= System.nanoTime()
    action
    val endMem = free
    val endTime = System.nanoTime()
    val deltaMem = (startMem - endMem) / 1024 / 1024
    val deltaTime = (endTime-startTime)/1000/1000/1000
    println(s"processed in $deltaTime s using $deltaMem MB")
  }

}
