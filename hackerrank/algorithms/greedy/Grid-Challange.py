import sys

T = int(input())
for t in range(T):
    N = int(input())
    matrix = [sorted(input().strip()) for n in range(N)]

    flag = True
    for j in range(N):
        for i in range(1, N):
            if matrix[i - 1][j] > matrix[i][j]:
                flag = False

    print(flag and 'YES' or 'NO')
