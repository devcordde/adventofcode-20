from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print

lines = read_lines("day02")

# Part one
valid_passwords = 0

for line in lines:
    parts = line.split(" ")

    lower_bound = int(parts[0].split("-")[0])
    upper_bound = int(parts[0].split("-")[1])

    letter = parts[1][:-1]

    password = parts[2]

    letter_count = len([x for x in password if x == letter])

    if lower_bound <= letter_count <= upper_bound:
        valid_passwords += 1

aoc_print(f"{valid_passwords} passwords are valid.")
