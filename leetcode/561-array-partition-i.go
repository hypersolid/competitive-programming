import "sort"

func arrayPairSum(nums []int) int {
	sort.Ints(nums)
	result := 0
	for i := 0; i < len(nums)/2; i++ {
		result += nums[i*2]
	}
	return result
}
