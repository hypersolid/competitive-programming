func hasAlternatingBits(n int) bool {
	un := uint64(n)
	set := un&1 == 1
	for i := uint64(1); i <= 63 && ((uint64(1) << i) <= un); i++ {
		set = !set
		if set != (((uint64(1) << i) & un) > 0) {
			return false
		}
	}
	return true
}
