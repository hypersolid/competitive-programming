import sys

n, k = [int(x) for x in input().strip().split()]
array = [int(x) for x in input().strip().split()]
frequencies = {}

for el in array:
    if el % k in frequencies:
        frequencies[el % k] = frequencies[el % k] + 1
    else:
        frequencies[el % k] = 1

if 0 in frequencies:
    frequencies[0] = min((frequencies[0], 1))
if k / 2 in frequencies:
    frequencies[k / 2] = min((frequencies[k / 2], 1))

# print(frequencies)

for key in frequencies.keys():
    if k / 2 != key and k - key in frequencies:
        if (frequencies[k - key] > frequencies[key]):
            frequencies[key] = 0
        else:
            frequencies[k - key] = 0

total = sum(frequencies.values())
print(total)
