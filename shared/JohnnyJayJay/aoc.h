int count_lines(FILE* file);

int count_blank_lines(FILE* file);

void read_ints(FILE* file, int* buf, int max);

void read_longs(FILE* file, long* buf, int max);

int chars_until(FILE* file, char term, int rewind);

int charcount(char* str, char c, int from, int to);

int intcmp(const void* one, const void* two);
