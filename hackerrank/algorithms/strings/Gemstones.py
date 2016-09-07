N = int(input())
composition = set(input().strip())
for n in range(1, N):
    composition &= set(input().strip())
print(len(composition))
