/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
public class Solution {
  public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }
}
