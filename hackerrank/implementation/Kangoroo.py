#!/bin/python3

import sys


def sign(x): return 1 if x >= 0 else -1

x1, v1, x2, v2 = input().strip().split(' ')
x1, v1, x2, v2 = [int(x1), int(v1), int(x2), int(v2)]

dx = x2 - x1
dv = v2 - v1

if (x1 == x2):
    print('YES')
    sys.exit()

if sign(dx) == sign(dv):
    print('NO')
    sys.exit()

if dx % dv == 0:
    print('YES')
else:
    print('NO')
