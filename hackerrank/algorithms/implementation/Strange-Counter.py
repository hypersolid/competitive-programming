T = int(input().strip())
S = 3
P = 1

while P < T:
    P += S
    S *= 2

if P == T:
    print(S)
else:
    print(P - T)
