package main

import (
	"fmt"
	"strings"
)

func check(array []int, index int) bool {
	baseline := 0
	for i, v := range array {
		if v == 0 {
			continue
		}
		if baseline == 0 {
			baseline = v
			continue
		}
		if baseline != v {
			if i == index && (baseline == v-1 || v == 1) {
				continue
			}
			return false
		}
	}
	return true
}

func main() {
	var s string
	fmt.Scanln(&s)
	diffs := make([]int, 26)

	for _, ch := range strings.ToLower(s) {
		diffs[ch-'a']++
	}

	for i := -1; i < len(diffs); i++ {
		if check(diffs, i) {
			fmt.Println("YES")
			return
		}
	}

	fmt.Println("NO")
}
