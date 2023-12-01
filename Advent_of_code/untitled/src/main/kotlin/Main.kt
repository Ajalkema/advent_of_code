fun main() {
    val day01 = Day01()
    day01.calculatePart1().println()
    day01.calculatePart2().println()

    val day01Alt = Day01_alt()
    day01Alt.part01(readInput("Data-1A")).println()
    day01Alt.part02(readInput("Data-1A")).println()
}
