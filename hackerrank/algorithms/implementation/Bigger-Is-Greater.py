def next_permutation(string):
    n = len(string)
    string = list(string)
    current = chr(1)
    pivot = None
    superior = None

    # find pivot, return if None
    for i in reversed(range(n)):
        if string[i] < current:
            pivot = i
            break
        current = string[i]
    if pivot is None:
        return None

    # swap and reverse
    for i in range(n - 1, pivot, -1):
        if string[i] > string[pivot]:
            superior = i
            break
    string[pivot], string[superior] = string[superior], string[pivot]
    d = string[:pivot + 1]
    r = list(reversed(string[pivot + 1:]))
    return d + r

T = int(input())
for t in range(T):
    string = input().strip()
    permutation = next_permutation(string)
    print(permutation and ''.join(permutation) or 'no answer')
