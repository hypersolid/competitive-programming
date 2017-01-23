package main

import "fmt"

func main() {
	var n int
	var s string
	var rs []rune
	fmt.Scanln(&n)
	for i := 0; i < n; i++ {
		fmt.Scanln(&s)
		if len(s)%2 != 0 {
			fmt.Println(-1)
		} else {
			rs = []rune(s)
			l := len(s) / 2
			f1 := make(map[rune]int, l)
			f2 := make(map[rune]int, l)
			for j := 0; j < l; j++ {
				f1[rs[j]] = f1[rs[j]] + 1
				f2[rs[len(rs)-1-j]] = f2[rs[len(rs)-1-j]] + 1
			}
			diff := 0
			for k, v := range f2 {
				if f1[k] < v {
					diff += v - f1[k]
				}
			}
			fmt.Println(diff)
		}
	}
}
