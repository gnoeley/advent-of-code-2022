import kotlin.io.path.toPath

fun main() {
    val input = "input/day01-a.txt".readLines()
    println("Day 01 - A result = ${Day01.solveA(input)}")
    println("Day 01 - B result = ${Day01.solveB(input)}")
}

class Day01 {
    companion object {
        fun solveA(input: List<String>) = input
            .joinToString(separator = "-")
            .split("--")
            .maxOfOrNull {
                it.split("-").sumOf { num -> num.toInt() }
            }

        fun solveB(input: List<String>) = input
            .joinToString(separator = "-")
            .split("--")
            .map {
                it.split("-").sumOf { num -> num.toInt() }
            }
            .sortedDescending()
            .take(3)
            .sum()

    }
}
