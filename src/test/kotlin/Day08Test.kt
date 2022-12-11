import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {

    // Each digit is the height of a tree
    // 0 = shortest, 9 = tallest
    @Test
    fun solveDay08A() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().lines()

        val actual = Day08.solveA(input)

        assertEquals(21, actual)
    }

    @Test
    fun solveDay08B() {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().lines()

        val actual = Day08.solveB(input)

        assertEquals(8, actual)
    }
}