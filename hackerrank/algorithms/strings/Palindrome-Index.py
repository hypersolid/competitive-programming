def check(string):
    mark = -1
    right = len(string) - 1
    for left in range(len(string) // 2):
        left_char = string[left]
        right_char = string[right]
        right -= 1
        if left_char != right_char:
            if mark == -1:
                mark = left
                right += 1
            else:
                mark = len(string) - 1 - mark
                break

    print(mark)

T = int(input())

for t in range(T):
    check(list(input().strip())
