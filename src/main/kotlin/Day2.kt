class Day2 : Day {
    private data class PositionUnits(val position: String, val units: Int)
    private val input: List<PositionUnits> = AocUtil.getFile("input_day2")
        .map { string ->
            string.split(Regex("\\s(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
        }.map { split ->
            PositionUnits(split[0], split[1].toInt())
        }

    init {
        println("Day 2 output:")
        part1()
        part2()
        println("----------")
    }

    override fun part1() {
        var horizontalPosition = 0
        var depth = 0

        input.forEach {
            when (it.position) {
                "up" -> depth -= it.units
                "down" -> depth += it.units
                "forward" -> horizontalPosition += it.units
            }
        }
        println(horizontalPosition * depth)
    }

    override fun part2() {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        input.forEach {
            when (it.position) {
                "up" -> aim -= it.units
                "down" -> aim += it.units
                "forward" -> {
                    horizontalPosition += it.units
                    depth += aim * it.units
                }
            }
        }
        println(horizontalPosition * depth)
    }
}
