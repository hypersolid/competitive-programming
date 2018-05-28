#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the largestPermutation function below.
def largestPermutation(k, arr):
    hash = {}
    sorted_array = reversed(sorted(arr))

    if k >= len(arr):
        return sorted_array

    for i, el in enumerate(arr):
        hash[el] = i

    for i, el in enumerate(sorted_array):
        if arr[i] != el:
            k -= 1
            index1 = i
            index2 = hash[el]
            arr[index1], arr[index2] = arr[index2], arr[index1]
            hash[arr[index1]], hash[arr[index2]] = index1, index2
            if k == 0:
                break
    return arr

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nk = input().split()

    n = int(nk[0])

    k = int(nk[1])

    arr = list(map(int, input().rstrip().split()))

    result = largestPermutation(k, arr)

    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
