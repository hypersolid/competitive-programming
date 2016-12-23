package main

import "fmt"

const ALPHABET = 26

func chipher(start, end, letter, offset int) (result int) {
	result = letter
	if letter >= start && letter <= end {
		result = start + (result+offset-start)%ALPHABET
	}
	return
}

func main() {
	var (
		n, k    int
		initial string
	)
	fmt.Scanf("%d\n", &n)
	fmt.Scanln(&initial)
	fmt.Scanf("%d", &k)

	chipherLower := func(letter int) int {
		return chipher(int('a'), int('z'), letter, k)
	}

	chipherUpper := func(letter int) int {
		return chipher(int('A'), int('Z'), letter, k)
	}

	for _, r := range initial {
		fmt.Printf("%c", chipherUpper(chipherLower(int(r))))
	}
}
