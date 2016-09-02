import re

s = list(input().strip())

repl = True
while repl:
    repl = False
    for i in range(1, len(s)):
        if s[i - 1] == s[i]:
            repl = True
            s = s[:max(0, i - 1)] + s[i + 1:]
            break

print(s and ''.join(s) or 'Empty String')
