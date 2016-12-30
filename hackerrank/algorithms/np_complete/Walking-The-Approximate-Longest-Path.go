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
	var n, m, node1, node2 int
	fmt.Scanf("%d %d\n", &n, &m)

	connectivity := make(map[int]map[int]struct{}, n)

	for i := 0; i < m; i++ {
		fmt.Scanf("%d %d\n", &node1, &node2)
		if _, ok := connectivity[node1]; !ok {
			connectivity[node1] = make(map[int]struct{})
		}
		if _, ok := connectivity[node2]; !ok {
			connectivity[node2] = make(map[int]struct{})
		}
		connectivity[node1][node2] = struct{}{}
		connectivity[node2][node1] = struct{}{}
	}

	rand.Seed(time.Now().UTC().UnixNano())
	path := make([]int, n)
	for i := 0; i < n; i++ {
		path[i] = i
	}
	var n1, n2, i1, i2 int
	for i := 0; i < 1000; i++ {
		i1 = rand.Intn(n)
		i2 = rand.Intn(n)

		if i1 == i2 {
			continue
		}

		n1 = path[i1]
		n2 = path[i2]

		fmt.Println("indices", i1, i2)
		fmt.Println("path", path)

		if i1 > 0 {
			left := path[i1-1]
			if connected(connectivity, left, n1) && !connected(connectivity, left, n2) {
				continue
			}
			fmt.Println(left, "->", n2)
		}
		if i1 < n-1 {
			right := path[i1+1]
			if connected(connectivity, right, n1) && !connected(connectivity, right, n2) {
				continue
			}
			fmt.Println(right, "->", n2)
		}
		fmt.Println("c2")
		if i2 > 0 {
			left := path[i2-1]
			if connected(connectivity, left, n2) && !connected(connectivity, left, n1) {
				continue
			}
			fmt.Println(left, "->", n1)
		}
		if i2 < n-1 {
			right := path[i2+1]
			if connected(connectivity, right, n2) && !connected(connectivity, right, n1) {
				continue
			}
			fmt.Println(right, "->", n1)
		}
		path[i1], path[i2] = path[i2], path[i1]
	}

	fmt.Println(path)
}
