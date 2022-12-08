fun main() {
    val input = "input/day03-a.txt".readLines()
    println("Day 03 - A result = ${Day03.solveA(input)}")
//    println("Day 02 - B result = ${Day02.solveB(input)}")
}

object Day03 {

    fun solveA(input: List<String>): Int = input
        .map { it.subSequence(0, it.length/2) to it.subSequence(it.length/2, it.length) }
        .map { rucksack -> rucksack.first.first { rucksack.second.contains(it) } }
        .map { item -> if (item.isLowerCase()) item - 96 else item - 38 }
        .sumOf { it.code }
}