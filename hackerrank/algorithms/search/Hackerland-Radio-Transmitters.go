package main

import (
	"fmt"
	"sort"
)

func main() {
	var n, k int
	fmt.Scanf("%d %d", &n, &k)
	if n == 0 {
		fmt.Print(0)
		return
	}

	houses := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &houses[i])
	}

	sort.Ints(houses)

	transmitters := 0
	coverage := houses[0] - 1
	toCover := -1

	for i := 0; i < n; i++ {
		if houses[i] <= coverage {
			continue
		}

		toCover = i

		for ; i < n; i++ {
			if i+1 < n {
				if houses[i+1]-k > houses[toCover] {
					break
				}
			} else {
				break
			}
		}
		coverage = houses[i] + k
		transmitters++
	}

	fmt.Print(transmitters)
}
