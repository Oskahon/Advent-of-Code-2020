import scala.io.Source
import scala.collection.mutable.{ListBuffer, Set}

object AoC6 extends App {

    val source = Source.fromFile("res/aoc6.txt")
    val lines = source.getLines().toList
    source.close()

    var sum = 0
    var ryhmat = ListBuffer[ListBuffer[String]]()
    var lista = ListBuffer[String]()

    for (line <- lines) if (line.nonEmpty) lista :+= line else { ryhmat :+= lista; lista = ListBuffer[String]() }
    ryhmat :+= lista

    sum = ryhmat.map(_.map(_.toSet).reduce(_ union _).size).sum
    println("Part1: "+sum)
    sum = ryhmat.map(_.map(_.toSet).reduce(_ intersect  _).size).sum
    println("Part2: "+sum)

    // Part 2
//    for (line <- lines) if (line.nonEmpty) {
//        lista :+= line
//    } else {
////        sum += lista.flatten.groupBy(identity).view.mapValues(_.length).count(x => {x._2 == lista.length})
//        sum += lista.reduce(_ intersect _).length
//        lista.clear()
//    }
////    sum += lista.flatten.groupBy(identity).view.mapValues(_.length).count(x => {x._2 == lista.length})
//    sum += lista.reduce(_ intersect _).length

//    println(sum)

    // Part 1
//    var setti: mutable.Set[Char] = mutable.Set()

//    for (line <- lines) if (line.nonEmpty) setti ++= line.toSet
//    else {
//        sum += setti.size
//        setti.clear()
//    }
//    sum += setti.size
//    println(sum)

}
