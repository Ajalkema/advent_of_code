class Day05(val input: List<String>) {
    private var seeds = input[0].split(":").last().split(" ").mapNotNull { it.toLongOrNull() }.toMutableList()
    private val mapIndices = input.mapIndexed { index, s ->  s to index }.filter { it.first.contains("map") }.map { it.second }

    fun part1(): Long {
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

    private fun convert(number: Long, sourceStart: Long, destinationStart: Long): Long {
        return number - sourceStart + destinationStart
    }
}
