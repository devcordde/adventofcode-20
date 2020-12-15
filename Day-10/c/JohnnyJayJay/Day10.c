#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "../../../shared/JohnnyJayJay/aoc.h"


int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    
    int* jolt_ratings = malloc(sizeof(int) * (lines + 2));
    jolt_ratings[0] = 0;
    jolt_ratings[lines + 1] = INT_MAX;
    read_ints(file, jolt_ratings + 1, lines);
    
    qsort(jolt_ratings, lines + 1, sizeof(int), &intcmp);
    jolt_ratings[lines + 1] = jolt_ratings[lines] + 3;
    
    int* difference_map = malloc(sizeof(int) * lines);
    int one_jolt_differences = 0;
    int three_jolt_differences = 0;
    for (int i = 0; i < lines + 1; i++) {
        int first = jolt_ratings[i];
        int second = jolt_ratings[i + 1];
        int difference = second - first;
        difference_map[i] = difference;
        if (difference == 1) {
            one_jolt_differences++;
        } else if (difference == 3) {
            three_jolt_differences++;
        }
    }
    
    printf("%d one jolt and %d three jolt differences - Result: %d\n", 
            one_jolt_differences, three_jolt_differences, one_jolt_differences * three_jolt_differences);
    
    // the number of permutations is equal to the product of all streaks in the sequence.
    // a streak is a consecutive subsequence of 1s in the difference map. Each streak's number of permutations 
    // can be calculated with the formula pow(2, s - 1) - max(0, pow(2, s - 3) - 1), where s is the streak length 
    // (number of 1s, in the code below exactly one lower)
    long permutations = 1;
    int streak_len = -1;
    for (int i = 0; i < lines + 1; i++) {
        int diff = difference_map[i];
        if (diff == 1) {
            streak_len++;
        } else  {
            if (streak_len > 0) {
                long streak_combs = 1 << streak_len;
                int distance = streak_len + 1 - 3;
                if (distance > 0) {
                    streak_combs -= (1 << distance) - 1;
                }
                permutations *= streak_combs;
            }
            streak_len = -1;
        }
    }
    
    printf("Possible permutations: %ld\n", permutations);
    
    
}