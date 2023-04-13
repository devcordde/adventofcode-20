original = [5, 1, 9, 18, 13, 8, 0]

data = dict()

offset = 1
for value in original:
    data[value] = offset
    offset += 1
length = len(data)

next = 0

# part1: end = 2020
#part2
end = 30000000

for i in range(len(data) + 1, end):
    if next in data.keys():
        offset = i - data[next]
        data[next] = i
        next = offset
    else:
        data[next] = i
        next = 0

print(str(next))