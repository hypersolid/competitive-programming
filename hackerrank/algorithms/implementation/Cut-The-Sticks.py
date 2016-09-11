#!/bin/python3

import sys


n = int(input().strip())
arr = [int(arr_temp) for arr_temp in input().strip().split(' ')]

arr.sort()

print(n)

for i, _ in enumerate(arr):
    if i < n - 1 and arr[i + 1] > arr[i]:
        print(n - 1 - i)
