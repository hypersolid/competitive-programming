T = int(input())
for t in range(T):
    string = list(input().strip())
    deletions = 0
    prev = '-'
    for ch in string:
        if prev == ch:
            deletions += 1
        prev = ch
    print(deletions)
