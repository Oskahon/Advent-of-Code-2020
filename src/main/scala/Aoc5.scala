import scala.annotation.tailrec
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source

object Aoc5 extends App{

    val source = Source.fromFile("res/aoc5.txt")
    val lines = source.getLines().toArray
    source.close()

    val rows = (0 to 127).toArray
    val columns = (0 to 7).toArray
    val idList = ListBuffer[Int]()

    for (line <- lines) {
        var row = ""
        var column = ""
        (0 to 6).toArray.foreach(x => row += line.charAt(x))
        (7 to 9).toArray.foreach(x => column += line.charAt(x))

        val rownum = findRow(rows, row)
        val colnum = findRow(columns, column)
        val id = (rownum * 8) + colnum
        idList += id
//        println(s"Row: $rownum Columns: $colnum ID: $id")
    }

    println(idList.max)
    println(idList.min)
    val buffer = (idList.min to idList.max).toBuffer
//    for (id <- idList) buffer -= id
    println(buffer.diff(idList))

    @tailrec
    def findRow(rows: Array[Int], code: String): Int = code match {
        case x if x.charAt(0) == 'F' || x.charAt(0) == 'L' =>
            if (rows.length == 2) rows(0) else findRow(rows.filter(_ < (rows.head + (rows.length / 2))), code.tail)
        case x if x.charAt(0) == 'B' || x.charAt(0) == 'R' =>
            if (rows.length == 2) rows(1) else findRow(rows.filter(_ >= (rows.head + (rows.length / 2))), code.tail)
        case _ => -1
    }
}

object Aoc5binary extends App {

    val source = Source.fromFile("res/aoc5.txt")
    val lines = source.getLines().toList
    source.close()

    def getId(seat: String): Int = {
        val row = seat.substring(0, 7).replace('F', '0').replace('B', '1')
        val col = seat.substring(7, 10).replace('L', '0').replace('R', '1')
        Integer.parseInt(row, 2) * 8 + Integer.parseInt(col, 2)
    }

    val ids = lines.map(x => getId(x)).sorted
    println(ids.max)
    println(List.range(ids.head, ids.last).diff(ids))

}
