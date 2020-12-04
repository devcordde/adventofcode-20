from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print

passport_keys = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"}


def is_valid(passport: dict) -> bool:
    keys = set(passport.keys())
    keys.add("cid")

    return keys == passport_keys


passports = []

# Parse passports
passport = {}
for line in read_lines("day04"):
    if not line.strip():
        passports.append(passport)
        passport = {}
        print("empty")
        continue

    print(line)
    for pair in line.split(" "):
        passport[pair.split(":")[0]] = pair.split(":")[1]

passports.append(passport)

# Part one
count_valid_passports = len([x for x in passports if is_valid(x)])

aoc_print(f"The passport list contains {count_valid_passports} valid passports.")
assert_equals(239, count_valid_passports)

# Part two
