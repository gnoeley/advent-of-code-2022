import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    // Split half for each rucksack compartment 1 and 2
    // a-z -> priorities 1-26
    // A-Z -> priorities 27-52
    // Sum of priorities
    @Test
    fun solveDay03A() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent().lines()

        val actual = Day03.solveA(input)

        assertEquals(157, actual)
    }

    // Badge "type" is common between all three elves
    @Test
    fun solveDay03B() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent().lines()

        val actual = Day03.solveB(input)

        assertEquals(70, actual)
    }
}