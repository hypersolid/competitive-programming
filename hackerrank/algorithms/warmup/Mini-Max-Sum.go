package main

import "fmt"
import "math"

func main() {
	N := 5
	var sum, current int
	min := math.MaxInt64
	max := math.MinInt64
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &current)
		if current > max {
			max = current
		}
		if current < min {
			min = current
		}
		sum += current
	}
	fmt.Println(sum-max, sum-min)
}
