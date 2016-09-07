def funny_or_not(string):
    N = len(string)
    for i in range(1, N):
        left = ord(string[i]) - ord(string[i - 1])
        right = ord(string[N - 1 - i]) - ord(string[N - i])
        if abs(left) != abs(right):
            return False
    return True

T = int(input())
for t in range(T):
    string = input().strip()
    result = funny_or_not(list(string))
    print(result and 'Funny' or 'Not Funny')
