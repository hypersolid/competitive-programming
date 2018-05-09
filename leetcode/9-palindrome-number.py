class Solution:
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x < 0:
            return False

        digits = []
        while x > 0:
            digit = x % 10
            x //=  10
            digits.append(digit)

        for i in range(len(digits)//2):
            if digits[i] != digits[len(digits)-1-i]:
                return False

        return True
