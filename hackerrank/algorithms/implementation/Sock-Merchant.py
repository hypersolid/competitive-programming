n = int(input().strip())
color_map = {}
for color in input().strip().split(' '):
    color_map.setdefault(color, 0)
    color_map[color] += 1

pairs = 0
for socks in color_map.values():
    pairs += socks // 2

print(pairs)
