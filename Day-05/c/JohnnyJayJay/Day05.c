// Read my README in shared/JohnnyJayJay

#include <stdio.h>
#include <stdlib.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int find_value(char* desc, int length, char lower, char upper) {
    int min = 0;
    int delta = 1 << length;
    for (int i = 0; i < length; i++) {
        delta /= 2;
        if (desc[i] == upper) {
            min += delta;
        }
    }
    return min;
}

int intcmp(const void* one, const void* two) {
    int a = *((int*) one);
    int b = *((int*) two);
    if (a == b) {
        return 0;
    } else if (a < b) {
        return -1;
    } else {
        return 1;
    }
}

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    char row_desc[8];
    char column_desc[4];
    int* passes = malloc(sizeof(int) * lines); 
    for (int i = 0; i < lines; i++) {
        fscanf(file, "%7s%3s", row_desc, column_desc);
        int row = find_value(row_desc, 7, 'F', 'B');
        int column = find_value(column_desc, 3, 'L', 'R');
        passes[i] = row * 8 + column;
    }
    qsort(passes, lines, sizeof(int), &intcmp);
    int max_seat_id = passes[lines - 1];
    printf("Highest seat id: %d\n", max_seat_id);
    int own_seat_id = 0;
    for (int i = 0; i < lines - 1; i++) {
        int first = passes[i];
        int second = passes[i + 1];
        if (second - first == 2) {
            own_seat_id = first + 1;
            break;
        }
    }
    printf("Own seat id: %d\n", own_seat_id);
}
        
        
        