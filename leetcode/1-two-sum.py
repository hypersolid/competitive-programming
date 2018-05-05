class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        matching_index = {}
        for i, v in enumerate(nums):
            desired_number = target - v
            if desired_number in matching_index:
                return [matching_index[desired_number], i]
            matching_index[v] = i
