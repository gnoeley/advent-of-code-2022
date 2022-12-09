import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {

    @Test
    fun solveDay05A() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent().lines()

        val actual = Day05.solveA(input)

        assertEquals("CMZ", actual)
    }

    @Test
    fun solveDay05B() {
        val input = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent().lines()

        val actual = Day05.solveB(input)

        assertEquals("MCD", actual)
    }
}