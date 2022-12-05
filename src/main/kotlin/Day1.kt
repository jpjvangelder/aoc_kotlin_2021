class Day1 : Day {
    private val input: List<Int> = AocUtil.getFile("input_day1").map(String::toInt)

    init {
        println("Day 1 output:")
        part1()
        part2()
        println("----------")
    }

    override fun part1() {
        println((1 until input.size).count { input[it] > input[it - 1] })
    }

    override fun part2() {
        println((3 until input.size).count { input[it] > input[it - 3] })
    }
}
