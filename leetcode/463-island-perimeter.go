func islandPerimeter(grid [][]int) int {
	sum := 0
	for i := range grid {
		for j := range grid[i] {
			if grid[i][j] != 1 {
				continue
			}
			sum += probe(grid, i, j)
		}
	}
	return sum
}

func probe(grid [][]int, i, j int) int {
	result := 4
	if i > 0 && grid[i-1][j] == 1 {
		result--
	}
	if j > 0 && grid[i][j-1] == 1 {
		result--
	}
	if i < len(grid)-1 && grid[i+1][j] == 1 {
		result--
	}
	if j < len(grid[i])-1 && grid[i][j+1] == 1 {
		result--
	}
	return result
}
