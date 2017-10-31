import "fmt"

func fizzBuzz(n int) []string {
	result := make([]string, n)
	for i := 1; i <= n; i++ {
		result[i-1] = ""
		if i%3 == 0 {
			result[i-1] = "Fizz"
		}
		if i%5 == 0 {
			result[i-1] = result[i-1] + "Buzz"
		}
		if result[i-1] == "" {
			result[i-1] = fmt.Sprintf("%d", i)
		}
	}
	return result
}
