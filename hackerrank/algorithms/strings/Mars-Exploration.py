#!/bin/python3

import sys

signal = list(input().strip())
errors = 0
for i in range(len(signal)):
    if (i - 1) % 3 == 0:
        if signal[i] != 'O':
            errors += 1
    else:
        if signal[i] != 'S':
            errors += 1
print(errors)
