package de.nycode.aoc2020.day06

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val answers = ArrayList<Answer>()

    var answer = Answer()
    val lines = Files.readAllLines(Paths.get("input.txt"))

    var index = 0
    var lastLineCount = 0

    while (index < lines.size) {
        val line = lines[index]

        if (line.isBlank()) {
            index++
            continue
        }

        answer.people = ++lastLineCount
        answer.questions.addAll(line.toCharArray().toMutableSet())

        if (++index < lines.size) {
            if (lines[index].isBlank()) {
                answers.add(answer)
                answer = Answer()
                lastLineCount = 0
            }
        } else {
            answers.add(answer)
            answer = Answer()
            lastLineCount = 0
        }
    }

    println(answers.map { it.questions.size }.sum())

    val groups = ArrayList<Group>()
    var group = Group()
    index = 0
    lastLineCount = 0

    while (index < lines.size) {
        val line = lines[index]

        if (line.isBlank()) {
            index++
            continue
        }

        group.answers[++lastLineCount] = Answer(line.toCharArray().toMutableSet(), lastLineCount)

        if (++index < lines.size) {
            if (lines[index].isBlank()) {
                groups.add(group)
                group = Group()
                lastLineCount = 0
            }
        } else {
            groups.add(group)
            group = Group()
            lastLineCount = 0
        }
    }

    println(groups.map { aGroup ->
        val questions = aGroup.answers.map { it.value.questions }.flatten().toMutableSet()
        questions.removeIf { char ->
            aGroup.answers.filter { !it.value.questions.contains(char) }.isNotEmpty()
        }
        questions.size
    }.sum())
}

data class Group(val answers: MutableMap<Int, Answer> = mutableMapOf())

data class Answer(val questions: MutableSet<Char> = mutableSetOf(), var people: Int = 0)
