fun main() {
    val input = "input/day05-a.txt".readLines()
    println("Day 05 - A result = ${Day05.solveA(input)}")
//    println("Day 05 - B result = ${Day05.solveB(input)}")
}

object Day05 {
    fun solveA(input: List<String>): String {
        val stacks = input
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

        input
            .takeLastWhile { it.contains("move") }
            .map { "move ([0-9]+) from ([0-9]+) to ([0-9]+)".toRegex().matchEntire(it)!!.groupValues.takeLast(3).map(String::toInt) }
            .forEach { (move, from, to) ->
                repeat(move) { stacks[to - 1].add(stacks[from - 1].removeLast()) }
            }

        return stacks.joinToString("") { it.last() }
    }
}
