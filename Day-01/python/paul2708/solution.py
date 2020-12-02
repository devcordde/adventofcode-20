from assertions import assert_equals
from input_reader import read_ints
from printer import aoc_print

ints = read_ints("day01")

# Part one
res = [x * y for x in ints for y in ints if x + y == 2020]
aoc_print(f"The first product is {res[0]}.")

assert_equals(1003971, res[0])

# Part two
res = [x * y * z for x in ints for y in ints for z in ints if x + y + z == 2020]
aoc_print(f"The second product is {res[0]}.")

assert_equals(84035952, res[0])
