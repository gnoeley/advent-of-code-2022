import kotlin.math.abs

fun main() {
    val input = "input/day09.txt".readLines()
    println("Day 09 - A result = ${Day09.solveA(input)}")
//    println("Day 09 - B result = ${Day09.solveB(input)}")
}

object Day09 {
    fun solveA(input: List<String>): Int {
        var head = 0 to 0
        var tail = 0 to 0
        val visited = mutableSetOf(tail.copy())

        val moves = input.flatMap {
            it.split(" ")
                .let { (direction, times) ->
                    (1..times.toInt()).map {
                        when(direction) {
                            "R" -> 1 to 0
                            "L" -> -1 to 0
                            "U" -> 0 to 1
                            "D" -> 0 to -1
                            else -> throw RuntimeException("Invalid direction: $direction")
                        }
                    }
                }
        }

        for ((x, y) in moves) {
            head = head.first + x to head.second + y
            val diffX = tail.first - head.first // if > abs(2); move 1 in that direction
            val diffY = tail.second - head.second


            if (abs(diffX) > 1 || abs(diffY) > 1) {
                tail = tail.first + (if (diffX != 0) (diffX / abs(diffX) * -1) else 0) to tail.second + (if (diffY != 0) (diffY / abs(diffY) * -1) else 0)
                visited.add(tail.copy())
            }
        }
        return visited.size
    }
}
