package main

import (
	"fmt"
	"math"
	"sort"
)

type Price struct {
	V, I int
}
type Prices []Price

func (p Prices) Len() int           { return len(p) }
func (p Prices) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }
func (p Prices) Less(i, j int) bool { return p[i].V < p[j].V }

func main() {
	var N int
	fmt.Scanf("%d", &N)
	prices := make(Prices, N)
	for i := 0; i < N; i++ {
		fmt.Scanf("%d", &prices[i].V)
		prices[i].I = i
	}
	sort.Sort(prices)

	var min, diff int
	min = math.MaxInt64
	for distance := 1; distance < N; distance++ {
		for i := 0; i < N-distance; i++ {
			if prices[i].I < prices[i+distance].I {
				continue
			}
			diff = prices[i+distance].V - prices[i].V
			if diff < min {
				min = diff
			}
		}
		if min < math.MaxInt64 {
			fmt.Println(min)
			return
		}
	}
}
