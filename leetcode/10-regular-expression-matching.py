class Solution:
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        if len(p) == 0:
            return len(s) == 0

        # case of *
        if len(p) > 1 and p[1] == "*":
            if self.isMatch(s,p[2:]):
                return True
            for i in range(len(s)):
                if p[0] != '.' and s[i] != p[0]:
                    return False
                if self.isMatch(s[i+1:],p[2:]):
                    return True
            return False

        # case of other letters
        if len(s) == 0:
            return False
        if p[0] != '.' and s[0] != p[0]:
            return False
        return self.isMatch(s[1:],p[1:])
