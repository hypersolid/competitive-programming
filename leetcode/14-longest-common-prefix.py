class Solution:
    def longestCommonPrefixInternal(self, strs):
        length = 0
        while True:
            letter = None
            for s in strs:
                if len(s) < length+1:
                    return length
                if letter is None:
                    letter = s[length]

                if s[length] != letter:
                    return length

            length+=1
        return length

    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if len(strs) == 0:
            return ""

        length = self.longestCommonPrefixInternal(strs)
        if length == 0:
            return ""
        return strs[0][:length]
