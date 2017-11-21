func isOneBitCharacter(bits []int) bool {
	blocked := false
	for i := 0; i < len(bits)-1; i++ {
		blocked = bits[i] == 1 && !blocked
	}
	return !blocked
}
