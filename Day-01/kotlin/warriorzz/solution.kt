import java.io.File

fun main() {
    var partOne = false
    var partTwo = false

    val file = File("YOUR-PATH-TO-FILE-HERE") //Just copy the output of the website in this file
    val inputArray: Array<Int> = Array(file.bufferedReader().lines().count().toInt()) { i -> i * 1000 }
    val inputArray2: List<String> = file.bufferedReader().readLines()

    for (i in inputArray.indices)
        inputArray[i] = inputArray2[i].toInt()

    for (i in inputArray)
        for (j in inputArray) {
            if (i + j == 2020 && !partOne) {
                println("" + i + " * " + j + " = " + i * j)
                partOne = true
            }
            for (k in inputArray)
                if (i + j + k == 2020 && !partTwo) {
                    println("" + i + " * " + j + " * " + k + " = " + i * j * k)
                    partTwo = true
                }
        }
}