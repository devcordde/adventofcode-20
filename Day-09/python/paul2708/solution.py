from assertions import assert_equals
from input_reader import read_ints
from printer import aoc_print

numbers = read_ints("day09")
preamble = 25  # has to be 5 if you run the test set
start = 0

# Part one
while True:
    pairs = [(x, y) for x in numbers[start:preamble + start] for y in numbers[start:preamble + start] if
             x != y and x + y == numbers[preamble + start]]

    if len(pairs) == 0:
        aoc_print(f"The first number that does not have a pair is {numbers[preamble + start]}")
        assert_equals(22406676, numbers[preamble + start])
        break

    start += 1

# Part two
