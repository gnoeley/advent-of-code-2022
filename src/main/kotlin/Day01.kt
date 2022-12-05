import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.toPath

fun main() {
    val input = object {}.javaClass.getResource("input/day01-a.txt").toURI().toPath().toFile().readLines()
    println("Day 01 - A result = ${Day01.solveA(input)}")
    println("Day 01 - B result = ${Day01.solveB(input)}")
}

class Day01 {
    companion object {
        fun solveA(input: List<String>) = input
            .map { it.toIntOrNull() }
            .fold(mutableListOf(AtomicInteger(0))) { res, it ->
                res.apply { if (it == null) add(AtomicInteger(0)) else last().getAndAdd(it) }
            }
            .maxOfOrNull { it.get() }

        fun solveB(input: List<String>) = input
            .map { it.toIntOrNull() }
            .fold(mutableListOf(AtomicInteger(0))) { res, it ->
                res.apply { if (it == null) add(AtomicInteger(0)) else last().getAndAdd(it) }
            }
            .sortedByDescending { it.get() }
            .take(3)
            .sumOf { it.get() }

    }
}
