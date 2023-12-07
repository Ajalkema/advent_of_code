class Day07(input: List<String>) {

    val FIVE_OF_A_KIND = Regex("(.)\\1{5,}")
    val FOUR_OF_A_KIND = Regex("(.)\\1{4,}")
    val THREE_OF_A_KIND = Regex("(.)\\1{3,}")
    val TWO_PAIR = Regex("(.)\\1{2,}(.)\\2{2,}")
    val ONE_PAIR = Regex("(.)\\1{2,}")

    fun part1(): Long {

        return 0
    }
}