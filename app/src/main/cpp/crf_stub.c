#include <stddef.h>

int crf1de_create_instance(const char *interface, void **ptr) {
    (void)interface;
    (void)ptr;
    return 1; // Training encoder not supported in mobile inference-only build
}
