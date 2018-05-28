# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def split(self, nums):
        center = len(nums) // 2
        return nums[0:center], nums[center], nums[center+1:len(nums)]
    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        if len(nums) == 0:
            return None

        left, center, right = self.split(nums)

        root = TreeNode(center)
        root.left = self.sortedArrayToBST(left)
        root.right = self.sortedArrayToBST(right)

        return root
        
