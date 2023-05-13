public class MyHashTable<K, V> {

    // Define a private inner class for a hash node, which has a key-value pair and a reference to the next node
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // Override toString method to return the string representation of the key-value pair
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    // Define an array of hash nodes (the hash table) and its size
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    // Constructor with default size
    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    // Constructor with a custom size
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    // Hash function to calculate the index in the array for a given key
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // Put a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    // Get the value associated with a given key from the hash table
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    // Remove the key-value pair associated with a given key from the hash table
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> node = chainArray[index];
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    // Check if the hash table contains a given value
    public boolean contains(V value) {
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    // Get the key associated with a given value from the hash table
    public K getKey(V value) {
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    // Returns an array containing the number of elements in each bucket of the hash table
    public int[] getBucketSizes() {
        int[] sizes = new int[M];
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                sizes[i]++;
                node = node.next;
            }
        }
        return sizes;
    }
}