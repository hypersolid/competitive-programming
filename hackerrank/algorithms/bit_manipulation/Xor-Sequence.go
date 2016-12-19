package main

import "fmt"

func element(number uint64) uint64 {
	a := number % 8
	if a < 2 {
		return number
	}
	if a < 4 {
		return 2
	}
	if a < 6 {
		return number + 2
	}
	return 0
}

func main() {
	var n int
	var l, r uint64
	fmt.Scanf("%d", &n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d %d", &l, &r)
		fmt.Println(element(l-1) ^ element(r))
	}
}
