class Solution:
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0

        sum = 0
        max = nums[0]
        for n in nums:
            sum +=  n
            if sum < n:
                sum = n
            if  max < sum:
                max = sum
        return max
