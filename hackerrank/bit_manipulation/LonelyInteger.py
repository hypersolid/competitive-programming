#!/usr/bin/py
# Head ends here


def lonelyinteger(b):
    answer = 0
    for i in b:
        answer ^= i
    return answer

# Tail starts here
if __name__ == '__main__':
    a = int(input())
    b = map(int, input().strip().split(" "))
    print(lonelyinteger(b))
