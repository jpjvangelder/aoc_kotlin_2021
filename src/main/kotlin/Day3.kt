import java.io.File

class Day3 {
    private val input: List<ByteArray> = File("src/main/resources/input_day3.txt").useLines {
        it.map(String::toByteArray).toList()
    }
    private val zeroByte: Byte = 48
    private val oneByte: Byte = 49
    private var gammaRate = StringBuilder()
    private var epsilonRate = StringBuilder()

    private fun part1() {
        for (i in 0 until input[0].size) {
            var zeroBits = 0;
            var oneBits = 0;

            input.forEach {
                when (it[i]) {
                    zeroByte -> zeroBits++
                    oneByte -> oneBits++
                }
            }

            when {
                zeroBits > oneBits -> {
                    gammaRate.append(0)
                    epsilonRate.append(1)
                }
                zeroBits < oneBits -> {
                    gammaRate.append(1)
                    epsilonRate.append(0)
                }
            }
        }
        println("$gammaRate".toInt(2) * "$epsilonRate".toInt(2))
    }

    private fun part2() {
        val oxygen = input.toMutableList()
        val co2 = input.toMutableList();

        for (i in 0 until input[0].size) {
            var oxygenZeroBits = 0;
            var oxygenOneBits = 0;
            var co2ZeroBits = 0;
            var co2OneBits = 0;

            oxygen.forEach {
                when (it[i]) {
                    zeroByte -> oxygenZeroBits++
                    oneByte -> oxygenOneBits++
                }
            }

            co2.forEach {
                when (it[i]) {
                    zeroByte -> co2ZeroBits++
                    oneByte -> co2OneBits++
                }
            }

            when {
                oxygenOneBits >= oxygenZeroBits && oxygen.size > 1 -> oxygen.retainAll { bytes -> bytes[i] == oneByte }
                oxygenZeroBits > oxygenOneBits && oxygen.size > 1 -> oxygen.retainAll { bytes -> bytes[i] == zeroByte }
            }
            when {
                co2OneBits >= co2ZeroBits && co2.size > 1 -> co2.retainAll { bytes -> bytes[i] == zeroByte }
                co2ZeroBits > co2OneBits && co2.size > 1 -> co2.retainAll { bytes -> bytes[i] == oneByte }
            }

            if (oxygen.size == 1 && co2.size == 1) {
                val oxygenString = StringBuilder()
                val co2String = StringBuilder()
                oxygen[0].forEach {
                    when (it) {
                        zeroByte -> oxygenString.append(0)
                        oneByte -> oxygenString.append(1)
                    }
                }
                co2[0].forEach {
                    when (it) {
                        zeroByte -> co2String.append(0)
                        oneByte -> co2String.append(1)
                    }
                }
                println("$oxygenString".toInt(2) * "$co2String".toInt(2))
            }
        }
    }

    init {
        println("Day 3 output:")
        part1()
        part2()
        println("----------")
    }
}
