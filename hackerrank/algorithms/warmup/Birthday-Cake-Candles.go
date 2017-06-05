package main

import "fmt"

func main() {
	var N, current, count, max int
	fmt.Scanf("%d", &N)
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &current)
		if current == max {
			count++
		}
		if current > max {
			max = current
			count = 1
		}
	}
	fmt.Println(count)
}
