/*
*
* kotlinc> :load day3.kt
*
*/

// Part I:
//val diagnosticReport = java.io.File("day3_input").readLines()
//val diagnosticLength = diagnosticReport[0].length - 1
//val gammaRate = doit({ it.value.size.toDouble() })
//val epsilonRate = doit({ 1.0 / it.value.size })
//
//println(gammaRate * epsilonRate)
//
//fun doit(selector: (Map.Entry<Char, List<Char>>) -> Double) = Integer.parseInt((0..diagnosticLength).map { digit ->
//    diagnosticReport
//            .map { it.toCharArray()[digit] }
//            .groupBy { it }
//            .maxBy(selector)
//            ?.key
//}.joinToString(""), 2)

// Part II:

val diagnosticReport = java.io.File("day3_input").readLines()
val diagnosticLength = diagnosticReport[0].length - 1

println(
        rating({ 1.0 * it.size }, '1') *
                rating({ 1.0 / it.size }, '0')
)

fun rating(comparator: (List<Char>) -> Double, preferredDigit: Char): Int {
    var position = 0
    var matches = diagnosticReport
    while (matches.size > 1) {
        val frequentByPosition = frequentByComparator(matches, { it.value.size.toDouble() }, comparator, preferredDigit)
        matches = matches.filter { it[position] == frequentByPosition[position] }
        position++
    }
    return Integer.parseInt(matches.first(), 2)
}

fun frequentByComparator(
        entries: List<String>,
        selector: (Map.Entry<Char, List<Char>>) -> Double,
        comparator: (List<Char>) -> Double,
        preferredDigit: Char,
) = (0..diagnosticLength).map { digit ->
    entries
            .map { it.toCharArray()[digit] }
            .partition { it == preferredDigit }
            .toList()
            .filter { it.isNotEmpty() }.maxBy(comparator)
            ?.first()
}