import org.scalatest.funsuite.AnyFunSuite
import AoC4._

class AoC4Test extends AnyFunSuite{

    test("test checkField function") {
        val testString = Array("eyr", "2028")
        assert(checkField(testString))
    }

}
