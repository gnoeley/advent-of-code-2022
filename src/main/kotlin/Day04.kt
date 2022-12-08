fun main() {
    val input = "input/day04-b.txt".readLines()
//    println("Day 04 - A result = ${Day04.solveA(input)}")
    println("Day 04 - B result = ${Day04.solveB(input)}")
}

object Day04 {
    fun solveA(input: List<String>): Int = input
        .map { it.toSectionPair() }
        .count { (first, second) -> first.entirelyOverlap(second) }

    fun solveB(input: List<String>): Int = input
        .map { it.toSectionPair() }
        .count { (first, second) -> first.anyOverlap(second) }

    private fun IntRange.entirelyOverlap(second: IntRange) =
        all { second.contains(it) } || second.all { contains(it) }

    private fun IntRange.anyOverlap(second: IntRange) =
        any { second.contains(it) } || second.any { contains(it) }

    private fun String.toSectionPair() =
        split(",").let { (first, second) -> first.toRange() to second.toRange() }

    private fun String.toRange() = split("-").map { it.toInt() }.let { (start, end) -> start..end }
}
