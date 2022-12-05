fun main() {
    val input = "input/day02-a.txt".readLines()
    println("Day 02 - A result = ${Day02.solveA(input)}")
    println("Day 02 - B result = ${Day02.solveB(input)}")
}

class Day02 {
    companion object {

        private const val P_ROCK = "X"
        private const val P_PAPER = "Y"
        private const val P_SCISSOR = "Z"

        private const val P_LOOSE = "X"
        private const val P_DRAW = "Y"
        private const val P_WIN = "Z"

        private const val O_ROCK = "A"
        private const val O_PAPER = "B"
        private const val O_SCISSOR = "C"

        private val shapeScore = mapOf(
            P_ROCK to 1,
            P_PAPER to 2,
            P_SCISSOR to 3,
        )

        private fun outcomeScore(opponent: String, player: String) = when {
            opponent == O_ROCK && player == P_SCISSOR ||
                    opponent == O_PAPER && player == P_ROCK ||
                    opponent == O_SCISSOR && player == P_PAPER -> 0
            opponent == O_ROCK && player == P_PAPER ||
                    opponent == O_PAPER && player == P_SCISSOR ||
                    opponent == O_SCISSOR && player == P_ROCK -> 6
            else -> 3
        }

        private fun chooseShape(opponent: String, outcome: String) = when {
            opponent == O_ROCK && outcome == P_LOOSE -> P_SCISSOR
            opponent == O_ROCK && outcome == P_DRAW -> P_ROCK
            opponent == O_ROCK && outcome == P_WIN -> P_PAPER

            opponent == O_PAPER && outcome == P_LOOSE -> P_ROCK
            opponent == O_PAPER && outcome == P_DRAW -> P_PAPER
            opponent == O_PAPER && outcome == P_WIN -> P_SCISSOR

            opponent == O_SCISSOR && outcome == P_LOOSE -> P_PAPER
            opponent == O_SCISSOR && outcome == P_DRAW -> P_SCISSOR
            opponent == O_SCISSOR && outcome == P_WIN -> P_ROCK

            else -> null
        }

        fun solveA(input: List<String>) = input.sumOf {
            it.split(" ").let { round ->
                shapeScore[round[1]]!! + outcomeScore(round[0], round[1])
            }
        }

        fun solveB(input: List<String>) = input.sumOf {
            it.split(" ").let { round ->
                val shape = chooseShape(round[0], round[1])!!
                shapeScore[shape]!! + outcomeScore(round[0], shape)
            }
        }
    }
}