from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print

required_passport_keys = {
    "byr": lambda x: x.isdigit() and 1920 <= int(x) <= 2002,
    "iyr": lambda x: x.isdigit() and 2010 <= int(x) <= 2020,
    "eyr": lambda x: x.isdigit() and 2020 <= int(x) <= 2030,
    "hgt": lambda x: validate_height(x),
    "hcl": lambda x: validate_hair_color(x),
    "ecl": lambda x: x in {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"},
    "pid": lambda x: len(x) == 9 and 0 <= int(x) <= 999999999,
    "cid": lambda x: True
}


def validate_hair_color(val: str) -> bool:
    if val[0] != "#" or len(val) != 7:
        return False

    for i in range(1, 7):
        if val[i] not in {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                          "a", "b", "c", "d", "e", "f"}:
            return False

    return True


def validate_height(val: str) -> bool:
    if "cm" in val:
        return 150 <= int(val[:-2]) <= 193
    elif "in" in val:
        return 59 <= int(val[:-2]) <= 76
    else:
        return False


def is_present(passport: dict) -> bool:
    keys = set(passport.keys())
    keys.add("cid")

    return keys == set(required_passport_keys)


def is_valid(passport: dict) -> bool:
    for key in passport:
        if not required_passport_keys[key](passport[key]):
            return False

    return True


passports = []

# Parse passports
passport = {}
for line in read_lines("day04"):
    if not line.strip():
        passports.append(passport)
        passport = {}
        continue

    for pair in line.split(" "):
        passport[pair.split(":")[0]] = pair.split(":")[1]

passports.append(passport)

# Part one
count_valid_passports = len([x for x in passports if is_present(x)])

aoc_print(f"The passport list contains {count_valid_passports} valid passports.")
assert_equals(239, count_valid_passports)

# Part two
count_valid_passports = len([x for x in passports if is_present(x) and is_valid(x)])

aoc_print(f"The passport list contains {count_valid_passports} valid passports.")
assert_equals(188, count_valid_passports)
