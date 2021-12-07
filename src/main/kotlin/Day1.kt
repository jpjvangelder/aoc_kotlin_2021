import java.io.File

class Day1() {
    private val input: List<Int> = File("src/main/resources/input_day1.txt").useLines { it.map(String::toInt).toList() }
    private var totalDeepervalues = 0
    private var previousValue = 0
    private var currentValue = 0
    private fun resetValues() = run { totalDeepervalues = 0; previousValue = 0; currentValue = 0; }

    private fun part1() {
        resetValues()
        input.forEachIndexed { index, value ->
            currentValue = value
            if (index != 0 && currentValue > previousValue) totalDeepervalues++
            previousValue = currentValue
        }
        println(totalDeepervalues)
    }

    private var measurementWindow = 3;

    private fun part2() {
        resetValues()
        input.forEachIndexed { index, value ->
            if (index + measurementWindow <= input.size) {
                for (j in 0 until measurementWindow) currentValue += input[index + j]
                if (index != 0 && currentValue > previousValue) totalDeepervalues++
                previousValue = currentValue
                currentValue = 0
            }
        }
        println(totalDeepervalues)
    }

    init {
        println("Day 1 output:")
        part1()
        part2()
        println("----------")
    }
}
