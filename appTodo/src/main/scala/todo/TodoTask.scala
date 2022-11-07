package todo

case class Task(
    priority: Option[String] = None,
    created: Option[String] = None,
    completed: Option[String] = None,
    contexts: Set[Context] = Set.empty,
    projects: Set[Project] = Set.empty,
    description: Option[String] = None
)
case class Context(name: String)
case class Project(name: String)
