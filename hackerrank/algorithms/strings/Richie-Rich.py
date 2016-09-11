#!/bin/python3

import sys


def mismatches(string):
    N = len(string)
    result = 0
    for i in range(N // 2):
        if string[i] != string[N - 1 - i]:
            result += 1
    return result


def improve(string, k, m):
    N = len(string)
    spare_replacements = k - m
    for i in range(N // 2):
        if string[i] == string[N - 1 - i]:
            if string[i] != '9' and spare_replacements > 1:
                string[i] = '9'
                string[N - 1 - i] = '9'
                spare_replacements -= 2
        else:
            if string[i] != '9' and string[N - 1 - i] != '9' and spare_replacements > 0:
                string[i] = '9'
                string[N - 1 - i] = '9'
                spare_replacements -= 1
            else:
                maximium = max((string[i], string[N - 1 - i]))
                string[N - 1 - i] = maximium
                string[i] = maximium

    if N % 2 == 1 and spare_replacements > 0:
        string[N // 2] = '9'

if __name__ == '__main__':
    n, k = input().strip().split(' ')
    n, k = [int(n), int(k)]
    number = list(input().strip())
    m = mismatches(number)
    if m > k:
        print(-1)
    else:
        improve(number, k, m)
        print(''.join(number))
