package main

import (
	"container/heap"
	"fmt"
)

type MinHeap []float64

func (h *MinHeap) Len() int          { return len(*h) }
func (h *MinHeap) Peek() float64     { return (*h)[0] }
func (h MinHeap) Swap(i, j int)      { (h)[i], (h)[j] = (h)[j], (h)[i] }
func (h MinHeap) Less(i, j int) bool { return (h)[i] < (h)[j] }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(float64))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type MaxHeap []float64

func (h *MaxHeap) Len() int          { return len(*h) }
func (h *MaxHeap) Peek() float64     { return (*h)[0] }
func (h MaxHeap) Swap(i, j int)      { (h)[i], (h)[j] = (h)[j], (h)[i] }
func (h MaxHeap) Less(i, j int) bool { return (h)[i] > (h)[j] }
func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(float64))
}
func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func balance(left *MaxHeap, right *MinHeap, element float64) {
	if left.Len() == 0 || element <= left.Peek() {
		heap.Push(left, element)
	} else {
		heap.Push(right, element)
	}

	if left.Len()-1 > right.Len() {
		heap.Push(right, heap.Pop(left))
	}
	if right.Len()-1 > left.Len() {
		heap.Push(left, heap.Pop(right))
	}
}

func median(left *MaxHeap, right *MinHeap) float64 {
	if left.Len() > right.Len() {
		return left.Peek()
	} else {
		if right.Len() > left.Len() {
			return right.Peek()
		} else {
			return (right.Peek() + left.Peek()) / 2
		}
	}
}

func main() {
	var n int
	fmt.Scanf("%d", &n)

	left := make(MaxHeap, 0)
	right := make(MinHeap, 0)
	var element float64
	for i := 0; i < n; i++ {
		fmt.Scanf("%f", &element)
		balance(&left, &right, element)
		fmt.Printf("%.1f\n", median(&left, &right))
	}
}
