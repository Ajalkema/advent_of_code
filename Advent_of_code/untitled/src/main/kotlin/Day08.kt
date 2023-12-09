import java.math.BigInteger

class Day08(input: List<String>) {

    private val instructions = input[0].toCharArray()
    private val directionsMap = input.subList(2, input.size).map {
            line -> line.replace(Regex("[\\s()]+"), "") }.map {
            line -> line.split("=").first() to (line.split("=").last().split(",").first()
                to line.split("=").last().split(",").last()) }.toMap()

    fun part1(): BigInteger {
        var steps = BigInteger.ZERO
        var currentLocation = "AAA"
        while (currentLocation != "ZZZ") {
            if (instructions[steps.mod(BigInteger.valueOf(instructions.size.toLong())).toInt()] == 'L') {
                currentLocation = directionsMap[currentLocation]!!.first
            } else {
                currentLocation = directionsMap[currentLocation]!!.second
            }
            steps++
        }
        return steps
    }

    fun part2(): BigInteger {
        val currentLocations = directionsMap.keys.filter { it.endsWith("A") }.toMutableList()
        val lol = currentLocations.map { location -> stepsToFirstViableNode(location) }
        return findLCMOfListOfNumbers(lol)
    }

    private fun stepsToFirstViableNode(start: String): BigInteger {
        var steps = BigInteger.ZERO
        var currentLocation = start

        while (!currentLocation.endsWith("Z")) {
            if (instructions[steps.mod(BigInteger.valueOf(instructions.size.toLong())).toInt()] == 'L') {
                currentLocation = directionsMap[currentLocation]!!.first
            } else {
                currentLocation = directionsMap[currentLocation]!!.second
            }
            steps++
        }
        return steps
    }

    private fun findLCM(a: BigInteger, b: BigInteger): BigInteger {
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


    private fun findLCMOfListOfNumbers(numbers: List<BigInteger>): BigInteger {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }
}