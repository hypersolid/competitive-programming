// Implement a data structure supporting the following operations:
import "fmt"

// Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
// Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
// GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
// GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
// Challenge: Perform all these in O(1) time complexity.

func (this *AllOne) print() {
	ptr := this.tail
	fmt.Printf(" tail: ")
	for ptr != nil {
		fmt.Printf("-> %d %#v", ptr.value, ptr.set)
		ptr = ptr.next
	}
	ptr = this.head
	fmt.Printf("\n")
	fmt.Printf(" head: ")
	for ptr != nil {
		fmt.Printf("%d %#v -> ", ptr.value, ptr.set)
		ptr = ptr.prev
	}
	fmt.Printf("\n\n")
}

func (this *AllOne) sync() {
	// this.print()
}

/////////////////////////////////////////////////////////

type element struct {
	value      int
	set        map[string]struct{}
	next, prev *element
}

func newElement(val int) *element {
	return &element{
		set:   make(map[string]struct{}),
		value: val,
	}
}

func (e *element) empty() bool {
	return len(e.set) == 0
}

func (e *element) erradicate(key string) {
	delete(e.set, key)
	if !e.empty() || e.value == 1 {
		return
	}
	if e.next != nil {
		e.next.prev = e.prev
	}
	if e.prev != nil {
		e.prev.next = e.next
	}
}

func (e *element) inc(key string) *element {
	if e.value != 1 && len(e.set) == 1 && (e.next == nil || e.next.value > e.value+1) {
		e.value++
		return e
	}

	if e.next == nil || e.next.value > e.value+1 {
		ne := newElement(e.value + 1)
		ne.prev = e
		ne.next = e.next
		if e.next != nil {
			e.next.prev = ne
		}
		e.next = ne
	}
	// moves key up the list
	e.next.set[key] = struct{}{}
	e.erradicate(key)
	return e.next
}

func (e *element) dec(key string) *element {
	if len(e.set) == 1 && (e.prev == nil || e.prev.value < e.value-1) {
		e.value--
		return e
	}

	// check if there's suitable prev element
	if e.prev == nil || e.prev.value < e.value-1 {
		ne := newElement(e.value - 1)
		ne.next = e
		ne.prev = e.prev
		if e.prev != nil {
			e.prev.next = ne
		}
		e.prev = ne
	}
	// moves key down the list
	e.prev.set[key] = struct{}{}
	e.erradicate(key)
	return e.prev
}

/////////////////////////////////////////////////////////
type AllOne struct {
	head, tail *element
	one        *element
	values     map[string]*element
}

func Constructor() AllOne {
	return AllOne{
		values: make(map[string]*element),
		one:    newElement(1),
	}
}

func (a *AllOne) initToOneIfNotExistent(key string) bool {
	if _, ok := a.values[key]; ok {
		return false
	}
	a.one.set[key] = struct{}{}
	a.values[key] = a.one
	return true
}

/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
func (this *AllOne) Inc(key string) {
	if !this.initToOneIfNotExistent(key) {
		this.values[key] = this.values[key].inc(key)
	}
	if this.tail == nil || this.tail.empty() || this.tail.value > this.values[key].value {
		this.tail = this.values[key]
	}
	if this.head == nil || this.head.value < this.values[key].value {
		this.head = this.values[key]
	}
	this.sync()
}

/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
func (this *AllOne) Dec(key string) {
	if _, ok := this.values[key]; !ok {
		return
	}

	if this.values[key] == this.one {
		this.one.erradicate(key)
		delete(this.values, key)
		if this.tail.empty() {
			this.tail = this.one.next
		}
		if this.head == nil || this.head.empty() {
			this.head = this.one.next
		}
	} else {
		this.values[key] = this.values[key].dec(key)
		if this.tail.value > this.values[key].value {
			this.tail = this.values[key]
		}
		if this.head.empty() {
			this.head = this.values[key]
		}
	}
}

/** Returns one of the keys with maximal value. */
func (this *AllOne) GetMaxKey() string {
	if this.head == nil {
		return ""
	}
	for k := range this.head.set {
		return k
	}
	return "ERROR"
}

/** Returns one of the keys with Minimal value. */
func (this *AllOne) GetMinKey() string {
	if this.tail == nil {
		return ""
	}
	for k := range this.tail.set {
		return k
	}
	return "ERROR"
}
