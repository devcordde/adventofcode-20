#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../../../shared/JohnnyJayJay/aoc.h"

char ray_cast(char* map, int width, int height, int start_pos, int x_step, int y_step, int max) {
    int previous_pos = start_pos;
    for (int i = 0; i < max; i++) {
        int next_pos = previous_pos + (width * y_step) + x_step;
        if (next_pos < 0 || next_pos >= width * height || (next_pos / width) - (previous_pos / width) != y_step) {
            return '.';
        } else if (map[next_pos] != '.') {
            return map[next_pos];
        }
        previous_pos = next_pos;
    }
    return '.';
}  

void next_state(char* map, char* buf, int width, int height, int seat_pos, int max_sight, int max_tolerance) {
    char cur_state = map[seat_pos];
    if (cur_state == '.') {
        return;
    }
    
    char adjacents[8];
    int i = 0;
    for (int x = -1; x < 2; x++) {
        for (int y = -1; y < 2; y++) {
            if (x || y) {
                adjacents[i] = ray_cast(map, width, height, seat_pos, x, y, max_sight);
                i++;
            }
        }
    }
    
    int adjacent_occupied = charcount(adjacents, '#', 0, 8);
    if (cur_state == 'L' && adjacent_occupied == 0) {
        buf[seat_pos] = '#';
    } else if (cur_state == '#' && adjacent_occupied > max_tolerance) {
        buf[seat_pos] = 'L';
    } else {
        buf[seat_pos] = cur_state;
    }
}

int predict_occupied_seats(const char* original_seats, int width, int height, int max_sight, int max_tolerance) {
    int seat_count = width * height;
    char* seats = malloc(seat_count + 1);
    memcpy(seats, original_seats, seat_count + 1);
    char* seat_buf = malloc(seat_count + 1);
    memcpy(seat_buf, original_seats, seat_count + 1);
    
    do {
        for (int i = 0; i < seat_count; i++) {
            next_state(seats, seat_buf, width, height, i, max_sight, max_tolerance);
        }
        char* tmp = seat_buf;
        seat_buf = seats;
        seats = tmp;
    } while (strcmp(seats, seat_buf) != 0);
    
    int count = charcount(seats, '#', 0, seat_count);
    free(seats);
    free(seat_buf);
    return count;
}

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int width = chars_until(file, '\n', 1);
    int height = count_lines(file);
    int seat_count = width * height;
    char* seats = malloc(seat_count + 1);
    for (int i = 0; i < seat_count; i++) {
        char c = fgetc(file);
        if (c == '\n') {
            i--;
            continue;
        }
        seats[i] = c;
    }
    seats[seat_count] = 0;
    
    printf("Seats occupied (part 1): %d\n", predict_occupied_seats(seats, width, height, 1, 3));
    printf("Seats occupied (part 2): %d\n", predict_occupied_seats(seats, width, height, seat_count, 4));
}
