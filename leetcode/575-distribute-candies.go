import "sort"

func distributeCandies(candies []int) int {
	if len(candies) == 0 {
		return 0
	}
	sort.Ints(candies)
	count := 1
	for i := 1; i < len(candies); i++ {
		if candies[i] != candies[i-1] {
			count++
		}
	}
	if count > len(candies)/2 {
		return len(candies) / 2
	}
	return count
}
