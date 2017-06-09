package main

import (
	"fmt"
	"math"
)

func decompose(n, base, limit float64) int {
	if n == 0 {
		return 1
	}
	power := math.Floor(math.Pow(n, 1/base))

	result := 0
	for i := math.Min(power, limit); i >= 1; i-- {
		result += decompose(n-math.Pow(i, base), base, i-1)
	}
	return result
}

func main() {
	var p, n int
	fmt.Scanf("%d\n%d", &n, &p)
	fmt.Println(decompose(float64(n), float64(p), math.MaxFloat64))
}
