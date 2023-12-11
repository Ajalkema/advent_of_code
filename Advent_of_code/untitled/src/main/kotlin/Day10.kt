class Day10(val input: List<String>) {

    private val grid = input.map { it.map { it } }
    private val start: Position = findStart()

    fun part1(): Int {
        var currentPosition = start
        var previousPosition: Position? = null
        var count = 0
        do {
            val tmp = currentPosition
            currentPosition = findNextTile(currentPosition, previousPosition)
            previousPosition = tmp
            count++
        } while (currentPosition != start)
        return count.floorDiv(2)
    }

    fun part2(): Int {
        var up = 1
        var down = 0
        var left = 0
        var right = 1

        var currentPosition = start
        var previousPosition: Position? = null
        do {
            val tmp = currentPosition
            currentPosition = findNextTile(currentPosition, previousPosition)
            previousPosition = tmp
            when (grid[currentPosition.x][currentPosition.y]) {
                'L' -> { up++; right++ }
                'F' -> { down++; right++ }
                'J' -> { up++; left++ }
                '7' -> { down++; left++ }
                '|' -> { up++; down++ }
                '-' -> { left++; right++ }
            }
        } while (currentPosition != start)
        up.println()
        down.println()
        left.println()
        right.println()
        return 0
    }

    private fun findNextTile(position: Position, previousPosition: Position?): Position {
        if (grid[position.x][position.y] in listOf('|','L', 'J', 'S')) {
            if (northAight(position) && previousPosition != position.copy(x = position.x - 1)) return position.copy(x = position.x - 1)
        }
        if (grid[position.x][position.y] in listOf('-','L', 'F', 'S')) {
            if (eastAight(position) && previousPosition != position.copy(y = position.y + 1)) return position.copy(y = position.y + 1)
        }
        if (grid[position.x][position.y] in listOf('|','7', 'F', 'S')) {
            if (southAight(position) && previousPosition != position.copy(x = position.x + 1)) return position.copy(x = position.x + 1)
        }
        if (grid[position.x][position.y] in listOf('-','7', 'J', 'S')) {
            if (westAight(position) && previousPosition != position.copy(y = position.y - 1)) return position.copy(y = position.y - 1)
        }
        throw Exception("Wrong hole")
    }

    private fun northAight(position: Position): Boolean {
        if (position.x-1 < 0) return false
        if (grid[position.x-1][position.y] in listOf('|','7', 'F', 'S')) return true
        return false
    }

    private fun eastAight(position: Position): Boolean {
        if (position.y+1 > grid[0].size-1) return false
        if (grid[position.x][position.y+1] in listOf('-','7', 'J', 'S')) return true
        return false
    }

    private fun southAight(position: Position): Boolean {
        if (position.x+1 > grid.size-1) return false
        if (grid[position.x+1][position.y] in listOf('|','L', 'J', 'S')) return true
        return false
    }

    private fun westAight(position: Position): Boolean {
        if (position.y-1 < 0) return false
        if (grid[position.x][position.y-1] in listOf('-','F', 'L', 'S')) return true
        return false
    }

    private fun findStart(): Position {
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'S') return Position(x = lineIndex, y = charIndex)
            }
        }
        throw Exception("hehe")
    }
}
