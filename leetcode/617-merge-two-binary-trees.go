func mergeTrees(t1 *TreeNode, t2 *TreeNode) *TreeNode {
  if t1 == nil && t2 == nil {
    return nil
  }
  if t1 == nil {
      t1 = &TreeNode{Val: 0}
  }
  if t2 == nil {
      t2 = &TreeNode{Val: 0}
  }

  root := t1
  root.Val += t2.Val

  root.Left = mergeTrees(t1.Left, t2.Left)
  root.Right = mergeTrees(t1.Right, t2.Right)

  return root
}
