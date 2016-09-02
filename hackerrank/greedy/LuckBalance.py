N, K = [int(x) for x in input().strip().split(' ')]

non_important = []
important = []

for n in range(N):
    L, I = [int(x) for x in input().strip().split(' ')]
    if I == 1:
        important.append(L)
    else:
        non_important.append(L)

important.sort(reverse=True)
print(sum(important[:K]) - sum(important[K:]) + sum(non_important))
