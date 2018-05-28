class Solution:
    def findPeakElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        return self.findPeakElementInternal(nums, 0, len(nums) - 1)

    def findPeakElementInternal(self, nums, start, end):
        """
        :type nums: List[int]
        :rtype: int
        """

        n = len(nums)
        mid = start + (end - start) // 2
        left_condition = mid - 1 < 0 or nums[mid - 1] < nums[mid]
        right_condition = mid + 1 >= n or nums[mid + 1] < nums[mid]
        if left_condition and right_condition:
            return mid

        if left_condition:
            return self.findPeakElementInternal(nums, mid + 1, end)
        return self.findPeakElementInternal(nums, start, mid - 1)
