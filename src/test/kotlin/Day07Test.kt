import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {


    // Files -> 123 name
    // Dirs -> dir name
    // Command -> command target
    @Test
    fun solveDay07A() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().lines()

        val actual = Day07.solveA(input)

        assertEquals(95437, actual)
    }

    @Test
    fun solveDay07B() {
        val input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
        """.trimIndent().lines()

        val actual = Day07.solveB(input)

        assertEquals(24933642, actual)
    }
}