import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    // Heightmap where a=lowest,z=highest
    // current location = S (a)
    // best signal = E (z)
    // want to get to the best signal location taking fewest steps
    // can only move u,d,l,r to a square one higher than current location
    //
    // Can be represented by a graph (with  cycles)
    // - Each location is a node (N) with all possible exits as edges to other nodes
    // Special nodes for Start and End
    // Could be a breadth-first-search
    @Test
    fun solveDay12A() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent().lines()

        val actual = Day12.solveA(input)

        assertEquals(31, actual)
    }
}