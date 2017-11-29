
import "container/list"

func averageOfLevels(root *TreeNode) []float64 {
	l := list.New()
	result := make([]float64, 0)
	if root != nil {
		l.PushBack(root)
		l.PushBack(nil)
	}
	var acc, cnt int
	for l.Len() > 0 {
		el := l.Front()
		l.Remove(el)
		if el.Value == nil {
			result = append(result, float64(acc)/float64(cnt))
			if l.Len() > 0 {
				l.PushBack(nil)
				acc = 0
				cnt = 0
			}
		} else {
			v := el.Value.(*TreeNode)
			acc += v.Val
			cnt++
			if v.Left != nil {
				l.PushBack(v.Left)
			}
			if v.Right != nil {
				l.PushBack(v.Right)
			}
		}
	}
	return result
}
