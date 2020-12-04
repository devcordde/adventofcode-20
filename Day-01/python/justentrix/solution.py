from file_reader import read_numbers

numbers = read_numbers('day01.txt')

def get_first_numbers():
  max_index = len(numbers) - 1

  for i in range(max_index):
    curr = numbers[i]

    for j in range(i + 1, max_index):
      next = numbers[j]
      if (curr + next) == 2020:
        return curr, next

def get_second_numbers():
  max_index = len(numbers) - 2

  for i in range(max_index):
    first = numbers[i]

    for j in range(i + 1, max_index):
      second = numbers[j]

      for k in range(i + 2, max_index):
        third = numbers[k]

        if (first + second + third) == 2020:
          return first, second, third

# Part one
nums = get_first_numbers()
first = nums[0]
second = nums[1]

print(f'{first} + {second} = {first + second}')
print(f'{first} x {second} = {first * second}')

# Part two
nums = get_second_numbers()
first = nums[0]
second = nums[1]
third = nums[2]

print(f'{first} + {second} + {third} = {first + second + third}')
print(f'{first} x {second} x {third} = {first * second * third}')
