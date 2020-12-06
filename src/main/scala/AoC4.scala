import scala.collection.mutable
import scala.io.Source
import scala.collection.mutable.Map

object AoC4 extends App {

    val source = Source.fromFile("res/aoc4.txt")
    val lines = source.getLines().toArray
    source.close()

    val flags = mutable.Map("byr" -> 0, "iyr" -> 0, "eyr" -> 0, "hgt" -> 0, "hcl" -> 0, "ecl" -> 0, "pid" -> 0, "cid" -> 0)
    val eyeColor = List("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val numregex = "[^0-9]"
    val hairegex = "[^0-9,a-f]"

    val strings = lines.flatMap(_.split(" ")).map(_.split(":"))

    var count = 0
    for (string <- strings) {
            if (flags.contains(string(0))) {
                if (checkField(string)) flags(string(0)) = 1
            }
            else {
                if (flags.foldRight(true)((flag, result) => if (flag._2 == 0 && flag._1 != "cid") false else result)) count += 1
                flags.foreach(x => flags(x._1) = 0)
            }
    }
    println(count)


    def checkField(field: Array[String]): Boolean = field match {
        case x if x(0) == "byr" =>
            if (isAllDigits(x(1))) {
                if ((1920 to 2002).contains(x(1).toInt)) true
                else false
            } else false
        case x if x(0) == "iyr" =>
            if (isAllDigits(x(1))) {
                if ((2010 to 2020).contains(x(1).toInt)) true
                else false
            } else false
        case x if x(0) == "eyr" =>
            if (isAllDigits(x(1))) {
                if ((2020 to 2030).contains(x(1).toInt)) true
                else false
            } else false
        case x if x(0) == "hgt" =>
            if ((4 to 5).contains(x(1).length)) {
                if (x(1).charAt(x(1).length - 1) == 'n' && x(1).charAt(x(1).length - 2) == 'i' && (59 to 76).contains(x(1).replaceAll(numregex, "").toInt)) true
                else if (x(1).charAt(x(1).length - 1) == 'm' && x(1).charAt(x(1).length - 2) == 'c' && (150 to 193).contains(x(1).replaceAll(numregex, "").toInt)) true
                else false
            } else false
        case x if x(0) == "hcl" => if (x(1).replaceAll(hairegex, "").length == 6 && x(1).charAt(0) == '#') true else false
        case x if x(0) == "ecl" => if (eyeColor.contains(x(1))) true else false
        case x if x(0) == "pid" => if (isAllDigits(x(1)) && x(1).length == 9) true else false
        case _ => false
    }

    def isAllDigits(str: String) = str forall Character.isDigit

}