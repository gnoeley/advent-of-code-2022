import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {

    // Each section in camp has an ID
    // Each elf is assigned a range of sections
    @Test
    fun solveDay04A() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().lines()

        val actual = Day04.solveA(input)

        assertEquals(2, actual)
    }
}