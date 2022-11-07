package todo

import org.parboiled2.{CharPredicate, Parser, ParserInput}

/**
 * Parser for the todo.txt format
 * https://github.com/ginatrapani/todo.txt-cli/wiki/The-Todo.txt-Format
 */
case class TodoParser(val input: ParserInput) extends Parser {
  def tasks = rule { push(Seq.empty[Task]) ~ zeroOrMore(task ~> {
    (s: Seq[Task], t: Task) => s :+ t
  }) ~ EOI }
  def task = rule {
    push(Task()) ~ zeroOrMore(!(" @" | " +") ~ " ") ~
    optional(completed) ~> {
      (t: Task, c) =>
        t.copy(completed = c)
    } ~ optional(priority) ~> {
      (t: Task, p) =>
        t.copy(priority = p)
    } ~ optional(capture(date)) ~> {
      (t: Task, c) =>
        t.copy(created = c)
    } ~ body ~ optional(EOL) }
  private def priority = rule { "(" ~ capture("A" - "Z") ~ ") " } // Rule 1
  private def completed = rule { "x " ~ capture(date) } // Completed task
  private def date = rule { digits(4) ~ "-" ~ digits(2) ~ "-" ~ digits(2) ~ " " } // Rule 2
  private def digits(n: Int) = rule { n times CharPredicate.Digit }
  private def body = rule { oneOrMore(context | project | freedoc) } // Rule 3
  private def context = rule { " @" ~ capture(oneOrMore(noneOf(" \r\n"))) ~> {
    (t: Task, c) =>
      t.copy(contexts = t.contexts + Context(c))
  }}
  private def project = rule { " +" ~ capture(oneOrMore(noneOf(" \r\n"))) ~> {
    (t: Task, p) =>
      t.copy(projects = t.projects + Project(p))
  }}
  private def freedoc = rule { capture(oneOrMore(!(" @" | " +") ~ noneOf("\r\n"))) ~> {
    (t: Task, d) =>
      t.copy(description = Some(t.description.getOrElse("") + d))
  }}
  private def EOL = rule { "\r\n" | "\n" }
}
