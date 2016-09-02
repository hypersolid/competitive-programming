T = int(input())

for t in range(T):
    N, M, S = [int(x) for x in input().strip().split(' ')]

    id = (M + S - 2) % N + 1
    print(id)
