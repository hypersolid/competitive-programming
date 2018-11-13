/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
 * right; TreeNode(int x) { val = x; } }
 */
class Solution {
  int result = 0;
  boolean flag = false;
  int L;
  int R;

  void checkNode(TreeNode root) {
    if (root.val == L) {
      flag = true;
    }
    if (flag) {
      result += root.val;
    }
    if (root.val == R) {
      flag = false;
    }
  }

  public int rangeSumBST(TreeNode root, int L, int R) {
    this.R = R;
    this.L = L;
    if (root == null) {
      return 0;
    }
    ArrayDeque<TreeNode> stack = new ArrayDeque<>();

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      root = stack.pop();
      checkNode(root);
      root = root.right;
    }

    return result;
  }
}
