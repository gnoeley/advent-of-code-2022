fun main() {
    val input = "input/day07-a.txt".readLines()
    println("Day 07 - A result = ${Day07.solveA(input)}")
    println("Day 07 - B result = ${Day07.solveB(input)}")
}

object Day07 {

    data class File(val name: String, val size: Int)
    data class Directory(val name: String) {
        val directories: MutableList<Directory> = mutableListOf()
        val files: MutableList<File> = mutableListOf()

        fun size(): Int = files.sumOf { it.size } + directories.sumOf { it.size() }
        fun allDirs(): List<Directory> = directories + directories.flatMap { it.allDirs() }
        override fun toString(): String = "dir $name\n" +
                files.joinToString("\n", postfix = "\n") { "  ${it.size} ${it.name}" } +
                directories.joinToString("\n") { it.toString() }.lines().joinToString("\n") { "    $it" }
    }

    fun solveA(input: List<String>): Int {
        val root = Directory("")
        parseConsole(input, root)
        return root.allDirs().map { it.size() }.filter { it < 100000 }.sum()
    }

    fun solveB(input: List<String>): Int {
        val root = Directory("")
        parseConsole(input, root)
        val sizeToFree = 30000000 - (70000000 - root.size())
        return root.allDirs().filter { it.size() > sizeToFree }.minByOrNull { it.size() }!!.size()
    }

    private fun parseConsole(console: List<String>, directory: Directory): Int {
        var index = 0
        while (index < console.size) {
            val line = console[index]

            if (line.matches("\\$ cd \\.\\.".toRegex())) {
                return index
            }

            if (line.matches("\\$ cd ([A-z/]+)".toRegex())) {
                val next = Directory("\\$ cd ([A-z/]+)".toRegex().matchEntire(line)!!.groupValues[1])
                directory.directories.add(next)
                index += parseConsole(console.subList(index+1, console.size), next) + 1

            } else if (line.matches("([0-9]+) (.+)".toRegex())) {
                "([0-9]+) (.+)".toRegex().matchEntire(line)?.groupValues?.also { (_, size, name) ->
                    directory.files.add(File(name, size.toInt()))
                }

            }

            index++
        }
        return console.size
    }
}
