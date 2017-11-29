func numberOfArithmeticSlices(A []int) int {
	var counter, result int
	for i := 1; i < len(A)-1; i++ {
		j := i
		for counter = 0; j+counter < len(A)-1; counter++ {
			diff := A[j+counter] - A[j-1+counter]
			nextDiff := A[j+1+counter] - A[j+counter]
			if nextDiff != diff {
				break
			}
		}
		if counter > 0 {
			result += (counter) * (1 + counter) / 2
			i += counter
		}
	}
	return result
}
