package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func fuse(n int, start int, edges map[int]map[int]bool) []int {
	distances := make([]int, n)
	linked := make([]int, 0, n)
	linked = append(linked, start)
	unlinked := make(map[int]bool)
	for i := 0; i < n; i++ {
		if i != start {
			unlinked[i] = true
		}
	}

	distance := 1
	transfer := make([]int, 0, n)
	for len(unlinked) != 0 {
		transfer = transfer[:0]
		for source, _ := range unlinked {
			for _, target := range linked {
				if !edges[source][target] {
					transfer = append(transfer, source)
					break
				}
			}
		}

		for _, node := range transfer {
			distances[node] = distance
			delete(unlinked, node)
			linked = append(linked, node)
		}
		distance++
	}

	return distances
}

func solve() {
	var n, m, s int
	fmt.Scanln(&n, &m)

	edges := make(map[int]map[int]bool)

	var town1, town2 int
	for i := 0; i < m; i++ {
		fmt.Scanln(&town1, &town2)
		town1--
		town2--
		if _, ok := edges[town1]; !ok {
			edges[town1] = make(map[int]bool)
		}
		if _, ok := edges[town2]; !ok {
			edges[town2] = make(map[int]bool)
		}
		edges[town1][town2] = true
		edges[town2][town1] = true
	}
	fmt.Scanln(&s)
	s--

	f := bufio.NewWriter(os.Stdout)
	distances := fuse(n, s, edges)

	output := make([]string, 0, n)
	for i := 0; i < n; i++ {
		if i != s {
			output = append(output, strconv.Itoa(distances[i]))
		}
	}
	fmt.Fprintln(f, strings.Join(output, " "))
	f.Flush()
}

func main() {
	var t int
	fmt.Scanln(&t)
	for i := 0; i < t; i++ {
		solve()
	}
}
