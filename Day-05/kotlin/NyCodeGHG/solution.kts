import java.nio.file.Files
import java.nio.file.Paths

val BACK_KEY = 'B'
val RIGHT_KEY = 'R'

val ids = Files.readAllLines(Paths.get("input.txt"))
    .map { line ->

        val chars = line.toCharArray()

        var range = 0..127

        var index = 0
        while (index < 7) {
            val char = chars[index]
            range = calculate(range, char == BACK_KEY)
            index++
        }

        if (range.start != range.endInclusive) {
            error("Not the same number!")
        }
        val row = range.start

        range = 0..7
        while (index < 10) {
            val char = chars[index]
            range = calculate(range, char == RIGHT_KEY)
            index++
        }
        if (range.start != range.endInclusive) {
            error("Not the same number!")
        }
        val column = range.start

        row * 8 + column
    }

val highest = ids.maxOrNull() ?: error("Cannot get highest seat id")
val lowest = ids.minOrNull() ?: error("Cannot get lowest seat id")

println("Highest seat id is $highest")

fun calculate(range: IntRange, upper: Boolean): IntRange {
    val difference = range.endInclusive - range.start

    return if (upper) {
        (range.endInclusive - difference / 2)..range.endInclusive
    } else {
        range.first..(range.start + difference / 2)
    }
}

val allIds = (lowest..highest).map { it }.toList()

val missing = allIds.filter { it !in ids }.first()

println("Youre seat id is $missing")
