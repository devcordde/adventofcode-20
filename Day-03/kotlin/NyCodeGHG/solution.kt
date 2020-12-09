package de.nycode.aoc2020.day03

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    println("Advent of Code Day 3")

    val input = parseInput(Paths.get("input.txt"))

    val results: Array<Int> = arrayOf(
            getTreeCountForPath(input, 1, 1),
            getTreeCountForPath(input, 3, 1),
            getTreeCountForPath(input, 5, 1),
            getTreeCountForPath(input, 7, 1),
            getTreeCountForPath(input, 1, 2),
    )

    var result = 1L

    results.forEach {
        result *= it
        println(it)
    }

    println("Result: $result")
}

fun getTreeCountForPath(input: Array<Array<Position>>, right: Int, down: Int): Int {
    var treeCounter = 0

    var x = 0
    var y = 0

    while (true) {

        x += right
        y += down

        if (y >= input.size) {
            break
        }

        if (x >= input[y].size) {
            x -= input[y].size
        }

        val position = input[y][x]
        if (position.isTree) {
            ++treeCounter
        }
    }
    return treeCounter
}

fun parseInput(path: Path): Array<Array<Position>> {
    try {
        return Files.readAllLines(path, StandardCharsets.UTF_8)
                .map {
                    it.toCharArray()
                            .map {
                                Position(it == '#')
                            }
                            .toTypedArray()
                }
                .toTypedArray()
    } catch (exception: NumberFormatException) {
        error("Invalid input ${exception.message}")
    }
}

class Position(val isTree: Boolean)
