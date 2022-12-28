import Day12.Location.Companion.location
import kotlin.math.absoluteValue

fun main() {
    val input = "input/day12.txt".readLines()
    println("Day 12 - A result = ${Day12.solveA(input)}")
//    println("Day 12 - B result = ${Day12.solveB(input)}")
}

object Day12 {
    fun solveA(input: List<String>): Int {
        // Visit node
        // Find all "exits"
        // Follow each exist until either:
        // - Exit is found
        // - Path loops
        // - Return path legnth to exit _or_ -1 for dead end


        // Create Locations
        // Relate locations to each-other (graph!)
        // Walk locations
        // Return result
        return input
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, height ->
                    location(x to y, height)
                }
            }
            .run {
                onEach { location ->
                    location.otherLocations.addAll(filter { location.canVisit(it) })
                }
            }
            .find { it.type == Location.Type.START }!!
            .findEnd()
            .count() - 1
    }

    class Location private constructor(
        val position: Pair<Int, Int>,
        val height: Int,
        val type: Type,
        val otherLocations: MutableSet<Location> = mutableSetOf()
    ) {
        enum class Type {
            START,
            END,
            EMPTY
        }

        companion object {
            fun location(position: Pair<Int, Int>, height: Char) = Location(
                position,
                when (height) {
                    'S' -> 1
                    'E' -> 26
                    else -> height.code - 96
                },
                when (height) {
                    'S' -> Type.START
                    'E' -> Type.END
                    else -> Type.EMPTY
                }
            )
        }

        fun canVisit(other: Location): Boolean = (other.height - height) <= 1 && isNextTo(other)

        fun findEnd(): List<Location> = findEnd(listOf())

        private fun findEnd(path: List<Location>): List<Location> =
            if (path.find { it === this } != null) {
                listOf()
            } else if (type == Type.END) {
                path + this
            } else {
                val nextPath = path + this
                otherLocations
                    .map { it.findEnd(nextPath) }
                    .filter { possiblePath -> possiblePath.any { it.type == Type.END } }
                    .minByOrNull { it.size }
                    ?: listOf()
            }

        private fun isNextTo(other: Location) =
            (other.position.first - position.first).absoluteValue +
                    (other.position.second - position.second).absoluteValue == 1
    }
}
