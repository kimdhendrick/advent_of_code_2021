/*
*
* kotlinc> :load day1.kt
*
*/

// Part I:
//val depths = java.io.File("day1_input").readLines().map { it.toInt() }
//val increases = depths
//        .filterIndexed { index, depth -> index > 0 && depth > depths[index - 1] }
//println(increases.size)

// Part II:
val depths = java.io.File("day1_input").readLines().map { it.toInt() }
val windowSums = depths
        .drop(2)
        .mapIndexed { index, depth -> depth + depths[index] + depths[index + 1] }

val increases = windowSums
        .filterIndexed { index, windowSum -> index > 0 && windowSum > windowSums[index - 1] }

println(increases.size)
