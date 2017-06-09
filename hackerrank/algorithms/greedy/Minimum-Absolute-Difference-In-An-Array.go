package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	var N int
	fmt.Scanf("%d", &N)
	arr := make([]int, N)
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &arr[i])
	}
	sort.Ints(arr)

	min := math.MaxInt64
	for i := 1; i < N; i++ {
		diff := arr[i-1] - arr[i]
		if diff < 0 {
			diff = -diff
		}
		if diff < min {
			min = diff
		}
	}

	fmt.Println(min)
}
