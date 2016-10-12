import datetime

d1, m1, y1 = input().strip().split(' ')
d1, m1, y1 = [int(d1), int(m1), int(y1)]
d2, m2, y2 = input().strip().split(' ')
d2, m2, y2 = [int(d2), int(m2), int(y2)]

returned = datetime.datetime(year=y1, month=m1, day=d1)
expected = datetime.datetime(year=y2, month=m2, day=d2)

fine = 0
if returned > expected:
    if m1 == m2 and y1 == y2:
        fine = (returned - expected).days * 15
    else:
        if y1 == y2:
            fine = (m1 - m2) * 500
        else:
            fine = 10000

print(fine)
