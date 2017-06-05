package main

import "fmt"

func main() {
	var N, grade int
	fmt.Scanf("%d", &N)
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &grade)
		fmt.Println(round(grade))
	}

}

func round(grade int) int {
	if grade < 38 {
		return grade
	}
	if grade%5 >= 3 {
		return (grade + 5) / 5 * 5
	}
	return grade
}
