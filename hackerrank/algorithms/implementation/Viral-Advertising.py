N = int(input())

total = 0
viewers = 5

for i in range(N):
    total += viewers // 2
    viewers = viewers // 2 * 3

print(total)
