# CAVEAT
# It's actually Belman-Ford algorithm (does not pass last test case)

INF = int(10**9)


def route(N, M, S, edges):
    distances = [INF for j in range(N)]

    distances[S - 1] = 0

    for step in range(N - 1):
        changes = False
        for x, y, r in edges.values():
            # relaxing edge
            if distances[x - 1] > distances[y - 1] + r:
                distances[x - 1] = distances[y - 1] + r
                changes = True
            if distances[y - 1] > distances[x - 1] + r:
                distances[y - 1] = distances[x - 1] + r
                changes = True
        if not changes:
            break

    return distances


def main():
    T = int(input())
    for t in range(T):
        N, M = input().strip().split()
        N, M = (int(N), int(M))
        edges = {}
        for m in range(M):
            x, y, r = [int(i) for i in input().strip().split()]
            key = "{}-{}".format(*sorted((x, y)))
            if not key in edges or edges[key][2] > r:
                edges[key] = (x, y, r)

        S = int(input())

        distances = route(N, M, S, edges)
        distances.pop(S - 1)
        distances = [(x == INF and '-1' or str(x)) for x in distances]
        print(' '.join(distances))

main()
