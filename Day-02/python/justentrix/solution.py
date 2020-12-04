from file_reader import read_lines
import re

class Password:
  def __init__(self, letter, first_number, second_number, plain):
    self.letter = letter
    self.first_number = first_number
    self.second_number = second_number
    self.plain = plain

def read_password_from_string(line):
  split = line.split(' ')
  amount = split[0].split('-')

  first_number = int(amount[0])
  second_number = int(amount[1])

  letter = split[1][0]
  password = split[2]

  return Password(letter, first_number, second_number, password)

# Part one
def is_password_valid(password):
  letters = re.sub(f'[^{password.letter}]', '', password.plain)
  length = len(letters)
  return length >= password.first_number and length <= password.second_number

available_passwords = []
for line in read_lines('day02.txt'):
  password = read_password_from_string(line)
  available_passwords.append(password)

valid_passwords = []

for password in available_passwords:
  if is_password_valid(password):
    valid_passwords.append(password)

print(len(valid_passwords))

# Part two
def is_password_valid(password):
  first_letter = password.plain[password.first_number - 1]
  second_letter = password.plain[password.second_number - 1]

  return (first_letter == password.letter and not second_letter == password.letter
    or not first_letter == password.letter and second_letter == password.letter)

valid_passwords = []

for password in available_passwords:
  if is_password_valid(password):
    valid_passwords.append(password)
  
print(len(valid_passwords))
