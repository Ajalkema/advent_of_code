class Day07(input: List<String>) {

    private val FIVE_OF_A_KIND = Regex("(.)\\1{5,}")
    private val FOUR_OF_A_KIND = Regex("(.)\\1{4,}")
    private val THREE_OF_A_KIND = Regex("(.)\\1{3,}")
    private val TWO_PAIR = Regex("(.)\\1{2,}(.)\\2{2,}")
    private val ONE_PAIR = Regex("(.)\\1{2,}")

    fun part1(): Boolean {
        return THREE_OF_A_KIND.containsMatchIn("AAABB")
    }
}
