def part1(grid, right, down):
    i, j, count = 0, 0, 0

    # i as row, j as column

    while i < len(grid):
        if grid[i][j] == '#':
            count += 1

        j = (j + right) % len(grid[0])
        i = i + down

    return count

def part2(grid):
    ans = 1
    for element in [(1, 1), (3, 1), (5, 1), (7, 1), (1, 2)]:
        ans *= part1(grid, element[0], element[1])

    return ans
if __name__ == '__main__':
    with open('PATH') as f:
        grid = [line.strip('\n') for line in f]

        print(part1(grid, 3, 1))
        print(part2(grid))