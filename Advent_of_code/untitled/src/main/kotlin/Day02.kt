class Day02 {

    private val colors = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun part1(input: List<String>): Int {
        val splittedLine = input.map { it.split(" ").map { it.replace(Regex("""[:;,]"""), "") } }
        return splittedLine.filter { gamePossible(it) }.sumOf { it[1].toInt() }
    }

    fun part2(input: List<String>): Int {
        val splittedLine = input.map { it.split(" ").map { it.replace(Regex("""[:;,]"""), "") } }
        return splittedLine.sumOf { minimumCubesSquared(it) }
    }

    private fun gamePossible(line: List<String>): Boolean {
        line.drop(2).forEachIndexed { index, s ->
            if ( s.toIntOrNull() != null && s.toInt() > colors.get(line[index + 3])!!) return false
        }
        return true
    }

    private fun minimumCubesSquared(line: List<String>): Int {
        val minimumCubesMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        colors.keys.forEach { color ->
            line.forEachIndexed { i, s ->
                if (s.toIntOrNull() != null && line[i + 1].equals(color) && minimumCubesMap[color]!! < s.toInt()) minimumCubesMap[color] = s.toInt()
            }
        }
        return minimumCubesMap.values.reduce(Int::times)
    }
}
