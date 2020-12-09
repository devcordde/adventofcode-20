package de.nycode.aoc2020.day05

import java.nio.file.Files
import java.nio.file.Paths

const val BACK_KEY = 'B'
const val RIGHT_KEY = 'R'

fun main() {
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

                if (range.first != range.last) {
                    error("Not the same number!")
                }
                val row = range.first

                range = 0..7
                while (index < 10) {
                    val char = chars[index]
                    range = calculate(range, char == RIGHT_KEY)
                    index++
                }
                if (range.first != range.last) {
                    error("Not the same number!")
                }
                val column = range.first

                row * 8 + column
            }

    val highest = ids.maxOrNull() ?: error("Cannot get highest seat id")
    val lowest = ids.minOrNull() ?: error("Cannot get lowest seat id")
    println("Highest seat id is $highest")

    val allIds = (lowest..highest).map { it }.toList()
    val missing = allIds.first { it !in ids }
    println("Your seat id is $missing")
}

fun calculate(range: IntRange, upper: Boolean): IntRange {
    val difference = range.last - range.first

    return if (upper) {
        (range.last - difference / 2)..range.last
    } else {
        range.first..(range.first + difference / 2)
    }
}