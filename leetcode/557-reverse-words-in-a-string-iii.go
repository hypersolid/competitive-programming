import (
	"bytes"
	"regexp"
	"strings"
)

func reverseWords(s string) string {
	var result bytes.Buffer
	s = strings.Trim(s, " ")

	re := regexp.MustCompile("\\s+")
	s = re.ReplaceAllString(s, " ")

	limiter := -1
	for i, ch := range s {
		if ch == ' ' {
			for j := i - 1; j > limiter; j-- {
				result.WriteByte(s[j])
			}
			limiter = i
			result.WriteByte(' ')
		}
	}
	for j := len(s) - 1; j > limiter; j-- {
		result.WriteByte(s[j])
	}
	return string(result.Bytes())
}
