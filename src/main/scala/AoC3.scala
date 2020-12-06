import scala.io.Source

object AoC3 extends App{

    // Liian pieni tulos...

    val source = Source.fromFile("res/aoc3.txt")
    val lines = source.getLines().toArray
    source.close()


    var pos = 0
    val puita = new Array[Int](5)

    // eka reitti (right 1, down 1)
    for (rivi <- lines) {
        if (onkoPuu(rivi, pos)) puita(0) += 1
        pos += 1
        if (pos >= rivi.length) pos -= rivi.length
    }
    println(s"reitti 1: ${puita(0)}")
    pos = 0

    // toka reitti (right 3, down 1)(part 1)
    for (rivi <- lines) {
        if (onkoPuu(rivi, pos)) puita(1) += 1
        pos += 3
        if (pos >= rivi.length) pos -= rivi.length
    }
    println(s"reitti 2: ${puita(1)}")
    pos = 0

    // kolmas reitti (right 5, down 1)
    for (rivi <- lines) {
        if (onkoPuu(rivi, pos)) puita(2) += 1
        pos += 5
        if (pos >= rivi.length) pos -= rivi.length
    }
    println(s"reitti 3: ${puita(2)}")
    pos = 0

    // neljäs reitti (right 7, down 1)
    for (rivi <- lines) {
        if (onkoPuu(rivi, pos)) puita(3) += 1
        pos += 7
        if (pos >= rivi.length) pos -= rivi.length
    }
    println(s"reitti 4: ${puita(3)}")
    pos = 0

    // viides reitti (right 1, down 2)
    for (rivi <- lines) {
        if ((lines.indexOf(rivi) % 2) == 0) {
            if (onkoPuu(rivi, pos)) puita(4) += 1
            pos += 1
            if (pos >= rivi.length) pos -= rivi.length
        }
    }
    println(s"reitti 5: ${puita(4)}")

    var kertoma: Long = 1
    for (x <- puita) {kertoma *= x;println(kertoma)}
    println(kertoma)

    def onkoPuu(rivi: String, sijainti: Int): Boolean = if (rivi.charAt(sijainti) == '#') true else false

}

