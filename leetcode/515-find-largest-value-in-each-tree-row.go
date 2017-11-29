package main

import "container/list"

func main() {}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

const minValue = 1 << 60 * -1

func largestValues(root *TreeNode) []int {
	currentMax := minValue
	values := make([]int, 0, 1000)
	if root == nil {
		return values
	}

	l := list.New()
	l.PushBack(root)
	l.PushBack(nil)

	for l.Len() > 0 {
		el := l.Front()
		l.Remove(el)
		if el.Value == nil {
			if l.Len() > 0 {
				l.PushBack(nil)
			}
			values = append(values, currentMax)
			currentMax = minValue
			continue
		}
		node := el.Value.(*TreeNode)
		if node.Val > currentMax {
			currentMax = node.Val
		}
		if node.Left != nil {
			l.PushBack(node.Left)
		}
		if node.Right != nil {
			l.PushBack(node.Right)
		}
	}
	return values

}
