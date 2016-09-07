T = int(input())
for t in range(T):
  s1 = set(input().strip())
  s2 = set(input().strip())
  print(s1 & s2 and 'YES' or 'NO')
