// Part I:
//val score = lastCardToWin!!.key?.flatten()!!.filterNot { allDrawnNumbers.subList(0, winningQuantity!!).contains(it) }.sum() * winningNumber
//println("score: " + score)

//val winningQuantity = (1..allDrawnNumbers.size).forEach { quantityCalled ->
//    println("numbers called: ${allDrawnNumbers.subList(0, quantityCalled)}")
//    println("card result: ${getWinningCard(allDrawnNumbers.subList(0, quantityCalled))}")
//    println("winning card #: ${cards.indexOf(getWinningCard(allDrawnNumbers.subList(0, quantityCalled)))}")
//    getWinningCard(allDrawnNumbers.subList(0, quantityCalled)) != null
//}
//println("***")
//val winningNumber = allDrawnNumbers[winningQuantity!! - 1]
//val winningCard = getWinningCard(allDrawnNumbers.subList(0, winningQuantity!!))
//println(winningNumber)
//println(winningCard)

//val score = winningCard?.flatten()!!.filterNot { allDrawnNumbers.subList(0, winningQuantity!!).contains(it) }.sum() * winningNumber
//println("score: " + score)


val input = java.io.File("day4_input").readLines()

val allDrawnNumbers = input[0].split(",").map { it.toInt() }

val cards = input.drop(2)
        .filter { it.length > 0 }
        .map {
            it.split(" ").filter { it.isNotEmpty() }
        }
        .map { it.map { it.toInt() } }
        .chunked(5)

val cardsToWinningQuantity = cards.associateWith { card ->
    val howManyNumbersToWin = (1..allDrawnNumbers.size).find { quantityCalled ->
        val numbersDrawn = allDrawnNumbers.subList(0, quantityCalled)
        listOf(getColumns(card), getRows(card))
                .flatten()
                .map { it.filter { number -> numbersDrawn.contains(number) } }
                .map { it.size }
                .any { it == 5 }
    }
    howManyNumbersToWin!!
}
val lastCardToWin = cardsToWinningQuantity.maxByOrNull { it.value }
val winningCardNumbers = lastCardToWin!!.key!!.flatten()
val qtyCalledToWin = lastCardToWin!!.value
val winningNumber = allDrawnNumbers[qtyCalledToWin - 1]

val score = winningCardNumbers.filterNot { allDrawnNumbers.subList(0, qtyCalledToWin!!).contains(it) }.sum() * winningNumber
println("score: $score")


fun getWinningCard(numbersDrawn: List<Int>) = cards.find { card: List<List<Int>> ->
    listOf(getColumns(card), getRows(card))
            .flatten()
            .map { it.filter { number -> numbersDrawn.contains(number) } }
            .map { it.size }
            .any { it == 5 }
}

fun getColumns(card: List<List<Int>>) = (0..4).map { column -> card.map { it[column] } }
fun getRows(card: List<List<Int>>) = card.map { (0..4).map { column -> it[column] } }
