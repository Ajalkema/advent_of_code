class Day06(input: List<String>) {

    private val times = input[0].split(" ").mapNotNull { it.toIntOrNull() }
    private val distances = input[1].split(" ").mapNotNull { it.toIntOrNull() }

    fun part1(): Int {
        val result = MutableList(times.size) { 0 }
        times.forEachIndexed { i, time ->
            val indexOfFirstViable = (1..<time).indexOfFirst { wait ->
                distanceTravelled(time, wait) > distances[i]
            }
            result[i] = time.floorDiv(2).minus(indexOfFirstViable).times(2)
            if (time % 2 == 0) result[i]--
        }
        return result.reduce(Int::times)
    }

    private fun distanceTravelled(time: Int, wait: Int): Int {
        return (time-wait).times(wait)
    }
}
