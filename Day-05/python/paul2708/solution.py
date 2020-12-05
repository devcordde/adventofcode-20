from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print
import math

lines = read_lines("day05")


def search(line: str) -> (int, int):
    lower_bound = 0
    upper_bound = 127

    for letter in line:
        if letter == "F":
            upper_bound = math.floor((lower_bound + upper_bound) / 2)
        elif letter == "B":
            lower_bound = math.ceil((lower_bound + upper_bound) / 2)

    column = upper_bound

    lower_bound = 0
    upper_bound = 7

    for letter in line:
        if letter == "L":
            upper_bound = math.floor((lower_bound + upper_bound) / 2)
        elif letter == "R":
            lower_bound = math.ceil((lower_bound + upper_bound) / 2)

    row = upper_bound

    return column, row


# Part one
result = max([coord[0] * 8 + coord[1] for coord in list(map(search, lines))])

aoc_print(f"The highest seat ID on the boarding pass is {result}.")
assert_equals(878, result)

# Part two
