n, k = input().strip().split(' ')
n, k = [int(n), int(k)]
c = [int(c_temp) for c_temp in input().strip().split(' ')]

i = 0
e = 100
while i != 0 or e == 100:
    i = (i + k) % n
    if c[i] == 1:
        e -= 3
    else:
        e -= 1

print(e)
