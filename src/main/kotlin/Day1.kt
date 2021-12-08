import java.io.File

class Day1 : Day {
    private val input: List<Int> = File("src/main/resources/input_day1.txt").useLines { it.map(String::toInt).toList() }

    override fun part1() {
        println((1 until input.size).count { input[it] > input[it - 1] })
    }

    override fun part2() {
        println((3 until input.size).count { input[it] > input[it - 3] })
    }

    init {
        println("Day 1 output:")
        part1()
        part2()
        println("----------")
    }
}
