def kurskal(N, edges):
    forest_nodes = [x for x in range(N)]
    forest_trees = {x: [x] for x in range(N)}

    weight = 0
    for edge in edges:
        tree1 = forest_nodes[edge[1] - 1]
        tree2 = forest_nodes[edge[2] - 1]
        if tree1 == tree2:
            continue
        weight += edge[0][0]

        forest_trees[tree1] += forest_trees[tree2]
        del forest_trees[tree2]
        for node in forest_trees[tree1]:
            forest_nodes[node] = tree1

    return weight


def main():
    N, M = input().strip().split()
    N, M = (int(N), int(M))

    edges = []
    for m in range(M):
        x, y, r = [int(x) for x in input().strip().split()]
        edges.append(((r, x + y), x, y))
    edges.sort(key=lambda tup: tup[0])

    S = int(input())

    weight = kurskal(N, edges)

    print(weight)

main()
