/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 func maxInRange(arr []int, i int, j int) *TreeNode  {
  if i > j  {
    return nil
  }
  var max, id int
  max = -1
  for k:=i; k<=j; k++ {
    if arr[k] > max {
      max = arr[k]
      id = k
    }
  }
  return &TreeNode{
    Val: max,
    Left: maxInRange(arr, i, id - 1),
    Right: maxInRange(arr, id + 1, j),
  }
 }

 func constructMaximumBinaryTree(nums []int) *TreeNode {
  return maxInRange(nums, 0, len(nums) - 1)
 }
