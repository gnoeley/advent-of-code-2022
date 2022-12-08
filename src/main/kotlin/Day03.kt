fun main() {
    val input = "input/day03-b.txt".readLines()
//    println("Day 03 - A result = ${Day03.solveA(input)}")
    println("Day 02 - B result = ${Day03.solveB(input)}")
}

object Day03 {

    fun solveA(input: List<String>): Int = input
        .map { it.subSequence(0, it.length / 2) to it.subSequence(it.length / 2, it.length) }
        .map { rucksack -> rucksack.first.first { rucksack.second.contains(it) } }
        .sumOf { it.toItemPriority }

    private val Char.toItemPriority get() =
        (if (isLowerCase()) this - 96 else this - 38).code

    fun solveB(input: List<String>): Int = input
        .foldRight(mutableListOf(BackpackGroup())) { backpack, acc ->
            if (!acc.last().add(backpack)) {
                val newGroup = BackpackGroup()
                newGroup.add(backpack)
                acc.add(newGroup)
            }
            acc
        }
        .sumOf { it.findBadge().toItemPriority }

    class BackpackGroup {
        private val backpacks = mutableListOf<String>()
        fun add(backpack: String): Boolean = if (backpacks.size == 3) false else backpacks.add(backpack)
        fun findBadge(): Char = backpacks
            .flatMap { it.toSet() }
            .groupBy { it }
            .mapValues { it.value.size }
            .filterValues { it == 3 }
            .keys
            .first()
    }
}