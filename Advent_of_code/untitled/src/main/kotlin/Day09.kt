class Day09(input: List<String>) {

    private val data = input.map { it.split(" ").map { num -> num.toLong() } }.toMutableList()

    fun part1(): Long {
        return data.sumOf {
            findExtrapolatedNextNumber(it)
        }
    }

    fun part2(): Long{
        return data.sumOf {
            findExtrapolatedHistoricNumber(it)
        }
    }

    private fun findExtrapolatedNextNumber(differences: List<Long>): Long {
        val difOfDif = calculateDifferences(differences)
        if (!difOfDif.all { difOfDif[0] == it }) {
            difOfDif.add(findExtrapolatedNextNumber(difOfDif))
        }
        return difOfDif.last() + differences.last()
    }

    private fun findExtrapolatedHistoricNumber(differences: List<Long>): Long {
        val difOfDif = calculateDifferences(differences)
        if (!difOfDif.all { difOfDif[0] == it }) {
            difOfDif.add(0, findExtrapolatedHistoricNumber(difOfDif))
        }
        return differences.first() - difOfDif.first()
    }

    private fun calculateDifferences(line: List<Long>): MutableList<Long> {
        val lissa = MutableList(line.size - 1) { 0L }
        line.forEachIndexed { index, number ->
            if (index != line.size - 1) lissa[index] = line[index+1] - number
        }
        return lissa
    }
}