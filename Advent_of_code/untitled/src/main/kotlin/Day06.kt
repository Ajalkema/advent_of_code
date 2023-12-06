class Day06(input: List<String>) {

    private val times = input[0].split(" ").mapNotNull { it.toLongOrNull() }
    private val distances = input[1].split(" ").mapNotNull { it.toLongOrNull() }

    fun part1(): Long {
        val result = MutableList(times.size) { 0L }
        times.forEachIndexed { i, time ->
            result[i] = calculateResult(time, distances[i])
        }
        return result.reduce(Long::times)
    }

    fun part2(): Long {
        val combinedTime = times.joinToString("") { it.toString() }.toLong()
        val combinedDistance = distances.joinToString("") { it.toString() }.toLong()

        return calculateResult(combinedTime, combinedDistance)
    }

    private fun calculateResult(time: Long, distance: Long): Long {
        val indexOfFirstViable = (1..<time).indexOfFirst { wait ->
            distanceTravelled(time, wait) > distance
        }
        var result = time.floorDiv(2).minus(indexOfFirstViable).times(2)
        if (time % 2 == 0L) result--

        return result
    }

    private fun distanceTravelled(time: Long, wait: Long): Long {
        return (time-wait).times(wait)
    }
}
