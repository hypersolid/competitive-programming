# https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm


def makeTable(word):
    '''
        makes jump-back table
        i.e. for word 'abcabc' and search is 'abcabcabc'
        we need to return 3 steps back after matching 6 letters
    '''
    table = [0] * (len(word) + 1)
    for i in range(2, len(word) + 1):
        ptr = table[i - 1]
        if word[ptr] == word[i - 1]:
            table[i] = ptr + 1
        else:
            table[i] = 0
    # table = [0, 0, 0, 0, 1, 2, 3]
    return table


def nextMatch(search, word):
    table = makeTable(word)

    i = 0
    while i < len(search):
        m = 0
        while i + m < len(search) and m < len(word) and search[i + m] == word[m]:
            m += 1
        if m == len(word):
            yield(i)
        i += max(m - table[m], 1)

# word = list('abcabc')
# search = list('abcdefabcabcabc')
# word = list('aba')
# search = list('ababa')
word = list('b')
search = list('bbbbb')


table = makeTable(word)
for idx in nextMatch(search, word):
    print("starts from {}".format(idx))
