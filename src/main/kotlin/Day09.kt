import java.lang.Thread.sleep
import kotlin.math.abs

fun main() {
    val input = "input/day09.txt".readLines()
    println("Day 09 - A result = ${Day09.solveA(input)}")
    println("Day 09 - B result = ${Day09.solveB(input)}")
}

object Day09 {
    fun solveA(input: List<String>): Int {
        val moves = parseMoves(input)
        val rope = mutableListOf(0 to 0, 0 to 0)
        printRope(rope, emptySet())
        return calculateVisitedLocations(rope, moves)
    }

    fun solveB(input: List<String>): Int {
        val moves = parseMoves(input)
        val rope = (1 .. 10).map { 0 to 0 }.toMutableList()
        return calculateVisitedLocations(rope, moves)
    }

    private fun calculateVisitedLocations(
        rope: MutableList<Pair<Int, Int>>,
        moves: List<Pair<Int, Int>>
    ): Int {
        val visited = mutableSetOf(rope.last().copy())

        for ((x, y) in moves) {
            val head = rope[0]
            rope[0] = head.first + x to head.second + y

            for (i in (0..rope.size - 2)) {
                val nextTail = nextTail(rope[i], rope[i + 1])
                rope[i + 1] = nextTail
            }

            visited.add(rope.last().copy())

            printRope(rope, visited)
        }

        return visited.size
    }

    private fun nextTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
        val diffX = tail.first - head.first // if > abs(2); move 1 in that direction
        val diffY = tail.second - head.second
        return if (abs(diffX) > 1 || abs(diffY) > 1) {
            tail.first + (if (diffX != 0) (diffX / abs(diffX) * -1) else 0) to tail.second + (if (diffY != 0) (diffY / abs(diffY) * -1) else 0)
        } else {
            tail
        }
    }

    private fun parseMoves(input: List<String>) = input.flatMap {
        it.split(" ")
            .let { (direction, times) ->
                (1..times.toInt()).map {
                    when (direction) {
                        "R" -> 1 to 0
                        "L" -> -1 to 0
                        "U" -> 0 to 1
                        "D" -> 0 to -1
                        else -> throw RuntimeException("Invalid direction: $direction")
                    }
                }
            }
    }

    private fun printRope(rope: List<Pair<Int, Int>>, visited: Set<Pair<Int, Int>>) {
        val xOffset = 15
        val yOffset = 5
        val result = StringBuilder()
        for (y in (21 downTo 0)) {
            for (x in (0 .. 32)) {
                var output = if (x - xOffset to y - yOffset == 0 to 0)  // starting pos
                    "s"
                else if (visited.contains(x - xOffset to y - yOffset))  // visited location
                    "#"
                else                                                    // unvisited
                    "."

                // Check for current rope pos
                for ((i, pos) in rope.withIndex()) {
                    if (pos.first + xOffset to pos.second + yOffset == x to y) {
                        output = if (i == 0) "H" else if (i == rope.size - 1) "T" else i.toString()
                        break
                    }
                }

                result.append("$output ")
            }
            result.append("\n")
        }

        print((23 downTo 0).fold(StringBuilder()) { acc, _ -> acc.append("\n") }.toString())
        sleep(100)
        println(result.toString())
        sleep(300)
    }
}
