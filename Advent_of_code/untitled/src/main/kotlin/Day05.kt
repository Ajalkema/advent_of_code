class Day05(val input: List<String>) {
    private val seeds = input[0].split(":").last().split(" ").mapNotNull { it.toLongOrNull() }
    private val mapIndices = input.mapIndexed { index, s ->  s to index }.filter { it.first.contains("map") }.map { it.second }

    fun part1(): Int {
        mapIndices.forEachIndexed { i, mapIndex ->
            val rangesMap = mutableMapOf<Long, Long>()
            (mapIndex+1 until mapIndices[i+1]).forEach { rangeIndex ->
                val ranges = mapRangeLineToRangeMap(input[rangeIndex])
                ranges.getDestinationRange().forEach {
                    rangesMap[it] = convert(it, ranges.getDestinationRange(), ranges.getSourceRange())
                }
            }
        }
        return 0
    }

    private fun convert(number: Long, original: LongRange, target: LongRange): Long {
        val ratio = number.toFloat() / (original.endInclusive - original.start)
        return (ratio * (target.endInclusive - target.start)).toLong()
    }

    private fun mapRangeLineToRangeMap(line: String): Ranges {
        val range = line.split(" ").mapNotNull { it.toLongOrNull() }
        return Ranges(range[0], range[1], range[2])
    }
}


class Ranges(val destinationStart: Long, val sourceStart: Long, val length: Long) {
    fun getDestinationRange() = destinationStart..destinationStart+length
    fun getSourceRange() = sourceStart..sourceStart+length
}