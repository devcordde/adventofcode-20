import java.io.File

fun main() {
    val file = File("./src/main/kotlin/day-02-input")

    val inputArray: List<String> = file.bufferedReader().readLines()

    var counter = 0

    for(i in inputArray.indices) {
        val split: List<String> = inputArray[i].split(" ")

        val numbers = split[0]
        val letter = split[1].replace(":", "")
        val password = split[2]

        var charCounter = 0
        for(char in password.subSequence(password.indices)) {
            if(char == letter[0])
                charCounter++
        }

        if(charCounter <= numbers.split("-")[1].toInt() && charCounter >= numbers.split("-")[0].toInt())
            counter++
    }

    println("PartOne:")
    println(counter)

    counter = 0
    for(i in inputArray.indices) {
        val split: List<String> = inputArray[i].split(" ")

        val numbers = split[0]
        val letter = split[1].replace(":", "")
        val password = split[2]

        val firstNumber = numbers.split("-")[0].toInt()
        val secondNumber = numbers.split("-")[1].toInt()

        if (password[firstNumber -1] == letter[0]) {
            if (password[secondNumber -1] != letter[0]) {
                counter++
            }
        } else {
            if (password[secondNumber -1] == letter[0]) {
                counter++
            }
        }
    }
    println("PartTwo:")
    println(counter)
}