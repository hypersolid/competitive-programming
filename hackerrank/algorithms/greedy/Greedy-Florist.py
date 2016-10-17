N, K = input().split(" ")
N, K = int(N), int(K)

prices = [int(x) for x in input().split(" ")]
descendentPrices = reversed(sorted(prices)[:N])

total = 0
purchased = 0
for i, price in enumerate(descendentPrices):
    total += price * (purchased + 1)
    if (i + 1) % K == 0:
        purchased += 1

print(total)
