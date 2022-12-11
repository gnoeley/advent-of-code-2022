fun main() {
    val input = "input/day08.txt".readLines()
    println("Day 08 - A result = ${Day08.solveA(input)}")
    println("Day 08 - B result = ${Day08.solveB(input)}")
}

object Day08 {
    fun solveA(input: List<String>): Int = input.map { it.map { height -> height.toString().toInt() } }
        .run {
            foldIndexed(0) { rowIndex, rowAcc, rowOfTrees ->
                rowAcc + rowOfTrees.foldIndexed(0) { colIndex, colAcc, tree ->
                    colAcc + calculateVisibility(this, rowIndex, colIndex, tree)
                }
            }
        }

    private fun calculateVisibility(
        trees: List<List<Int>>,
        rowIndex: Int,
        colIndex: Int,
        tree: Int
    ) = if (treeIsOnPerimeter(trees, rowIndex, colIndex)) {
        1
    } else {
        otherTrees(trees, rowIndex, colIndex)
            .any { it.all { otherTree -> otherTree < tree } }
            .let { if (it) 1 else 0 }
    }

    private fun treeIsOnPerimeter(
        trees: List<List<Int>>,
        row: Int,
        col: Int
    ) = row == 0 || row == trees.size - 1 || col == 0 || col == trees.first().size - 1

    private fun otherTrees(trees: List<List<Int>>, col: Int, row: Int) = trees.run {
        val treesToLeft = get(row).take(col)
        val treesToRight = get(row).takeLast(get(row).size - col - 1)
        val treesToTop = take(row).map { it[col] }
        val treesToBottom = takeLast(size - row - 1).map { it[col] }
        listOf(treesToLeft, treesToRight, treesToTop, treesToBottom)
    }

    fun solveB(input: List<String>): Int = 0
}
