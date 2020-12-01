#include "aoc.h"

int count_lines(FILE* file) {
    long pos = ftell(file);
    int lines = 0;
    int c;
    while ((c = fgetc(file)) != EOF) {
        if (c == '\n') {
            lines++;
        }
    }
    fseek(file, pos, SEEK_SET);
    return lines;
}

void read_ints(FILE* file, int* buf, int max) {
    for (int i = 0; i < max; i++) {
        int num;
        if (EOF == fscanf(file, "%d", &num)) {
            return;
        }
        buf[i] = num;
    }
}
    