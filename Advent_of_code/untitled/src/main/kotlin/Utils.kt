import java.io.File
import java.math.BigInteger

fun readInput(name: String) = File("src/main/resources/$name.txt").readLines()

fun Any?.println() = println(this)

class Counter(var count: Int = 0)

fun findLCM(a: BigInteger, b: BigInteger): BigInteger {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm.mod(a).toInt() == 0 && lcm.mod(b).toInt() == 0) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}


fun findLCMOfListOfNumbers(numbers: List<BigInteger>): BigInteger {
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}
