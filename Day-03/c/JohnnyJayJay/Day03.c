// Read my README in shared/JohnnyJayJay

#include <stdlib.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int traverse(u_int32_t* levels, int lines, int width, int x_step, int y_step) {
    int x_pos = 0;
    int tree_count = 0;
    for (int i = 0; i < lines; i += y_step) {
        u_int32_t level = levels[i];
        if ((level >> (width - 1 - x_pos)) & ((u_int32_t) 1)) {
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
    u_int32_t* levels = malloc(sizeof(u_int32_t) * lines);
    for (int i = 0; i < lines; i++) {
        u_int32_t level = 0;
        int spot;
        while ((spot = fgetc(file)) != '\n' && spot != EOF) {
            level <<= 1;
            level |= spot == '#';
        }
        levels[i] = level;
    }
    
    u_int64_t first = traverse(levels, lines, width, 3, 1);
    printf("You encounter %lu trees the first slope.\n", first);
    u_int64_t second = traverse(levels, lines, width, 1, 1);
    u_int64_t third = traverse(levels, lines, width, 5, 1);
    u_int64_t fourth = traverse(levels, lines, width, 7, 1);
    u_int64_t fiveth = traverse(levels, lines, width, 1, 2);
    printf("The trees on all slopes multiplied together: %lu\n", first * second * third * fourth * fiveth);
    
}