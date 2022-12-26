import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {
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

        val actual = Day11.solveA(input)

        assertEquals(10605, actual)
    }
}