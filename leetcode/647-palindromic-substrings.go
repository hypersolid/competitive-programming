func expandCenter(chars []rune, i int) int {
	padding := 0
	for i-padding-1 >= 0 && i+padding+1 < len(chars) && chars[i-padding-1] == chars[i+padding+1] {
		padding++
	}
	return padding/2 + padding%2

}

func countSubstrings(s string) int {
	N := len(s)*2 + 1
	chars := make([]rune, N)
	i := 0
	for _, r := range s {
		chars[2*i+1] = r
		i++
	}
	sum := 0
	for i := 0; i < N; i++ {
		sum += expandCenter(chars, i)
	}
	return sum
}
