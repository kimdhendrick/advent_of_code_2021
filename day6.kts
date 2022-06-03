import java.io.File

var fishAges = File("day6_input").readLines().first().split(",").map { it.toInt() }

val frequencies = mutableMapOf<Int, Long>()

fishAges.groupingBy { it }.eachCount().forEach { entry ->
    frequencies.put(entry.key, entry.value.toLong())
}

val histogram: List<Long> = (0..8).map { frequencies.getOrDefault(it, 0L) }

var thing: List<Long> = histogram
(1..256).forEach { _day ->
    thing = (0..8).map { shiftIt(thing, it) }
}
val countFishies = thing.reduce { acc, i -> acc + i }
println(countFishies)

fun shiftIt(histogram: List<Long>, position: Int): Long {
    return when (position) {
        6 -> histogram[0] + histogram[7]
        8 -> histogram[0]
        else -> histogram[position + 1]
    }
}

//import java.io.File
//
//lateinit var numberFishSpawned: (Int, Int) -> Int
//
//var fishAges = File("day6_test_input").readLines().first().split(",").map { it.toInt() }
//
//tailrec fun newFish(fish: List<Int>, accumulatedFish: List<Int>): List<Int> {
//    val first = fish.take(1)
//
//    return when (fish.size) {
//        0 -> accumulatedFish
//        1 -> accumulatedFish + nextAge(first)
//        else -> newFish(fish.drop(1), accumulatedFish + nextAge(first))
//    }
//}
//
//lateinit var doit: (Int) -> Int
//
//fun <T, R> ((T) -> R).memoize(): ((T) -> R) {
//    val original = this
//    val cache = mutableMapOf<T, R>()
//
//    return { n: T -> cache.getOrPut(n) { original(n) } }
//}
//
//doit = { y: Int ->
//    return 3+y
//}.memoize<Int>()
//
//
//println(doit(3, 4))
//
////numberFishSpawned = { (dayNumber: Int, fishAge: Int): Int ->
////    (1..dayNumber).forEach { fishAges = newFish(listOf(fishAge), emptyList<Int>()) }
////    fishAges.size
////}.memoize<Int, Int>()
////
////println(numberFishSpawned(18, 0))
//
////(1..18).forEach { fishAges = newFish(fishAges, emptyList<Int>()) }
////println("${fishAges.size}")
//
//fun nextAge(ages: List<Int>): List<Int> =
//        if (ages.first() == 0) listOf(6, 8) else listOf(ages.first() - 1)
//
//////////////////////////////////
//
////val fishAges = File("day6_test_input").readLines().first().split(",").map { it.toInt() }
////println("$fishAges")
////
////val numDays = 18
////
////val totalNumFish: Int = (1..numDays).map { newFish ->
////    val newFish = fishAges.map { age ->
////        nextAge(age)
////    }.flatten()
////    println(newFish)
////    newFish
////}
////
////println(newFish)
////
////fun nextAge(age: Int): List<Int> =
////        if (age == 0) listOf(6, 8) else listOf(age - 1)