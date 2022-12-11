fun main() {
    val input = "input/day08.txt".readLines()
    println("Day 08 - A result = ${Day08.solveA(input)}")
    println("Day 08 - B result = ${Day08.solveB(input)}")
}

object Day08 {
    // For each tree:
    // If at edge; visible = true
    // Elif any tree to left, right, bottom or top < height; visible = true
    // Else; visible = false
    fun solveA(input: List<String>): Int {
        val trees = input.map { it.map { height -> height.toString().toInt() } }

        var visibleCount = 0
        for ((rowIndex, rowOfTrees) in trees.withIndex()) {
            for ((colIndex, tree) in rowOfTrees.withIndex()) {
                if (rowIndex == 0 || rowIndex == trees.size - 1 || colIndex == 0 || colIndex == trees.first().size - 1) {
                    visibleCount += 1
                } else {
                    val treesToLeft = rowOfTrees.take(colIndex)
                    val treesToRight = rowOfTrees.takeLast(rowOfTrees.size - colIndex - 1)
                    val treesToTop = trees.take(rowIndex).map { it[colIndex] }
                    val treesToBottom = trees.takeLast(trees.size - rowIndex - 1).map { it[colIndex] }

                    val isVisible = listOf(treesToLeft, treesToRight, treesToTop, treesToBottom)
                        .any { it.all { otherTree -> otherTree < tree } }
                    if (isVisible) {
                        visibleCount += 1
                    }
                }
            }
        }

        return visibleCount
    }

    fun solveB(input: List<String>): Int = 0
}
