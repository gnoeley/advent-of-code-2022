import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {

    // Rope has head (H) and tail (T) which must always be touching
    // Tail follows head in orthogonal direction e.g. # T H # -> # T # H -> # # T H
    // Tail follows diagonally in the direction head moved to be adjacent e.g.
    //  # # #       # # #       # H #       # H #
    //  # # #   ->  # H #   ->  # # #   ->  # T #
    //  T H #       T # #       T # #       # # #
    // tail x or y can never be more than 1 away from head x or y
    // tail moves in the direction of the difference greater than 1
    @Test
    fun solveDay09A() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().lines()

        val actual = Day09.solveA(input)

        assertEquals(13, actual)
    }

    @Test
    fun solveDay09B() {
        val input = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent().lines()

        val actual = Day09.solveB(input)

        assertEquals(36, actual)
    }
}