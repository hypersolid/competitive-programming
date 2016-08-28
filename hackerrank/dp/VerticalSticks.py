def rank(list):
    buffer = []
    for i in range(len(list)):
        count = 0
        for j in range(len(list)):
            if list[j] >= list[i]:
                count += 1
        buffer.append(count)
    return buffer


def main():
    T = int(input())
    for t in range(T):
        N = int(input())
        string_values = input().split(" ")
        values = [int(el) for el in string_values]

        ranks = rank(values)

        total = 0
        for i in range(N):
            total += (N + 1) / (ranks[i] + 1)

        print("{:.2f}".format(total))

main()
