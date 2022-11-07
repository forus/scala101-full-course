package todo

import org.parboiled2.{ErrorFormatter, ParseError}

import scala.io.Source
import scala.util.{Failure, Success}

/**
 * Run this program to see some non-humanfriendly output and to verify that it all works
 */
object TodoList extends App {

  getTasks("todo.txt").foreach(println)

  private def getTasks(fileName: String): Seq[Task] = {
    val lines = Source.fromResource(fileName).getLines()

    lines.filter(_ != "").foldLeft(Seq.empty[Task]) {
      case (tasks, s) =>
        val parser = TodoParser(s)

        parser.task.run() match {
          case Failure(error: ParseError) =>
            println(parser.formatError(error, new ErrorFormatter(showTraces = true)))
            tasks
          case Failure(exception) =>
            println(exception)
            tasks
          case Success(task) =>
            tasks :+ task
        }
    }
  }
}
