inf = 1e9
n, _ = input().strip().split(' ')
n = int(n)
stations = [0 for x in range(n)]
left = [inf for x in range(n)]
right = [inf for x in range(n)]
for x in input().strip().split(' '):
    stations[int(x)] = 1

odometer = inf
for i in range(0, n):
    if stations[i]:
        odometer = 0
    else:
        if odometer < inf:
            odometer += 1
            left[i] = odometer

odometer = inf
for i in range(n - 1, -1, -1):
    if stations[i]:
        odometer = 0
    else:
        if odometer < inf:
            odometer += 1
            right[i] = odometer

result = 0
for i in range(0, n):
    min_distance = min(left[i], right[i])
    if min_distance < inf:
        result = max(result, min_distance)

print(result)
