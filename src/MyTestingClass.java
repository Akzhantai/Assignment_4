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
