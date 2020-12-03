#!/usr/bin/env kscript
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

println("Advent of Code Day 3")

val input = parseInput(Paths.get("input.txt"))

var treeCounter = 0

var x = 0
var y = 0

while (true) {

    x += 3
    y += 1

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
    position.hitten = true

}

input.forEach {
    it.forEach {
        val char = if (it.isTree && it.hitten) 'X' else if (it.isTree && !it.hitten) '#' else if (!it.isTree && it.hitten) 'O' else '.'
        print(char)
    }
    println()
}

println("Found $treeCounter trees!")

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

class Position(val isTree: Boolean, var hitten: Boolean = false)
