#!/bin/python3

import sys


n = int(input().strip())
arr = [int(arr_temp) for arr_temp in input().strip().split(' ')]

zeroes = 0
positives = 0
negatives = 0
for el in arr:
    if el > 0:
        positives += 1
    else:
        if el < 0:
            negatives += 1
        else:
            zeroes += 1

print(positives / n)
print(negatives / n)
print(zeroes / n)
