from heapq import heappush, heappop


def add_to_heap(heap, src, edges):
    for dst, distance in edges[src].items():
        heappush(heap, (distance, src, dst))


def prim(N, S, edges):
    weight = 0
    vertices = [S]
    heap = []
    add_to_heap(heap, S, edges)

    while len(vertices) != N:
        distance, src, dst = heappop(heap)
        if dst in vertices:
            continue
        weight += distance
        vertices.append(dst)
        add_to_heap(heap, dst, edges)

    return weight


def add_edge(edges, x, y, r):
    edges[x] = edges.get(x, {})
    edges[x][y] = edges[x].get(y, INF)
    edges[x][y] = min(edges[x][y], r)


def main():
    N, M = input().strip().split()
    N, M = (int(N), int(M))

    edges = {}
    for m in range(M):
        x, y, r = [int(x) for x in input().strip().split()]
        add_edge(edges, x, y, r)
        add_edge(edges, y, x, r)
    S = int(input())

    weight = prim(N, S, edges)

    print(weight)

main()
