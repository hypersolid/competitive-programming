package main

import "fmt"

func main() {
	var s, t, a, b, m, n, position, apples, oranges int
	fmt.Scanf("%d %d", &s, &t)
	fmt.Scanf("%d %d", &a, &b)
	fmt.Scanf("%d %d", &m, &n)

	hit := func(coord int) bool {
		return coord >= s && coord <= t
	}

	for i := 0; i < m; i++ {
		fmt.Scanf("%d", &position)
		if hit(a + position) {
			apples++
		}
	}

	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &position)
		if hit(b + position) {
			oranges++
		}
	}
	fmt.Printf("%d\n%d", apples, oranges)
}
