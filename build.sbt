import sbt.Keys._

val projectName = "Scala 101 Full Course"
val projectVersion = "0.1"
val scalaVer = "2.13.10"

val scalaTestDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.14" % "test"
)

lazy val appHamlet = project.settings(
  scalaVersion := scalaVer,
  libraryDependencies ++= scalaTestDependencies
)

lazy val appTodo = project.settings(
  scalaVersion := scalaVer,
  libraryDependencies ++= scalaTestDependencies
)

lazy val exercises = project.settings(
  scalaVersion := scalaVer,
  libraryDependencies ++= scalaTestDependencies
)

lazy val root = (project in file("."))
  .aggregate(appHamlet, appTodo, exercises)
  .settings(name := projectName, version := projectVersion)
