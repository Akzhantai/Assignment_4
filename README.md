## Assignment_4

## MyHashTable Class:
``` java
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
```

## Explanation:
The hash table is implemented using an array of linked lists (also known as separate chaining), where each linked list represents a bucket in the hash table.

The MyHashTable class has a private inner class HashNode which represents a node in the linked list. Each node has a key-value pair and a reference to the next node in the list. The class overrides the toString method to return a string representation of the key-value pair.

The hash table itself is defined as an array of HashNode objects, with a default size of 11. The class has several methods to manipulate the hash table, including:
put: adds a key-value pair to the hash table

get: retrieves the value associated with a given key from the hash table

remove: removes the key-value pair associated with a given key from the hash table

contains: checks if the hash table contains a given value

getKey: retrieves the key associated with a given value from the hash table

getBucketSizes: returns an array containing the number of elements in each bucket of the hash table

The hash function used by the hash table is defined as a private method, hash, which takes a key as input and returns an index into the array. This implementation uses the standard hash code method for keys and then takes the modulus of the hash code with the table size to get the index.

## MyTestingClass Class:
``` java
// Import the Random class to generate random numbers
import java.util.Random;

// Define a class called MyTestingClass
public class MyTestingClass {

    // Instance variables
    private int id;
    private String name;

    // Constructor to initialize instance variables
    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Override the hashCode method to compute a hash value for this object
    @Override
    public int hashCode() {
        int result = 17; // Start with an arbitrary non-zero constant
        result = 31 * result + id; // Multiply by a prime number and add id
        result = 31 * result + (name == null ? 0 : name.hashCode()); // Multiply by a prime number and add name (or 0 if name is null)
        return result; // Return the hash value
    }

    // Main method to test the MyHashTable class
    public static void main(String[] args) {

        // Create a new instance of MyHashTable
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        // Create a Random object to generate random numbers
        Random random = new Random();

        // Insert 10,000 random keys and values into the hash table
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(1000), "name" + i); // Generate a new key with a random id and a name based on the loop index
            Student value = new Student("student" + i, i % 2 == 0 ? "male" : "female"); // Generate a new value with a name based on the loop index and a gender based on whether the index is even or odd
            table.put(key, value); // Insert the key-value pair into the hash table
        }

        // Get the size of each bucket in the hash table
        int[] bucketSizes = table.getBucketSizes();

        // Print out the size of each bucket
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }
    }
}
```

## Explanation:
MyTestingClass has two instance variables id and name. it is imports the Random library to generate random numbers. The MyTestingClass class has a constructor that takes two parameters to initialize the instance variables. The class also overrides the hashCode() method to compute a hash value for the object.

The main() method of the program creates a new instance of a MyHashTable class and a Random object. The program then inserts 10,000 random key-value pairs into the hash table using the put() method. Each key is a new instance of the MyTestingClass class with a random id and a name based on the loop index. Each value is a new instance of the Student class with a name based on the loop index and a gender based on whether the index is even or odd.

Finally, the program gets the size of each bucket in the hash table using the getBucketSizes() method and prints out the size of each bucket using a loop.

## Student Class:
``` java
public class Student {
    // Private instance variables
    private String name;
    private String gender;

    // Constructor
    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

}
```

## Explanation:
Student class with two private instance variables name and gender. 
It also contains a constructor that takes in two parameters name and gender to initialize the instance variables.

While writing this code, I hava such kind of error in main function: "Type parameter 'Student' cannot be instantiated directly java".
So, to fix this error, I define this class that can be used as the type parameter.









