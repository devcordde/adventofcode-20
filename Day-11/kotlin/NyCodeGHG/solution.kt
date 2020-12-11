package de.nycode.aoc2020.day11

import java.nio.file.Files
import java.nio.file.Paths

fun main() {

    val seats: Array<Array<Char>> = Files.readAllLines(Paths.get("input.txt"))
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()

    while (true) {
        if (seats.runSimulation().isEmpty())
            break
    }

    val occupiedSeats = seats.flatten().filter { it == '#' }.size
    println(occupiedSeats)
}

fun Array<Array<Char>>.runSimulation(): Map<Pair<Int, Int>, Char> {

    val changes = mutableMapOf<Pair<Int, Int>, Char>()

    this.forEachIndexed { x, array ->
        array.forEachIndexed { y, seat ->
            if (seat == '.')
                return@forEachIndexed

            val neighbors = this.getNeighbours(x, y)

            if (seat == 'L' && neighbors.all { it == 'L' || it == '.' }) {
                changes[x to y] = '#'
            }

            if (seat == '#' && neighbors.filter { it == '#' }.size >= 4) {
                changes[x to y] = 'L'
            }
        }
    }
    changes.forEach { (position, state) ->
        val (x, y) = position
        this[x][y] = state
    }

    return changes
}

fun Array<Array<Char>>.getNeighbours(x: Int, y: Int): Array<Char> {
    return arrayOf(
            this.getOffsetSeat(x - 1, y - 1), // Top Left
            this.getOffsetSeat(x - 1, y), // Center Left
            this.getOffsetSeat(x - 1, y + 1), // Bottom Left
            this.getOffsetSeat(x, y + 1), // Bottom Center
            this.getOffsetSeat(x + 1, y + 1), // Bottom Right
            this.getOffsetSeat(x + 1, y), // Center Right
            this.getOffsetSeat(x + 1, y - 1), // Top Right
            this.getOffsetSeat(x, y - 1), // Top Center
    )
}

fun Array<Array<Char>>.getOffsetSeat(offsetX: Int, offsetY: Int): Char {
    if (offsetX >= this.size || offsetX < 0)
        return '.' // Return floor

    val column = this[offsetX]
    if (offsetY >= column.size || offsetY < 0)
        return '.'

    return column[offsetY]
}