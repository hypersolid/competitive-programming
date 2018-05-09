class Solution:

    def longestFromI(self, i):
        offset = i % 1
        span = min(i, self.N - 1 - i)
        length = 0
        if offset == 0:
            length = 1

        for k in range(length, int(span) + 1):
            if self.s[int(i - offset - k)] != self.s[int(i + offset + k)]:
                break
            length += 1

        if offset == 0:
            return self.s[int(i - length + 1):int(i + length)]
        return self.s[int(i - offset - length + 1):int(i - offset + length + 1)]

    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        self.s = s
        self.N = len(self.s)
        max_len = 0
        result = ""

        for i in range(0, self.N * 2 - 1):
            palindrome = self.longestFromI(i / 2)
            if len(palindrome) > max_len:
                max_len = len(palindrome)
                result = palindrome

        return result

result = Solution().longestPalindrome("abbc")
print(result)
