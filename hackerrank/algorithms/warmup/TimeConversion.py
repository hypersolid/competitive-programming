#!/bin/python3

import sys


time = input().strip()
h, m, s = time.split(':')
z = s[-2:]
s = s[:-2]
if (h == '12'):
    if (z == 'AM'):
        h = 0
else:
    h = int(h) + (z == 'PM' and 12 or 0)

h = '{:02d}'.format(int(h))
print(':'.join([h, m, s]))
