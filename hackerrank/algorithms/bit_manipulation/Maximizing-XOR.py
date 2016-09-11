#!/usr/bin/python3
def maxXor(l, r):
    result = 0
    for i1 in range(l, r + 1):
        for i2 in range(i1, r + 1):
            result = max(result, i1 ^ i2)
    return result

if __name__ == '__main__':
    l = int(input())
    r = int(input())
    print(maxXor(l, r))
