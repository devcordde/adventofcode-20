from file_reader import read_lines

lines = read_lines('day03.txt')
width = len(lines[0])
height = len(lines)

def count_trees(coords):
  trees_amount = 0
  x = 0
  y = 0
  
  while y < height:
    if lines[y][x] == '#':
      trees_amount += 1

    x += coords[0]
    y += coords[1]

    if x >= width:
      x %= width
  
  return trees_amount

# Part one
print(count_trees((3, 1)))

# Part two
coords_list = [(1, 1), (3, 1), (5, 1), (7, 1), (1, 2)]
result = 1

for coords in coords_list:
  result *= count_trees(coords)

print(result)
