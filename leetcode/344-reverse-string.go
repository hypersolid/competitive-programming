import "bytes"

func reverseString(s string) string {
	var buff bytes.Buffer
	for i := range s {
		buff.WriteByte(s[len(s)-1-i])
	}
	return string(buff.Bytes())

}
