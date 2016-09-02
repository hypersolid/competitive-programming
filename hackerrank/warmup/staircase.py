#!/bin/python3

import sys


n = int(input().strip())

for i in range(1, n + 1):
    print(' ' * (n - i) + '#' * i)
