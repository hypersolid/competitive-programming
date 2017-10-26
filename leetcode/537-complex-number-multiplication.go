import (
	"fmt"
	"strconv"
	"strings"
)

func complexNumberMultiply(str1 string, str2 string) string {
	aParts := strings.Split(strings.Replace(str1, "i", "", 1), "+")
	bParts := strings.Split(strings.Replace(str2, "i", "", 1), "+")
	aS, bS, cS, dS := aParts[0], aParts[1], bParts[0], bParts[1]
	a, _ := strconv.Atoi(aS)
	b, _ := strconv.Atoi(bS)
	c, _ := strconv.Atoi(cS)
	d, _ := strconv.Atoi(dS)
	return fmt.Sprintf("%d+%di", a*c-b*d, b*c+a*d)
}
