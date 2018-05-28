class Solution:
    def print(self):
        self.result.append(self.nums[:])

    def permuteInternal(self, start, N):
        if start == N - 1:
            self.print()
            return
        for i in range(start, N):
            self.nums[start], self.nums[i] = self.nums[i], self.nums[start]
            self.permuteInternal(start+1, N)
            self.nums[start], self.nums[i] = self.nums[i], self.nums[start]

    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        self.nums = nums
        self.result = []
        self.permuteInternal(0, len(nums))
        return self.result


        
