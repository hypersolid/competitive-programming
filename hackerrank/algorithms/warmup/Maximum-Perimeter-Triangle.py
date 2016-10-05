def solve():
    N = input()
    sticks = [int(x) for x in input().strip().split()]

    sticks.sort(reverse=True)

    for i, stick1 in enumerate(sticks):
        for j, stick2 in enumerate(sticks):
            for k, stick3 in enumerate(sticks):
                if i == j or j == k or k == i:
                    continue
                valid = True
                valid &= stick1 + stick2 > stick3
                valid &= stick2 + stick3 > stick1
                valid &= stick3 + stick1 > stick2
                if valid:
                    print(*sorted([stick1, stick2, stick3]))
                    return
    print("-1")

if __name__ == "__main__":
    solve()
