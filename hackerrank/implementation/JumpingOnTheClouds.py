#!/bin/python3

import sys

n = int(input().strip())
c = [int(c_temp) for c_temp in input().strip().split(' ')]


def jump(position):
    steps = []

    for i in range(1, 3):
        if (position <= n - 1 - i and c[position + i] == 0):
            steps.append(jump(position + i) + 1)

    return steps and min(steps) or 0

print(jump(0))
