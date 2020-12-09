package de.nycode.aoc2020.day09

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val numbers = Files.readAllLines(Paths.get("input.txt"))
            .map { it.toLongOrNull() ?: error("Invalid input $it") }

    val invalidNumber = findInvalidNumber(numbers, 25)
    println("Invalid Number: $invalidNumber")
}

fun findInvalidNumber(numbers: List<Long>, preambleCount: Int): Long? {
    val data = numbers.subList(preambleCount, numbers.size - 1)
    val preamble = numbers.take(preambleCount).toMutableList()

    var invalidNumber: Long? = null

    for (number in data) {
        if (!preamble.isCalculable(number)) {
            invalidNumber = number
            break
        }
        preamble.removeFirst()
        preamble.add(number)
    }
    return invalidNumber
}

fun MutableList<Long>.isCalculable(long: Long): Boolean {
    this.forEachIndexed { aIndex, a ->
        this.forEachIndexed { bIndex, b ->
            if (aIndex == bIndex) return@forEachIndexed
            if (a + b == long)
                return true
        }
    }
    return false
}