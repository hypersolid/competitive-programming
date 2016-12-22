package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
)

type void struct{}

type Node struct {
	number  int
	fare    int
	visited bool
	index   int
}

type Queue []*Node

func (q Queue) Len() int           { return len(q) }
func (q Queue) Less(i, j int) bool { return q[i].fare < q[j].fare }
func (q Queue) Swap(i, j int) {
	q[i], q[j] = q[j], q[i]
	q[i].index = i
	q[j].index = j
}
func (q *Queue) Push(np interface{}) {
	np.(*Node).index = q.Len()
	*q = append(*q, np.(*Node))
}
func (q *Queue) Pop() interface{} {
	node := (*q)[q.Len()-1]
	node.index = -1
	*q = (*q)[0 : q.Len()-1]
	return node
}
func (q *Queue) Update(node *Node, fare int) {
	node.fare = fare
	if node.index == -1 {
		heap.Push(q, node)
	} else {
		heap.Fix(q, node.index)
	}
}

func fancyFare(fareSoFar, fare int) (result int) {
	result = fare - fareSoFar
	if result < 0 {
		result = 0
	}
	result += fareSoFar
	return
}

func solve(n int, edges map[int]map[int]int) (result int) {
	nodes := make([]*Node, n)
	for i := 0; i < n; i++ {
		nodes[i] = &Node{number: i, index: -1}
		nodes[i].fare = 1 << 31
	}
	queue := make(Queue, 0, n)
	queue.Update(nodes[0], 0)

	for queue.Len() > 0 {
		current := heap.Pop(&queue).(*Node)
		if current.visited {
			continue
		}
		if current.number == n-1 {
			result = current.fare
			return
		}
		current.visited = true
		for next, fare := range edges[current.number] {
			nextNode := nodes[next]
			if nextNode.visited {
				continue
			}
			oldFare, newFare := nextNode.fare, fancyFare(current.fare, fare)
			if oldFare <= newFare {
				continue
			}
			queue.Update(nextNode, newFare)
		}
	}

	result = -1
	return
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)

	var n, e int
	scanner.Scan()
	fmt.Sscanln(scanner.Text(), &n, &e)

	edges := make(map[int]map[int]int)

	var town1, town2, fare int

	for scanner.Scan() {
		fmt.Sscanln(scanner.Text(), &town1, &town2, &fare)
		town1--
		town2--
		if _, ok := edges[town1]; !ok {
			edges[town1] = make(map[int]int)
		}
		if _, ok := edges[town2]; !ok {
			edges[town2] = make(map[int]int)
		}
		edges[town1][town2] = fare
		edges[town2][town1] = fare
	}

	cost := solve(n, edges)
	if cost == -1 {
		fmt.Print("NO PATH EXISTS")
	} else {
		fmt.Print(cost)
	}
}
