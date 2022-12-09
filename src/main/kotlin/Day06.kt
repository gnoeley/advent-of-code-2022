fun main() {
    val input = "input/day06-a.txt".readLines()
    println("Day 06 - A result = ${Day06.solveA(input)}")
    println("Day 06 - B result = ${Day06.solveB(input)}")
}

object Day06 {
    fun solveA(input: List<String>): Int = input.first().let {
        val marker = it.windowed(4).first { it.groupingBy { it }.eachCount().values.all { it == 1 } }
        it.indexOf(marker) + 4
    }

    fun solveB(input: List<String>): Int = input.first().let {
        val marker = it.windowed(14).first { it.groupingBy { it }.eachCount().values.all { it == 1 } }
        it.indexOf(marker) + 14
    }
}
