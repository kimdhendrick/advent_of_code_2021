import java.io.File

var crabPositions = File("day7_input").readLines().first().split(",").map { it.toInt() }

val result = (1..crabPositions.max()!!).map { position ->
    val sum = crabPositions.sumOf { crab: Int -> cost(kotlin.math.abs(crab - position)) }
    position to sum
}.toMap()

val min = result.minByOrNull { it.value }
println(min)

fun cost(distance: Int) = (1..distance).sumOf { it }