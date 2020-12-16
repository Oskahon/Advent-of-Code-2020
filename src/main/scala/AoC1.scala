import scala.io.Source

object AoC1 {

    def main(args: Array[String]): Unit = {

        val source = Source.fromFile("res/aoc1.txt")
        val lines = source.getLines().filter(_.nonEmpty).map(_.toInt).toArray
        source.close()

        println(part1(lines).mkString(""))
        println(part2(lines).mkString(""))
    }

    def part1(data: Array[Int]): IndexedSeq[Int] = {
        for (i <- data.indices; j <- data.indices
            if i < j && data(i)+data(j)==2020)
                yield data(i)*data(j)
    }

    def part2(data: Array[Int]): IndexedSeq[Int] = {
        for (i <- data.indices; j <- data.indices; k <- data.indices
            if i < j && j < k && data(i)+data(j)+data(k)==2020)
                yield data(i)*data(j)*data(k)
    }

}
