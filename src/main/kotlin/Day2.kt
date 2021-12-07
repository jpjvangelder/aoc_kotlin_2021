import java.io.File

class Day2 {
    private data class PositionUnits(val position: String, val units: Int)

    private val input = File("src/main/resources/input_day2.txt").useLines {
        it.map { string ->
            string.split(Regex("\\s(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
        }.map { split ->
            PositionUnits(split[0], split[1].toInt())
        }.toList()
    }
    private var horizontalPosition = 0
    private var depth = 0
    private var aim = 0
    private fun resetValues() = run { horizontalPosition = 0; depth = 0; aim = 0; }

    private fun part1() {
        resetValues()
        input.forEach { positionUnits ->
            when (positionUnits.position) {
                "up" -> depth -= positionUnits.units
                "down" -> depth += positionUnits.units
                "forward" -> horizontalPosition += positionUnits.units
            }
        }
        println(horizontalPosition * depth)
    }

    private fun part2() {
        resetValues()
        input.forEach { positionUnits ->
            when (positionUnits.position) {
                "up" -> aim -= positionUnits.units
                "down" -> aim += positionUnits.units
                "forward" -> {
                    horizontalPosition += positionUnits.units
                    depth += aim * positionUnits.units
                }
            }
        }
        println(horizontalPosition * depth)
    }

    init {
        println("Day 2 output:")
        part1()
        part2()
        println("----------")
    }
}
