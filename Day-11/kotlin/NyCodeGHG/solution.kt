package de.nycode.aoc2020.day11

import java.nio.file.Files
import java.nio.file.Paths

fun main() {

    var seats: Array<Array<Char>> = readSeats()

    while (true) {
        if (seats.runSimulation().isEmpty())
            break
    }

    val occupiedSeats = seats.flatten().filter { it == '#' }.size
    println(occupiedSeats)

    seats = readSeats()
    while (true) {
        if (seats.runSimulation(true).isEmpty())
            break
    }

    val occupiedSeats2 = seats.flatten().filter { it == '#' }.size
    println(occupiedSeats2)
}

fun readSeats(): Array<Array<Char>> {
    return Files.readAllLines(Paths.get("input.txt"))
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
}

fun Array<Array<Char>>.runSimulation(viewMode: Boolean = false): Map<Pair<Int, Int>, Char> {

    val changes = mutableMapOf<Pair<Int, Int>, Char>()

    this.forEachIndexed { x, array ->
        array.forEachIndexed { y, seat ->
            if (seat == '.')
                return@forEachIndexed

            val neighbors = if (!viewMode) this.getNeighbours(x, y) else this.getSeatsInView(x, y)

            if (seat == 'L' && neighbors.all { it == 'L' || it == '.' }) {
                changes[x to y] = '#'
            }

            if (seat == '#' && neighbors.filter { it == '#' }.size >= if (viewMode) 5 else 4) {
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

fun Array<Array<Char>>.getSeatInView(x: Int, y: Int, direction: Direction): Char {
    var counter = 1
    val (offsetX, offsetY) = direction

    while (true) {
        val posX = x + offsetX * counter
        val posY = y + offsetY * counter

        if (posX >= this.size || posX < 0)
            return '.'
        if (posY >= this[posX].size || posY < 0)
            return '.'

        val seat = this[posX][posY]
        if (seat != '.')
            return seat
        counter++
    }
}

fun Array<Array<Char>>.getSeatsInView(x: Int, y: Int): Array<Char> {
    return Direction.values().map { this.getSeatInView(x, y, it) }.toTypedArray()
}

enum class Direction(private val x: Int, private val y: Int) {
    TOP(0, 1),
    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    CENTER_LEFT(-1, 0),
    CENTER_RIGHT(1, 0),
    BOTTOM(0, -1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1);

    operator fun component1() = x
    operator fun component2() = y
}