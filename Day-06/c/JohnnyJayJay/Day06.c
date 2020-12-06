// Read my README in shared/JohnnyJayJay

#include <stdio.h>
#include <stdint.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int popcount(uint32_t bits) {
    int count;
    for (count = 0; bits > 0; bits >>= 1) {
        count += bits & 1;
    }
    return count;
}

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int groups = count_blank_lines(file) + 1;
    int sum_1 = 0;
    int sum_2 = 0;
    for (int i = 0; i < groups; i++) {
        int next;
        uint32_t group_set_1 = 0;
        uint32_t group_set_2 = 0xffffffff;
        while ((next = fgetc(file)) != '\n' && next != EOF) {
            uint32_t person_set = 0;
            do {
                int pos = next - 'a';
                person_set |= 1 << pos;
                next = fgetc(file);
            } while (next != EOF && next != '\n');
            group_set_1 |= person_set;
            group_set_2 &= person_set;
        }
        sum_1 += popcount(group_set_1);
        sum_2 += popcount(group_set_2);
    }
    printf("Sum of the group answer counts (part 1): %d\n", sum_1);
    printf("Sum of the group answer counts (part 2): %d\n", sum_2);
}
