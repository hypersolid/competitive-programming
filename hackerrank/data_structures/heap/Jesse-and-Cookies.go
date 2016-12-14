package main

import (
	"container/heap"
	"fmt"
)

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func sweeten(h *IntHeap, k int) int {
	counter := 0
	for (*h)[0] < k {
		if h.Len() < 2 {
			return -1
		}
		sweetness := heap.Pop(h).(int)
		sweetness += heap.Pop(h).(int) * 2
		heap.Push(h, sweetness)
		counter++
	}
	return counter
}

func main() {
	var n, k int
	fmt.Scanf("%d %d", &n, &k)

	if n == 0 {
		fmt.Print(-1)
		return
	}

	h := make(IntHeap, n)
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &h[i])
	}
	heap.Init(&h)
	fmt.Print(sweeten(&h, k))
}
