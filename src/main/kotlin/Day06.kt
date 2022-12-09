fun main() {
    val input = "input/day06-a.txt".readLines()
    println("Day 06 - A result = ${Day06.solveA(input)}")
    println("Day 06 - B result = ${Day06.solveB(input)}")
}

object Day06 {
    fun solveA(input: List<String>): Int = indexOfMarker(input.first(), 4)

    fun solveB(input: List<String>): Int = indexOfMarker(input.first(), 14)

    private fun indexOfMarker(message: String, markerLength: Int) =
        message.windowedSequence(markerLength)
            .withIndex()
            .first { (_, value) -> value.toSet().size == markerLength }
            .let { (index) -> index + markerLength }
}
