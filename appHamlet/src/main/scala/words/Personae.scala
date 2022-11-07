package words

import scala.io.Source
import Shakespeare._


/** The dramatis personae is the part of a play or novel that enumerates the characters that play a role in it.
  * See [[Personae.dramatisExample]] for a scala string that contains an example of a dramatis personae.
  */
object Personae {

  sealed trait ParsingState
  case object Dramatis extends ParsingState
  case object Other extends ParsingState

  type Accumulator = (Set[String], ParsingState)

  val zeroAcc: Accumulator = (Set.empty[String], Other)

  def scan(s: Iterator[String]): Set[String] = {
    val (personae, _) = s.map(_.trim).foldLeft(zeroAcc)(parsePersonae)
    personae
  }

  /** Given the current accumulated set of personae, the current parsing state, and the next line,
    * returns the new accumulated set of personae and the new parsing state.
    *
    * Use the function [[Shakespeare.isDramatisStart]] to determine whether the next lines will be part of the dramatis personae.
    * Use the function [[Shakespeare.isDramatisEnd]] to determine whether the following lines are not part of the dramatis personae.
    */
  def parsePersonae(acc: Accumulator, line: String): Accumulator = ???


  /** Takes a Dramatis Personae line string without outer whitespace and returns the person, if any. */
  def parsePersona(line: String): Option[String] = {
    val prefix = line.takeWhile(c => c != ',' && (c.isUpper || c.isWhitespace))
    if (prefix.size <= 1 || (prefix.size == 3 && prefix.charAt(1).isWhitespace))
      None
    else
      Some(prefix)
  }

  private val dramatisExample =
    """
      |1606
      |
      |THE TRAGEDY OF MACBETH
      |
      |
      |by William Shakespeare
      |
      |
      |
      |Dramatis Personae
      |
      |  DUNCAN, King of Scotland
      |  MACBETH, Thane of Glamis and Cawdor, a general in the King's army
      |  LADY MACBETH, his wife
      |  MACDUFF, Thane of Fife, a nobleman of Scotland
      |  LADY MACDUFF, his wife
      |  MALCOLM, elder son of Duncan
      |  DONALBAIN, younger son of Duncan
      |  BANQUO, Thane of Lochaber, a general in the King's army
      |  FLEANCE, his son
      |  LENNOX, nobleman of Scotland
      |  ROSS, nobleman of Scotland
      |  MENTEITH nobleman of Scotland
      |  ANGUS, nobleman of Scotland
      |  CAITHNESS, nobleman of Scotland
      |  SIWARD, Earl of Northumberland, general of the English forces
      |  YOUNG SIWARD, his son
      |  SEYTON, attendant to Macbeth
      |  HECATE, Queen of the Witches
      |  The Three Witches
      |  Boy, Son of Macduff
      |  Gentlewoman attending on Lady Macbeth
      |  An English Doctor
      |  A Scottish Doctor
      |  A Sergeant
      |  A Porter
      |  An Old Man
      |  The Ghost of Banquo and other Apparitions
      |  Lords, Gentlemen, Officers, Soldiers, Murtherers, Attendants,
      |     and Messengers
      |
      |
      |
      |
      |<<THIS ELECTRONIC VERSION OF THE COMPLETE WORKS OF WILLIAM
      |SHAKESPEARE IS COPYRIGHT 1990-1993 BY WORLD LIBRARY, INC., AND IS
      |PROVIDED BY PROJECT GUTENBERG ETEXT OF ILLINOIS BENEDICTINE COLLEGE
      |WITH PERMISSION.  ELECTRONIC AND MACHINE READABLE COPIES MAY BE
      |DISTRIBUTED SO LONG AS SUCH COPIES (1) ARE FOR YOUR OR OTHERS
      |PERSONAL USE ONLY, AND (2) ARE NOT DISTRIBUTED OR USED
      |COMMERCIALLY.  PROHIBITED COMMERCIAL DISTRIBUTION INCLUDES BY ANY
      |SERVICE THAT CHARGES FOR DOWNLOAD TIME OR FOR MEMBERSHIP.>>
      |
      |
      |
      |SCENE: Scotland and England
      |
      |
      |ACT I. SCENE I.
      |A desert place. Thunder and lightning.
      |
      |Enter three Witches.
      |
      |  FIRST WITCH. When shall we three meet again?
      |    In thunder, lightning, or in rain?
    """.stripMargin
}
