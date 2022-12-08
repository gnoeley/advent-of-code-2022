fun main() {
    val input = "input/day03-b.txt".readLines()
//    println("Day 03 - A result = ${Day03.solveA(input)}")
    println("Day 02 - B result = ${Day03.solveB(input)}")
}

object Day03 {

    fun solveA(input: List<String>): Int = input
        .map { backpack -> backpack.chunked(backpack.length / 2) }
        .map { (firstPocket, secondPocket) -> firstPocket.first { it in secondPocket } }
        .sumOf { it.toItemPriority }

    fun solveB(input: List<String>): Int = input
        .chunked(3)
        .sumOf { (first, second, third) -> first.first { it in second && it in third }.toItemPriority }

    private val Char.toItemPriority
        get() =
            (if (isLowerCase()) this - 96 else this - 38).code
}