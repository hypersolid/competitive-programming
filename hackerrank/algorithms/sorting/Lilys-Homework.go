package main

import (
	"fmt"
	"sort"
)

func solve(parr, pbp *[]int) int {
	arr := *parr
	bp := *pbp
	ops := 0
	mm := make(map[int]int, len(arr))
	for i := 0; i < len(arr); i++ {
		mm[arr[i]] = i
	}
	for i := 0; i < len(arr); i++ {
		if arr[i] == bp[i] {
			continue
		}
		subIdx := mm[bp[i]]
		arr[i], arr[subIdx] = arr[subIdx], arr[i]
		mm[arr[subIdx]], mm[arr[i]] = subIdx, i
		ops++
	}
	return ops
}

func main() {
	var N, v int
	fmt.Scanf("%d", &N)
	if N <= 2 {
		fmt.Println(0)
		return
	}
	rev := make([]int, N)
	arr := make([]int, N)
	bp := make([]int, N)
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &v)
		rev[N-1-i] = v
		arr[i] = v
		bp[i] = v
	}
	sort.Ints(bp)
	direct := solve(&arr, &bp)
	reverse := solve(&rev, &bp)
	if direct < reverse {
		fmt.Println(direct)
	} else {
		fmt.Println(reverse)
	}
}
