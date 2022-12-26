import kotlin.math.floor

fun main() {
    val input = "input/day11.txt".readLines()
    println("Day 11 - A result = ${Day11.solveA(input)}")
//    println("Day 11 - B result = ${Day11.solveB(input)}")
}


object Day11 {
    fun solveA(input: List<String>): Int = input
        .joinToString("\n")
        .split("\n\n")
        .map { Monkey.from(it) }
        .let { monkeys ->
            monkeys.forEachIndexed { index, monkey ->
                monkeys.forEach { other -> other.monkeyDiscovered(index, monkey) }
            }
            (1 .. 20).forEach { monkeys.forEach { it.doRound() } }
            monkeys
        }
        .sortedByDescending { it.inspections }
        .take(2)
        .let { (first, second) -> first.inspections * second.inspections }
}

class Monkey private constructor(
    val items: MutableList<Item>,
    private val inspectFun: (worry: Int) -> Int,
    private val checkCondition: (worry: Int) -> Boolean,
    private val throwOnTrue: Int,
    private val throwOnFalse: Int,
    var inspections: Int = 0,
    val otherMonkeys: MutableMap<Int, Monkey> = mutableMapOf()
) {
    companion object {
        fun from(input: String): Monkey {
            val lines = input.split("\n").map { it.trim() }
            val itemWorryScores = Regex("(\\d+)+").findAll(lines[1]).map { it.value.toInt() }.toList()
            val testCondition = Regex("(\\d+)+").find(lines[3])!!.value.toInt()
            val monkeyThrowOnTrue = Regex("(\\d+)+").find(lines[4])!!.value.toInt()
            val monkeyThrowOnFalse = Regex("(\\d+)+").find(lines[5])!!.value.toInt()

            return Monkey(
                itemWorryScores.map { Item(it) }.toMutableList(),
                parseUpdateFunction(lines[2]),
                { worry -> worry % testCondition == 0 },
                monkeyThrowOnTrue,
                monkeyThrowOnFalse
            )
        }

        private fun parseUpdateFunction(input: String) =
            Regex("Operation: new = (old|\\d+) ([+\\-*/]) (old|\\d+)").find(input)!!.groupValues
                .let { (_, firstOperand, operator, secondOperand) ->
                    { worry: Int ->
                        val parsedFirstOperand = when (firstOperand) {
                            "old" -> worry.toDouble()
                            else -> firstOperand.toDouble()
                        }

                        val parsedSecondOperand = when (secondOperand) {
                            "old" -> worry.toDouble()
                            else -> secondOperand.toDouble()
                        }

                        val parsedOperator: (Double, Double) -> Double = when (operator) {
                            "+" -> Double::plus
                            "-" -> Double::minus
                            "*" -> Double::times
                            "/" -> Double::div
                            else -> throw RuntimeException("Unknown operator: $operator")
                        }

                        floor(
                            parsedOperator(
                                parsedFirstOperand,
                                parsedSecondOperand
                            ) / 3.0
                        ).toInt()
                    }
                }
    }

    fun doRound() {
        items.forEach {
            inspections++
            it.update(inspectFun)
            if (checkCondition(it.worryScore)) passItem(it, throwOnTrue) else passItem(it, throwOnFalse)
        }
        items.clear()
    }

    fun catchItem(item: Item) {
        items.add(item)
    }

    fun monkeyDiscovered(identity: Int, monkey: Monkey) {
        otherMonkeys[identity] = monkey
    }

    private fun passItem(item: Item, identity: Int) {
        otherMonkeys[identity]!!.catchItem(item)
    }
}

class Item(var worryScore: Int) {
    fun update(updateFn: (Int) -> Int) {
        worryScore = updateFn(worryScore)
    }
}