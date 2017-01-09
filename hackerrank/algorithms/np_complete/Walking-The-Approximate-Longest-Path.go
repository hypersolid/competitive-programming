package main

import (
	"fmt"
	"math/rand"
	"time"
)

func connected(connectivity map[int]map[int]struct{}, node1, node2 int) bool {
	_, ok := connectivity[node1][node2]
	return ok
}

func main() {
	// read the inputs
	rand.Seed(time.Now().UTC().UnixNano())
	var n, m, node1, node2 int
	fmt.Scanf("%d %d\n", &n, &m)
	connectivity := make(map[int]map[int]struct{}, n)
	for i := 0; i < m; i++ {
		fmt.Scanf("%d %d\n", &node1, &node2)
		node1--
		node2--
		if _, ok := connectivity[node1]; !ok {
			connectivity[node1] = make(map[int]struct{})
		}
		if _, ok := connectivity[node2]; !ok {
			connectivity[node2] = make(map[int]struct{})
		}
		connectivity[node1][node2] = struct{}{}
		connectivity[node2][node1] = struct{}{}
	}

	// traverses graph from random point in several directions
	maxLen := 0
	maxPath := make([]int, 0)
	path := make([]int, n)
	iterations := 500000 / n
	for i := 0; i < iterations; i++ {
		node := rand.Intn(n)
		pathLen := 0
		visited := make([]bool, n)
		visited[node] = true
		path[pathLen] = node
		pathLen++

		for direction := 0; direction < 2; direction++ {
			current := node
			advanced := true
			for advanced {
				advanced = false
				perm := rand.Perm(len(connectivity[current]))
				permKeys := make([]int, len(connectivity[current]))
				i := 0
				for k := range connectivity[current] {
					permKeys[perm[i]] = k
					i++
				}
				for _, nextNode := range permKeys {
					if visited[nextNode] {
						continue
					}
					// prepend or append
					if direction > 0 {
						copy(path[1:], path[0:pathLen])
						path[0] = nextNode
					} else {
						path[pathLen] = nextNode
					}
					pathLen++
					current = nextNode
					visited[current] = true
					advanced = true
					break
				}
			}
		}

		if pathLen > maxLen {
			maxLen = pathLen
			maxPath = make([]int, pathLen)
			copy(maxPath[:], path[:pathLen])
		}
	}

	fmt.Println(maxLen)
	for u := 0; u < maxLen; u++ {
		fmt.Printf("%d ", maxPath[u]+1)
	}
}
