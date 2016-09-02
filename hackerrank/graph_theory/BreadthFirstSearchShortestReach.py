import collections


class Graph():

    def __init__(self, node_number, edges):
        self.size = node_number
        self.edges = {}
        for node1, node2 in edges:
            if not node1 in self.edges:
                self.edges[node1] = [node2]
            else:
                self.edges[node1].append(node2)
            if not node2 in self.edges:
                self.edges[node2] = [node1]
            else:
                self.edges[node2].append(node1)

    def distances_from(self, node):
        distances = {node: 0}
        wavefront = [[node, 1]]
        steps = 1
        while wavefront:
            current, steps = wavefront.pop()
            if current in self.edges:
                for neighbor in self.edges[current]:
                    if not neighbor in distances:
                        distances[neighbor] = steps
                        wavefront.insert(0, [neighbor, steps + 1])
        return distances


def main():
    T = int(input())
    for t in range(T):
        N, M = [int(x) for x in input().strip().split(' ')]
        edges = []
        for m in range(M):
            edges.append([int(x) for x in input().strip().split(' ')])
        S = int(input())
        graph = Graph(N, edges)
        distances = graph.distances_from(S)
        result = []
        for node in range(1, N + 1):
            if not node in distances:
                result.append(-1)
            else:
                if distances[node]:
                    result.append(distances[node] * 6)
        print(' '.join([str(x) for x in result]))

main()
