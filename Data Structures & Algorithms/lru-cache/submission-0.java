

class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    int cap;

    HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        cap = capacity;

        head.next = tail;
        tail.prev = head;
    }

    // Remove a node from the linked list
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // Add node immediately after head
    private void addNode(Node node) {
        Node nextNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextNode;
        nextNode.prev = node;
    }

    public int get(int key) {

        // Key doesn't exist
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // This node was recently used,
        // so move it to the front
        removeNode(node);
        addNode(node);

        return node.val;
    }

    public void put(int key, int value) {

        // Key already exists
        if (map.containsKey(key)) {

            Node node = map.get(key);

            // Update value
            node.val = value;

            // Move to front because it is recently used
            removeNode(node);
            addNode(node);

            return;
        }

        // Create new node
        Node newNode = new Node(key, value);

        map.put(key, newNode);
        addNode(newNode);

        // Capacity exceeded
        if (map.size() > cap) {

            // Least recently used node
            Node lru = tail.prev;

            removeNode(lru);
            map.remove(lru.key);
        }
    }
}