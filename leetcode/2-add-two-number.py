# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        result_head = ListNode(0)
        result = result_head
        slow_result = result_head
        base  = 10

        # add digits
        while l1 is not None or l2 is not None:
            slow_result = result
            if l1 is not None:
                result.val += l1.val
            if l2 is not None:
                result.val += l2.val

            carry = result.val // base
            result.next = ListNode(carry)
            result.val = result.val % base

            result = result.next
            if l1 is not None:
                l1 = l1.next
            if l2 is not None:
                l2 = l2.next

        # gets rid of prefix zero
        if slow_result.next and slow_result.next.val == 0:
            slow_result.next = None

        return result_head
