#!/bin/python3

import os
import sys
import collections

#
# Complete the truckTour function below.
#
def truckTour(petrolpumps):
    d = collections.deque()

    diffs = [pump[0]-pump[1] for pump in petrolpumps]
    N = len(petrolpumps)

    sum = 0
    i = 0
    while len(d) < N:
        idx = (i + len(d)) % N
        if sum + diffs[idx] >= 0:
            d.appendleft(diffs[idx])
            sum += diffs[idx]
        else:
            if len(d) > 0:
                sum -= d.pop()
            i = (i + 1) % N
    return i

    for pump in petrolpumps:

        sum += diff
        if sum < 0:
            break
    for i in range(len(petrolpumps)):
        if sum >=0:
            return i

        sum -= d.pop()
        diff = petrolpumps[i][0]-petrolpumps[i][1]
        d.appendleft(diff)
        sum += diff

    return -1

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    petrolpumps = []

    for _ in range(n):
        petrolpumps.append(list(map(int, input().rstrip().split())))

    result = truckTour(petrolpumps)

    fptr.write(str(result) + '\n')

    fptr.close()
