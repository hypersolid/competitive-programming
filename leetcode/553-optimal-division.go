import (
	"fmt"
	"strconv"
	"strings"
)

func optimalDivision(nums []int) string {
	strs := make([]string, len(nums))
	for i, v := range nums {
		strs[i] = strconv.Itoa(v)
	}
	if len(strs) < 3 {
		return strings.Join(strs, "/")
	}
	return fmt.Sprintf("%s/(%s)", strs[0], strings.Join(strs[1:], "/"))
}
