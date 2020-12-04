#include <stdlib.h>
#include <string.h>
#include <regex.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

int skip_newline(FILE* file) {
    int next = fgetc(file);
    int ahead = next == '\n' || next == EOF;
    if (!ahead) {
        fseek(file, -1, SEEK_CUR);
    }    
    return ahead;
}

int main(int argc, char** argv) {
    regex_t hcl_regex;
    regcomp(&hcl_regex, "^#[0-9a-f]\\{6\\}$", 0);
    regex_t pid_regex;
    regcomp(&pid_regex, "^[0-9]\\{9\\}$", 0);
    regex_t hgt_regex;
    regcomp(&hgt_regex, "^[0-9]\\{2,3\\}[a-z]\\{2\\}$", 0);
    
    FILE* file = fopen(argv[1], "r");
    int num_passports = count_blank_lines(file) + 1;
    int num_valid_1 = 0;
    int num_valid_2 = 0;
    for (int i = 0; i < num_passports; i++) {
        int invalid_part2 = 0;
        int field_count = 0;
        while (!skip_newline(file)) {
            char key[4];
            char value[10];
            fscanf(file, "%3s:%s", key, value);
            if (strstr(key, "yr")) {
                int year = atoi(value);
                
                if (year != 0) {
                    char year_type = key[0];
                    int valid = 0;
                    switch (year_type) {
                        case 'b':
                            valid = year >= 1920 && year <= 2002;
                            break;
                        case 'i':
                            valid = year >= 2010 && year <= 2020;
                            break;
                        case 'e':
                            valid = year >= 2020 && year <= 2030;
                            break;
                        default:
                            break;
                    }
                    if (!valid) {
                        invalid_part2 = 1;
                    }
                } else {
                    invalid_part2 = 1;
                }
            } else if (strcmp(key, "hgt") == 0) {
                if (regexec(&hgt_regex, value, 0, NULL, 0) == 0) {
                    int num;
                    char unit[3];
                    sscanf(value, "%d%s", &num, unit);
                    int valid = 0;
                    if (strcmp(unit, "cm") == 0) {
                        valid = num >= 150 && num <= 193;
                    } else if (strcmp(unit, "in") == 0) {
                        valid = num >= 59 && num <= 76;
                    } 
                
                    if (!valid) {
                        invalid_part2 = 1;
                    }
                } else {
                    invalid_part2 = 1;
                }
                
            } else if (strcmp(key, "hcl") == 0) {
                if (regexec(&hcl_regex, value, 0, NULL, 0) != 0) {
                    invalid_part2 = 1;
                }
            } else if (strcmp(key, "ecl") == 0) {
                if (strcmp(value, "amb") && strcmp(value, "blu") && strcmp(value, "brn") 
                    && strcmp(value, "gry") && strcmp(value, "grn") && strcmp(value, "hzl") && strcmp(value, "oth")) {
                    invalid_part2 = 1;
                }
            } else if (strcmp(key, "pid") == 0) {
                if (regexec(&pid_regex, value, 0, NULL, 0) != 0) {
                    invalid_part2 = 1;
                }
            }
              
            if (strcmp(key, "cid") != 0) {
                field_count++;
            }
            fgetc(file);  
        }
        
        if (field_count == 7) {
            num_valid_1++;
        }
        
        if (field_count == 7 && !invalid_part2) {
            num_valid_2++;
        }   
    }
    
    printf("Valid passports (part 1): %d\n", num_valid_1);
    printf("Valid passports (part 2): %d\n", num_valid_2);
}