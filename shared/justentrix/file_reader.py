from os.path import join, dirname

inputs_dir = join(dirname(__file__), 'inputs')

def open_file(file_name):
  return open(f'{inputs_dir}/{file_name}', 'r')

def read_numbers(file_name):
  file = open_file(file_name)
  nums = []

  for line in file:
    nums.append(int(line))
  
  return nums
