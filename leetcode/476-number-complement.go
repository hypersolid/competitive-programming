func findComplement(num int) int {
	var flag bool
	for i := uint(63); i < uint(64); i-- {
		if num&(1<<i) > 0 {
			flag = true
		}
		if flag {
			num ^= (1 << i)
		}
	}
	return num
}
