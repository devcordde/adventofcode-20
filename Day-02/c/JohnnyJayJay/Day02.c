// Read my README in shared/JohnnyJayJay

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

typedef struct {
    int min;
    int max;
    char c;
    char* password;
} db_entry;


int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    
    db_entry entries[lines];
    for (int i = 0; i < lines; i++) {
        int min, max;
        char c;
        char* password = malloc(chars_until(file, '\n', 1) + 1);
        fscanf(file, "%d-%d %c: %s", &min, &max, &c, password);
        db_entry entry = {.min = min, .max = max, .c = c, .password = password};
        entries[i] = entry;
    }
    
    int valid_passwords1 = 0;
    int valid_passwords2 = 0;
    for (int i = 0; i < lines; i++) {
        db_entry entry = entries[i];
        char* pw = entry.password;
        char c = entry.c;
        int min = entry.min;
        int max = entry.max;
        int count = charcount(pw, c, 0, strlen(pw));
        if (count >= min && count <= max) {
            valid_passwords1++;
        }
        if ((pw[min - 1] == c) ^ (pw[max - 1] == c)) {
            valid_passwords2++;
        }
    }
    
    printf("Valid passwords (part 1): %d\n", valid_passwords1);
    printf("Valid passwords (part 2): %d\n", valid_passwords2);
}
    