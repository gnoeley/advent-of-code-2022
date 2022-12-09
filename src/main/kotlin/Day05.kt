fun main() {
    val input = "input/day05-a.txt".readLines()
//    println("Day 05 - A result = ${Day05.solveA(input)}")
    println("Day 05 - B result = ${Day05.solveB(input)}")
}

object Day05 {
    fun solveA(input: List<String>): String = toContainerStacks(input).apply {
        toMoves(input).forEach { (move, from, to) ->
            repeat(move) { this[to - 1].add(this[from - 1].removeLast()) }
        }
    }.joinToString("") { it.last() }

    fun solveB(input: List<String>): String = toContainerStacks(input).apply {
        toMoves(input).forEach { (move, from, to) ->
            val toMove = (1..move).map { this[from - 1].removeLast() }.reversed()
            this[to - 1].addAll(toMove)
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
                .map(String::toInt)
        }
}
