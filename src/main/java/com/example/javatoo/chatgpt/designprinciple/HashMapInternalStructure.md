In Java, the `HashMap` class is an implementation of the `Map` interface and provides a way to store key-value pairs. Internally, `HashMap` uses an array of `Entry` objects to store the data.

The `HashMap` class uses a technique called "hashing" to efficiently store and retrieve elements. Here's a brief overview of the internal structure of `HashMap`:

1. Array of Buckets: `HashMap` internally maintains an array of "buckets" or "bins" to store the key-value pairs. The size of this array is determined by the initial capacity specified when creating the `HashMap`. Each bucket can hold multiple entries.

2. Hashing Function: When a key-value pair is inserted into the `HashMap`, the key's `hashCode()` method is called to calculate its hash code. The hash code is an integer that is used to determine the index of the bucket where the entry will be stored. The hashing function is responsible for distributing the entries evenly across the buckets to achieve efficient retrieval.

3. Collisions: Since multiple keys can have the same hash code or different hash codes can map to the same index, collisions can occur. To handle collisions, `HashMap` uses a technique called "chaining." Each bucket holds a linked list of entries. If multiple entries have the same index, they are stored as a linked list in the corresponding bucket.

4. Entry Objects: Each key-value pair in the `HashMap` is represented by an `Entry` object, which contains the key, value, and references to the next entry in the linked list (in case of collisions). The `Entry` objects are stored in the linked lists within the buckets.

5. Load Factor and Rehashing: `HashMap` also has a load factor, which determines when the internal array should be resized. When the number of entries exceeds the product of the load factor and the current capacity, the `HashMap` is resized (rehashed) to increase the capacity and redistribute the entries. This helps maintain efficient performance as the number of entries grows.

By using the hash code of the keys and the internal array of buckets, `HashMap` provides constant-time complexity (O(1)) for basic operations such as `get()`, `put()`, and `remove()` on average. However, the actual performance can degrade in the presence of collisions and a high number of elements in a single bucket.

It's important to note that the iteration order of entries in a `HashMap` is not guaranteed to be consistent, as it depends on the hash codes and the ordering of the entries within the buckets.

Overall, the internal structure of `HashMap` provides an efficient way to store and retrieve key-value pairs, making it a widely used data structure in Java.