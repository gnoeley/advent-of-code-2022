import java.lang.Integer.max

fun main() {
    val input = "input/day08.txt".readLines()
    println("Day 08 - A result = ${Day08.solveA(input)}")
    println("Day 08 - B result = ${Day08.solveB(input)}")
}

object Day08 {
    fun solveA(input: List<String>): Int =
        input.map { it.map { height -> height.toString().toInt() } }
            .run {
                foldIndexed(0) { row, acc, rowOfTrees ->
                    acc + List(rowOfTrees.size) { col -> calculateVisibility(this, row, col) }.sum()
                }
            }

    fun solveB(input: List<String>): Int =
        input.map { it.map { height -> height.toString().toInt() } }
            .run {
                foldIndexed(0) { row, acc, rowOfTrees ->
                    max(
                        acc,
                        List(rowOfTrees.size) { col ->
                            calculateScenicScore(
                                this,
                                row,
                                col
                            )
                        }.maxOrNull()!!
                    )
                }
            }

    private fun calculateScenicScore(trees: List<List<Int>>, row: Int, col: Int) =
        otherTrees(trees, col, row).fold(1) { acc, otherTrees -> acc * otherTrees.countVisible(trees[row][col]) }

    private fun List<Int>.countVisible(tree: Int): Int {
        var result = 0
        for (otherTree in this) {
            if (otherTree >= tree) {
                result++
                break
            } else {
                result++
            }
        }
        return result
    }

    private fun calculateVisibility(trees: List<List<Int>>, col: Int, row: Int) =
        otherTrees(trees, col, row)
            .any { it.isEmpty() || it.all { otherTree -> otherTree < trees[row][col] } }
            .let { if (it) 1 else 0 }

    private fun otherTrees(trees: List<List<Int>>, col: Int, row: Int) = trees.run {
        val treesToLeft = get(row).take(col).reversed()
        val treesToRight = get(row).takeLast(get(row).size - col - 1)
        val treesToTop = take(row).map { it[col] }.reversed()
        val treesToBottom = takeLast(size - row - 1).map { it[col] }
        listOf(treesToLeft, treesToRight, treesToTop, treesToBottom)
    }
}
