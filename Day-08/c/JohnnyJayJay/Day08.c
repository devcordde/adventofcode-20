#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../../../shared/JohnnyJayJay/aoc.h"

typedef struct {
    char* opcode;
    int arg;
} instruction;

typedef struct {
    int* possible_corruption;
    int possible_corruption_len;
    int acc;
    int infinite_loop;
} execution_result;

execution_result* run_program(int length, instruction* insts) {
    int* visits = calloc(sizeof(int), length);
    int acc = 0;
    int* possible_corruption = malloc(sizeof(int) * length);
    int possible_corruption_len = 0; 
    int infinite_loop = 0;
    for (int i = 0; i < length; i++) {
        if (visits[i]) {
            infinite_loop = 1;
            break;
        }
        visits[i] = 1;
        instruction* inst = &insts[i];
        char* opcode = inst->opcode;
        int arg = inst->arg;
        if (strcmp(opcode, "acc") == 0) {
            acc += arg;
        } else { 
            possible_corruption[possible_corruption_len] = i;
            possible_corruption_len++;
            if (strcmp(opcode, "jmp") == 0) {
                i += arg - 1;
            }
        }
        
    }
    free(visits);
    execution_result* res = malloc(sizeof(execution_result));
    res->possible_corruption = possible_corruption;
    res->possible_corruption_len = possible_corruption_len;
    res->acc = acc;
    res->infinite_loop = infinite_loop;
    return res;
}

void free_execution_result(execution_result* result) {
    free(result->possible_corruption);
    free(result);
}

void fix_program(int length, instruction* insts, int* possible_corruption, int possible_corruption_len) {
    for (int i = 0; i < possible_corruption_len; i++) {
        int pos = possible_corruption[i];
        instruction old = insts[pos];
        instruction new = {strcmp(old.opcode, "jmp") == 0 ? "nop" : "jmp", old.arg};
        insts[pos] = new;
        execution_result* result = run_program(length, insts);
        int infinite_loop = result->infinite_loop;
        free_execution_result(result);
        if (infinite_loop) {
            insts[pos] = old;
        } else {
            return;
        }
    }    
}
    

int main(int argc, char** argv) {
    FILE* file = fopen(argv[1], "r");
    int lines = count_lines(file);
    instruction* insts = malloc(sizeof(instruction) * lines);
    for (int i = 0; i < lines; i++) {
        char* opcode = malloc(4);
        int arg;
        fscanf(file, "%3s %d", opcode, &arg);
        instruction inst = {opcode, arg};
        insts[i] = inst;
    }
    
    execution_result* result = run_program(lines, insts);
    printf("The accumulator is %d\n", result->acc);
    fix_program(lines, insts, result->possible_corruption, result->possible_corruption_len);
    result = run_program(lines, insts);
    printf("The fixed accumulator is %d\n", result->acc);
}
