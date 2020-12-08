from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print


class Program():
    def __init__(self, instructions: [str]):
        self.accumulator = 0
        self.instruction_pointer = 0
        self.instructions = list(instructions)

    def run(self):
        instruction = instructions[self.instruction_pointer]
        op_code = instruction.split(" ")[0]

        self.instruction_pointer += 1

        if op_code == "nop":
            pass
        elif op_code == "jmp":
            self.instruction_pointer += int(instruction.split(" ")[1]) - 1
        elif op_code == "acc":
            self.accumulator += int(instruction.split(" ")[1])
        else:
            print("invalid instruction")

    def run_all(self):
        while self.instruction_pointer < len(instructions):
            self.run()

    def terminates(self) -> bool:
        executed_lines = set()

        while self.instruction_pointer not in executed_lines:
            executed_lines.add(program.instruction_pointer)

            if self.instruction_pointer >= len(self.instructions):
                return True

            self.run()

        return False


instructions = read_lines("day08")
program = Program(instructions)

# Part one
program.terminates()

aoc_print(f"The program gets interrupted with an accumulator of {program.accumulator}.")
assert_equals(1801, program.accumulator)


# Part two
def replace_all(base_list: [str], old: str, new: str) -> [[str]]:
    total = list()
    index = 0

    while True:
        tup = replace(list(base_list), old, new, index)
        if tup[1] == -1:
            break

        total.append(tup[0])
        index = tup[1]

    return total


def replace(replacement_list: [str], old: str, new: str, start: int) -> ([str], int):
    for i in range(start, len(replacement_list)):
        if old in replacement_list[i]:
            replacement_list[i] = replacement_list[i].replace(old, new)
            return replacement_list, i + 1

    return [], -1


replaced_instructions_list = replace_all(instructions, "jmp", "nop") \
                             + replace_all(instructions, "nop", "jmp")

for instructions in replaced_instructions_list:
    program = Program(instructions)
    if program.terminates():
        program = Program(instructions)
        program.run_all()

        aoc_print(f"The program terminates with an accumulator of {program.accumulator}.")
        assert_equals(2060, program.accumulator)
