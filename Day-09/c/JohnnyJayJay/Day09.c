#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int find_sum(long sum, long* numbers, int max) {
    for (int i = 0; i < max; i++) {
        for (int j = 0; j < max; j++) {
            if (numbers[i] + numbers[j] == sum) {
                return 1;
            }
        }
    }
    return 0;
}

long find_nonconforming_number(long* numbers, int length, int preamble_len) {
    for (int i = preamble_len; i < length; i++) {
        long num = numbers[i];
        if (!find_sum(num, (numbers + i - preamble_len), preamble_len)) {
            return num;
        }
    }
    return -1;
}

long find_contiguous_sum(long* numbers, int length, long sum) {
    int begin_pos = 0;
    int steps = 0;
    long partial_sum = 0;
    
    for (int i = 0; i < length && partial_sum != sum; i++) {
        long num = numbers[i];
        partial_sum += num;
        steps++;
        while (partial_sum > sum) {
            long first_summand = numbers[begin_pos];
            partial_sum -= first_summand;
            begin_pos++;
            steps--;
        }
    }
    
    long max = LONG_MIN;
    long min = LONG_MAX;
    for (int i = begin_pos; i < begin_pos + steps; i++) {
        long num = numbers[i];
        if (num > max) {
            max = num;
        }
        if (num < min) {
            min = num;
        }
    }
    return min + max;
}

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    long* encrypted = malloc(sizeof(long) * lines);
    read_longs(file, encrypted, lines);
    long number = find_nonconforming_number(encrypted, lines, 25);
    printf("The first nonconforming number is %ld\n", number);
    long cs = find_contiguous_sum(encrypted, lines, number);
    printf("Checksum of the contiguous sum (part 2): %ld\n", cs);
}
    