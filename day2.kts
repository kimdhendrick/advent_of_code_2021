/*
*
* kotlinc> :load day2.kt
*
*/
// Part I:
// forward 5 adds 5 to your horizontal position, a total of 5.
// down 5 adds 5 to your depth, resulting in a value of 5.
// forward 8 adds 8 to your horizontal position, a total of 13.
// up 3 decreases your depth by 3, resulting in a value of 2.
// down 8 adds 8 to your depth, resulting in a value of 10.
// forward 2 adds 2 to your horizontal position, a total of 15.

// forward: 15

// After following these instructions, you would have a horizontal position of 15 and a depth of 10.
// Multiplying these together produces 150.

//val commands = java.io.File("day2_input").readLines()
//
//println(sumOf("forward") * (sumOf("down") - sumOf("up")))
//
//
//fun sumOf(direction: String, pattern: Regex = """($direction) (\d+)""".toRegex()): Int =
//        commands
//                .filter { it.matches(pattern) }
//                .map { pattern.find(it)!!.destructured.component2().toInt() }
//                .sum()

// Part II:
// forward 5 adds 5 to your horizontal position, a total of 5. Because your aim is 0, your depth does not change.
// down 5 adds 5 to your aim, resulting in a value of 5.
// forward 8 adds 8 to your horizontal position, a total of 13. Because your aim is 5, your depth increases by 8*5=40.
// up 3 decreases your aim by 3, resulting in a value of 2.
// down 8 adds 8 to your aim, resulting in a value of 10.
// forward 2 adds 2 to your horizontal position, a total of 15. Because your aim is 10, your depth increases by 2*10=20 to a total of 60.
// After following these new instructions, you would have a horizontal position of 15 and a depth of 60. (Multiplying these produces 900.)

val FORWARD = "forward"
val DOWN = "down"
val UP = "up"

val commands = java.io.File("day2_test_input").readLines()
val commandPattern: Regex = """(\w+) (\d+)""".toRegex()

var depth: Int = 0
var forward: Int = 0

commands
        .forEachIndexed { index, command ->
            when (direction(command)) {
                FORWARD -> {
                    val distance = distance(command)
                    val aims = commands.subList(0, index).filter { direction(it) != FORWARD }
                    forward += distance
                    depth += distance * (sumOf(aims, DOWN) - sumOf(aims, UP))
                }
            }
        }

println(forward * depth)

fun direction(command: String): String = commandPattern.find(command)!!.destructured.component1()
fun distance(command: String): Int = commandPattern.find(command)!!.destructured.component2().toInt()

fun sumOf(list: List<String>, direction: String, pattern: Regex = """($direction) (\d+)""".toRegex()): Int =
        list
                .filter { it.matches(pattern) }
                .map { pattern.find(it)!!.destructured.component2().toInt() }
                .sum()