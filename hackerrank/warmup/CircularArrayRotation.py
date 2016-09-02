n, k, q = input().strip().split(' ')
a = [int(x) for x in input().strip().split(' ')]

for i in range(int(q)):
    query = int(input())
    print(a[(query - int(k)) % int(n)])
