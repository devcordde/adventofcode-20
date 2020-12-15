def read():
    with open("PATH", "r", newline=None) as f:
        f = f.read().splitlines()
        f = [(d[0], int(d[1:])) for d in f]
        return f

def part1(data):
    di = {"W":(-1,0), "N":(0,1), "S":(0,-1), "E":(1,0)}
    ro = [0, 1, 0, -1]
    pos = [0,0]
    r = 1
    for d in data:
        if d[0] == "L":
            r -= d[1]//90
        elif d[0] == "R":
            r += d[1]//90
        elif d[0] == "F":
            pos[0] += ro[r%4]*d[1]
            pos[1] += ro[(r+1)%4]*d[1]
        else:
            pos[0] += di[d[0]][0]*d[1]
            pos[1] += di[d[0]][1]*d[1]

    return abs(pos[0]) + abs(pos[1])


def part2(data):
    da = {"E":0, "S":1, "W":2, "N":3}
    ra = [10, 0, 0, 1]
    pos = [0,0]
    for d in data:
        if d[0] == "L":
            t = d[1]//90
            ra = ra[t:] + ra[:t]
        elif d[0] == "R":
            t = d[1]//90
            ra = ra[-t:] + ra[:-t]
        elif d[0] == "F":
            pos[0] += (ra[0]-ra[2])*d[1]
            pos[1] += (ra[3]-ra[1])*d[1]
        else:
            ra[da[d[0]]] += d[1]

    return abs(pos[0])+abs(pos[1])


if __name__ == "__main__":
    data = read()
    print(part1(data))
    print(part2(data))