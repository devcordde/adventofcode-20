// Read my README in shared/JohnnyJayJay

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int traverse(uint32_t* levels, int lines, int width, int x_step, int y_step) {
    int x_pos = 0;
    int tree_count = 0;
    for (int i = 0; i < lines; i += y_step) {
        uint32_t level = levels[i];
        if ((level >> (width - 1 - x_pos)) & 1) {
            tree_count++;
        }
        x_pos = (x_pos + x_step) % width;
    }
    return tree_count;
}

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    int width = chars_until(file, '\n', 1);
    uint32_t* levels = malloc(sizeof(uint32_t) * lines);
    for (int i = 0; i < lines; i++) {
        uint32_t level = 0;
        int spot;
        while ((spot = fgetc(file)) != '\n' && spot != EOF) {
            level <<= 1;
            level |= spot == '#';
        }
        levels[i] = level;
    }
    
    uint64_t first = traverse(levels, lines, width, 3, 1);
    printf("You encounter %lu trees the first slope.\n", first);
    uint64_t second = traverse(levels, lines, width, 1, 1);
    uint64_t third = traverse(levels, lines, width, 5, 1);
    uint64_t fourth = traverse(levels, lines, width, 7, 1);
    uint64_t fiveth = traverse(levels, lines, width, 1, 2);
    printf("The trees on all slopes multiplied together: %lu\n", first * second * third * fourth * fiveth);
    
}
