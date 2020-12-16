import scala.annotation.tailrec
import scala.io.Source
import scala.collection.mutable
import scala.math._

object AoC10 extends App{

    val source = Source.fromFile("src/main/scala/testi.txt")
    var data = source.getLines().toList.map(_.toInt).sorted
    data = data.appended(data.last+3)
    data = data.appended(0).sorted
    println(data)
    val result = data
        .foldLeft(mutable.Map("one" -> 0, "two" -> 0, "three" -> 0, "prev" -> 0))((res, x) => {
            if ((x-res("prev")) == 1) {
                res("one") += 1
            } else if ((x-res("prev")) == 3) {
                res("three") += 1
            }
            res("prev") = x
            res
        })


    println(result)
    println(result("one")*result("three"))
    println((pow(2,result("one"))*pow(7,result("three"))).toLong)


    @tailrec
    def splitThree(list: List[Int]): List[Int] = list match {
        case _ if list.length < 4 => list
        case list if (list(1) - list.head) == 3 => list.tail
        case _ => splitThree(list.tail)
    }

    var list = mutable.Set[List[Int]]()
    val asd = List(1,2,3,4,7,8,9,10)
    var i = data.length-1
    while (i >= 0) {
        val split = data.splitAt(i)
            if (split._1.isEmpty) {
                list += data.diff(list.toList.flatten)
            } else if ((split._2.head - split._1.last) == 3) {
                list += split._2.diff(list.toList.flatten)
        }
        i-=1
    }
    println(list)
    var sum: Long = 1
    var two = 0
    var seven = 0
    var four = 0
    for (sub <- list) {
        val x = tribo(sub.length+2)
        sum *= x
    }

    println(sum)

    def tribo(n: Long): Long = {
        if (n == 0 || n == 1 || n == 2) 0
        else if (n == 3) 1
        else tribo(n - 1) + tribo(n - 2) + tribo(n - 3)
    }
}
