import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day01Test {
    @Test
    fun solveDay01A_topSummed() {
        val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent().lines()

        val actual = Day01.solveA(input)

        assertEquals(24000, actual)
    }

    @Test
    fun solveDay01B_topThreeTotalsSummed() {
        val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent().lines()

        val actual = Day01.solveB(input)

        assertEquals(45000, actual)
    }
}