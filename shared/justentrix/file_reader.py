from os.path import join, dirname

inputs_dir = join(dirname(__file__), 'inputs')

def open_file(file_name):
  return open(f'{inputs_dir}/{file_name}', 'r')

def read_lines(file_name):
  file = open_file(file_name)
  lines = []

  for line in file:
    lines.append(line.replace('\n', ''))

  return lines

def read_numbers(file_name):
  lines = read_lines(file_name)
  return list(map(int, lines))
