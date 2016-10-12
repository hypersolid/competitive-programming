N, K = input().split(" ")
N, K = int(N), int(K)

prices = [int(x) for x in input().split(" ")]
charged = int(input())

annas_check = (sum(prices) - prices[K]) // 2

if charged == annas_check:
    print("Bon Appetit")
else:
    print(charged - annas_check)
