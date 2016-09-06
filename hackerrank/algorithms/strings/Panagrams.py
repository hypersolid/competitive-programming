def to_i(ch):
    if ch >= 'a' and ch <= 'z':
        return ord(ch) - ord('a') + 1


def panagram(str):
    mask = 0
    target = int('1' * 26, 2)
    for ch in list(str):
        shift = to_i(ch.lower())
        if shift:
            mask |= 1 << (shift - 1)
            if mask == target:
                return 'pangram'

    return 'not pangram'

print(panagram(input().strip()))
