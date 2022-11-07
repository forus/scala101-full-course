package words

import scala.io.Source._

object Shakespeare {
  def source = fromInputStream(getClass.getResourceAsStream("/shakespeare - works.txt")).getLines()

  val endOfInitialLicense = 170

  val startOfFinalLicense = 124452

  def isDramatisStart(line: String): Boolean =
    line.toLowerCase.startsWith("dramatis personae")


  def isDramatisEnd(line: String): Boolean =
    line.startsWith("<<THIS ELECTRONIC VERSION")
}
