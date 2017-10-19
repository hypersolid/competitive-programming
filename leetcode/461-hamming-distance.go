func hammingDistance(x int, y int) int {
	res := 0
	k := uint32(x ^ y)
	for i := uint32(0); i < uint32(31); i++ {
		if k&(1<<i) > uint32(0) {
			res++
		}
	}
	return res
}
