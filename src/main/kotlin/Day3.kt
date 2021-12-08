import java.io.File

class Day3 : Day {
    private enum class Mode { MOST_COMMON, LEAST_COMMON }

    private val input: List<String> = File("src/main/resources/input_day3.txt").useLines { it.toList() }

    override fun part1() {
        val gammaRate = StringBuilder()
        val epsilonRate = StringBuilder()
        (0 until input[0].length).forEach { column ->
            when {
                input.count { it[column] == '1' } > input.size / 2 -> {
                    gammaRate.append(1)
                    epsilonRate.append(0)
                }
                else -> {
                    gammaRate.append(0)
                    epsilonRate.append(1)
                }
            }
        }
        println("$gammaRate".toInt(2) * "$epsilonRate".toInt(2))
    }


    override fun part2() {
        val oxygen = filterLines(input.toMutableList(), Mode.MOST_COMMON)
        val co2 = filterLines(input.toMutableList(), Mode.LEAST_COMMON)

        println(oxygen.toInt(2) * co2.toInt(2))
    }

    private fun filterLines(list: List<String>, commonMode: Mode): String {
        var filtered = list.toList()
        (0 until list[0].length).forEach {
            filtered = findCommonValue(filtered, it, commonMode)
            if (filtered.size <= 1) return filtered[0]
        }
        return "0"
    }

    private fun findCommonValue(input: List<String>, column: Int, commonMode: Mode): List<String> {
        val ones = input.count { it[column] == '1' }
        val commonValue = when (commonMode) {
            Mode.MOST_COMMON -> {
                if (ones >= input.size / 2.0) '1'
                else '0'
            }
            Mode.LEAST_COMMON -> {
                if (ones >= input.size / 2.0) '0'
                else '1'
            }
        }

        return input.filter { it[column] == commonValue }
    }

    init {
        println("Day 3 output:")
        part1()
        part2()
        println("----------")
    }
}
