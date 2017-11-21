
import "container/list"

func findBottomLeftValue(root *TreeNode) int {
	list := list.New()
	current := root
	if root != nil {
		list.PushBack(root)
	}
	for list.Len() != 0 {
		currentElement := list.Front()
		current = currentElement.Value.(*TreeNode)
		list.Remove(currentElement)
		if current.Right != nil {
			list.PushBack(current.Right)
		}
		if current.Left != nil {
			list.PushBack(current.Left)
		}
	}
	return current.Val
}
