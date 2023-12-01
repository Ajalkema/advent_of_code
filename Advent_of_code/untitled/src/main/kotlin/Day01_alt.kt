class Day01_alt {

    fun part01(input: List<String>): Int {
        return input.sumOf { line -> "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt() }
    }

    fun part02(input: List<String>): Int {
        return part01(input.map { it.replaceSpelledDigits(digits) })
    }

    fun String.replaceSpelledDigits(map: Map<String, String>): String {
        return map.keys.fold(this) { s, key -> s.replace(key, map.getValue(key)) }
    }

}

private val digits = mapOf(
    "one" to "o1e",
    "two" to "t2o",
    "three" to "th3ee",
    "four" to "fo4r",
    "five" to "fi5e",
    "six" to "s6x",
    "seven" to "se7en",
    "eight" to "ei8ht",
    "nine" to "n9ne"
)