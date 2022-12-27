import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
    val input = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
        """.trimIndent().lines()

    // Items (worry score)
    // Operation:
    //  - old or number
    //  - operator
    // Test:
    //  - Divisible by 'x'
    // Throw:
    // - true to monkey 'y'
    // - false to monkey 'y'
    //
    // 1: monkey
    // 2: items
    // 3: operation
    // 4: test condition
    // 5: true throw
    // 6: false throw
    @Test
    fun solveDay11A() {
        val actual = Day11.solveA(input)
        assertEquals(10605U, actual)
    }

    // Numbers tooooo big...
    /// Maths; is truncating to "hundreds" units viable after each test
    @Test
    fun solvedDay11B() {
        val actual = Day11.solveB(input)
        assertEquals(2713310158L, actual)
    }
}