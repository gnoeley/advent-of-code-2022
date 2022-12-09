fun main() {
    val input = "input/day05-a.txt".readLines()
//    println("Day 05 - A result = ${Day05.solveA(input)}")
    println("Day 05 - B result = ${Day05.solveB(input)}")
}

object Day05 {
    fun solveA(input: List<String>): String = toContainerStacks(input).apply {
        toMoves(input).forEach { (move, from, to) ->
            repeat(move) { get(to).add(get(from).removeLast()) }
        }
    }.joinToString("") { it.last() }

    fun solveB(input: List<String>): String = toContainerStacks(input).apply {
        toMoves(input).forEach { (move, from, to) ->
            get(to).addAll((1..move).map { get(from).removeLast() }.reversed())
        }
    }.joinToString("") { it.last() }

    private fun toContainerStacks(manifest: List<String>) = manifest
        .takeWhile { it.contains("[A-Z]".toRegex()) }
        .flatMap {
            it.chunked(4) { crate ->
                crate.replace("[^A-Z]".toRegex(), "")
            }.withIndex()
        }
        .groupBy({ it.index }, { it.value })
        .toSortedMap()
        .mapValues { (_, stack) -> stack.filter { it.isNotBlank() }.reversed().toMutableList() }
        .values
        .toList()

    private fun toMoves(manifest: List<String>) = manifest
        .takeLastWhile { it.contains("move") }
        .map {
            "move ([0-9]+) from ([0-9]+) to ([0-9]+)".toRegex()
                .matchEntire(it)!!.groupValues
                .takeLast(3)
                .let { (moves, from, to) -> listOf(moves.toInt(), from.toInt() - 1, to.toInt() - 1)}
        }
}
