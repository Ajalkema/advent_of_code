import kotlin.math.max
import kotlin.math.min

class Day03(val input: List<String>) {

    private val symbols = Regex("[^\\d.]")

    fun part1(): Int {
        val numbersMap = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, _ ->
                if (charIndex > line.length - 2) return@forEachIndexed
                if (charIndex > 0) {
                    if (line.subSequence(charIndex, min(charIndex + 3, line.length)).toString().toIntOrNull() != null && !line[charIndex-1].isDigit()) {
                        numbersMap[charIndex to lineIndex] = line.subSequence(charIndex, min(charIndex + 3, line.length)).toString().toIntOrNull() ?: 0
                    }
                    if (charIndex < line.length - 2) {
                        if (line.subSequence(charIndex, min(charIndex + 2, line.length)).toString().toIntOrNull() != null
                            && !line[charIndex - 1].isDigit()
                            && !line[charIndex + 2].isDigit()) {
                                numbersMap[charIndex to lineIndex] = line.subSequence(charIndex, min(charIndex + 2, line.length)).toString().toIntOrNull() ?: 0
                        }
                    }
                } else {
                    if (line.subSequence(charIndex, min(charIndex + 3, line.length)).toString().toIntOrNull() != null) {
                        numbersMap[charIndex to lineIndex] = line.subSequence(charIndex, min(charIndex + 3, line.length)).toString().toIntOrNull() ?: 0
                    }
                    if (line.subSequence(charIndex, min(charIndex + 2, line.length)).toString().toIntOrNull() != null && !line[charIndex+2].isDigit()) {
                        numbersMap[charIndex to lineIndex] = line.subSequence(charIndex, min(charIndex + 2, line.length)).toString().toIntOrNull() ?: 0
                    }
                }
            }
        }

        var sum = 0
        numbersMap.keys.forEach {
            val (charIndex, lineIndex) = it
            if (hasSymbolsNearby(input[lineIndex], charIndex, lineIndex)) {
                sum += numbersMap[charIndex to lineIndex]!!
            }
        }
        return sum
    }

    fun hasSymbolsNearby(line: String, charIndex: Int, lineIndex: Int): Boolean {
        if (input[lineIndex].subSequence(max(charIndex - 1, 0), min(charIndex + 4, line.length)).contains(symbols)) return true
        if (input[max(lineIndex - 1, 0)].subSequence(max(charIndex - 1, 0), min(charIndex + 4, line.length)).contains(symbols)) return true
        if (input[min(lineIndex + 1, input.size -1)].subSequence(max(charIndex - 1, 0), min(charIndex + 4, line.length)).contains(symbols)) return true
        return false
    }
}