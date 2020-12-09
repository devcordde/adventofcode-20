package de.nycode.aoc2020.day08

import java.nio.file.Files
import java.nio.file.Paths

val instructions = Files.readAllLines(Paths.get("input.txt"))
        .map { it.split(" ") }
        .mapIndexed { index, splitten -> Instruction(index, splitten[0], splitten[1].toInt()) }
        .toTypedArray()

fun main() {
    val program = Program(instructions.createCopy())
    val result = program.execute()
    println("accumulator is at ${result.accumulator}")

    val alternativeProgramResult = instructions
            .filter { it.type in arrayOf("jmp", "nop") }
            .map {
                val copiedProgram = Program(instructions.createCopy())
                copiedProgram.instructions[it.index].flipType("jmp", "nop")
                copiedProgram.execute()
            }
            .firstOrNull { it.exitCode == 0 }

    println("accumulator with fixed program is ${alternativeProgramResult?.accumulator}")
}

fun Instruction.execute(program: Program): Int {
    this.executions++
    return when (type) {
        "acc" -> {
            program.accumulator += this.value
            1
        }
        "jmp" -> {
            this.value
        }
        else -> {
            1
        }
    }
}

fun Array<Instruction>.createCopy() = this.map { it.copy() }.toTypedArray()

fun Program.execute(): ExecutionResult {
    var currentInstruction = 0

    while (currentInstruction < this.instructions.size) {
        val instruction = this.instructions[currentInstruction]
        if (instruction.executions > 0) {
            return ExecutionResult(accumulator, -1)
        }
        currentInstruction += instruction.execute(this)
    }
    return ExecutionResult(accumulator, 0)
}

fun Instruction.flipType(first: String, second: String) {
    when (this.type) {
        first -> {
            this.type = second
        }
        second -> {
            this.type = first
        }
    }
}

data class Program(val instructions: Array<Instruction>, var accumulator: Int = 0)
data class ExecutionResult(val accumulator: Int, val exitCode: Int)
data class Instruction(var index: Int, var type: String, val value: Int, var executions: Int = 0) : Cloneable