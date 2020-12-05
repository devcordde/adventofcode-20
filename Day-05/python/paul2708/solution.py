from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print
import math


def binary_search(line: str, lower: int, upper: int, lower_char: str, upper_char: str) -> int:
    lower_bound = lower
    upper_bound = upper

    for letter in line:
        if letter == lower_char:
            upper_bound = math.floor((lower_bound + upper_bound) / 2)
        elif letter == upper_char:
            lower_bound = math.ceil((lower_bound + upper_bound) / 2)

    return lower_bound


def calc_seat(line: str) -> (int, int):
    return binary_search(line[0:7], 0, 127, "F", "B"), binary_search(line[7:10], 0, 7, "L", "R")


def calc_seat_id(seat: (int, int)) -> int:
    return seat[0] * 8 + seat[1]


lines = read_lines("day05")

# Calculate seats
seats = list(map(calc_seat, lines))

# Part one
result = max([calc_seat_id(seat) for seat in seats])

aoc_print(f"The highest seat ID on the boarding pass is {result}.")
assert_equals(878, result)

# Part two
sorted_seats = sorted(seats, key=lambda tup: (tup[0], tup[1]))
min_seat = min(sorted_seats)
max_seat = max(sorted_seats)

all_seats = [(row, column) for row in range(0, 128) for column in range(0, 8)]

missing_seat = (0, 0)
for seat in all_seats:
    if (min_seat <= seat <= max_seat) and (seat not in seats):
        missing_seat = seat
        break

aoc_print(f"The seat id of your seat is {calc_seat_id(missing_seat)}.")
assert_equals(504, calc_seat_id(missing_seat))
