N, K = input().split(" ")
N, K = int(N), int(K)
T = [int(x) for x in input().split(" ")]

total = 0

new_page = False
page = 0
for tasks in T:
    if not new_page:
        page += 1
    for task in range(1, tasks + 1):
        new_page = False
        if task == page:
            total += 1
        if task % K == 0:
            page += 1
            new_page = True

print(total)
