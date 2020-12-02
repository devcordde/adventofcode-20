import re
lines = []
with open("PATH") as f:
    lines = f.readlines()

valid_1 = 0
valid_2 = 0

pattern = '([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)'

for i in lines:
    match = re.search(pattern, i)
    min = int(match.group(1))
    max = int(match.group(2))
    char = match.group(3)
    pw = match.group(4)

    count = pw.count(char)

    if count >= min and count <= max:
        valid_1 += 1
    
    # part 2

    if pw[min - 1] == char and not pw[max - 1] == char:
        valid_2 += 1
    if pw[max - 1] == char and not pw[min - 1] == char:
        valid_2 += 1

print(valid_1)
print(valid_2)