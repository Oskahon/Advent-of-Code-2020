import scala.io.Source

object AoC1 extends App {

  val source = Source.fromFile("res/aoc1.txt")
  val lines = source.getLines().filter(!_.isEmpty).map(_.toInt).toArray
  source.close()

  for (line <- lines) {
    for (line2 <- lines) {
      for (line3 <- lines)
      if (line + line2 + line3 == 2020) println(line * line2 * line3)
    }
  }

}
