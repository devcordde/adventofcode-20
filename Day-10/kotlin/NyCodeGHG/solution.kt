package de.nycode.aoc2020.day10

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val adapters: List<Int> = Files.readAllLines(Paths.get("input.txt"))
            .map { it.toIntOrNull() ?: error("Invalid input $it") }
            .plus(0)
            .run {
                plus(maxOrNull()!! + 3)
            }.sorted()

    val partOneSolution = adapters
            .asSequence()
            .zipWithNext()
            .map { it.second - it.first }
            .groupingBy { it }
            .eachCount()
            .run {
                (this[1] ?: 1) * (this[3] ?: 1)
            }
    println(partOneSolution)

    val paths = mutableMapOf(0 to 1L)
    adapters.drop(1).forEach {
        paths[it] = (1..3)
                .map { last -> paths[it - last] ?: 0 }
                .sum()
    }
    println(paths[adapters.last()])
}
