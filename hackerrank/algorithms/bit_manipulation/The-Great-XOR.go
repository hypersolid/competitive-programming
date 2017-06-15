package main

import "fmt"

func solve(x uint64) uint64 {
	// a^x>x
	// 0<a<x
	var answer, mask uint64
	for sh := uint64(0); sh < 64; sh++ {
		mask = 1 << sh
		if mask >= x {
			return answer
		}
		if mask&^x == mask {
			answer += mask
		}
	}
	return answer
}

func main() {
	var N, v uint64
	fmt.Scanf("%d", &N)
	for n := uint64(0); n < N; n++ {
		fmt.Scanf("%d", &v)
		fmt.Println(solve(v))
	}
}
