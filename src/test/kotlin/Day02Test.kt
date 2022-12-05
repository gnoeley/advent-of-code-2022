import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// First column (opponent plays)
//  A (Rock)    /   B (Paper)   /   C (Scissors)
// Score per round
//  rock = 1    /   paper = 2   /   scissors = 3
//  loose = 0   /   draw = 3    /   win = 6
class Day02Test {

    // Second column (you play)
    //  X (rock)  /  Y (paper)  /  Z (scissors)
    @Test
    fun solveA() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent().lines()

        val actual = Day02.solveA(input)

        assertEquals(15, actual)
    }

    // Second column (you play)
    //  X (loose)  /  Y (draw)  /  Z (win)
    @Test
    fun solveB() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent().lines()

        val actual = Day02.solveB(input)

        assertEquals(12, actual)
    }
}