import java.io.File
import java.nio.charset.Charset

class Day1 {

    private val nums = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val numsSpelled = listOf("one", "two", "three" , "four", "five", "six" , "seven", "eight", "nine")
    private val data = parse()

    private fun parse(): List<String> {
        return File("/Users/aalkema/Private/Advent_of_code/untitled/src/main/resources/Data-1A.txt").readLines(Charset.defaultCharset())
    }

    fun calculatePart1(): Number {
        var sum = 0
        data.forEach { line ->
            val numsInLine = line.filter { c -> nums.contains(c) }
            val firstNum = numsInLine.first()
            val lastNum = numsInLine.last()
            val number = firstNum.toString() + lastNum.toString()
            sum += number.toInt()
        }
        return sum
    }

    fun calculatePart2(): Number {
        var sum = 0
        data.forEach {line ->
            line.forEachIndexed { index, c ->

            }
        }
        return sum
    }
}
