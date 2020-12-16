import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object AoC9 extends App {

    val source = Source.fromFile("src/main/scala/testi.txt")
    val data = source.getLines().toVector.map(_.toLong)

    var que = new mutable.Queue[Long]

    for (i <- 0 to 24) que += data(i)
    var found: Long = -1
    var number: Long = 0
    for (l <- 25 until data.length) {
        found = -1
        for (i <- que.indices) {
            for (j <- i + 1 until que.length) {
                if (que(j) == data(l) - que(i)) found = data(l)
            }
        }
        if (found < 0) number = data(l)
        que += data(l)
        que.dequeue()
    }
    println(number)
    var list = new ListBuffer[Long]()
    for (num <- data.indices) {
        list.clear()
        for (i <- num until data.length) {
            list += data(i)
            if (list.sum == number) println(list.min + list.max)
        }
    }
}
