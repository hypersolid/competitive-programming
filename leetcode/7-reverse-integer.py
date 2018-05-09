class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        result = 0
        base = 10

        def sgn(x): return (1, -1)[x < 0]
        sign = sgn(x)
        x *= sign
        while x > 0:
            digit = x % base
            x = x // base
            result = result * base + digit

        if result < -2**31 or result > 2**31 - 1:
            return 0
        return sign * result


result = Solution().reverse(12)
print(result)
