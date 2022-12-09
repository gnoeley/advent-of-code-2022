import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun solveDay06A() {
        val input = """
            mjqjpqmgbljsphdztnvjfqwrcgsmlb
        """.trimIndent().lines()

        val actual = Day06.solveA(input)

        assertEquals(7, actual)
    }

    @Test
    fun solveDay06B() {
        val input = """
            bvwbjplbgvbhsrlpgdmjqwftvncz
        """.trimIndent().lines()

        val actual = Day06.solveB(input)

        assertEquals(23, actual)
    }
}