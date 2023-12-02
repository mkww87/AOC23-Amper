import java.io.File

// sequence takes in the determined digits from each string using getPuzzleLines()
val sequence = mutableListOf<Int>()

// puzzleText accumulates each string into a mutable list to be passed into solvePuzzleInput()
val puzzleText = mutableListOf<String>()

// variables for first and second digits
var firstDigit: Int = 0
var secondDigit: Int = 0

// bool flag to direct logical flow in if statement located in solvePuzzleInput() if blocks
var reversed: Boolean = false

// result variable
var finalNumber: Int = 0

// takes one string as input, returns Int
fun solvePuzzleInput (puzzleInput: String): Int {
    val sliceAndDice = puzzleInput.toCharArray() // takes string, chop into individual Chars
    val diceAndSlice = puzzleInput.reversed() // takes string, reverse it and recursively pass line 30

    for (character in sliceAndDice) { // loop through all Chars

        if (character.isDigit()) { // if digit is found
            if (!reversed){ // and it has not been reversed yet
                firstDigit = character.digitToInt() * 10 // convert to Int, multiply by 10, assign to firstDigit
                reversed = true // change reverse flag
                solvePuzzleInput(diceAndSlice) // recall function with reversed string
                break // after recursion, break from loop
            } else { // enters else statement if reverse = true
                secondDigit = character.digitToInt() * 1 // convert to Int, multiply by 1, assign to secondDigit
                break // break from loop
            }
        }
    }
    if (reversed) { // if true
        reversed = false // make false to reset
    }
    return firstDigit + secondDigit // return combined digits (i.e. 1 + 0 = 10), this is passed to sequence array through getPuzzleLines()
}

fun getPuzzleLines () { // open PuzzleInput.txt file and passes one line at a time through solvePuzzleInput()
    File("src/main/kotlin/PuzzleInput.txt").useLines { lines -> lines.forEach {puzzleText.add(it) }}
    for (line in puzzleText) {
        sequence.add(solvePuzzleInput(line)) // one line at a time
    }
}

fun sumDigits (): Int {
    return sequence.sum(); // add all digits determined by solvePuzzleInput()
}

fun main(args: Array<String>) {
    getPuzzleLines() // start
    finalNumber = sumDigits() // add
    println("The number is $finalNumber") // display
}
