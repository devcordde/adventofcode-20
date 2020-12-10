package de.nycode.aoc2020.day02

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    println("Advent of Code Day 2")

    val input = parseInput(Paths.get("input.txt"))
    println("Checking ${input.size} cases")

    var validPasswords = 0

    input.forEach { pair ->
        val (rule, password) = pair

        val count = password.toCharArray()
                .filter { it == rule.character }
                .size

        if (count in rule.first..rule.second) {
            validPasswords++
        }
    }

    println("Found $validPasswords valid passwords for the old policy!")

    validPasswords = 0
    input.forEach { pair ->
        val (rule, password) = pair

        val count = password.toCharArray()
        val firstChar = count[rule.first - 1]
        val secondChar = count[rule.second - 1]

        if (firstChar == rule.character && secondChar != rule.character || firstChar != rule.character && secondChar == rule.character) {
            validPasswords++
        }
    }

    println("Found $validPasswords valid passwords for the Official Toboggan Corporate Policy!")
}

fun parseInput(path: Path): List<Pair<PolicyRule, String>> {
    return Files.readAllLines(path, StandardCharsets.UTF_8)
            .map {
                val parts = it.split(" ")

                val numbers = parts[0].split("-")
                val first = numbers[0].toInt()
                val second = numbers[1].toInt()

                val character = parts[1].substring(0, 1)
                        .toCharArray()
                        .first()

                val password = parts[2]
                PolicyRule(first, second, character) to password
            }
            .toList()
}

data class PolicyRule(val first: Int, val second: Int, val character: Char)
