from math import ceil
from functools import reduce

with open("PATH", 'r') as f:
    time = int(f.readline().strip())
    buses = f.readline().split(',')

# part 1
valid = [int(value) for value in buses if value != 'x']

departues = ([(ceil(time/bus)*bus, bus) for bus in valid])
bus = min(departues)

print((bus[0]-time)*bus[1])

# part 2
def remainder(n, na):
    def mul_inv(a, b):
        b0 = b
        x0, x1 = 0, 1
        if b == 1: return 1
        while a > 1:
            q = a // b
            a, b = b, a%b
            x0, x1 = x1 - q * x0, x0
        if x1 < 0: x1 += b0
        return x1

    sum = 0
    prod = reduce(lambda a, b: a*b, n)
    for n_i, a_i in zip(n, a):
        p = prod // n_i
        sum += a_i * mul_inv(p, n_i) * p
    return sum % prod

n = valid
a = [-i for i in range(len(buses)) if buses[i] != 'x']

print(remainder(n, a))