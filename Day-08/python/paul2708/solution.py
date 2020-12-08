from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print


class Program():
    def __init__(self, instructions: [str]):
        self.accumulator = 0
        self.instruction_pointer = 0
        self.instructions = instructions

    def run(self):
        instruction = instructions[self.instruction_pointer]
        op_code = instruction.split(" ")[0]

        if op_code == "nop":
            print("hehe")
        elif op_code == "jmp":
            self.instruction_pointer += int(instruction.split(" ")[1]) - 1
        elif op_code == "acc":
            self.accumulator += int(instruction.split(" ")[1])
        else:
            print("invalid instruction")

        self.instruction_pointer += 1


instructions = read_lines("day08")
program = Program(instructions)

# Part one
executed_lines = set()

while program.instruction_pointer not in executed_lines:
    executed_lines.add(program.instruction_pointer)
    program.run()

print(program.accumulator)

# Part two
