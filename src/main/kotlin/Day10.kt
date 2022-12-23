fun main() {
    val input = "input/day10.txt".readLines()
    println("Day 10 - A result = ${Day10.solveA(input)}")
//    println("Day 10 - B result = ${Day10.solveB(input)}")
}


object Day10 {
    fun solveA(input: List<String>): Int = input
        .fold(setOf(State(1, 1))) { acc, command ->
            val previousCycle = acc.last()
            val parsed = command.split(" ")
            acc + when (parsed.size) {
                1 -> setOf(previousCycle.copy(cycle = previousCycle.cycle + 1))
                else -> setOf(
                    previousCycle.copy(cycle = previousCycle.cycle + 1),
                    previousCycle.copy(
                        registerX = previousCycle.registerX + parsed[1].toInt(),
                        cycle = previousCycle.cycle + 2
                    )
                )
            }
        }
        .filter { it.cycle in (20..220 step 40) }
        .sumOf { it.registerX * it.cycle }

    data class State(val registerX: Int, val cycle: Int)
}