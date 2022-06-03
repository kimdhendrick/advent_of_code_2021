import kotlin.math.abs
import kotlin.math.min
import kotlin.ranges.IntProgression.Companion.fromClosedRange

val input = java.io.File("day5_input").readLines()

val coordinatePattern: Regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

val points = input.map {
    val (x1, y1, x2, y2) = coordinatePattern.find(it)!!.destructured
    val start = Pair(convertInput(x1), convertInput(y1))
    val end = Pair(convertInput(x2), convertInput(y2))

    pointsFrom(start, end)
}
val countDangerZones = points.flatten().groupingBy { it }.eachCount().filter { it.value > 1 }.size
println(countDangerZones)

fun convertInput(input: String): Int {
    return input.replace(",", "").toInt()
}

fun pointsFrom(start: Pair<Int, Int>, end: Pair<Int, Int>): List<Pair<Int, Int>> {
    val xDiff = abs(start.first - end.first)
    val yDiff = abs(start.second - end.second)
    val minSize = Integer.max(xDiff, yDiff) + 1
    val xBigger = xDiff > yDiff
    val yBigger = yDiff > xDiff

    val xValues = if (yBigger) {
        IntArray(minSize) { start.first }.map { it }
    } else {
        val xStep = stepFor(start.first, end.first)
        fromClosedRange(start.first, end.first, xStep).map { it }
    }

    val yValues = if (xBigger) {
        IntArray(minSize) { start.second }.map { it }
    } else {
        val yStep = stepFor(start.second, end.second)
        fromClosedRange(start.second, end.second, yStep).map { it }
    }

    return xValues zip yValues
}

fun stepFor(start: Int, end: Int): Int = if (start < end) 1 else -1
