from typing import List


def read_split_lines(day: str) -> List[List[str]]:
    total_groups = []
    group = []
    for line in read_lines(day):
        if not line.strip():
            total_groups.append(group)
            group = []
            continue

        group.append(line)

    total_groups.append(group)

    return total_groups


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
