package main

import (
	"fmt"
	"strings"
)

func main() {
	var a, b string
	fmt.Scanln(&a)
	fmt.Scanln(&b)
	diffs := make([]int, 26)
	for _, ch1 := range strings.ToLower(a) {
		diffs[ch1-'a']++
	}
	for _, ch2 := range strings.ToLower(b) {
		diffs[ch2-'a']--
	}
	total := 0
	for _, v := range diffs {
		if v < 0 {
			total -= v
		} else {
			total += v
		}

	}
	fmt.Println(total)
}
