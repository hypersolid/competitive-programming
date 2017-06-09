package main

import (
	"fmt"
	"math"
)

var (
	options = []int{1, 2, 5}
	lore    = make([]int, 1010)
)

func lowGlow(id int) int {
	if id < 0 {
		return 1
	}
	if lore[id] >= 0 {
		return lore[id]
	}
	minSteps := math.MaxInt64
	for _, option := range options {
		if minSteps > lowGlow(id-option) {
			minSteps = lowGlow(id - option)
		}
	}
	lore[id] = minSteps + 1
	return lore[id]
}

func fillLore() {
	for i := 1; i < len(lore); i++ {
		lore[i] = -1
	}
	for id := range lore {
		lowGlow(id)
	}
}

func trim(arr *[]int) int {
	a := *arr
	min := math.MaxInt64
	for i := 0; i < len(a); i++ {
		if min > a[i] {
			min = a[i]
		}
	}
	return min
}

func solve(arr []int, min int) int {
	steps := 0
	for i := 0; i < len(arr); i++ {
		steps += lore[arr[i]-min]
	}
	return steps
}

func main() {
	fillLore()
	var T, N, v int
	fmt.Scanf("%d", &T)
	for t := 0; t < T; t++ {
		fmt.Scanf("%d", &N)
		if N <= 1 {
			fmt.Println(0)
			continue
		}
		arr := make([]int, N)
		for n := 0; n < N; n++ {
			fmt.Scanf("%d", &v)
			arr[n] = v
		}
		min := trim(&arr)
		res := math.MaxInt64
		for option := -4; option <= 0; option++ {
			if min >= option {
				tmp := solve(arr, min+option)
				if res > tmp {
					res = tmp
				}
			}
		}
		fmt.Println(res)
	}
}
