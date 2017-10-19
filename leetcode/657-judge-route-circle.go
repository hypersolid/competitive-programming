func judgeCircle(moves string) bool {
	var x, y int
	for _, ch := range moves {
		switch ch {
		case 'U':
			y++
		case 'D':
			y--
		case 'L':
			x--
		case 'R':
			x++
		}
	}
	if x == 0 && y == 0 {
		return true
	}
	return false
}
