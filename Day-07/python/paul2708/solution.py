from assertions import assert_equals
from input_reader import read_lines
from printer import aoc_print


def contains_bag(root: str, needle: str) -> bool:
    ignore_counts = map(lambda x: x[0], bag_rules[root])

    if needle in ignore_counts:
        return True
    else:
        for bag in bag_rules[root]:
            if contains_bag(bag[0], needle):
                return True

        return False


def count_bags(root: str) -> int:
    total_sum = 0
    for rule in bag_rules[root]:
        total_sum += rule[1] + rule[1] * count_bags(rule[0])

    return total_sum


# Parse input (plz forgive me python god and regex skiller)
rule_list = read_lines("day07")

# {str, [(int,str)]}
bag_rules = {}

for rule in rule_list:
    rule = rule[:-1].replace("bags", "").replace("bag", "")

    bag = rule.split(" contain ")[0].strip()
    container_bags = []

    container = rule.split(" contain ")[1].strip()
    if "," in container:
        for single_bag in container.split(", "):
            count = int(single_bag.split(" ")[0])
            bag_type = single_bag[len(single_bag.split(" ")[0]):].strip()
            container_bags.append((bag_type, count))
    elif "no " not in container:
        count = int(container.split(" ")[0])
        bag_type = container[len(rule.split(" contain ")[1].strip().split(" ")[0]):].strip()
        container_bags.append((bag_type, count))

    bag_rules[bag] = container_bags

# Part one
res = len([bag for bag in bag_rules.keys() if contains_bag(bag, "shiny gold")])

aoc_print(f"{res} bag colors can contain at least one shiny gold.")
assert_equals(302, res)

# Part two
res = count_bags("shiny gold")

aoc_print(f"It requires {res} bags in my shiny gold bag.")
assert_equals(4165, res)
