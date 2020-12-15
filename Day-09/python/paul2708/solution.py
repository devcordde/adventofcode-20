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
        aoc_print(f"The first number that does not have a pair is {numbers[preamble + start]}.")
        assert_equals(22406676, numbers[preamble + start])
        break

    start += 1

# Part two
invalid_number = numbers[preamble + start]

set_start = -1
set_end = -1

for i in range(0, preamble + start):
    total_sum = 0
    for j in range(i, preamble + start):
        total_sum += numbers[j]

        if total_sum == invalid_number:
            set_start = i
            set_end = j
            break
        elif total_sum > invalid_number:
            break

    if set_start != -1:
        break

min_set = min(numbers[set_start:set_end])
max_set = max(numbers[set_start:set_end])

aoc_print(f"The min value ({min_set}) and max value ({max_set}) sums up to {min_set + max_set}.")
assert_equals(2942387, min_set + max_set)
