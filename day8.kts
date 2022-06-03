// digit -> count segments
// 0 -> 6
// 1 -> 2 *
// 2 -> 5
// 3 -> 5
// 4 -> 4 *
// 5 -> 5
// 6 -> 6
// 7 -> 3 *
// 8 -> 7 *
// 9 -> 6
// 1, 4, 7, 8

/////////////////
//0:      1:      2:      3:      4:
// aaaa    ....    aaaa    aaaa    ....
//b    c  .    c  .    c  .    c  b    c
//b    c  .    c  .    c  .    c  b    c
// ....    ....    dddd    dddd    dddd
//e    f  .    f  e    .  .    f  .    f
//e    f  .    f  e    .  .    f  .    f
// gggg    ....    gggg    gggg    ....
//
//5:      6:      7:      8:      9:
// aaaa    aaaa    aaaa    aaaa    aaaa
//b    .  b    .  .    c  b    c  b    c
//b    .  b    .  .    c  b    c  b    c
// dddd    dddd    ....    dddd    dddd
//.    f  e    f  .    f  e    f  .    f
//.    f  e    f  .    f  e    f  .    f
// gggg    gggg    ....    gggg    gggg

val input = java.io.File("day8_test_input").readLines()

val total = input.map { line ->
    val signalSet = line.split("|").first().trim().split(" ")
    val one = signalSet.find { it.length == 2 }!!
    val four = signalSet.find { it.length == 4 }!!
    val seven = signalSet.find { it.length == 3 }!!
    val eight = signalSet.find { it.length == 7 }!!
    val fiveLengths = signalSet.filter { it.length == 5 }
    val three = fiveLengths.find { signal -> allMatching(one, signal) }!!
    val five = (fiveLengths - three).maxByOrNull { signal -> countMatching(four, signal) }!!
    val two = (fiveLengths - three - five).first()
    val sixLengths = signalSet.filter { it.length == 6 }
    val nine = sixLengths.find { signal -> allMatching(four, signal) }!!
    val zero = (sixLengths - nine).maxByOrNull { signal -> countMatching(one, signal) }!!
    val six = (sixLengths - nine - zero).first()!!
    val decoding = listOf(zero, one, two, three, four, five, six, seven, eight, nine).map { it.toSortedSet().joinToString("") }

    val numbers = line.split("|").last().trim().split(" ")

    numbers.map { number ->
        decoding.indexOf(number.toSortedSet().joinToString(""))
    }.joinToString("").toLong()
}

println(total.sum())

fun allMatching(matcher: String, signal: String) =
        matcher.toCharArray().all { it -> signal.toCharArray().contains(it) }

fun countMatching(matcher: String, signal: String) =
        matcher.toCharArray().count { it -> signal.toCharArray().contains(it) }