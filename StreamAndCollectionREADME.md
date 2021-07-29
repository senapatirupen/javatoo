##### Problem: What is a functional interface?
Solution: In Java, a functional interface is an interface that contains only one abstract method. In other 
words, a functional interface contains only one method that is not implemented. So, a functional interface 
wraps a function as an interface and the function is represented by a single abstract method on the interface.

Optionally, besides this abstract method, a functional interface can have default and/or static methods as 
well. Commonly, a functional interface is annotated with @FunctionalInterface. This is just an informative 
annotation type that's used to mark a functional interface.
Here is an example of a functional interface:
@FunctionalInterface
public interface Callable<V> {
  V call() throws Exception;
}
As a rule of thumb, if an interface has more methods without implementation (that is, abstract methods), 
then it is no longer a functional interface. This means that such an interface cannot be implemented by a 
Java lambda expression.

##### Problem: What are the main differences between collections and streams?
Solution: Collections and streams are quite different. Some of the differences are as follows:
•Conceptual differences: The main difference between collections and streams consists of the fact that they 
are conceptually two different things. While collections are meant to store data (for example, List, Set, 
and Map), streams are meant to apply operations (for example, filtering, mapping, and matching) on that data. 
In other words, streams apply complex operations on a view/source represented by data stored on a collection. 
Moreover, any modification/change performed on a stream is not reflected in the original collection.

•Data modification: While we can add/remove elements from a collection, we cannot add/remove elements from a 
stream. Practically, a stream consumes a view/source, performs operations on it, and returns a result without 
modifying the view/source. •Iteration: While a stream consumes a view/source, it automatically and internally 
performs the iteration of that view/source. The iteration takes place depending on the chosen operations 
that should be applied to the view/source. On the other hand, collections must be iterated externally.

•Traversal: While collections can be traversed multiple times, streams can be traversed only once. So, by 
default, Java streams cannot be reused. Attempting to traverse a stream twice will lead to an error reading 
Stream has already been operated on or closed.

•Construction: Collections are eagerly constructed (all the 
elements are present right from the beginning). On the other hand, streams are lazily constructed (the 
so-called intermediate operations are not evaluated until a terminal operation is invoked).

##### Problem: What does the map() function do and why would you use it?
The map() function is an intermediate operation named mapping and available via the Stream API. 
It is used to transform a type of object to other type by simply applying the given function. 
So, map() traverses the given stream and transforms each element in a new version of it by applying
the given function and accumulating the results in a new Stream. The given Stream is not modified. 
For example, transforming a List<String> into a List<Integer> via Stream#map() can be done as follows:

List<String> strList = Arrays.asList("1", "2", "3", "4");
strList.stream().map(Integer::parseInt).collect(Collectors.toList());

##### Problem: What does the flatMap() function do and why would you use it?
Solution: The flatMap() function is an intermediate operation named flattening and is available via the Stream API. 
This function is an extension of map(), meaning that apart from transforming the given object into another type of 
object, it can also flatten it. For example, having a List<List<Object>>, we can turn it into a List<Object> via 
Stream#flatMap() as follows:
List<List<Object>> list = ...
List<Object> flatList = list.stream()
  .flatMap(List::stream)
  .collect(Collectors.toList());
  
##### Problem: What's the difference between map() and flatMap() functions?
Solution: Both of these functions are intermediate operations capable of transforming a given type of object into 
another type of object by applying the given function. In addition, the flatMap() function is capable of flattening 
the given object as well. In other words, flatMap() can also flatten a Stream object. 

Why does this matter? Well, map() knows how to wrap a sequence of elements in a Stream, right? This means that map() 
can produce streams such as Stream<String[]>, Stream<List<String>>, Stream<Set<String>>, or even Stream<Stream<R>>. 
But the problem is that these kinds of streams cannot be manipulated successfully (that is, as we expected) by stream 
operations such as sum(), distinct(), and filter().
For example, let's consider the following List:
List<List<String>> melonLists = Arrays.asList(
  Arrays.asList("Gac", "Cantaloupe"),
  Arrays.asList("Hemi", "Gac", "Apollo"),
  Arrays.asList("Gac", "Hemi", "Cantaloupe"));
  
We try to obtain the distinct names of melons from this list. If wrapping an array into a stream can be done via 
Arrays.stream(), for a collection, we have Collection.stream(). Therefore, the first attempt may look as follows:
melonLists.stream()
  .map(Collection::stream) // Stream<Stream<String>>
  .distinct();
  
But this will not work because map() will return Stream<Stream<String>>. The solution is provided by flatMap(), as follows:
List<String> distinctNames = melonLists.stream()
  .flatMap(Collection::stream) // Stream<String>
  .distinct()
  .collect(Collectors.toList());
The output is as follows: Gac, Cantaloupe, Hemi, Apollo.

##### Problem: What does the filter() function do and why would you use it?
Solution: The filter() function is an intermediate operation named filtering available via the Stream API. It is used 
to filter the elements of a Stream that satisfy a certain condition. The condition is specified via the 
java.util.function.Predicate function. This predicate function is nothing but a function that takes as a parameter 
an Object and returns a boolean.
Let's assume that we have the following List of integers:
List<Integer> ints
  = Arrays.asList(1, 2, -4, 0, 2, 0, -1, 14, 0, -1);
Streaming this list and extracting only non-zero elements can be accomplished as follows:
List<Integer> result = ints.stream()
  .filter(i -> i != 0)
  .collect(Collectors.toList());
The resulting list will contain the following elements: 1, 2, -4, 2, -1, 14, -1.

Notice that, for several common operations, the Java Stream API already provides out-of-the-box intermediate 
operations. For example, there is no need to use filter() and define a Predicate for operations such as the 
following:
•distinct(): Removes duplicates from the stream
•skip(n): Discards the first n elements
•limit(s): Truncates the stream to be no longer than s in length
•sorted(): Sorts the stream according to the natural order
•sorted(Comparator<? super T> comparator): Sorts the stream according to the given Comparator
All these functions are built into the Stream API.

##### Problem: What is the main difference between intermediate and terminal operations?
Solution: Intermediate operations return another Stream, while the terminal operations produce a result other than 
Stream (for example, a collection or a scalar value). In other words, intermediate operations allow us to chain/call 
multiple operations in a type of query named a pipeline. 

Intermediate operations are not executed until a terminal operation is invoked. This means that intermediate 
operations are lazy. Mainly, they are executed at the moment when a result of some given processing is actually 
needed. A terminal operation triggers the traversal of the Stream and the pipeline is executed.

Among the intermediate operations, we have map(), flatMap(), filter(), limit(), and skip(). Among the terminal 
operations, we have sum(), min(), max(), count(), and collect().

##### Problem: What does the peek() function do and why would you use it?
Solution: The peek() function is an intermediate operation named peeking available via the Stream API. It allows us to 
see through a Stream pipeline. Mainly, peek()should execute a certain non-interfering action on the current element 
and forward the element to the next operation in the pipeline. Typically, this action consists of printing a 
meaningful message on the console. In other words, peek() is a good choice for debugging issues related to streams 
and lambda expression processing. For example, imagine that we had the following list of addresses:
addresses.stream()
  .peek(p -> System.out.println("\tstream(): " + p))
  .filter(s -> s.startsWith("c"))
  .sorted()
  .peek(p -> System.out.println("\tsorted(): " + p))
  .collect(Collectors.toList());
  
It is important to mention that, even if peek() can be used to mutate state (to modify the data source of the stream), 
it stands for look, but don't touch. Mutating state via peek() can become a real problem in case of parallel stream 
pipelines because the mutating action may be called at whatever time and in whatever thread the element is made 
available by the upstream operation. So, if the action modifies the shared state, it is responsible for providing the 
required synchronization. 

As a rule of thumb, think twice before using peek() to mutate the state. Also, be aware that this practice is a point 
of contention among developers and can be categorized as bad practice or even anti-pattern umbrellas

##### Problem: What are the main differences between Supplier and Consumer?
Solution: Supplier and Consumer are two built-in functional interfaces. Supplier acts as a factory method or as the 
new keyword. In other words, Supplier defines a method named get() that doesn't take arguments and returns an object 
of type T. So, a Supplier is useful to supply some value.

On the other hand, Consumer defines a method named void accept(T t). This method accepts a single argument and returns 
void. The Consumer interface consumes the given value and applies some operations to it. Unlike other functional 
interfaces, Consumer may cause side effects. For example, Consumer can be used as a setter method.

##### Problem: What is Predicate?
Solution: Predicate is a built-in functional interface that contains an abstract method whose signature is boolean 
test(T object):
@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
  // default and static methods omitted for brevity
}
The test() method tests a condition and returns true if that condition is met, otherwise it returns false. A common 
usage of a Predicate is in conjunction with the Stream<T> filter(Predicate<? super T> predicate) method for filtering 
unwanted elements of a stream.

##### Problem: What are the main differences between findFirst() and findAny()?
Solution: The findFirst() method returns the first element from the stream and is especially useful in obtaining the 
first element from a sequence. It returns the first element from the stream as long as the stream has a defined order. 
If there is no encounter order, then findFirst() returns any element from the stream. 

On the other hand, the findAny() method returns any element from the stream. In other words, it returns an arbitrary 
(non-deterministic) element from the stream.The findAny() method ignores the encountered order, and, in a non-parallel 
operation, it will most likely return the first element, but there is no guarantee of this. In order to maximize 
performance, the result cannot be reliably determined in parallel operations.

Notice that, depending on the stream's source and the intermediate operations, streams may or may not have a defined 
encounter order.

##### Problem: How would you convert an array to a stream?
Solution: Converting an array of objects into a stream can be done in at least three ways, as follows:
1.The first is via Arrays#stream():
public static <T> Stream<T> toStream(T[] arr) {
  return Arrays.stream(arr);
}
2.Second, we can use Stream#of():
public static <T> Stream<T> toStream(T[] arr) {        
  return Stream.of(arr);
}
3.The last technique is via List#stream():
public static <T> Stream<T> toStream(T[] arr) {        
  return Arrays.asList(arr).stream();
}
Converting an array of primitives (for example, integers) into a stream can be done in at least two ways, as 
follows:
1.Firstly, via Arrays#stream():
public static IntStream toStream(int[] arr) {       
  return Arrays.stream(arr);
}
2.Secondly, by using IntStream#of():
public static IntStream toStream(int[] arr) {
  return IntStream.of(arr);
}
Of course, for longs, you can use LongStream, and for doubles, you can use DoubleStream.

##### Problem: What is a parallel stream?
Solution: A parallel stream is a stream that can parallelize the execution using multiple threads. For example, you 
may need to filter a stream of 10 million integers to find the integers smaller than a certain value. Instead of 
using a single thread to traverse the stream sequentially, you can employ a parallel stream. This means that multiple 
threads will concurrently search for those integers in different parts of the stream and then combine the result.

##### Problem: What is a method reference?
Solution: In a nutshell, method references are shortcuts for lambda expressions. Mainly, the method reference is a 
technique that's used to call a method by name rather than by a description of how to call it. The main benefit is 
readability. A method reference is written by placing the target reference before the delimiter, ::, and the name of 
the method is provided after it. We have the following references:

•A method reference to a static method: Class::staticMethod (for example, Math::max is equivalent to Math.max(x, y))
•A method reference to a constructor: Class::new (for example, AtomicInteger::new is equivalent to new AtomicInteger(x))
•A method reference to an instance method from instance: object::instanceMethod (System.out::println equivalent to System.out.println(foo))
•A method reference to an instance method from class type: Class::instanceMethod (String::length equivalent to str.length())

##### Problem: What is a default method?
Solution: Default methods were added to Java 8 mainly to provide support for interfaces so that they can evolve 
beyond an abstract contract (that is, containing only abstract methods). This facility is very useful for people who 
write libraries and want to evolve APIs in a compatible way. Via default methods, an interface can be enriched without 
disrupting existing implementations.

A default method is implemented directly in the interface and is recognized by the default keyword. For example, the 
following interface defines an abstract method called area() and a default method called perimeter():
public interface Polygon {
  public double area();
  default double perimeter(double... segments) {
    return Arrays.stream(segments)
      .sum();
  }
}
Since Polygon has a single abstract method, it is a functional interface as well. So, it can be annotated with 
@FunctionalInterface.

##### Problem: What are the main differences between Iterator and Spliterator?
Solution: Iterator was created for the Collection API, while Spliterator was created for the Stream API. 
By analyzing their names, we notice that Spliterator = Splittable Iterator. Hence, a Spliterator can split a given 
source and it can iterate it, too. Splitting is needed for parallel processing. In other words, an Iterator can 
sequentially iterate the elements in Collection, while a Spliterator can iterate the elements of a stream in parallel 
or sequential order.

An Iterator can traverse the elements of a collection only via hasNext()/next() because it doesn't have a size. 
On the other hand, a Spliterator can provide the size of the collection either by approximating it via estimateSize() 
or exactly via getExactSizeIfKnown().

A Spliterator can use several flags for internally disabling unnecessary operations (for example, CONCURRENT, 
DISTINCT, and IMMUTABLE). An Iterator doesn't have such flags.
Finally, you can create a Spliterator around an Iterator as follows:
Spliterators.spliteratorUnknownSize(
  your_Iterator, your_Properties);
  
##### Problem: What is the Optional class?
Solution: Inspired by Haskell and Scala, the Optional class was introduced in Java 8 with the main purpose of 
mitigating/avoiding NullPointerException. The Java language architect Brian Goetz's definition is as follows:
Optional is intended to provide a limited mechanism for library method return types where there needed to be a 
clear way to represent no result, and using null for such was overwhelmingly likely to cause errors.

In a nutshell, you can think of Optional as a single value container that contains either a value or is empty. 
For example, an empty Optional looks like this:
Optional<User> userOptional = Optional.empty();
And a non-empty Optional looks like this:
User user = new User();
Optional<User> userOptional = Optional.of(user);

##### Problem: What does String::valueOf mean?
Solution: String::valueOf is a method reference to the valueOf static method of the String class.