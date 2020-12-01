/* compile from project root using 
   cc -Wall -g shared/JohnnyJayJay/aoc.c Day-01/c/JohnnyJayJay/Day01.c -o day01 */

#include <stdio.h>
#include <stdlib.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int main(int argc, char** argv) {
    puts("Reading file...");
    char* input_file = argv[1];
    FILE* file = fopen(input_file, "r");
    int lines = count_lines(file);
    printf("Read %d lines\n", lines);
    int* numbers = malloc(lines);
    read_ints(file, numbers, lines);
    puts("Solving...");
    int part1 = 0;
    int part2 = 0;
    for (int i = 0; i < lines; i++) {
        int first = numbers[i];
        for (int j = 0; j < lines; j++) {
            int second = numbers[j];
            if (first + second == 2020) {
                printf("Found numbers for part 1: %d %d - Result: %d\n", first, second, first * second);
                part1 = 1;
            }
            for (int k = 0; !part2 && k < lines; k++) {
                int third = numbers[k];
                if (first + second + third == 2020) {
                    printf("Found numbers for part 2: %d %d %d - Result: %d\n", first, second, third, first * second * third);
                    part2 = 1;
                    break;
                }
            }
            if (part1 && part2) {
                i = lines;
                break;
            }
        }
    }
}
