import kotlin.math.abs

class Day11(val input: List<String>) {

    private val expandedColumns = input.first().indices.map { cIndex -> cIndex to input.map { it[cIndex] } }.filter { it.second.all { char -> char == '.' } }.map { it.first }
    private val expandedRows = input.indices.map { rIndex -> rIndex to input[rIndex] }.filter { it.second.all { char -> char == '.' } }.map { it.first }
    private val galaxies = findGalaxies()

    fun part1(): Long {
        return iterateGalaxies(expansionMultiplier = 1)
    }

    fun part2(): Long {
        return iterateGalaxies(expansionMultiplier = 999_999)
    }

    private fun calculateDistance(galaxy: Position, other: Position, expansionMultiplier: Int): Long {
        var result = 0L
        result += abs(galaxy.x - other.x) + abs(galaxy.y - other.y)
        result += (galaxy.x..other.x).count { expandedRows.contains(it) }.times(expansionMultiplier)
        result += (other.x..galaxy.x).count { expandedRows.contains(it) }.times(expansionMultiplier)
        result += (galaxy.y..other.y).count { expandedColumns.contains(it) }.times(expansionMultiplier)
        result += (other.y..galaxy.y).count { expandedColumns.contains(it) }.times(expansionMultiplier)
        return result
    }

    private fun iterateGalaxies(expansionMultiplier: Int): Long {
        var sum = 0L
        galaxies.forEachIndexed { galaxyIndex, galaxy ->
            galaxies.subList(galaxyIndex+1, galaxies.size).forEachIndexed { otherIndex, other ->
                sum += calculateDistance(galaxy, other, expansionMultiplier)
            }
        }
        return sum
    }

    private fun findGalaxies(): List<Position> {
        val galaxies = mutableListOf<Position>()
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { columnIndex, c ->
                if (c == '#') galaxies.add(Position(lineIndex, columnIndex))
            }
        }
        return galaxies
    }
}
