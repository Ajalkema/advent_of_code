import java.io.File
import java.nio.charset.Charset

class Day1 {

    private val digits = (0..9).map { it.toString() }
    private val spelledDigits = listOf("one", "two", "three" , "four", "five", "six", "seven", "eight", "nine")
    private val data = parse()

    private fun parse(): List<String> {
        return File("src/main/resources/Data-1A.txt").readLines(Charset.defaultCharset())
    }

    fun calculatePart1(): Number {
        var sum = 0
        data.forEach { line ->
            val firstNum = line.findAnyOf(digits)?.second
            val lastNum = line.findLastAnyOf(digits)?.second
            val number = firstNum + lastNum
            sum += number.toInt()
        }
        return sum
    }

    fun calculatePart2(): Number {
        var sum = 0
        data.forEach { line ->
            val firstNum = calculateFirstNum(line)
            val lastNum = calculateLastNum(line)

            val combinedNum = firstNum + lastNum
            sum += combinedNum.toInt()
        }
        return sum
    }

    private fun calculateFirstNum(line: String): String {
        val (indexSpelledFirst: Int?, firstNumSpelled: String?) = line.findAnyOf(spelledDigits) ?: (null to null)
        val (indexNumsFirst: Int?, firstNum: String?) = line.findAnyOf(digits) ?: (null to null)
        return indexSpelledFirst?.let {
            if (indexNumsFirst != null && indexNumsFirst < indexSpelledFirst) firstNum!! else firstNumSpelled!!.convertToDigitString()
        } ?: firstNum!!
    }

    private fun calculateLastNum(line: String): String {
        val (indexSpelledLast: Int?, lastNumSpelled: String?) = line.findLastAnyOf(spelledDigits) ?: (null to null)
        val (indexNumslast: Int?, lastNums: String?) = line.findLastAnyOf(digits) ?: (null to null)
        return indexSpelledLast?.let {
            if (indexNumslast != null && indexNumslast > indexSpelledLast) lastNums!! else lastNumSpelled!!.convertToDigitString()
        } ?: lastNums!!
    }

    private fun String.convertToDigitString(): String = when (this) {
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> throw Exception("Bruh")
    }
}
