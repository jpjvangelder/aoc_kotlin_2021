class Day5 : Day {
    private data class Cords(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
    private data class GridRow(val row: MutableList<Int>)
    private data class Grid(val rows: MutableList<GridRow>)

    private val cords: List<Cords> = AocUtil.getFile("input_day5")
        .map { string -> string.replace(Regex("\\s"), "").split(",", "->") }
        .map { split -> Cords(split[0].toInt(), split[1].toInt(), split[2].toInt(), split[3].toInt()) }

    init {
        println("Day 5 output:")
        part1()
        part2()
        println("----------")
    }

    override fun part1() {
        val grid = Grid(MutableList(1000) { GridRow(MutableList(1000) { 0 }) })

        cords.forEach { cords ->
            if (cords.x1 == cords.x2) {
                if (cords.y2 > cords.y1) {
                    (cords.y1..cords.y2).forEach { row ->
                        grid.rows[row].row[cords.x1] = grid.rows[row].row[cords.x1].inc()
                    }
                } else {
                    (cords.y2..cords.y1).forEach { row ->
                        grid.rows[row].row[cords.x1] = grid.rows[row].row[cords.x1].inc()
                    }
                }
            }
            if (cords.y1 == cords.y2) {
                if (cords.x2 > cords.x1) {
                    (cords.x1..cords.x2).forEach { column ->
                        grid.rows[cords.y1].row[column] = grid.rows[cords.y1].row[column].inc()
                    }
                } else {
                    (cords.x2..cords.x1).forEach { column ->
                        grid.rows[cords.y1].row[column] = grid.rows[cords.y1].row[column].inc()
                    }
                }
            }
        }

        var totalOverlapPoints = 0
        grid.rows.forEach { gridRow ->
            totalOverlapPoints += gridRow.row.filter { it >= 2 }.size
        }

        println("Total overlap points of 2 or higher: $totalOverlapPoints")
    }

    override fun part2() {
        TODO("Not yet implemented")
    }
}
