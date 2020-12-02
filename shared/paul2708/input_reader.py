from typing import List


def read_ints(day: str) -> List[int]:
    file = open(f"../../../shared/paul2708/input/{day}.txt", "r")

    lines = file.read().splitlines()
    ints = []

    for i in range(len(lines) - 1):
        ints.append(int(lines[i]))

    file.close()
    return ints

def read_lines(day: str) -> List[str]:
    file = open(f"../../../shared/paul2708/input/{day}.txt", "r")

    lines = file.read().splitlines()

    file.close()
    return lines
