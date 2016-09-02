def bsearch(array, start, end, value):
    center = start + (end - start) // 2
    if (array[center] == value):
        return center
    else:
        if (array[center] > value):
            return bsearch(array, start, center - 1, value)
        else:
            return bsearch(array, center + 1, end, value)


def main():
    V = int(input())
    N = int(input())
    array = [int(x) for x in input().strip().split()]
    return bsearch(array, 0, N - 1, V)

print(main())
