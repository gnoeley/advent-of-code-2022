fun main() {
    val input = "input/day04-a.txt".readLines()
    println("Day 04 - A result = ${Day04.solveA(input)}")
//    println("Day 04 - B result = ${Day04.solveB(input)}")
}

object Day04 {
    fun solveA(input: List<String>): Int = input
        .map { it.toSectionPair() }
        .count { (first, second) -> first.all { second.contains(it) } || second.all { first.contains(it) } }

    private fun String.toSectionPair() =
        split(",").let { (first, second) -> first.toRange() to second.toRange() }

    private fun String.toRange() = split("-").map { it.toInt() }.let { (start, end) -> start..end }
}
