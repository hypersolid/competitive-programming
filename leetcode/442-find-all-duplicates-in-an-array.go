func findDuplicates(nums []int) []int {
	for _, n := range nums {
		if n < 0 {
			n *= -1
		}
		nums[n-1] *= -1
	}
	result := make([]int, 0, len(nums))
	for i := range nums {
		v := nums[i]
		if v < 0 {
			v *= -1
		}
		if nums[v-1] > 0 {
			result = append(result, v)
			nums[v-1] *= -1
		}
	}
	return result
}
