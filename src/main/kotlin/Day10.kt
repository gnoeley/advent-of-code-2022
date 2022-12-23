fun main() {
    val input = "input/day10.txt".readLines()
    println("Day 10 - A result = ${Day10.solveA(input)}")
    println("Day 10 - B result = ${Day10.solveB(input)}")
}


object Day10 {
    fun solveA(input: List<String>): Int = input
        .fold(listOf(State(1, 1))) { acc, command ->
            val previousCycle = acc.last()
            val parsed = command.split(" ")
            acc + when (parsed.size) {
                1 -> listOf(previousCycle.copy(cycle = previousCycle.cycle + 1))
                else -> listOf(
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

    fun solveB(input: List<String>): String = input
        .fold(listOf(State(1, 1))) { acc, command ->
            val previousCycle = acc.last()
            val parsed = command.split(" ")
            acc + when (parsed.size) {
                1 -> listOf(previousCycle.copy(cycle = previousCycle.cycle + 1))
                else -> listOf(
                    previousCycle.copy(cycle = previousCycle.cycle + 1),
                    previousCycle.copy(
                        registerX = previousCycle.registerX + parsed[1].toInt(),
                        cycle = previousCycle.cycle + 2
                    )
                )
            }
        }
        .windowed(step = 40, size = 40)
        .joinToString("\n", prefix = "\n") {
            it.joinToString("") { state -> if ((state.cycle - 1)  % 40 in (state.registerX - 1..state.registerX + 1)) "#" else "." }
        }

    data class State(val registerX: Int, val cycle: Int)
}