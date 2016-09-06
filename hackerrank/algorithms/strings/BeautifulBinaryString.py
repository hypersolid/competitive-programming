#!/bin/python3

import sys


n = int(input().strip())
str = list(input().strip())

last_match = -100
counter = 0
streak = 0
for i in range(len(str) - 2):
    if (str[i: i + 3] == ['0', '1', '0']):
        if i - last_match >= 3:
            counter += streak // 2 + streak % 2
            streak = 1
        else:
            streak += 1
        last_match = i

counter += streak // 2 + streak % 2
print(counter)
