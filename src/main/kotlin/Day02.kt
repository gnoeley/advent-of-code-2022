import Day02.Companion.Shape.Companion.fromStrategy

fun main() {
    val input = "input/day02-a.txt".readLines()
    println("Day 02 - A result = ${Day02.solveA(input)}")
    println("Day 02 - B result = ${Day02.solveB(input)}")
}

class Day02 {
    companion object {

        private enum class Shape(val score: Int) {
            ROCK(1),
            PAPER(2),
            SCISSORS(3);

            companion object {
                fun fromStrategy(input: String) = when(input) {
                    "A", "X" -> ROCK
                    "B", "Y" -> PAPER
                    "C", "Z" -> SCISSORS
                    else -> throw RuntimeException("Invalid shape: $input")
                }
            }

            fun forStrategyOutcome(input: String) = when(input) {
                "X" -> toLoose()
                "Y" -> toDraw()
                "Z" -> toWin()
                else -> throw RuntimeException("Invalid outcome: $input")
            }

            fun outcomeScore(other: Shape) = when(other) {
                toWin() -> 0
                toDraw() -> 3
                else -> 6
            }

            private fun toWin() = values()[(ordinal + 1) % 3]
            private fun toDraw() = this
            private fun toLoose() = values()[(ordinal + 2) % 3]
        }

        fun solveA(input: List<String>) = input.sumOf {
            it.split(" ").let { strategy ->
                val opponentShape = fromStrategy(strategy[0])
                val playerShape = fromStrategy(strategy[1])
                playerShape.score + playerShape.outcomeScore(opponentShape)
            }
        }

        fun solveB(input: List<String>) = input.sumOf {
            it.split(" ").let { strategy ->
                val opponentShape = fromStrategy(strategy[0])
                val playerShape = opponentShape.forStrategyOutcome(strategy[1])
                playerShape.score + playerShape.outcomeScore(opponentShape)
            }
        }
    }
}