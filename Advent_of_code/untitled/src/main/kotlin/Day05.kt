class Day05(val input: List<String>) {
    private var seeds = input[0].split(":").last().split(" ").mapNotNull { it.toLongOrNull() }.toMutableList()
    private val mapIndices = input.mapIndexed { index, s ->  s to index }.filter { it.first.contains("map") }.map { it.second }

    fun part1(): Long {
        mapIndices.forEachIndexed { i, mapIndex ->
            val rangesMap = mutableMapOf<Long, Long>()
            if ((i+1) < mapIndices.size) {
                (mapIndex + 1..<mapIndices[i + 1]).forEach { rangeIndex ->
                    if (input[rangeIndex] == "") return@forEach
                    val ranges = mapRangeLineToRangeMap(input[rangeIndex])
                    ranges.getSourceRange().forEach {
                        rangesMap[it] = convert(it, ranges.getSourceRange(), ranges.getDestinationRange())
                    }
                }
                seeds.forEachIndexed { index, seed ->
                    if (rangesMap.getOrDefault(seed, null) != null) {
                        seeds[index] = rangesMap[seed]!!
                    }
                }
            }
        }
        return seeds.min()
    }

    private fun convert(number: Long, source: LongRange, destination: LongRange): Long {
        return number - source.first + destination.first
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
