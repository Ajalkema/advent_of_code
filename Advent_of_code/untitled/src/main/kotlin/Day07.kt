import kotlin.math.min

class Day07(private val input: List<String>) {

    fun part1(): Int {
        return getAnswer(false)
    }

    fun part2(): Int {
        return getAnswer(true)
    }

    private fun getAnswer(hasJokers: Boolean): Int {
        val handsWithBids = input.map { HandWithBid(Hand(it.split(" ").first(), hehe = hasJokers), it.split(" ").last().toInt()) }.sortedBy { it.hand }
        val scored = handsWithBids.mapIndexed { index, handWithBid ->  handWithBid.bid.times(index + 1) }
        return scored.sum()
    }

}

data class HandWithBid(val hand: Hand, val bid: Int)

data class Hand(val cards: String, val hehe: Boolean): Comparable<Hand> {

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
        isFiveOfAKind() -> 6
        isFourOfAKind() -> if (!hehe) 5 else if (charMap.getOrDefault('J', null) == 4) 6 else 5 + (charMap.getOrDefault('J', null) ?: 0)

        isFullHouse() -> if (!hehe) 4 else {
            if (charMap.getOrDefault('J', null) in listOf(2, 3)) 6 else 4
        }

        isThreeOfAKind() -> if (!hehe) 3 else {
            if (charMap.getOrDefault('J', null) == 3) 5 else 3 + min((charMap.getOrDefault('J', null)?.plus(1) ?: 0), 3)
        }

        isTwoPair() -> if (!hehe) 2 else {
            if (charMap.getOrDefault('J', null) == 2) 5 else 2 + min((charMap.getOrDefault('J', null)?.plus(1) ?: 0), 4)
        }

        isTwoOfAKind() -> if (!hehe) 1 else {
            if (charMap.getOrDefault('J', null) == 2) 3 else 1 + min((charMap.getOrDefault('J', null)?.plus(1) ?: 0), 5)
        }

        else -> if (!hehe) 0 else when {
            charMap.getOrDefault('J', null) == 1 -> 1
            else -> 0
        }
    }

    private fun Char.getCardValue() = when (this) {
        'A' -> 14
        'K' -> 13
        'Q' -> 12
        'J' -> if (!hehe) 11 else 1
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


