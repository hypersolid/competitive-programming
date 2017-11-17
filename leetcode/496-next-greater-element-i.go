func nextGreaterElement(findNums []int, nums []int) []int {
	hash := make(map[int]int, len(nums))
	for i := range nums {
		hash[nums[i]] = -1
		for j := i + 1; j < len(nums); j++ {
			if nums[i] < nums[j] {
				hash[nums[i]] = nums[j]
				break
			}
		}
	}
	for i := range findNums {
		findNums[i] = hash[findNums[i]]
	}
	return findNums
}
