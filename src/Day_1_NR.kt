import java.io.File

fun main(args: Array<String>) {
    val sequence = mutableListOf<Int>()
    val puzzleText = mutableListOf<String>()
    var firstDigit: Int = 0
    var secondDigit: Int = 0
    var reversed: Boolean = false
    var finalNumber: Int = 0

    fun solvePuzzleInput (puzzleInput: String): Int {
        val sliceAndDice = puzzleInput.toCharArray()
        val diceAndSlice = puzzleInput.reversed()

        for (character in sliceAndDice) {

            if (character.isDigit()) {
                if (!reversed){
                    firstDigit = character.digitToInt() * 10
                    reversed = true
                    solvePuzzleInput(diceAndSlice)
                    break
                } else {
                    secondDigit = character.digitToInt() * 1
                    break
                }
            }
        }
        if (reversed) {
            reversed = false
            val total = firstDigit + secondDigit
        }
        return firstDigit + secondDigit
    }

    fun getPuzzleLines () {
        File("src/main/kotlin/PuzzleInput.txt").useLines { lines -> lines.forEach {puzzleText.add(it) }}
        for (line in puzzleText) {
            sequence.add(solvePuzzleInput(line))
        }
    }

    fun sumDigits (): Int {
        return sequence.sum();
    }

    getPuzzleLines()
    finalNumber = sumDigits()
    println("The number is $finalNumber")
}