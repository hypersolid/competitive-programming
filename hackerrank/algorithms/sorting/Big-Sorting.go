package main

import "fmt"

func main() {
	var N, max int
	const BASE = 10
	buckets := make([][]*string, BASE)
	fmt.Scanf("%d\n", &N)
	arr := make([]*string, N)
	res := make([]*string, 0, N)
	repeat := make([]*string, 0, N)

	// input
	for i := 0; i < N; i++ {
		var s string
		fmt.Scanln(&s)
		arr[i] = &s
		if max < len(s) {
			max = len(s)
		}
	}
	for b := 0; b < BASE; b++ {
		buckets[b] = make([]*string, len(arr))
	}

	// sort
	for i := 0; i < max; i++ {
		// reset buckets
		for b := 0; b < BASE; b++ {
			buckets[b] = buckets[b][:0]
		}
		// put strings into buckets
		for n := 0; n < len(arr); n++ {
			st := *arr[n]
			if len(st) < i+1 {
				res = append(res, arr[n])
			} else {
				idx := len(st) - 1 - i
				bucketID := int(st[idx] - '0')
				buckets[bucketID] = append(buckets[bucketID], arr[n])
			}
		}
		// restore array
		repeat = repeat[:0]
		for b := 0; b < BASE; b++ {
			for _, ptr := range buckets[b] {
				repeat = append(repeat, ptr)
			}
		}
		// copy repeat to source array
		arr = arr[:len(repeat)]
		for i := range repeat {
			arr[i] = repeat[i]
		}
	}

	// output
	for i := 0; i < len(res); i++ {
		fmt.Println(*res[i])
	}
	for i := 0; i < len(repeat); i++ {
		fmt.Println(*repeat[i])
	}
}
