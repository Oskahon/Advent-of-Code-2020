import scala.io.Source


object AoC7 extends App {

    val source = Source.fromFile("res/aoc7.txt")
    val data = source.getLines().toVector
    source.close()
    val lines = data.map(_.replaceAll("bags", "").replaceAll("bag", "").replaceAll("\\.","").split("contain").map(_.trim).toVector)

    val start = System.nanoTime()
    val golden = "shiny gold"
    var total = 0

    // Part 1
//    for (line <- lines) if (searchGolden(line)) total += 1
//    println(total-1)

    // Part 2
    println(countBags(getInnerBagsAndNumbers(findBag(golden)))-1)

    println((System.nanoTime() - start) / 1e9d + "s")

    def countBags(bag: Vector[String]): Int = bag match {
        case _ if bag(0) == "no other" => println("hep"); 1
        case bag =>
            var sum = 0
            for (inner <- bag) {
                println(inner)
                println(inner.split(" ")(0).toInt)
                sum += inner.split(" ")(0).toInt * countBags(getInnerBagsAndNumbers(findBag(inner.substring(2, inner.length))))
            }
            sum += 1
            sum

    }

    def searchGolden(bag: Vector[String]): Boolean = {
        var gold = false
        if (!gold) {
            bag match {
                case _ if bag(0) == golden => gold = true
                case _ if bag(1) == "no other" =>
                case bag =>
                    var tulokset = Vector[Boolean]()
                    for (innerBag <- getInnerBags(bag)) {
                        tulokset +:= searchGolden(findBag(innerBag))
                    }
                    gold = tulokset.reduce(_ || _)
                case _ => println("error")
            }
        }
        gold
    }

    def findBag(desc: String): Vector[String] = {
        val result = Vector[String]()
        for (line <- lines) if (line(0).contains(desc)) return line
        result
    }

    def getInnerBags(bag: Vector[String]): Vector[String] = bag(1).split(", ").map(_.replaceAll("[0-9]", "").trim).toVector
    def getInnerBagsAndNumbers(bag: Vector[String]): Vector[String] = bag(1).split(", ").map(_.trim).toVector
}

