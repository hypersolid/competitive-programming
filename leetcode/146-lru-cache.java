class LRUCache {
  class Node {
    Node next = null;
    Node prev = null;
    int key, value;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }

    void insertBefore(Node other) {
      if (other == null) return;
      this.next = other;
      if (other.prev != null) other.prev.next = this;
      other.prev = this;
    }
  }

  HashMap<Integer, Node> hash = new HashMap<>();
  int capacity;
  Node head = null, tail = null;

  void promote(Node node) {
    if (head == node) return;

    unbind(node);

    node.insertBefore(head);
    head = node;

    if (head.next == null) tail = head;
    else if (head.next.next == null) tail = head.next;
  }

  void unbind(Node node) {
    if (head == node) head = node.next;
    if (tail == node) {
      tail = node.prev;
    }

    if (node.prev != null) node.prev.next = node.next;
    if (node.next != null) node.next.prev = node.prev;

    node.prev = null;
    node.next = null;
  }

  void evict(int key) {
    Node node = hash.remove(key);
    if (node == null) throw new RuntimeException("tried to evict nonexistent node");
    unbind(node);
  }

  public LRUCache(int capacity) {
    if (capacity <= 0) throw new RuntimeException("wrong capacity");
    this.capacity = capacity;
  }

  public int get(int key) {
    Node node = hash.get(key);
    if (node == null) return -1;
    promote(node);
    return node.value;
  }

  public void put(int key, int value) {
    // clear space
    if (hash.containsKey(key)) {
      evict(key);
    } else {
      if (hash.size() == capacity) {
        evict(tail.key);
      }
    }

    // insert node
    Node node = new Node(key, value);
    hash.put(key, node);
    promote(node);
  }
}
/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj = new
 * LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
