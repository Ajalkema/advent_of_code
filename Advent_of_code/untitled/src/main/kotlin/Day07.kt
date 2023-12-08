class Day07(private val input: List<String>) {

    fun part1(): Int {
        val handsWithBids = input.map { HandWithBid(Hand(it.split(" ").first()), it.split(" ").last().toInt()) }.sortedBy { it.hand }
        val scored = handsWithBids.mapIndexed { index, handWithBid ->  handWithBid.bid.times(index + 1) }
        return scored.sum()
    }

}

data class HandWithBid(val hand: Hand, val bid: Int)

data class Hand(val cards: String): Comparable<Hand> {

    private val charMap: Map<Char, Int>

    init {
        val occurrencesMap = mutableMapOf<Char, Int>()
        cards.forEach { char ->
            occurrencesMap.putIfAbsent(char, 0)
            occurrencesMap[char] = occurrencesMap[char]!! + 1
        }
        charMap = occurrencesMap
    }

    override fun compareTo(other: Hand): Int {
        if (getHandValue() > other.getHandValue()) return 1
        if (getHandValue() < other.getHandValue()) return -1
        if (hasHighestCard(other)) return 1
        return -1
    }

    private fun isFiveOfAKind() = charMap.containsValue(5)
    private fun isFourOfAKind() = charMap.containsValue(4)
    private fun isFullHouse() = charMap.containsValue(3) && charMap.containsValue(2)
    private fun isThreeOfAKind() = charMap.containsValue(3) && !charMap.containsValue(2)
    private fun isTwoPair() = charMap.filter { it.value == 2 }.size == 2
    private fun isTwoOfAKind() = charMap.containsValue(2) && !isTwoPair()

    private fun hasHighestCard(other: Hand): Boolean {
        cards.forEachIndexed { i, char ->
            if (char.getCardValue() > other.cards[i].getCardValue()) return true
            if (char.getCardValue() < other.cards[i].getCardValue()) return false
        }
        throw Exception("equal cards?")
    }


    private fun getHandValue() = when {
        this.isFiveOfAKind() -> 6
        this.isFourOfAKind() -> 5
        this.isFullHouse() -> 4
        this.isThreeOfAKind() -> 3
        this.isTwoPair() -> 2
        this.isTwoOfAKind() -> 1
        else -> 0
    }

    private fun Char.getCardValue() = when (this) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> 11
        'T' -> 10
        '9' -> 9
        '8' -> 8
        '7' -> 7
        '6' -> 6
        '5' -> 5
        '4' -> 4
        '3' -> 3
        '2' -> 2
        else -> throw Exception("hehe")
    }
}


