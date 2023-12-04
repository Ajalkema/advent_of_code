import kotlin.math.max
import kotlin.math.min

class Day03(val input: List<String>) {

    private val numbers = Regex("\\d+")
    private val symbols = Regex("[^\\d.]")
    private val stars = Regex("[*]")
    private val lineLength = input[0].length
    private val numbersMap = input.map { line -> numbers.findAll(line).map { it.value to it.range } }

    fun part1(): Int {
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

    fun part2(): Int {
        val starIndex = input.map { line -> stars.findAll(line).map { it.range } }

        var sum = 0
        starIndex.forEachIndexed { lineIndexStar, line ->
            line.forEach { starIndex ->
                sum += twoNumbersMultipliedIfPossible(starIndex, lineIndexStar)
            }
        }
        return sum
    }

    private fun hasSymbolsNearby(numberMap: Pair<String, IntRange>, lineIndex: Int): Boolean {
        if (input[lineIndex].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        if (input[max(lineIndex - 1, 0)].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        if (input[min(lineIndex + 1, input.size -1)].subSequence(max(numberMap.second.first - 1, 0), min(numberMap.second.last + 2, lineLength)).contains(symbols)) return true
        return false
    }

    private fun twoNumbersMultipliedIfPossible(starIndex: IntRange, lineIndexStar: Int): Int {
        val numsCloseToStar = mutableListOf<Int>()
        numbersMap.forEachIndexed { lineIndex, line ->
            line.forEach {
                if (hasStarNearby(it.second, lineIndex, starIndex.first, lineIndexStar)) {
                    numsCloseToStar.add(it.first.toInt())
                }
            }
        }
        if (numsCloseToStar.size == 2) {
            return numsCloseToStar.reduce(Int::times)
        }
        return 0
    }

    private fun hasStarNearby(numRange: IntRange, numLineIndex: Int, starRange: Int, lineIndexStar: Int): Boolean {
        if (numLineIndex !in lineIndexStar-1..lineIndexStar+1) return false
        if (starRange !in (numRange.first-1..numRange.last+1)) return false
        return true
    }
}