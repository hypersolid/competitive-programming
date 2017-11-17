// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

// package main

import "sort"

// import (
// 	"fmt"
// 	"sort"
// )
//
// func main() {
// 	fmt.Println(reconstructQueue([][]int{[]int{7, 0}, []int{4, 4}, []int{7, 1}, []int{5, 0}, []int{6, 1}, []int{5, 2}}))
// }

type Pairs struct {
	array [][]int
}

func shift(array [][]int, to, from int) {
	val := array[from]
	for i := from; i > to; i-- {
		array[i] = array[i-1]
	}
	array[to] = val
}

func (p Pairs) Len() int      { return len(p.array) }
func (p Pairs) Swap(i, j int) { p.array[i], p.array[j] = p.array[j], p.array[i] }
func (p Pairs) Less(i, j int) bool {
	if p.array[i][0] == p.array[j][0] {
		return p.array[i][1] < p.array[j][1]
	}
	return p.array[i][0] > p.array[j][0]
}

func reconstructQueue(people [][]int) [][]int {
	p := Pairs{array: people}
	sort.Sort(p)
	for i := range people {
		if people[i][1] != i {
			shift(p.array, p.array[i][1], i)
		}
	}
	return people
}
