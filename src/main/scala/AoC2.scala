import scala.io.Source

object AoC2 extends App{

    val source = Source.fromFile("res/aoc2.txt")
    val lines = source.getLines().toArray
    source.close()

    val cleaned = lines.map(_.split("[ -]"))

    var count = 0
    // Part 1
    //  for (line <- cleaned) {
    //    val min = line(0).toInt
    //    val max = line(1).toInt
    //    val amount = line(3).count(_ == line(2).charAt(0))
    //    println(s"line: $min $max $amount")
    //    if (max >= amount && amount >= min) count += 1
    //  }

    // Part 2
    for (line <- cleaned) {
        val ekaMerkki = line(3).charAt(line(0).toInt-1)
        val tokaMerkki = line(3).charAt(line(1).toInt-1)
        val merkki = line(2).charAt(0)
        if ((ekaMerkki == merkki && tokaMerkki != merkki) ||
          ekaMerkki != merkki && tokaMerkki == merkki) count += 1
    }

    println(count)

}
