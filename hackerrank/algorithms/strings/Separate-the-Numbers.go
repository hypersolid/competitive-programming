package main

import (
	"fmt"
	"strconv"
)

func checkProgression(start int64, s string) bool {
	ptr := 0
	nextNumber := start + 1
	for ptr < len(s) {
		nextString := strconv.FormatInt(nextNumber, 10)
		if ptr+len(nextString) > len(s) {
			return false
		}
		for i := range nextString {
			if s[ptr+i] != nextString[i] {
				return false
			}
		}
		ptr += len(nextString)
		nextNumber++
	}
	return true
}

func solve(s string) int64 {
	var firstNumber int64
	for i := 1; i <= len(s)/2; i++ {
		firstNumber, _ = strconv.ParseInt(s[0:i], 10, 64)
		if checkProgression(firstNumber, s[i:]) {
			return firstNumber
		}
	}
	return -1
}

func main() {
	var (
		N int
		s string
	)
	fmt.Scanf("%d\n", &N)
	for n := 0; n < N; n++ {
		fmt.Scanln(&s)
		if len(s) < 2 || s[0] == '0' {
			fmt.Println("NO")
		} else {
			result := solve(s)
			if result > 0 {
				fmt.Println("YES", result)
			} else {
				fmt.Println("NO")
			}
		}
	}
}
