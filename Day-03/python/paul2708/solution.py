from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print


class Map:
    TREE = "#"
    SQUARE = "."

    def __init__(self, tree_map: [str]):
        self.width = len(tree_map[0])
        self.height = len(tree_map)
        self.tree_map = tree_map

    def get(self, x, y) -> str:
        return self.tree_map[y][x % self.width]

    def is_tree(self, x, y) -> bool:
        return self.get(x, y) == self.TREE

    def get_height(self) -> int:
        return self.height


# Part one
tree_map = Map(read_lines("day03"))
x_slope = 3
y_slope = 1

x = 0
y = 0

trees = 0
while y < tree_map.get_height():
    if tree_map.is_tree(x, y):
        trees += 1

    x += x_slope
    y += y_slope

aoc_print(f"You would hit {trees} trees. (ouch)")
assert_equals(151, trees)

# Part two
