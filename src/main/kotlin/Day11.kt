fun main() {
    val input = "input/day11.txt".readLines()
    println("Day 11 - A result = ${Day11.solveA(input)}")
    println("Day 11 - B result = ${Day11.solveB(input)}")
}

object Day11 {
    fun solveA(input: List<String>): UInt = input
        .joinToString("\n")
        .split("\n\n")
        .map { Monkey.from(it, true) }
        .let { monkeys ->
            monkeys.forEachIndexed { index, monkey ->
                monkeys.forEach { other -> other.monkeyDiscovered(index, monkey) }
            }
            repeat((1..20).count()) {
                monkeys.forEach { it.doRound() }
            }
            monkeys
        }
        .sortedByDescending { it.inspections }
        .take(2)
        .let { (first, second) -> first.inspections * second.inspections }

    fun solveB(input: List<String>): Long = input
        .joinToString("\n")
        .split("\n\n")
        .map { Monkey.from(it, false) }
        .let { monkeys ->
            monkeys.forEachIndexed { index, monkey ->
                monkeys.forEach { other -> other.monkeyDiscovered(index, monkey) }
            }
            repeat((1..10000).count()) {
                monkeys.forEach {
                    it.doRound()
                }
            }
            monkeys
        }
        .sortedByDescending { it.inspections }
        .take(2)
        .let { (first, second) -> first.inspections.toLong() * second.inspections.toLong() }
}

class Monkey private constructor(
    private val items: MutableList<Item>,
    private val inspectFun: (worry: Long) -> Long,
    private val testCondition: Long,
    private val throwOnTrue: Int,
    private val throwOnFalse: Int
) {

    var inspections: UInt = 0U

    private val otherMonkeys: MutableMap<Int, Monkey> = mutableMapOf()
    private val commonTestConditionDivisor by lazy {
        otherMonkeys.values.map { it.testCondition }.reduceRight(Long::times).also { println(it) }
    }
    companion object {
        fun from(input: String, worryDecreases: Boolean): Monkey {
            val lines = input.split("\n").map { it.trim() }
            val itemWorryScores = Regex("(\\d+)+").findAll(lines[1]).map { it.value.toLong() }.toList()
            val testCondition = Regex("(\\d+)+").find(lines[3])!!.value.toLong()
            val monkeyThrowOnTrue = Regex("(\\d+)+").find(lines[4])!!.value.toInt()
            val monkeyThrowOnFalse = Regex("(\\d+)+").find(lines[5])!!.value.toInt()

            return Monkey(
                itemWorryScores.map { Item(it) }.toMutableList(),
                parseUpdateFunction(lines[2], worryDecreases),
                testCondition,
                monkeyThrowOnTrue,
                monkeyThrowOnFalse
            )
        }

        private fun parseUpdateFunction(input: String, worryDecreases: Boolean) =
            Regex("Operation: new = (old|\\d+) ([+\\-*/]) (old|\\d+)").find(input)!!.groupValues
                .let { (_, firstOperand, operator, secondOperand) ->
                    { worry: Long ->
                        val parsedFirstOperand = when (firstOperand) {
                            "old" -> worry
                            else -> firstOperand.toLong()
                        }

                        val parsedSecondOperand = when (secondOperand) {
                            "old" -> worry
                            else -> secondOperand.toLong()
                        }

                        val parsedOperator: (Long, Long) -> Long = when (operator) {
                            "+" -> Long::plus
                            "-" -> Long::minus
                            "*" -> Long::times
                            else -> throw RuntimeException("Unknown operator: $operator")
                        }

                        if (worryDecreases) {
                            parsedOperator(parsedFirstOperand, parsedSecondOperand).div(3L)
                        } else {
                            parsedOperator(parsedFirstOperand, parsedSecondOperand)
                        }
                    }
                }
    }

    fun doRound() {
        items.forEach {
            inspections++
            it.update(inspectFun)
            it.update { worry -> worry.mod(commonTestConditionDivisor) }
            passItem(it)
        }
        items.clear()
    }

    fun monkeyDiscovered(identity: Int, monkey: Monkey) {
        otherMonkeys[identity] = monkey
    }

    private fun catchItem(item: Item) {
        items.add(item)
    }

    private fun passItem(item: Item) {
        otherMonkeys[if (item.worryScore.mod(testCondition) == 0L) throwOnTrue else throwOnFalse]!!.catchItem(item)
    }
}

class Item(var worryScore: Long) {
    fun update(updateFn: (Long) -> Long) {
        worryScore = updateFn(worryScore)
    }
}