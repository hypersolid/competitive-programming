package main

import (
	"fmt"
	"sort"
)

func main() {
	var n int
	fmt.Scanf("%d", &n)
	numbers := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &numbers[i])
	}
	sort.Ints(numbers)

	minDistance := 1 << 31
	for i := 1; i < n; i++ {
		if numbers[i]-numbers[i-1] < minDistance {
			minDistance = numbers[i] - numbers[i-1]
		}
	}

	for i := 1; i < n; i++ {
		if numbers[i]-numbers[i-1] == minDistance {
			fmt.Printf("%d %d ", numbers[i-1], numbers[i])
		}
	}
}
