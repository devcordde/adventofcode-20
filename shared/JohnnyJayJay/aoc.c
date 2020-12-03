#include <stdio.h>
#include "aoc.h"

int count_lines(FILE* file) {
    long pos = ftell(file);
    int lines = 1;
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

int chars_until(FILE* file, char term) {
    long pos = ftell(file);
    int count = -1;
    char cur;
    do {
        cur = fgetc(file);
        count++;
    } while (cur != term && cur != EOF);
    fseek(file, pos, SEEK_SET);
    return count;
}

int charcount(char* str, char c, int from, int to) {
    int count = 0;
    for (int i = from; i < to; i++) {
        if (str[i] == c) {
            count++;
        }
    }
    return count;
}
        
    