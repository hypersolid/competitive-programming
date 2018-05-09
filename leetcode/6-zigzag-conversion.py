class Solution:
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        if numRows == 1:
            return s
        result=[[] for x in range(numRows)]
        for i, ch in enumerate(s):
            block_position = i % (numRows + numRows - 2)
            if block_position < numRows:
                result[block_position].append(ch)
            else:
                result[numRows - (block_position%numRows+1) - 1 ].append(ch)

        # print(result)
        return ''.join([''.join(x) for x in result])
