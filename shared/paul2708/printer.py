C_RED = '\033[91m'
C_GREEN = '\33[32m'
C_END = '\033[0m'


def aoc_print(msg: str):
    print(f"{C_RED}[{C_GREEN}AoC{C_RED}]{C_END} {msg}")
