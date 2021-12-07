import java.io.File

class Day1 {
    private fun getDataFromFile(): List<Int> = File("src/main/resources/input_day1.txt").useLines { it.map(String::toInt).toList() }
    private var input = getDataFromFile()
    private var totalDeepervalues = 0
    private var previousValue = 0
    private var currentValue = 0
    private fun resetValues() = run { totalDeepervalues = 0; previousValue = 0; currentValue = 0; }

    fun part1(): Int {
        resetValues()
        input.forEachIndexed { index, value ->
            currentValue = value
            if(index != 0 && currentValue > previousValue) totalDeepervalues++
            previousValue = currentValue
        }
        return totalDeepervalues
    }

    private var measurementWindow = 3;

    fun part2(): Int {
        resetValues()
        input.forEachIndexed { index, value ->
            if (index + measurementWindow <= input.size) {
                for(j in 0 until measurementWindow) currentValue += input[index + j]
                if(index != 0 && currentValue > previousValue) totalDeepervalues++
                previousValue = currentValue
                currentValue = 0
            }
        }
        return totalDeepervalues;
    }
}
