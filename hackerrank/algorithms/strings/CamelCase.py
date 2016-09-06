#!/bin/python3

import sys


s = input().strip()

counter = 1
for ch in list(s):
    if ch >= 'A' and ch <= 'Z':
        counter += 1

print(counter)
