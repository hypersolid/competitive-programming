package main

import "fmt"

// 2
// hereiamstackerrank
// hackerworld
//
// YES
// NO

func main() {
	const QUERY = "hackerrank"
	var N int
	var sentence string
	fmt.Scanf("%d", &N)
	for i := 0; i < N; i++ {
		fmt.Scanln(&sentence)
		if subString(sentence, QUERY) {
			fmt.Println("YES")
		} else {
			fmt.Println("NO")
		}

	}
}

func subString(s, q string) bool {
	pQ := 0
	for pS := range s {
		if s[pS] != q[pQ] {
			continue
		}
		pQ++
		if pQ == len(q)-1 {
			return true
		}
	}
	return false
}
