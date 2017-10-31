func matrixReshape(nums [][]int, r int, c int) [][]int {
	if len(nums) == 0 || len(nums[0]) == 0 {
		return nums
	}
	if r*c != len(nums)*len(nums[0]) {
		return nums
	}

	ptrR, ptrC := 0, 0
	result := make([][]int, r)
	for row := 0; row < r; row++ {
		result[row] = make([]int, c)
	}

	for row := 0; row < r; row++ {
		for col := 0; col < c; col++ {
			result[row][col] = nums[ptrR][ptrC]
			ptrC++
			if ptrC/len(nums[0]) > 0 {
				ptrC = 0
				ptrR++
			}
			if ptrR == len(nums) {
				return result
			}
		}
	}

	return result
}
