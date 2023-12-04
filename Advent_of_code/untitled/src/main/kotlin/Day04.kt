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
}
