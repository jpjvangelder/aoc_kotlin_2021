class Day4 : Day {
    private val input: List<String> = AocUtil.getFile("input_day4").filter(String::isNotEmpty)
    private val numbers: List<Int> = input.toList()[0].split(",").map(String::toInt)
    private val bingoCards: List<BingoCard> = getBingoCards()

    init {
        println("Day 4 output:")
        part1()
        part2()
        println("----------")
    }

    override fun part1() {
        numbers.forEach { number ->
            bingoCards.forEach { bingoCard ->
                bingoCard.flagValue(number)
                if (bingoCard.hasWon) {
                    println(number * bingoCard.sumOfUnmarkedValues)
                    return
                }
            }
        }
    }

    override fun part2() {
        val winningBoards = ArrayList<BingoCard>()
        var finalNumber: Int
        numbers.forEach { number ->
            bingoCards.forEach { bingoCard ->
                bingoCard.flagValue(number)
                if (bingoCard.hasWon && !winningBoards.contains(bingoCard)) {
                    winningBoards.add(bingoCard)
                    finalNumber = number
                    if(winningBoards.size == bingoCards.size) {
                        println(winningBoards.last().sumOfUnmarkedValues * finalNumber)
                        return
                    }
                }
            }
        }
    }

    private data class BingoNumber(var number: Int, var flagged: Boolean)

    private data class BingoCard(var rows: MutableList<List<BingoNumber>>) {
        val sumOfUnmarkedValues: Int
            get() {
                var sum = 0
                rows.forEach { it.forEach { bingoNumber -> if (!bingoNumber.flagged) sum += bingoNumber.number } }
                return sum
            }

        val hasWon: Boolean
            get() {
                rows.forEachIndexed { index, list ->
                    if (list.all(BingoNumber::flagged)) return true
                    if (rows.all { it[index].flagged }) return true
                }
                return false
            }

        fun flagValue(value: Int) {
            rows.forEach { list ->
                list.forEach {
                    if (it.number == value) {
                        it.flagged = true
                    }
                }
            }
        }
    }

    private fun getBingoCards(): List<BingoCard> {
        val bingoCards = ArrayList<BingoCard>()
        val bingoInput = input.subList(1, input.size)
        (1..bingoInput.size).forEach { i ->
            if (i % 5 == 0) {
                val bingoCard = BingoCard(ArrayList())
                val subList = bingoInput.subList(i - 5, i)
                subList.forEach { s ->
                    val numbers = ArrayList<BingoNumber>()
                    s.split(Regex("\\s(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).filter(String::isNotEmpty).forEach { split ->
                        numbers.add(BingoNumber(split.toInt(), false))
                    }
                    bingoCard.rows.add(numbers)
                }
                bingoCards.add(bingoCard)
            }
        }
        return bingoCards
    }
}




