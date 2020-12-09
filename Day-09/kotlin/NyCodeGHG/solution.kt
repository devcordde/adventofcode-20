package de.nycode.aoc2020.day09

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

fun main() {
    val numbers = Files.readAllLines(Paths.get("input.txt"))
            .map { it.toLongOrNull() ?: error("Invalid input $it") }

    val time = measureTimeMillis {
        val invalidNumber = findInvalidNumber(numbers, 25) ?: error("Unknown error")
        println("Invalid Number: $invalidNumber")

        val weakness = findEncryptionWeakness(numbers, invalidNumber)
        println("Encryption weakness is: $weakness")
    }
    println("Took $time ms")
}

fun findInvalidNumber(numbers: List<Long>, preambleCount: Int): Long? {
    val data = numbers.subList(preambleCount, numbers.size)
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

fun findEncryptionWeakness(numbers: List<Long>, invalidNumber: Long): Long? {
    numbers.forEachIndexed { index, number ->
        val contiguous = mutableListOf<Long>()
        numbers.subList(index, numbers.size).forEach {
            contiguous.add(it)
            val sum = contiguous.sum()
            if (sum > invalidNumber) {
                return@forEachIndexed
            } else if (sum == invalidNumber) {
                return contiguous.minOrNull()!! + contiguous.maxOrNull()!!
            }
        }
    }
    return null
}
