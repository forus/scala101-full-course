package words

object MainPersonae extends App {

  Personae.scan(Shakespeare.source).foreach(println)
 
}
