class Day05(val input: List<String>) {

    private val mapIndices = input.mapIndexed { index, s -> s to index }.filter { it.first.contains("map") }.map { it.second }

    fun part1(): Long {
        val seeds = input[0].split(":").last().split(" ").mapNotNull { it.toLongOrNull() }.toMutableList()
        mapIndices.forEachIndexed { i, mapIndex ->
            seeds.forEachIndexed { index, seed ->
                val endOfConversionData = if (i + 1 < mapIndices.size) mapIndices[i+1] else input.size-1
                val conversionData = input.subList(mapIndex+1, endOfConversionData).map { it.split(" ").mapNotNull { it.toLongOrNull() } }
                conversionData.forEach { line ->
                    if (line.isNotEmpty() && seed >= line[1] && seed <= line[1] + line[2]) {
                        seeds[index] = convert(seed, line[1], line[0])
                    }
                }
            }
        }
        return seeds.min()
    }

    fun part2(): Long {
        val seedRow = input[0].split(":").last().split(" ").mapNotNull { it.toLongOrNull() }.toMutableList()
        val seedRangeStarts = seedRow.filterIndexed { index, _ -> index % 2 == 0 }
        val seedRangeLengths = seedRow.filterIndexed { index, _ -> index % 2 != 0 }

        val results = MutableList(seedRangeStarts.size) { 0L }
        seedRangeStarts.forEachIndexed { index, seedRangeStart ->
            val intermediateResult = MutableList(seedRangeLengths[index].toInt()) { 0L }
            intermediateResult.forEachIndexed { ii, _ ->
                intermediateResult[ii] = seedRangeStart.plus(ii)
                mapIndices.forEachIndexed lol@{ mapIndexIndex, mapIndexNumber ->
                    val endOfConversionData = if (mapIndexIndex + 1 < mapIndices.size) mapIndices[mapIndexIndex + 1] else input.size - 1
                    val conversionData = input.subList(mapIndexNumber + 1, endOfConversionData)
                        .map { it.split(" ").mapNotNull { num -> num.toLongOrNull() } }
                    conversionData.forEach { line ->
                        if (line.isNotEmpty() && intermediateResult[ii] >= line[1] && intermediateResult[ii] < line[1] + line[2]) {
                            intermediateResult[ii] = convert(intermediateResult[ii], line[1], line[0])
                            return@lol
                        }
                    }
                }
            }
            results[index] = intermediateResult.min().toLong()
        }
        return results.min()
    }

    private fun convert(number: Long, sourceStart: Long, destinationStart: Long): Long {
        return number - sourceStart + destinationStart
    }
}
