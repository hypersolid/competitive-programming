func findWords(words []string) []string {
    result := make([]string, 0, len(words))
    for _, word := range words {
        if check(word) {
            result = append(result, word)
        }
    }
    return result
}

func check(s string) bool {
    row := 0
    for _, ch := range s {
        switch ch {
            case 'a','s','d','f','g','h','j','k','l','A','S','D','F','G','H','J','K','L':
            if row > 0 && row != 1 {
                return false
            }
            row = 1
          case 'q','w','e','r','t','y','u','i','o','p','Q','W','E','R','T','Y','U','I','O','P':
                         if row > 0 && row != 2 {
                return false
            }
            row = 2
          case 'z', 'x','c','v','b','n','m','Z', 'X', 'C','V','B','N','M':
                if row > 0 && row != 3 {
                return false
            }
            row = 3
        }
    }
    return true
}
