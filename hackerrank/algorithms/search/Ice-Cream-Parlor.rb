def solve
  dollars = gets.to_i
  gets.to_i # don't need this line
  types = gets.split(' ').map(&:to_i)
  types = types.each_with_index
          .map { |v, i| [i + 1, v] }
          .sort { |a, b| a[1] <=> b[1] }

  i = 0
  j = types.size - 1
  while types[i][1] + types[j][1] != dollars do
    if types[i][1] + types[j][1] > dollars
      j -= 1
      if i == j
        i += 1
        j = types.size - 1
      end
    else
      i += 1
    end
  end
  result = [types[i][0], types[j][0]]
  puts result.sort.join(' ')
end

t = gets.to_i
t.times { solve }
