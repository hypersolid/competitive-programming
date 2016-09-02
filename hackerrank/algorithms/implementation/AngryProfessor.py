#!/bin/python3

import sys


t = int(input().strip())
for a0 in range(t):
    n, k = input().strip().split(' ')
    n, k = [int(n), int(k)]
    a = [int(a_temp)
         for a_temp in input().strip().split(' ') if int(a_temp) <= 0]

    print(len(a) < k and 'YES' or 'NO')
