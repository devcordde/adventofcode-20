import java.nio.file.Files
import java.nio.file.Paths

val instructions = Files.readAllLines(Paths.get("input.txt"))
        .map { it.split(" ") }
        .map { Instruction(it[0], it[1].toInt()) }

var accumulator = 0

var currentInstruction = 0

while (currentInstruction < instructions.size) {
    val instruction = instructions[currentInstruction]
    if (instruction.executions > 0) {
        println("accumulator is at $accumulator")
        break
    }
    currentInstruction += instruction.execute()
}

fun Instruction.execute(): Int {
    this.executions++
    return when (type) {
        "acc" -> {
            accumulator += this.value
            1
        }
        "jmp" -> {
            this.value
        }
        "nop" -> {
            1
        }
        else -> {
            error("Invalid instruction type: $type")
        }
    }
}

data class Instruction(val type: String, val value: Int, var executions: Int = 0)