package main

import "fmt"

func main() {
	var (
		s                 string
		t, count, diff, l int
	)
	fmt.Scanln(&t)
	for i := 0; i < t; i++ {
		fmt.Scanln(&s)
		count = 0
		l = len(s)
		for j := 0; j < l/2; j++ {
			diff = int(s[j]) - int(s[l-1-j])
			if diff < 0 {
				diff = -diff
			}
			count += diff
		}
		fmt.Println(count)
	}
}
