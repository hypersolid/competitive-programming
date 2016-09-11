def solve(stones):
    dp = [False, False, True, True] + [False] * (stones - 3)
    for i in range(4, stones + 1):
        dp[i] = not (dp[i - 2] and dp[i - 3] and dp[i - 5])
    return dp[stones]

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        stones = int(input())
        print(solve(stones) and 'First' or 'Second')
