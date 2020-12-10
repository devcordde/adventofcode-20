package de.nycode.aoc2020.day01
import java.lang.NumberFormatException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    println("Advent of Code Day 1")

    val input = parseInput(Paths.get("input.txt"))

    findFirst(input)
    findSecond(input)
}

fun parseInput(path: Path): List<Int> {
    try {
        return Files.readAllLines(path, StandardCharsets.UTF_8)
                .map { it.toInt() }
    } catch (exception: NumberFormatException) {
        error("Invalid input ${exception.message}")
    }
}

fun findFirst(input: List<Int>) {
    var found = false

    input.forEachIndexed { index, a ->
        input.forEachIndexed { secondIndex, b ->
            if (index == secondIndex || found)
                return@forEachIndexed

            val calculatedValue = a + b
            if (calculatedValue == 2020) {
                println("Found first solution: ${a * b}")
                found = true
            }
        }
    }
}

fun findSecond(input: List<Int>) {
    var found = false

    input.forEachIndexed { index, a ->
        input.forEachIndexed { secondIndex, b ->
            input.forEachIndexed { thirdIndex, c ->
                if (index == secondIndex || index == thirdIndex || secondIndex == thirdIndex || found)
                    return@forEachIndexed

                val calculatedValue = a + b + c
                if (calculatedValue == 2020) {
                    println("Found second solution: ${a * b * c}")
                    found = true
                }
            }
        }
    }
}
