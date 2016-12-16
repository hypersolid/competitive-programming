package main

import (
	"container/heap"
	"fmt"
	"sort"
)

type Order struct {
	arrived uint64
	weight  uint64
}

type Orders []Order

func (h *Orders) Len() int           { return len(*h) }
func (h *Orders) Swap(i, j int)      { (*h)[i], (*h)[j] = (*h)[j], (*h)[i] }
func (h *Orders) Less(i, j int) bool { return (*h)[i].arrived < (*h)[j].arrived }

type MinHeap []Order

func (h *MinHeap) Peek() Order       { return (*h)[0] }
func (h *MinHeap) Len() int          { return len(*h) }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h MinHeap) Less(i, j int) bool { return h[i].weight < h[j].weight }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(Order))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func process(nextOpportunity *uint64, total *uint64, queue *MinHeap) {
	order := heap.Pop(queue).(Order)
	*nextOpportunity += uint64(order.weight)
	*total += *nextOpportunity - uint64(order.arrived)
}

// https://en.wikipedia.org/wiki/Shortest_job_next
func main() {
	var n int
	fmt.Scanf("%d", &n)

	orders := make(Orders, n)
	queue := make(MinHeap, 0)

	for i := 0; i < n; i++ {
		orders[i] = Order{}
		fmt.Scanf("%d %d", &orders[i].arrived, &orders[i].weight)
	}

	sort.Sort(&orders)

	var (
		nextOpportunity uint64
		total           uint64 = 0
	)

	for i := 0; i < n; {
		if orders[i].arrived <= nextOpportunity {
			heap.Push(&queue, orders[i])
			i++
		} else {
			if len(queue) == 0 {
				nextOpportunity = orders[i].arrived
			} else {
				process(&nextOpportunity, &total, &queue)
			}
		}
	}
	for len(queue) > 0 {
		process(&nextOpportunity, &total, &queue)
	}

	fmt.Println(total / uint64(n))
}
