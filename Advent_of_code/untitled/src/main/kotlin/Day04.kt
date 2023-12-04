import kotlin.math.pow

class Day04(input: List<String>) {
    private val data = input.map { it.split(":").last() }
    private val winningNumbers = data.map { line -> line.split("|").first().replace("  ", " ").split(" ").mapNotNull { it.toIntOrNull() } }
    private val ourNumbers = data.map { line -> line.split("|").last().replace("  ", " ").split(" ").mapNotNull { it.toIntOrNull() } }

    fun part1(): Int {
        var sum = 0
        winningNumbers.forEachIndexed { index, line ->
            val matchedNums = line.filter { num -> ourNumbers[index].contains(num) }
            sum += matchedNums.indices.maxOfOrNull { 2.0.pow(it.toDouble()).toInt() } ?: 0
        }
        return sum
    }

    fun part2(): Int {
        val winningsMap = List(winningNumbers.size) { index -> index to Counter(count = 1) }
        winningNumbers.forEachIndexed { index, line ->
            val numberOfWinningNums = line.filter { num -> ourNumbers[index].contains(num) }.size
            repeat(winningsMap[index].second.count) {
                repeat(numberOfWinningNums) {
                    if ((index + it + 1) <= winningsMap.size - 1) (winningsMap[index + it + 1].second.count++)
                }
            }
        }
        return winningsMap.sumOf { it.second.count }
    }
}
