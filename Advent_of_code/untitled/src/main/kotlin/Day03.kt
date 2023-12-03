import kotlin.math.max
import kotlin.math.min

class Day03(val input: List<String>) {

    private val numbers = Regex("\\d+")
    private val symbols = Regex("[^\\d.]")
    private val stars = Regex("[*]")
    private val lineLength = input[0].length

    fun part1(): Int {
        val numbersMap = input.map { line -> numbers.findAll(line).map { it.value to it.range } }
        numbersMap.forEach { it.println() }

        var sum = 0
        numbersMap.forEachIndexed { index, line ->
            line.forEach { numberMap ->
                if (hasSymbolsNearby(numberMap, index)) {
                    sum += numberMap.first.toInt()
                }
            }
        }
        return sum
    }

    fun hasSymbolsNearby(numberMap: Pair<String, IntRange>, lineIndex: Int): Boolean {
        if (input[lineIndex].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        if (input[max(lineIndex - 1, 0)].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        if (input[min(lineIndex + 1, input.size -1)].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        return false
    }
}