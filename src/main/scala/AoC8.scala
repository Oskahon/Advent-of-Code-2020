import scala.io.Source

object AoC8 extends App {

    val source = Source.fromFile("res/aoc8.txt")
    val data = source.getLines().toVector
    var instruction = data.map(_.split(" "))
    source.close()

    println(run(instruction)._1)

    var success = false
    var acc = 0
    var ind = 0
    for (ins <- instruction) {
        if (!success) {
            if (ins(0) == "jmp") instruction(ind)(0) = "nop"
            else if (ins(0) == "nop") instruction(ind)(0) = "jmp"
            val result = run(instruction)
            if (result._2) {
                success = true
                acc = result._1
            } else {
                if (ins(0) == "jmp") instruction(ind)(0) = "nop"
                else if (ins(0) == "nop") instruction(ind)(0) = "jmp"
            }
        }
        ind += 1
    }
    println(acc)

    def run(list: Vector[Array[String]]): (Int, Boolean) = {
        var accumulator = 0
        var running = true
        var index = 0
        var success = false
        var history: Set[Int] = Set()

        while (running) {
            val line = list(index)
            history += index
            line match {
                case _ if line(0) == "nop" =>
                    index += 1
                case line if line(0) == "acc" =>
                    accumulator += line(1).toInt
                    index += 1
                case line if line(0) == "jmp" =>
                    index += line(1).toInt
                case _ if line(0) == "stop" => running = false
            }

            if (history.contains(index)) running = false
            if (index >= list.length) {
                running = false
                success = true
            }

        }

        (accumulator, success)
    }



}
