s = input().strip()
n = int(input().strip())

memo = []
count = 0
for i in range(len(s)):
    if s[i] == 'a':
        count += 1
    memo.append(count)

result = memo[-1] * (n // len(s))
if n % len(s) > 0:
    result += memo[n % len(s) - 1]

print(result)
