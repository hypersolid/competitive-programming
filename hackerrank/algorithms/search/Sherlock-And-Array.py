def solve():
    N = int(input())
    array = [int(number) for number in input().split()]
    left = []
    right = []
    for n in range(N):
        left.append(0)
        right.append(0)

    left_acc = 0
    right_acc = 0
    for n in range(N):
        left_acc += array[n]
        left[n] = left_acc
        right_acc += array[N - 1 - n]
        right[N - 1 - n] = right_acc

    for n in range(N):
        if left[n] == right[n]:
            return True
    return False

T = int(input())
for t in range(T):
    print(solve() and "YES" or "NO")
