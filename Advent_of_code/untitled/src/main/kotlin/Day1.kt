import java.io.File
import java.nio.charset.Charset

class Day1 {

    private val nums = (0..9).map { it.toString() }
    private val numsSpelled = listOf("one", "two", "three" , "four", "five", "six", "seven", "eight", "nine")
    private val data = parse()

    private fun parse(): List<String> {
        return File("src/main/resources/Data-1A.txt").readLines(Charset.defaultCharset())
    }

    fun calculatePart1(): Number {
        var sum = 0
        data.forEach { line ->
            val firstNum = line.findAnyOf(nums)?.second
            val lastNum = line.findLastAnyOf(nums)?.second
            val number = firstNum + lastNum
            sum += number.toInt()
        }
        return sum
    }

    fun calculatePart2(): Number {
        var sum = 0
        data.forEach { line ->
            val (indexSpelledFirst: Int?, firstNumSpelled: String?) = line.findAnyOf(numsSpelled) ?: (null to null)
            val (indexNumsFirst: Int?, firstNums: String?) = line.findAnyOf(nums) ?: (null to null)
            val firstNum = indexSpelledFirst?.let {
                if (indexNumsFirst != null && indexNumsFirst < indexSpelledFirst) firstNums!!.toInt() else firstNumSpelled!!.convertSpelledNumToInt()
            } ?: firstNums!!.toInt()

            val (indexSpelledLast: Int?, lastNumSpelled: String?) = line.findLastAnyOf(numsSpelled) ?: (null to null)
            val (indexNumslast: Int?, lastNums: String?) = line.findLastAnyOf(nums) ?: (null to null)
            val lastNum = indexSpelledLast?.let {
                if (indexNumslast != null && indexNumslast > indexSpelledLast) lastNums!!.toInt() else lastNumSpelled!!.convertSpelledNumToInt()
            } ?: lastNums!!.toInt()

            val combinedNum = firstNum.toString() + lastNum.toString()
            sum += combinedNum.toInt()
        }
        return sum
    }

    fun String.convertSpelledNumToInt(): Int = when (this) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> throw Exception("Bruh")
    }
}
