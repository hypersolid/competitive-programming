package main

import "fmt"

func countU(s string) (maxU []int) {
	maxU = make([]int, 26)
	if len(s) == 0 {
		return
	}

	var (
		ch, prev byte
		acc      int
	)

	prev = s[0]
	acc = 1
	for i := 1; i < len(s); i++ {
		ch = s[i]
		if ch == prev {
			acc++
		} else {
			acc = 1
		}
		idx := int(prev) - int('a')
		if maxU[idx] < acc {
			maxU[idx] = acc
		}
		prev = ch
	}
	idx := int(prev) - int('a')
	if maxU[idx] < acc {
		maxU[idx] = acc
	}
	return
}

func queryU(s *string, maxU *[]int, q int) bool {
	for i, v := range *maxU {
		if q%(i+1) == 0 && v >= q/(i+1) {
			return true
		}
	}
	return false
}

func main() {
	var (
		N, q int
		s    string
	)

	fmt.Scanln(&s)
	fmt.Scanf("%d", &N)

	maxU := countU(s)

	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &q)

		if queryU(&s, &maxU, q) {
			fmt.Println("Yes")
		} else {
			fmt.Println("No")
		}
	}
}
