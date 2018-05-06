class Solution:
    def internalLengthOfLongestSubstring(self):
        ptr = 1
        start = 0
        max_length = 1
        length = 1
        while ptr < len(self.lookback):
            v = self.lookback[ptr]
            if v is not None and v >= start:
                start = v + 1
                ptr = v + 2
                max_length = max(length, max_length)
                length = 1
            else:
                length += 1
                ptr += 1
        max_length = max(length, max_length)
        return max_length

    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        if len(s) == 0:
            return 0

        self.string = s
        self.lookback = [None for x in range(len(s))]
        lookdict = {}

        # preprocessing
        for i, ch in enumerate(s):
            if ch in lookdict:
                self.lookback[i] = lookdict[ch]
            lookdict[ch] = i

        # search
        return self.internalLengthOfLongestSubstring()
