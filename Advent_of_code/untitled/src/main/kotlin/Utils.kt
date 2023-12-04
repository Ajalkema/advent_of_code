import java.io.File


fun readInput(name: String) = File("src/main/resources/$name.txt").readLines()

fun Any?.println() = println(this)

class Counter(var count: Int = 0) {}
