Of course. Based on the provided text, here is a comprehensive extraction and explanation of all the design pattern examples from the book, including those that are implied or missing. I will provide a dry run, class diagram, and explanation for each.

---

## Part 1: Design Patterns and Java Platform Functionalities

### Chapter 1: Getting into Software Design Patterns

#### Examining OOP and APIE

This chapter introduces the foundational concepts of OOP and SOLID principles. The examples are pedagogical and focused on illustrating the concepts rather than being full implementations of a specific design pattern.

**Example 1.1: Encapsulation**

- **Description:** Demonstrates the principle of encapsulation by hiding the internal state (`moving`) of a `Vehicle` class and exposing it through public methods (`move`, `stop`).
- **Code:**
    ```java
    public class Vehicle {
        private boolean moving;

        public void move() {
            this.moving = true;
            System.out.println("moving...");
        }

        public void stop() {
            this.moving = false;
            System.out.println("stopped...");
        }
    }
    ```
- **Class Diagram:**
    ```mermaid
    classDiagram
      class Vehicle {
          -boolean moving
          +move()
          +stop()
      }
    ```
- **Explanation:** The `moving` field is private, so it cannot be directly accessed or modified by external code. The `move()` and `stop()` methods provide a controlled interface to change the vehicle's state, encapsulating the logic and preventing invalid states.
- **Dry Run:**
    1. A `Vehicle` object is instantiated. Its `moving` field is `false`.
    2. `vehicle.move()` is called. `this.moving` is set to `true`, and "moving..." is printed.
    3. `vehicle.stop()` is called. `this.moving` is set to `false`, and "stopped..." is printed.

**Example 1.2: Method Overriding (Polymorphism)**

- **Description:** Demonstrates dynamic polymorphism (method overriding) where a subclass (`Car`) provides a specific implementation of a method (`move()`) defined in its parent class (`Vehicle`).
- **Code:**
    ```java
    public class Vehicle {
        public void move() {
            System.out.println("moving...");
        }
    }

    public class Car extends Vehicle {
        @Override
        public void move() {
            System.out.println("moving faster.");
        }
    }

    // Usage
    Vehicle vehicle = new Car();
    vehicle.move(); // Output: moving faster.
    ```
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|-- Car
      class Vehicle {
          +move()
      }
      class Car {
          +move()
      }
    ```
- **Explanation:** Although the variable `vehicle` is of type `Vehicle`, the object it points to is a `Car`. At runtime, the JVM invokes the `move()` method of the actual object type (`Car`), not the reference type (`Vehicle`). This is a key feature of polymorphism.
- **Dry Run:**
    1. A `Car` object is instantiated and assigned to a `Vehicle` reference.
    2. `vehicle.move()` is called. The JVM determines that the object is a `Car`.
    3. The overridden `move()` method in `Car` is executed, printing "moving faster."

**Example 1.3: Abstraction with Abstract Class**

- **Description:** Shows how to use an abstract class (`AbstractVehicle`) to define a common template for subclasses (`CommonCar`, `SportCar`).
- **Code:**
    ```java
    public abstract class AbstractVehicle {
        public abstract void move();

        public void stop() {
            System.out.println("stopped...");
        }
    }

    public class CommonCar extends AbstractVehicle {
        @Override
        public void move() {
            System.out.println("move slow...");
        }
    }

    public class SportCar extends AbstractVehicle {
        @Override
        public void move() {
            System.out.println("move fast...");
        }
    }
    ```
- **Class Diagram:**
    ```mermaid
    classDiagram
      AbstractVehicle <|-- CommonCar
      AbstractVehicle <|-- SportCar
      class AbstractVehicle {
          <<abstract>>
          +move()*
          +stop()
      }
      class CommonCar {
          +move()
      }
      class SportCar {
          +move()
      }
    ```
- **Explanation:** `AbstractVehicle` defines an abstract `move()` method that *must* be implemented by any concrete subclass. It also provides a concrete `stop()` method that is common to all vehicles. This promotes code reuse and enforces a contract for all vehicle types.
- **Dry Run:**
    1. A `CommonCar` and a `SportCar` object are instantiated.
    2. Calling `commonCar.move()` prints "move slow...".
    3. Calling `sportCar.move()` prints "move fast...".
    4. Calling `stop()` on either object prints "stopped...".

**Example 1.4: Abstraction with Interface**

- **Description:** Demonstrates abstraction using a Java interface (`VehicleInterface`) to define a contract that multiple unrelated classes (`Truck`, `Bus`) can implement.
- **Code:**
    ```java
    public interface VehicleInterface {
        void move();
    }

    public class Truck implements VehicleInterface {
        @Override
        public void move() {
            System.out.println("truck moves...");
        }
    }

    public class Bus implements VehicleInterface {
        @Override
        public void move() {
            System.out.println("bus moves...");
        }
    }
    ```
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleInterface <|.. Truck
      VehicleInterface <|.. Bus
      class VehicleInterface {
          <<interface>>
          +move()
      }
      class Truck {
          +move()
      }
      class Bus {
          +move()
      }
    ```
- **Explanation:** An interface defines *what* to do, but not *how*. `Truck` and `Bus` are unrelated classes except that they both implement the same interface, allowing them to be treated polymorphically.
- **Dry Run:**
    1. `Truck` and `Bus` objects are instantiated.
    2. Calling `truck.move()` prints "truck moves...".
    3. Calling `bus.move()` prints "bus moves...".

**Example 1.5: Poor Abstraction (Violating OCP)**

- **Description:** Shows a problematic design where `Car` and `Truck` both implement the `Vehicle` interface, but due to a lack of a proper abstraction, the code using them is not polymorphic.
- **Code:**
    ```java
    public interface Vehicle {}

    public class Car implements Vehicle {
        public void move() {}
    }

    public class Truck implements Vehicle {
        public void move() {}
    }

    // Usage - This would cause an error because Vehicle has no 'move()' method.
    // List<Vehicle> vehicles = Arrays.asList(new Truck(), new Car());
    // vehicles.get(0).move(); // ERROR, NOT POSSIBLE!
    ```
- **Explanation:** The `Vehicle` interface has no methods. While `Car` and `Truck` have a `move()` method, it's not declared in the interface. Code that works with a `List<Vehicle>` cannot call `move()` because the interface doesn't guarantee its existence.
- **Dry Run:** The code wouldn't compile.
- **Remedy:** The `Vehicle` interface should have the `move()` method defined.

**Example 1.6: Good Abstraction**

- **Description:** The correct way to define the `Vehicle` interface with a `move()` method.
- **Code:**
    ```java
    public interface Vehicle {
        void move();
    }
    ```
- **Explanation:** This interface ensures all vehicles can `move()`, making the code polymorphic and flexible.

**Example 1.7: Liskov Substitution Principle (LSP)**

- **Description:** Demonstrates that a subclass (`SportCar`) can be used in place of its superclass (`Car` or `Vehicle`).
- **Code:**
    ```java
    public interface Vehicle {
        void move();
    }

    public class CarWash {
        public void wash(Vehicle vehicle) {}
    }

    public class Car implements Vehicle {
        public void move() {}
    }

    public class SportCar extends Car {}

    // Usage
    CarWash carWash = new CarWash();
    carWash.wash(new Car());      // Works
    carWash.wash(new SportCar()); // Works
    ```
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. Car
      Car <|-- SportCar
      class Vehicle {
          <<interface>>
          +move()
      }
      class Car {
          +move()
      }
      class SportCar
      class CarWash {
          +wash(Vehicle)
      }
    ```
- **Explanation:** The `wash()` method expects a `Vehicle`. Passing a `SportCar` (a subtype of `Vehicle`) is allowed, as a `SportCar` *is-a* `Vehicle`. This demonstrates the Liskov Substitution Principle.

**Example 1.8: Interface Segregation Principle (ISP) - Violation**

- **Description:** A bad design where a single interface `Vehicle` forces classes to implement methods they don't support.
- **Code:**
    ```java
    public interface Vehicle {
        void setMove(boolean moving);
        boolean engineOn();
        boolean pedalsMove();
    }

    public class Bike implements Vehicle {
        public boolean engineOn() {
            throw new IllegalStateException("not supported");
        }
        // ...
    }

    public class Car implements Vehicle {
        public boolean pedalsMove() {
            throw new IllegalStateException("not supported");
        }
        // ...
    }
    ```
- **Explanation:** `Bike` doesn't have an engine, so `engineOn()` is invalid. `Car` doesn't have pedals, so `pedalsMove()` is invalid. This violates ISP because clients (like the `printIsMoving` method) are forced to depend on methods they don't use, leading to brittle code.

**Example 1.9: Interface Segregation Principle (ISP) - Adherence**

- **Description:** A better design using smaller, focused interfaces.
- **Code:**
    ```java
    public interface Vehicle {
        void setMove(boolean moving);
    }
    public interface HasEngine {
        boolean engineOn();
    }
    public interface HasPedals {
        boolean pedalsMove();
    }

    public class Bike implements HasPedals, Vehicle { /* ... */ }
    public class Car implements HasEngine, Vehicle { /* ... */ }
    ```
- **Explanation:** Interfaces are segregated by responsibility. `Bike` only implements the interfaces it needs (`Vehicle`, `HasPedals`), and `Car` does the same (`Vehicle`, `HasEngine`). This avoids forcing classes to implement methods they don't support.

**Example 1.10: Dependency Inversion Principle (DIP)**

- **Description:** Shows that high-level modules should not depend on low-level modules, but both should depend on abstractions.
- **Code:**
    ```java
    public interface Vehicle { /* ... */ }
    public class Car implements Vehicle { /* ... */ }
    public class SportCar extends Car { /* ... */ }
    public class Truck implements Vehicle { /* ... */ }
    public class Bus implements Vehicle { /* ... */ }

    public class Garage {
        private List<Vehicle> parkingSpots = new ArrayList<>();

        public void park(Vehicle vehicle) {
            parkingSpots.add(vehicle);
        }
    }
    ```
- **Explanation:** The `Garage` class depends on the `Vehicle` abstraction, not on concrete classes like `Car` or `Truck`. This makes the `Garage` class more flexible and reusable, as it can work with any type of `Vehicle`.

---

### Chapter 2: Discovering the Java Platform for Design Patterns

This chapter introduces the Java platform and its features. Some "examples" are more like illustrative code snippets than full patterns.

**Example 2.1: Simple Java Program**

- **Description:** A minimal "Hello, World!" program in Java.
- **Code:**
    ```java
    public class Program {
        public static void main(String... args) {
            System.out.println("Hello Program!");
        }
    }
    ```
- **Explanation:** This is the entry point of a Java application. The `main` method is executed when the program starts.

**Example 2.2: Java Bytecode**

- **Description:** Shows what the bytecode generated from Example 2.1 looks like.
- **Code:**
    ```java
    // Bytecode example
    public static void main(java.lang.String...);
        descriptor: ([Ljava/lang/String;)V
        flags: (0x0089) ACC_PUBLIC, ACC_STATIC, ACC_VARARGS
        Code:
          stack=2, locals=1, args_size=1
             0: getstatic     #7 // Field java/lang/System.out:Ljava/io/PrintStream;
             3: ldc           #13 // String Hello Program!
    ```
- **Explanation:** This is the machine-readable code generated by the Java compiler. It is executed by the JVM.

**Example 2.3: Autoboxing**

- **Description:** Illustrates the automatic conversion of a primitive type to its wrapper class.
- **Code:**
    ```java
    int valueIntLiteral = 42;
    Integer valueIntWrapper = valueIntLiteral; // Autoboxing
    ```
- **Explanation:** The primitive `int` is automatically converted to an `Integer` object. This convenience can lead to performance issues if done excessively, as it creates new objects on the heap.

**Example 2.4: Primitive Casting**

- **Description:** Shows automatic type conversion between primitive types.
- **Code:**
    ```java
    byte byteNumber = 1;
    short shortNumber = byteNumber; // Automatic widening
    int intNumber = shortNumber;    // Automatic widening
    ```
- **Explanation:** A `byte` can be automatically assigned to a `short` (and then to an `int`) because these are widening conversions and don't lose information.

**Example 2.5: String Comparison and `intern()`**

- **Description:** Demonstrates the behavior of the String Pool and the `intern()` method.
- **Code:**
    ```java
    String t1 = "text1";
    String t2 = "text1";
    String t3 = new String("text1");
    String t4 = t3.intern();

    // Output:
    // t1 == t2 => true
    // t1 == t3 => false
    // t3 == t4 => false
    // t1 == t4 => true
    ```
- **Explanation:**
    - `t1` and `t2` refer to the same object in the String Pool.
    - `t3` creates a new `String` object on the heap.
    - `t4` is the interned version of `t3` from the String Pool, so it's the same object as `t1`.

**Example 2.6: `StringBuilder`**

- **Description:** Shows how `StringBuilder` can be used to efficiently concatenate strings.
- **Code:**
    ```java
    String t5 = new StringBuilder()
                    .append("value")
                    .append(42)
                    .toString();
    String t6 = "value42";

    // Output:
    // t5 == t6 => false
    ```
- **Explanation:** `StringBuilder` creates a new `String` object on the heap. `t6` is a string from the pool. They are different objects.

**Example 2.8: Array Declarations**

- **Description:** Demonstrates various ways to declare arrays.
- **Code:**
    ```java
    int[] array1;
    byte[][] array2;
    Object[] array3;
    Collection<?>[] array4;
    ```
- **Explanation:** Arrays can be of any type, including primitive types, objects, and even generic collections (though generic array creation can be tricky).

**Example 2.9: Array Creation and Usage**

- **Description:** Shows how to create and access array elements.
- **Code:**
    ```java
    int[] a1 = {1, 2, 3, 4};
    a1[0] = a1.length;
    int e1 = a1[0];
    // a1.length == 4 => TRUE
    // a1 instanceof Object => TRUE
    ```
- **Explanation:** The array is initialized and its elements are accessed using an index.

**Example 2.10: Math Functions**

- **Description:** Using common methods from the `Math` class.
- **Code:**
    ```java
    Double sin = Math.sin(90);
    double abs = Math.abs(-10);
    double sqrt = Math.sqrt(2);
    ```
- **Explanation:** The `Math` class provides static methods for basic mathematical operations.

**Example 2.11: Random Number Generation**

- **Description:** Using `Random` to generate random numbers within a specific range.
- **Code:**
    ```java
    Random randomNumberWithRange = new Random();
    int upperBound = 10;
    int randomIntInRange = randomNumberWithRange.nextInt(upperBound);
    double randomDoubleInRange = randomNumberWithRange.nextDouble(upperBound);
    ```
- **Explanation:** The `Random` class provides methods for generating random numbers of various types.

**Example 2.12: `forEach` with Method Reference**

- **Description:** Using a lambda expression and method reference to print elements of a list.
- **Code:**
    ```java
    List<String> list = Arrays.asList("one", "two", "forty_two");
    list.forEach(System.out::println);
    ```
- **Explanation:** The `forEach` method (a default method in `Iterable`) takes a `Consumer`. Here, we pass a method reference `System.out::println` which prints each element.

**Example 2.13: Stream API Operations**

- **Description:** Demonstrates a complex Stream pipeline with filtering, mapping, and collecting.
- **Code:**
    ```java
    Predicate<Integer> numberOfTest = new Predicate<Integer>() {
        @Override
        public boolean test(Integer e) {
            return e > 2;
        }
    };

    String result = Stream.of(1, 2, 3, 42)
        .filter(numberOfTest)
        .map(e -> "element" + e)
        .collect(Collectors.joining(","));

    System.out.println("result:" + result);
    ```
- **Explanation:**
    1. `Stream.of(1, 2, 3, 42)` creates a stream of integers.
    2. `.filter(numberOfTest)` keeps only elements greater than 2 (3 and 42).
    3. `.map(e -> "element" + e)` converts each integer to a string (e.g., "element3", "element42").
    4. `.collect(Collectors.joining(","))` joins the strings with commas.
    5. The final result is `"result:element3,element42"`.

**Example 2.14: JPMS `--list-modules`**

- **Description:** Command to list JDK modules.
- **Code:**
    ```bash
    java --list-modules
    ```
- **Explanation:** Lists all the modules available in the JRE.

**Example 2.15: JPMS `--describe-module`**

- **Description:** Describes a specific module.
- **Code:**
    ```bash
    java --describe-module java.logging
    ```
- **Explanation:** Shows details about the module, such as its exports, requires, and provides.

**Example 2.16: Module Descriptor (`module-info.java`)**

- **Description:** The contents of a `module-info.java` file for the `java.logging` module.
- **Code:**
    ```java
    module java.logging {
        exports java.util.logging;
        provides jdk.internal.logger.DefaultLoggerFinder with
            sun.util.logging.internal.LoggingProviderImpl;
    }
    ```
- **Explanation:**
    - `exports java.util.logging;` makes the package `java.util.logging` available to other modules.
    - `provides ...` declares that the module provides a service implementation.

**Example 2.17: Folder Structure for a Custom Module**

- **Description:** The directory structure for a simple modular Java project.
- **Code:**
    ```
    module-example
    ├── example
    │   └── ExampleMain.java
    └── module-info.java
    ```
- **Explanation:** The `module-info.java` file is placed at the root of the module's source directory.

**Example 2.18: Simple Custom Module**

- **Description:** Code for a simple module and its descriptor.
- **Code:**
    ```java
    // module-info.java
    module module.example {
        exports example;
    }

    // ExampleMain.java
    package example;
    public class ExampleMain {
        public static void main(String[] args) {
            System.out.println("Welcome to JPMS!");
        }
    }
    ```
- **Explanation:** The module `module.example` exports the `example` package. The `ExampleMain` class is the entry point.

**Example 2.19: Compiling and Running a Module**

- **Description:** Commands to compile and run the custom module.
- **Code:**
    ```bash
    javac -d ./out ./module-example/module-info.java ./module-example/example/ExampleMain.java
    jar --create --file module-example.jar -C ./out .
    java --module-path ./module-example.jar --module module.example/example.ExampleMain
    ```
- **Explanation:** The `javac` command compiles the Java files. The `jar` command packages it into a JAR. The `java` command runs the main class from the module.

**Example 2.20: `var` Keyword with Lambda**

- **Description:** Using the `var` keyword for type inference in a lambda parameter.
- **Code:**
    ```java
    Consumer<Integer> consumer = (var number) -> {
        var result = number + 1;
        System.out.println("result:" + result);
    };
    IntStream.of(1, 2, 3).boxed().forEach(consumer);
    ```
- **Explanation:** `var` can be used to infer the type of lambda parameters (e.g., `var number` is inferred as `Integer`). The `boxed()` method converts the `IntStream` of primitives to a `Stream<Integer>`.

**Example 2.21: Switch Expression**

- **Description:** Using the new, more concise switch expression syntax.
- **Code:**
    ```java
    var inputNumber = 42;
    String textNumber = switch (inputNumber) {
        case 22, 42 -> String.valueOf(inputNumber);
        default -> throw new RuntimeException("not allowed");
    };
    System.out.printf(" number:'%s' %n", textNumber);
    ```
- **Explanation:** The `switch` is used as an expression that returns a value. The `->` syntax is more concise and avoids fall-through problems. The `default` branch is required to handle all possible cases.

**Example 2.22: Pattern Matching with `instanceof`**

- **Description:** Using pattern matching to bind the result of an `instanceof` check to a variable.
- **Code:**
    ```java
    Object obj = "text";
    if (obj instanceof String s) {
        System.out.println(s.toUpperCase());
    }
    ```
- **Explanation:** The variable `s` is a `String` type, and the `instanceof` check is combined with its declaration. This eliminates the need for a separate cast and makes the code safer.

**Example 2.23: Record Class**

- **Description:** Defining a simple record.
- **Code:**
    ```java
    private record Example(int number, String text) {
        private String getTogether() {
            return number + text;
        }
    }
    ```
- **Explanation:** Records provide a transparent way to create immutable data carriers. They automatically generate constructors, `equals`, `hashCode`, and `toString` methods.

**Example 2.24: Sealed Class**

- **Description:** Defining a sealed interface and a permitted subclass.
- **Code:**
    ```java
    public sealed interface Vehicle permits Car, Bus {
        void start();
        void stop();
    }

    public non-sealed class Car extends NormalEngine implements Vehicle {
        // ...
    }
    ```
- **Explanation:** The `sealed` interface permits only specific classes (`Car`, `Bus`) to implement it. The `non-sealed` keyword allows `Car` to be extended further.

**Example 2.25: Sealed Class Restriction**

- **Description:** Compilation error when an unpermitted class tries to implement a sealed interface.
- **Code:**
    ```java
    public class Motorbike implements Vehicle { // Error!
        // ...
    }
    ```
- **Explanation:** This code results in a compilation error because `Motorbike` is not listed in the `permits` clause of the `Vehicle` interface.

**Example 2.26: Final Sealed Class**

- **Description:** A `Bus` class that is `final`, meaning it cannot be extended.
- **Code:**
    ```java
    public final class Bus extends SlowEngine implements Vehicle {
        // ...
    }
    ```
- **Explanation:** This provides a locked, unchangeable implementation of `Vehicle`.

**Example 2.27: Pattern Matching for Switch**

- **Description:** Using pattern matching in a `switch` statement.
- **Code:**
    ```java
    Object variable = 42;
    String text = switch (variable) {
        case Integer i -> "number" + i;
        default -> "text";
    };
    ```
- **Explanation:** The `switch` expression can match the type of the object. In this case, if `variable` is an `Integer`, it's extracted and used to form the result string.

**Example 2.28: Daemon Thread**

- **Description:** Creating a simple daemon thread.
- **Code:**
    ```java
    public class MultithreadedProgram {
        public static void main(String[] args) {
            var t = new Thread(() -> {
                while (true) {
                    System.out.println("Welcome Thread!");
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }
    ```
- **Explanation:** This thread runs indefinitely but is marked as a daemon. The JVM will exit when only daemon threads are running.

**Example 2.29: Executing a `Runnable`**

- **Description:** Submitting a `Runnable` to an `ExecutorService`.
- **Code:**
    ```java
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    var runnable = new Runnable(){
        @Override
        public void run() {
            System.out.println("Welcome Runnable");
        }
    };
    executorService.execute(runnable);
    executorService.execute(() -> System.out.println("Welcome Runnable"));
    ```
- **Explanation:** An `ExecutorService` manages the thread lifecycle. A `Runnable` (as an anonymous class or lambda) is submitted and executed.

**Example 2.30: Executing a `Callable`**

- **Description:** Submitting a `Callable` to an `ExecutorService` and retrieving its result with `Future`.
- **Code:**
    ```java
    var futureCallable = executorService.submit(callable);
    Future<String> futureCallableAnonymous = executor.submit(
        () -> "Welcome to Future"
    );

    System.out.printf("""
        futureCallable: '%s', futureCallableAnonymous: '%s'
        """.formatted(futureCallable.get(), futureCallableAnonymous.get())
    );
    ```
- **Explanation:** A `Callable` is similar to `Runnable` but can return a value. The `submit` method returns a `Future` object, which can be used to check if the task is complete and to retrieve its result.

---

## Part 2: Implementing Standard Design Patterns Using Java Programming

### Chapter 3: Working with Creational Design Patterns

#### 3.1 Factory Method Pattern

- **Motivation:** Centralizes object creation and allows subclasses to decide which class to instantiate.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. SportCar
      Vehicle <|.. SuvCar
      VehicleFactory ..> Vehicle : creates
      class Vehicle {
          <<interface>>
          +move()
      }
      class SportCar {
          +move()
      }
      class SuvCar {
          +move()
      }
      class VehicleFactory {
          -VehicleFactory()
          +produce(String) Vehicle$
      }
    ```
- **Example Code:**
    ```java
    // 1. Define the Product Interface
    interface Vehicle {
        void move();
    }

    // 2. Concrete Products
    record SportCar(String type) implements Vehicle {
        @Override
        public void move() {
            System.out.println("SportCar, type: '" + type + "', move");
        }
    }

    class SuvCar implements Vehicle {
        private final String type;

        public SuvCar(String t) {
            this.type = t;
        }

        @Override
        public void move() {
            System.out.println("SuvCar, type: '" + type + "', move");
        }
    }

    // 3. Factory Class
    final class VehicleFactory {
        private VehicleFactory() {}

        static Vehicle produce(String type) {
            return switch (type) {
                case "sport" -> new SportCar("porsche 911");
                case "suv" -> new SuvCar("skoda kodiaq");
                default -> throw new IllegalArgumentException("not implemented type");
            };
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Factory Method: Vehicle Factory");
            var sportCar = VehicleFactory.produce("sport");
            System.out.println("sport-car:" + sportCar);
            sportCar.move();
        }
    }
    ```
- **Explanation:** The `VehicleFactory` encapsulates the logic for creating different `Vehicle` types. The client requests a vehicle by providing a type, and the factory returns the appropriate object. This decouples the client from the concrete classes.
- **Dry Run:**
    1. `VehicleFactory.produce("sport")` is called.
    2. The `switch` expression matches `"sport"` and creates a new `SportCar` object.
    3. The `SportCar` is printed, and its `move()` method is called, outputting "SportCar, type: 'porsche 911', move".

#### 3.2 Abstract Factory Pattern

- **Motivation:** Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
- **Class Diagram:**
    ```mermaid
    classDiagram
      AbstractFactory <|-- CarFactory
      AbstractFactory <|-- TruckFactory
      FactoryProvider ..> AbstractFactory : creates
      Vehicle <|.. SlowCar
      Vehicle <|.. FastCar
      Vehicle <|.. HeavyTruck
      Vehicle <|.. LightTruck
      AbstractFactory ..> Vehicle : creates
      class AbstractFactory {
          <<abstract>>
          +createVehicle(String) Vehicle
      }
      class CarFactory {
          +createVehicle(String) Vehicle
      }
      class TruckFactory {
          +createVehicle(String) Vehicle
      }
      class FactoryProvider {
          -FactoryProvider()
          +getFactory(String) AbstractFactory$
      }
      class Vehicle {
          <<interface>>
          +move()
      }
      class SlowCar { +move() }
      class FastCar { +move() }
      class HeavyTruck { +move() }
      class LightTruck { +move() }
    ```
- **Example Code:**
    ```java
    // 1. Abstract Product Interface
    interface Vehicle {
        void move();
    }

    // 2. Concrete Products
    class SlowCar implements Vehicle { /* ... */ }
    class FastCar implements Vehicle { /* ... */ }
    class HeavyTruck implements Vehicle { /* ... */ }
    class LightTruck implements Vehicle { /* ... */ }

    // 3. Abstract Factory
    abstract class AbstractFactory {
        abstract Vehicle createVehicle(String type);
    }

    // 4. Concrete Factories
    class CarFactory extends AbstractFactory {
        @Override
        Vehicle createVehicle(String type) {
            return switch (type) {
                case "slow" -> new SlowCar();
                case "fast" -> new FastCar();
                default -> throw new IllegalArgumentException("not implemented");
            };
        }
    }

    class TruckFactory extends AbstractFactory {
        @Override
        Vehicle createVehicle(String type) {
            return switch (type) {
                case "heavy" -> new HeavyTruck();
                case "light" -> new LightTruck();
                default -> throw new IllegalArgumentException("not implemented");
            };
        }
    }

    // 5. Factory Provider
    final class FactoryProvider {
        private FactoryProvider() {}

        static AbstractFactory getFactory(String type) {
            return switch (type) {
                case "car" -> new CarFactory();
                case "truck" -> new TruckFactory();
                default -> throw new IllegalArgumentException("not implemented");
            };
        }
    }

    // 6. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Abstract Factory: create factory to produce vehicle...");
            AbstractFactory carFactory = FactoryProvider.getFactory("car");
            Vehicle slowCar = carFactory.createVehicle("slow");
            slowCar.move();
        }
    }
    ```
- **Explanation:** The `FactoryProvider` returns a specific `AbstractFactory` (`CarFactory` or `TruckFactory`) based on the input. The client then uses the factory to create a product. This ensures that the products from the same family are compatible.
- **Dry Run:**
    1. `FactoryProvider.getFactory("car")` is called, returning a `CarFactory`.
    2. `carFactory.createVehicle("slow")` is called.
    3. The `CarFactory` creates and returns a `SlowCar`.
    4. `slowCar.move()` outputs "slow car, move".

#### 3.3 Builder Pattern

- **Motivation:** Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. SlowCar
      Vehicle <|.. FastCar
      VehicleBuilder ..> Vehicle : creates
      FastCar ..> FastCar.Builder : creates
      class Vehicle {
          <<interface>>
          +move()
          +parts()
      }
      class SlowCar { +move() +parts() }
      class FastCar {
          -Part engine
          -Part cabin
          +move()
          +parts()
      }
      class FastCar.Builder {
          -Part engine
          -Part cabin
          +addEngine(String) Builder
          +addCabin(String) Builder
          +build() FastCar
      }
      class VehicleBuilder {
          -VehicleBuilder()
          +buildSlowCar() Vehicle$
      }
      class Part {
          <<interface>>
      }
      class RecordPart {
          +name
      }
      class StandardPart {
          +name
      }
    ```
- **Example Code:**
    ```java
    // 1. Product Interfaces and Classes
    interface Vehicle {
        void move();
        void parts();
    }

    // 2. Builder Implementation (Inline)
    class FastCar implements Vehicle {
        private final Part engine;
        private final Part cabin;

        private FastCar(Part engine, Part cabin) {
            this.engine = engine;
            this.cabin = cabin;
        }

        public static class Builder {
            private Part engine;
            private Part cabin;

            public Builder addEngine(String e) {
                this.engine = new StandardPart(e);
                return this;
            }

            public Builder addCabin(String c) {
                this.cabin = new RecordPart(c);
                return this;
            }

            public FastCar build() {
                return new FastCar(engine, cabin);
            }
        }

        @Override
        public void move() { /* ... */ }

        @Override
        public void parts() {
            System.out.println("FastVehicle,engine: " + engine);
            System.out.println("FastVehicle,cabin: " + cabin);
        }
    }

    class SlowCar implements Vehicle { /* ... */ }

    // 3. Separate Builder Class
    final class VehicleBuilder {
        private VehicleBuilder() {}

        static Vehicle buildSlowCar() {
            var engine = new RecordPart("engine");
            var cabin = new StandardPart("cabin");
            return new SlowCar(engine, cabin);
        }
    }

    // 4. Parts
    interface Part { /* ... */ }
    record RecordPart(String name) implements Part { /* ... */ }
    class StandardPart implements Part { /* ... */ }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Builder pattern: building vehicles");
            var slowVehicle = VehicleBuilder.buildSlowCar();
            var fastVehicle = new FastCar.Builder()
                    .addCabin("cabin")
                    .addEngine("Engine")
                    .build();
            slowVehicle.parts();
            fastVehicle.parts();
        }
    }
    ```
- **Explanation:** The `Builder` pattern allows step-by-step construction of a complex object. The `FastCar.Builder` provides a fluent interface for setting the parts, and the `VehicleBuilder` provides a simpler way to create a pre-configured `SlowCar`.
- **Dry Run:**
    1. `VehicleBuilder.buildSlowCar()` is called. It creates `RecordPart` and `StandardPart` objects and constructs a `SlowCar`.
    2. `slowVehicle.parts()` prints the parts of the `SlowCar`.
    3. A `FastCar.Builder` is created. `addCabin("cabin")` and `addEngine("Engine")` are called in a fluent manner.
    4. `build()` is called, creating a `FastCar` instance.
    5. `fastVehicle.parts()` prints the parts of the `FastCar`.

#### 3.4 Prototype Pattern

- **Motivation:** Creates new objects by copying an existing object (prototype) rather than instantiating a new class. Useful when object creation is expensive.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|-- SlowCar
      Vehicle <|-- FastCar
      VehicleCache ..> Vehicle : clones
      class Vehicle {
          <<abstract>>
          #String type
          +clone() Object
          +move()*
      }
      class SlowCar {
          +move()
      }
      class FastCar {
          +move()
      }
      class VehicleCache {
          -Map~String,Vehicle~ map
          -VehicleCache()
          +getVehicle(String) Vehicle$
      }
    ```
- **Example Code:**
    ```java
    // 1. Prototype Interface
    abstract class Vehicle implements Cloneable {
        protected final String type;

        Vehicle(String type) {
            this.type = type;
        }

        abstract void move();

        @Override
        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 2. Concrete Prototypes
    class SlowCar extends Vehicle {
        SlowCar() {
            super("slow car");
        }

        @Override
        void move() {
            System.out.println("slow car, move");
        }
    }

    class FastCar extends Vehicle {
        FastCar() {
            super("fast car");
        }

        @Override
        void move() {
            System.out.println("fast car, move");
        }
    }

    // 3. Cache
    final class VehicleCache {
        private static final Map<String, Vehicle> map = Map.of(
            "fast-car", new FastCar(),
            "slow-car", new SlowCar()
        );

        private VehicleCache() {}

        static Vehicle getVehicle(String type) {
            Vehicle vehicle = map.get(type);
            if (vehicle == null) {
                throw new IllegalArgumentException("not allowed:" + type);
            }
            return (Vehicle) vehicle.clone();
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Prototype: vehicle prototype");
            Vehicle fastCar1 = VehicleCache.getVehicle("fast-car");
            Vehicle fastCar2 = VehicleCache.getVehicle("fast-car");
            fastCar1.move();
            fastCar2.move();
            System.out.println("equals :" + (fastCar1.equals(fastCar2)));
            System.out.println("fastCar1:" + fastCar1);
            System.out.println("fastCar2:" + fastCar2);
        }
    }
    ```
- **Explanation:** The `VehicleCache` stores prototypes. When a client requests a vehicle, the cache clones the prototype and returns a new instance.
- **Dry Run:**
    1. `VehicleCache.getVehicle("fast-car")` is called twice.
    2. Each call retrieves the `FastCar` prototype from the map and clones it.
    3. Two distinct `FastCar` objects are created.
    4. `fastCar1.move()` and `fastCar2.move()` output "fast car, move".
    5. `equals` returns `false` because they are different objects.

#### 3.5 Singleton Pattern

- **Motivation:** Ensures that a class has only one instance and provides a global point of access to it.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Engine <|.. OnlyEngine
      class OnlyEngine {
          -OnlyEngine INSTANCE$
          -OnlyEngine()
          +getInstance() OnlyEngine$
      }
      class OnlyVehicle {
          -OnlyVehicle INSTANCE$
          -Engine engine
          -OnlyVehicle()
          +getInstance() OnlyVehicle$
          +move()
          +getEngine() Engine
      }
      class Engine {
          <<interface>>
      }
    ```
- **Example Code:**
    ```java
    // 1. Singleton (Lazy)
    interface Engine { /* ... */ }

    class OnlyEngine implements Engine {
        private static OnlyEngine INSTANCE;

        private OnlyEngine() {}

        static OnlyEngine getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new OnlyEngine();
            }
            return INSTANCE;
        }
    }

    // 2. Singleton (Eager)
    class OnlyVehicle {
        private static OnlyVehicle INSTANCE = new OnlyVehicle();
        private final Engine engine;

        private OnlyVehicle() {
            this.engine = OnlyEngine.getInstance();
        }

        static OnlyVehicle getInstance() {
            return INSTANCE;
        }

        void move() {
            System.out.println("OnlyVehicle, move");
        }

        Engine getEngine() {
            return engine;
        }
    }

    // 3. Singleton (Enum)
    enum OnlyEngineEnum implements Engine {
        INSTANCE;
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Singleton pattern: only one engine");
            var engine = OnlyEngine.getInstance();
            var vehicle = OnlyVehicle.getInstance();
            vehicle.move();
            System.out.println("OnlyEngine:'" + engine + "', equals with vehicle:'" + (vehicle.getEngine().equals(engine)) + "'");
        }
    }
    ```
- **Explanation:** The `OnlyEngine` class uses lazy initialization to create the instance only when it's first requested. `OnlyVehicle` uses eager initialization. The `Enum` approach is the safest and most concise way to implement a singleton in Java.
- **Dry Run:**
    1. `OnlyEngine.getInstance()` is called. The `INSTANCE` is `null`, so a new `OnlyEngine` is created and returned.
    2. `OnlyVehicle.getInstance()` returns the eagerly created `OnlyVehicle`.
    3. `vehicle.move()` outputs "OnlyVehicle, move".
    4. The output shows that the `Engine` inside the vehicle is the same as the standalone `OnlyEngine`.

#### 3.6 Object Pool Pattern

- **Motivation:** Manages a set of initialized objects to reuse them, rather than creating and destroying them repeatedly.
- **Class Diagram:**
    ```mermaid
    classDiagram
      AbstractGaragePool <|-- PooledVehicleGarage
      Vehicle <|.. PooledVehicle
      AbstractGaragePool ..> Vehicle
      class AbstractGaragePool~T~ {
          <<abstract>>
          -Set~T~ available
          -Set~T~ inUse
          +driveVehicle() T
          +returnVehicle(T)
          +printStatus()
          +buyVehicle()* T
      }
      class PooledVehicleGarage {
          +buyVehicle() Vehicle
      }
      class Vehicle {
          <<interface>>
          +getVin() int
          +move()
      }
      class PooledVehicle {
          -int vin
          +PooledVehicle()
          +getVin() int
          +move()
      }
    ```
- **Example Code:**
    ```java
    // 1. Poolable Object
    interface Vehicle {
        int getVin();
        void move();
    }

    class PooledVehicle implements Vehicle {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final int vin;

        PooledVehicle() {
            this.vin = COUNTER.incrementAndGet();
        }

        @Override
        public int getVin() {
            return vin;
        }

        @Override
        public void move() {
            System.out.println("PooledVehicle, move, vin = " + vin);
        }
    }

    // 2. Abstract Pool
    abstract class AbstractGaragePool<T extends Vehicle> {
        private final Set<T> available = new HashSet<>();
        private final Set<T> inUse = new HashSet<>();

        protected abstract T buyVehicle();

        synchronized T driveVehicle() {
            if (available.isEmpty()) {
                available.add(buyVehicle());
            }
            var instance = available.iterator().next();
            available.remove(instance);
            inUse.add(instance);
            return instance;
        }

        synchronized void returnVehicle(T instance) {
            inUse.remove(instance);
            available.add(instance);
        }

        void printStatus() {
            System.out.println("Garage Pool vehicles available = " + available.size() + " " + available);
            System.out.println("inUse = " + inUse.size() + " " + inUse);
        }
    }

    // 3. Concrete Pool
    class PooledVehicleGarage extends AbstractGaragePool<PooledVehicle> {
        @Override
        protected PooledVehicle buyVehicle() {
            return new PooledVehicle();
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Object Pool: vehicle garage");
            var garage = new PooledVehicleGarage();
            var vehicle1 = garage.driveVehicle();
            var vehicle2 = garage.driveVehicle();
            var vehicle3 = garage.driveVehicle();
            vehicle1.move();
            vehicle2.move();
            vehicle3.move();
            garage.returnVehicle(vehicle1);
            garage.returnVehicle(vehicle3);
            garage.printStatus();
            var vehicle4 = garage.driveVehicle();
            var vehicle5 = garage.driveVehicle();
            vehicle4.move();
            vehicle5.move();
            garage.printStatus();
        }
    }
    ```
- **Explanation:** The `AbstractGaragePool` manages a pool of reusable `Vehicle` objects. When a client needs a vehicle, it's taken from the `available` set. When the client is done, the vehicle is returned to the pool.
- **Dry Run:**
    1. Three vehicles are requested. The pool is empty, so three new `PooledVehicle` objects are created (`vin` 1, 2, 3).
    2. `vehicle1`, `vehicle2`, and `vehicle3` are used.
    3. `vehicle1` and `vehicle3` are returned to the pool. `available` now contains `[1, 3]`, `inUse` contains `[2]`.
    4. Two more vehicles are requested. The pool reuses `vehicle1` (3) and `vehicle3` (1) instead of creating new ones. `available` becomes empty, `inUse` becomes `[3, 2, 1]`.

#### 3.7 Lazy Initialization Pattern

- **Motivation:** Defers the creation of an object until it's actually needed.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. LazyVehicle
      VehicleProvider --> Vehicle
      class Vehicle {
          <<interface>>
          +move()
      }
      class LazyVehicle {
          -String type
          +move()
      }
      class VehicleProvider {
          -Vehicle truck
          -Vehicle car
          +getVehicleByType(String) Vehicle
          +printStatus()
      }
    ```
- **Example Code:**
    ```java
    // 1. Product Interface
    interface Vehicle {
        void move();
    }

    // 2. Concrete Product
    record LazyVehicle(String type) implements Vehicle {
        @Override
        public void move() {
            System.out.println("LazyVehicle, move, type:" + type);
        }
    }

    // 3. Lazy Provider
    final class VehicleProvider {
        private Vehicle truck;
        private Vehicle car;

        Vehicle getVehicleByType(String type) {
            switch (type) {
                case "car":
                    if (car == null) {
                        System.out.println("lazy car created");
                        car = new LazyVehicle(type);
                    }
                    return car;
                case "truck":
                    if (truck == null) {
                        System.out.println("lazy truck created");
                        truck = new LazyVehicle(type);
                    }
                    return truck;
                default:
                    throw new IllegalArgumentException("unknown type");
            }
        }

        void printStatus() {
            System.out.println("status, truck:" + truck);
            System.out.println("status, car:" + car);
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Lazy Initialization: lazy vehicles");
            var vehicleProvider = new VehicleProvider();
            var truck1 = vehicleProvider.getVehicleByType("truck");
            vehicleProvider.printStatus();
            truck1.move();
            var car1 = vehicleProvider.getVehicleByType("car");
            var car2 = vehicleProvider.getVehicleByType("car");
            vehicleProvider.printStatus();
            car1.move();
            car2.move();
            System.out.println("car1==car2:" + (car1.equals(car2)));
        }
    }
    ```
- **Explanation:** The `VehicleProvider` holds references to `truck` and `car`. They are only created when the `getVehicleByType` method is first called for that specific type.
- **Dry Run:**
    1. `vehicleProvider.getVehicleByType("truck")` is called. `truck` is `null`, so a new `LazyVehicle` is created and the provider stores the reference.
    2. `truck1.move()` outputs "LazyVehicle, move, type:truck".
    3. `car1` is requested. Since `car` is `null`, a new `LazyVehicle` is created.
    4. `car2` is requested. The `car` reference already exists, so it's returned.
    5. `car1.move()` and `car2.move()` both output "LazyVehicle, move, type:car".
    6. `car1 == car2` returns `true` because they are the same object.

#### 3.8 Dependency Injection Pattern

- **Motivation:** Injects dependencies into an object rather than having the object create them. Promotes loose coupling and testability.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. SportVehicle
      Engine <|.. FastEngine
      SportVehicle --> Engine
      EngineServiceProvider ..> Engine : provides
      class Vehicle {
          <<interface>>
          +move()
      }
      class SportVehicle {
          -Engine engine
          +SportVehicle(Engine)
          +move()
      }
      class Engine {
          <<interface>>
          +start()
          +isStarted() boolean
          +run()
          +type() String
      }
      class FastEngine {
          -String type
          -boolean started
          +start()
          +isStarted() boolean
          +run()
          +type() String
      }
      class EngineServiceProvider {
          -Map~String, Engine~ ENGINES$
          -EngineServiceProvider()
          +addEngine(Engine)$
          +getEngineByType(String) Engine$
      }
    ```
- **Example Code:**
    ```java
    // 1. Service Interface
    interface Engine {
        void start();
        boolean isStarted();
        void run();
        String type();
    }

    // 2. Service Implementation
    class FastEngine implements Engine {
        private final String type;
        private boolean started;

        FastEngine(String type) {
            this.type = type;
        }

        @Override
        public void start() {
            System.out.println("FastEngine, started");
            this.started = true;
        }

        @Override
        public boolean isStarted() {
            return started;
        }

        @Override
        public void run() {
            System.out.println("FastEngine, run");
        }

        @Override
        public String type() {
            return type;
        }
    }

    // 3. Service Provider (Simple DI Container)
    final class EngineServiceProvider {
        private static final Map<String, Engine> ENGINES = new HashMap<>();

        static void addEngine(Engine engine) {
            ENGINES.put(engine.type(), engine);
        }

        static Engine getEngineByType(String t) {
            return ENGINES.values().stream()
                    .filter(e -> e.type().equals(t))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    // 4. Client Class
    class SportVehicle implements Vehicle {
        private final Engine engine;

        SportVehicle(Engine e) {
            this.engine = e;
        }

        @Override
        public void move() {
            if (!engine.isStarted()) {
                engine.start();
            }
            engine.run();
            System.out.println("SportCar,move");
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Dependency Injection: vehicle and engine");
            EngineServiceProvider.addEngine(new FastEngine("sport"));
            Engine engine = EngineServiceProvider.getEngineByType("sport");
            Vehicle vehicle = new SportVehicle(engine);
            vehicle.move();
        }
    }
    ```
- **Explanation:** The `SportVehicle` receives its `Engine` dependency via its constructor. The `EngineServiceProvider` acts as a simple container to provide the dependency. This makes `SportVehicle` independent of the specific `Engine` implementation.
- **Dry Run:**
    1. `EngineServiceProvider.addEngine(new FastEngine("sport"))` is called, storing a `FastEngine`.
    2. `EngineServiceProvider.getEngineByType("sport")` retrieves the `FastEngine`.
    3. A `SportVehicle` is created with the `FastEngine` as its dependency.
    4. `vehicle.move()` is called. Since the engine is not started, it starts it, then runs it, and prints "SportCar,move".

---

### Chapter 4: Applying Structural Design Patterns

#### 4.1 Adapter Pattern

- **Motivation:** Allows two incompatible interfaces to work together. The adapter acts as a bridge between them.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle --> Engine
      Engine <|.. ElectricEngine
      Engine <|.. PetrolEngine
      class Vehicle {
          -Engine engine
          +Vehicle(Engine)
          +drive()
          +refuel()
      }
      class Engine {
          <<sealed interface>>
          +run()
          +tank()
      }
      class ElectricEngine {
          +run()
          +tank()
          +checkPlug()
      }
      class PetrolEngine {
          +run()
          +tank()
      }
    ```
- **Example Code:**
    ```java
    // 1. Target Interface
    sealed interface Engine permits ElectricEngine, PetrolEngine {
        void run();
        void tank();
    }

    // 2. Adaptee Classes
    final class ElectricEngine implements Engine {
        @Override
        public void run() {
            System.out.println("ElectricEngine, run");
        }

        @Override
        public void tank() {
            System.out.println("ElectricEngine, recharging");
        }

        public void checkPlug() {
            System.out.println("ElectricEngine, check plug");
        }
    }

    final class PetrolEngine implements Engine {
        @Override
        public void run() {
            System.out.println("PetrolEngine, run");
        }

        @Override
        public void tank() {
            System.out.println("PetrolEngine, tank");
        }
    }

    // 3. Adapter Class
    class Vehicle {
        private final Engine engine;

        Vehicle(Engine engine) {
            this.engine = engine;
        }

        void drive() {
            engine.run();
            System.out.println("Vehicle, stop");
        }

        void refuel() {
            System.out.println("Vehicle, stop");
            switch (engine) {
                case ElectricEngine de -> {
                    System.out.println("Vehicle needs recharge");
                    de.checkPlug();
                    de.tank();
                }
                case PetrolEngine pe -> {
                    System.out.println("Vehicle needs petrol");
                    pe.tank();
                }
                default -> throw new IllegalStateException("Vehicle has no engine");
            }
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Adapter Pattern: engines");
            var electricEngine = new ElectricEngine();
            var enginePetrol = new PetrolEngine();
            var vehicleElectric = new Vehicle(electricEngine);
            var vehiclePetrol = new Vehicle(enginePetrol);
            vehicleElectric.drive();
            vehicleElectric.refuel();
            vehiclePetrol.drive();
            vehiclePetrol.refuel();
        }
    }
    ```
- **Explanation:** The `Vehicle` class acts as an adapter. It takes any `Engine` type. The `refuel()` method checks the type of the engine and handles the specific fueling process appropriately.
- **Dry Run:**
    1. `vehicleElectric.drive()` calls `engine.run()` (ElectricEngine) and prints "Vehicle, stop".
    2. `vehicleElectric.refuel()` checks the engine type (ElectricEngine). Prints "Vehicle needs recharge", then calls `checkPlug()` and `tank()` on the ElectricEngine.
    3. `vehiclePetrol.drive()` calls `engine.run()` (PetrolEngine) and prints "Vehicle, stop".
    4. `vehiclePetrol.refuel()` checks the engine type (PetrolEngine). Prints "Vehicle needs petrol", then calls `tank()` on the PetrolEngine.

#### 4.2 Bridge Pattern

- **Motivation:** Decouples an abstraction from its implementation so that both can vary independently.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|-- SportVehicle
      Vehicle <|-- PickupVehicle
      Engine <|.. PetrolEngine
      Engine <|.. DieselEngine
      Vehicle --> Engine
      class Vehicle {
          <<abstract>>
          #Engine engine
          #int horsePower
          +drive()
          +stop()
      }
      class SportVehicle {
          +drive()
          +stop()
      }
      class PickupVehicle {
          +drive()
          +stop()
      }
      class Engine {
          <<interface>>
          +on()
          +off()
          +selfCheck()
      }
      class PetrolEngine {
          +on()
          +off()
          +selfCheck()
      }
      class DieselEngine {
          +on()
          +off()
      }
    ```
- **Example Code:**
    ```java
    // 1. Implementor Interface
    interface Engine {
        void on();
        void off();
        void selfCheck();
    }

    // 2. Concrete Implementors
    class PetrolEngine implements Engine {
        @Override
        public void on() {
            System.out.println("PetrolEngine, on");
        }

        @Override
        public void off() {
            System.out.println("PetrolEngine, off");
        }

        @Override
        public void selfCheck() {
            System.out.println("PetrolEngine, self check");
        }
    }

    class DieselEngine implements Engine {
        @Override
        public void on() {
            System.out.println("DieselEngine, on");
        }

        @Override
        public void off() {
            System.out.println("DieselEngine, off");
        }

        @Override
        public void selfCheck() {
            // Diesel engine doesn't have a self-check feature
        }
    }

    // 3. Abstraction
    abstract class Vehicle {
        protected final Engine engine;
        protected final int horsePower;

        Vehicle(Engine engine, int horsePower) {
            this.engine = engine;
            this.horsePower = horsePower;
        }

        abstract void drive();
        abstract void stop();
    }

    // 4. Refined Abstractions
    class SportVehicle extends Vehicle {
        SportVehicle(Engine engine, int horsePower) {
            super(engine, horsePower);
        }

        @Override
        void drive() {
            System.out.println("SportVehicle, starting engine");
            engine.on();
            System.out.println("SportVehicle, engine started, hp:" + horsePower);
        }

        @Override
        void stop() {
            System.out.println("SportVehicle, stopping engine");
            engine.selfCheck();
            engine.off();
            System.out.println("SportVehicle, engine stopped");
        }
    }

    class PickupVehicle extends Vehicle {
        PickupVehicle(Engine engine, int horsePower) {
            super(engine, horsePower);
        }

        @Override
        void drive() {
            System.out.println("PickupVehicle, starting engine");
            engine.on();
            System.out.println("PickupVehicle, engine started, hp:" + horsePower);
        }

        @Override
        void stop() {
            System.out.println("PickupVehicle, stopping engine");
            engine.off();
            System.out.println("PickupVehicle, engine stopped");
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Bridge, vehicle engines...");
            Vehicle sportVehicle = new SportVehicle(new PetrolEngine(), 911);
            Vehicle pickupVehicle = new PickupVehicle(new DieselEngine(), 300);
            sportVehicle.drive();
            sportVehicle.stop();
            pickupVehicle.drive();
            pickupVehicle.stop();
        }
    }
    ```
- **Explanation:** The `Vehicle` abstraction has a reference to an `Engine` implementor. The `Vehicle` subclasses (`SportVehicle`, `PickupVehicle`) are refined abstractions, and the `Engine` subclasses (`PetrolEngine`, `DieselEngine`) are concrete implementors. This allows both the vehicle type and the engine type to be extended independently.
- **Dry Run:**
    1. `sportVehicle.drive()` is called. It calls `engine.on()` (PetrolEngine) and prints "SportVehicle, engine started, hp:911".
    2. `sportVehicle.stop()` is called. It calls `engine.selfCheck()` and `engine.off()` (PetrolEngine) and prints "SportVehicle, engine stopped".
    3. `pickupVehicle.drive()` is called. It calls `engine.on()` (DieselEngine) and prints "PickupVehicle, engine started, hp:300".
    4. `pickupVehicle.stop()` is called. It calls `engine.off()` (DieselEngine) and prints "PickupVehicle, engine stopped".

#### 4.3 Composite Pattern

- **Motivation:** Composes objects into tree structures to represent part-whole hierarchies. Allows clients to treat individual objects and compositions of objects uniformly.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehiclePart "1" o-- "*" VehiclePart : contains
      VehiclePart <|.. SportVehicle
      VehiclePart <|-- VehiclePart
      class VehiclePart {
          +addPart(VehiclePart)
          +toString() String
      }
      class SportVehicle {
          -String type
          +SportVehicle(String)
          +toString() String
      }
    ```
- **Example Code:**
    ```java
    // 1. Component
    interface VehiclePart {
        void addPart(VehiclePart part);
        String toString();
    }

    // 2. Leaf (and also Composite)
    record VehiclePart(String type, List<VehiclePart> parts) implements VehiclePart {
        VehiclePart(String type) {
            this(type, new ArrayList<>());
        }

        @Override
        public void addPart(VehiclePart part) {
            parts.add(part);
        }

        @Override
        public String toString() {
            return "type='" + type + "', parts=" + parts;
        }
    }

    // 3. Composite
    class SportVehicle implements VehiclePart {
        private final String type;
        private final List<VehiclePart> parts = new ArrayList<>();

        SportVehicle(String type) {
            this.type = type;
        }

        @Override
        public void addPart(VehiclePart part) {
            parts.add(part);
        }

        @Override
        public String toString() {
            return "SportCar, type'" + type + "', parts: " + parts;
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Composite, vehicle parts...");
            var fastVehicle = new SportVehicle("sport");
            var engine = new VehiclePart("fast-engine");
            engine.addPart(new VehiclePart("cylinder-head"));
            fastVehicle.addPart(engine);
            fastVehicle.addPart(new VehiclePart("super-brakes"));
            fastVehicle.addPart(new VehiclePart("automatic-transmission"));
            System.out.println(fastVehicle);
        }
    }
    ```
- **Explanation:** `VehiclePart` can be a leaf (no children) or a composite (contains other `VehiclePart`s). `SportVehicle` is a composite that can contain `VehiclePart` objects. The `toString()` method prints the entire hierarchy.
- **Dry Run:**
    1. A `SportVehicle` is created with the type "sport".
    2. An `engine` part of type "fast-engine" is created. A "cylinder-head" part is added to the engine.
    3. The engine, "super-brakes", and "automatic-transmission" parts are added to the vehicle.
    4. `fastVehicle.toString()` recursively prints the entire structure.

#### 4.4 Decorator Pattern

- **Motivation:** Attaches additional responsibilities to an object dynamically. Provides a flexible alternative to subclassing for extending functionality.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. StandardVehicle
      Vehicle <|.. TunedVehicleDecorator
      TunedVehicleDecorator <|-- SportVehicle
      TunedVehicleDecorator o-- Vehicle
      class Vehicle {
          <<interface>>
          +move()
      }
      class StandardVehicle {
          +move()
      }
      class TunedVehicleDecorator {
          <<abstract>>
          -Vehicle vehicle
          +move()
      }
      class SportVehicle {
          -int horsePower
          +move()
      }
    ```
- **Example Code:**
    ```java
    // 1. Component Interface
    interface Vehicle {
        void move();
    }

    // 2. Concrete Component
    class StandardVehicle implements Vehicle {
        @Override
        public void move() {
            System.out.println("Vehicle, move");
        }
    }

    // 3. Decorator Base Class
    sealed abstract class TunedVehicleDecorator implements Vehicle permits SportVehicle {
        private final Vehicle vehicle;

        TunedVehicleDecorator(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        public void move() {
            vehicle.move();
        }
    }

    // 4. Concrete Decorator
    final class SportVehicle extends TunedVehicleDecorator {
        private final int horsePower;

        SportVehicle(Vehicle vehicle, int horsePower) {
            super(vehicle);
            this.horsePower = horsePower;
        }

        @Override
        public void move() {
            System.out.println("SportVehicle, activate horse power:" + horsePower);
            System.out.println("TunedVehicleDecorator, turbo on");
            super.move();
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Decorator, tuned vehicle");
            Vehicle standardVehicle = new StandardVehicle();
            Vehicle vehicleToBeTuned = new StandardVehicle();
            Vehicle tunedVehicle = new SportVehicle(vehicleToBeTuned, 200);
            System.out.println("Drive a standard vehicle");
            standardVehicle.move();
            System.out.println("Drive a tuned vehicle");
            tunedVehicle.move();
        }
    }
    ```
- **Explanation:** `TunedVehicleDecorator` wraps a `Vehicle` and adds extra behavior in its `move()` method. `SportVehicle` is a concrete decorator that adds turbo and horse power.
- **Dry Run:**
    1. `standardVehicle.move()` outputs "Vehicle, move".
    2. `tunedVehicle.move()` is called.
    3. It prints "SportVehicle, activate horse power:200" and "TunedVehicleDecorator, turbo on".
    4. Then it calls `super.move()`, which calls `vehicle.move()` on the wrapped `StandardVehicle`, outputting "Vehicle, move".

#### 4.5 Facade Pattern

- **Motivation:** Provides a simplified interface to a complex subsystem.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. DieselVehicle
      Vehicle <|.. PetrolVehicle
      class Vehicle {
          <<interface>>
          +start()
          +refuel()
      }
      class DieselVehicle {
          +start()
          +refuel()
      }
      class PetrolVehicle {
          +start()
          +refuel()
      }
    ```
- **Example Code:**
    ```java
    // 1. Subsystem Classes
    interface Vehicle {
        void start();
        void refuel();
    }

    class DieselVehicle implements Vehicle {
        @Override
        public void start() {
            System.out.println("DieselVehicle, engine warm up");
            System.out.println("DieselVehicle, engine start");
        }

        @Override
        public void refuel() {
            System.out.println("DieselVehicle, refuel diesel");
        }
    }

    class PetrolVehicle implements Vehicle {
        @Override
        public void start() {
            System.out.println("PetrolVehicle, engine start");
        }

        @Override
        public void refuel() {
            System.out.println("PetrolVehicle, refuel petrol");
        }
    }

    // 2. Client Code (Facade is the simplified interface itself)
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Facade, vehicle types");
            List<Vehicle> vehicles = Arrays.asList(new DieselVehicle(), new PetrolVehicle());
            for (var vehicle : vehicles) {
                vehicle.start();
                vehicle.refuel();
            }
        }
    }
    ```
- **Explanation:** The `Vehicle` interface acts as a facade for the underlying complex subsystems (diesel and petrol engines). The client only interacts with the `start()` and `refuel()` methods, which abstract away the details of starting and refueling each specific vehicle type.
- **Dry Run:**
    1. `vehicles` list contains a `DieselVehicle` and a `PetrolVehicle`.
    2. For the `DieselVehicle`: `start()` prints "DieselVehicle, engine warm up" and "DieselVehicle, engine start". `refuel()` prints "DieselVehicle, refuel diesel".
    3. For the `PetrolVehicle`: `start()` prints "PetrolVehicle, engine start". `refuel()` prints "PetrolVehicle, refuel petrol".

#### 4.6 Filter Pattern

- **Motivation:** Filters a set of objects using different criteria and chains them in a decoupled way.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Rule <|.. RuleAnalog
      Rule <|.. RuleType
      Rule <|.. RuleAnd
      Rule <|.. RuleOr
      Sensor " * " <-- Rule
      class Rule {
          <<interface>>
          +validateSensors(Collection~Sensor~) Collection~Sensor~
      }
      class RuleAnalog {
          +validateSensors(Collection~Sensor~) Collection~Sensor~
      }
      class RuleType {
          -String type
          +validateSensors(Collection~Sensor~) Collection~Sensor~
      }
      class RuleAnd {
          -Rule rule
          -Rule additionalRule
          +validateSensors(Collection~Sensor~) Collection~Sensor~
      }
      class RuleOr {
          -Rule rule
          -Rule additionalRule
          +validateSensors(Collection~Sensor~) Collection~Sensor~
      }
      class Sensor {
          -String type
          -boolean analog
          +toString() String
      }
    ```
- **Example Code:**
    ```java
    // 1. Filter Interface
    @FunctionalInterface
    interface Rule {
        Collection<Sensor> validateSensors(Collection<Sensor> sensors);
    }

    // 2. Concrete Filters
    class RuleAnalog implements Rule {
        @Override
        public Collection<Sensor> validateSensors(Collection<Sensor> sensors) {
            return sensors.stream()
                    .filter(Sensor::analog)
                    .collect(Collectors.toList());
        }
    }

    class RuleType implements Rule {
        private final String type;

        RuleType(String type) {
            this.type = type;
        }

        @Override
        public Collection<Sensor> validateSensors(Collection<Sensor> sensors) {
            return sensors.stream()
                    .filter(s -> s.type().equals(type))
                    .collect(Collectors.toList());
        }
    }

    record RuleAnd(Rule rule, Rule additionalRule) implements Rule {
        @Override
        public Collection<Sensor> validateSensors(Collection<Sensor> sensors) {
            var initRule = rule.validateSensors(sensors);
            return additionalRule.validateSensors(initRule);
        }
    }

    record RuleOr(Rule rule, Rule additionalRule) implements Rule {
        @Override
        public Collection<Sensor> validateSensors(Collection<Sensor> sensors) {
            var initSet = new HashSet<>(rule.validateSensors(sensors));
            initSet.addAll(additionalRule.validateSensors(sensors));
            return initSet;
        }
    }

    // 3. Element Class
    record Sensor(String type, boolean analog) {}

    // 4. Client Code
    public class Main {
        private static final List<Sensor> vehicleSensors = new ArrayList<>();

        static {
            vehicleSensors.add(new Sensor("fuel", true));
            vehicleSensors.add(new Sensor("fuel", false));
            vehicleSensors.add(new Sensor("speed", false));
            vehicleSensors.add(new Sensor("speed", true));
        }

        public static void main(String[] args) {
            System.out.println("Pattern Filter, vehicle sensors");
            Rule analog = new RuleAnalog();
            Rule speedSensor = new RuleType("speed");
            var analogAndSpeedSensors = new RuleAnd(analog, speedSensor);
            var analogOrSpeedSensors = new RuleOr(analog, speedSensor);
            System.out.println("analogAndSpeedSensors = " + analogAndSpeedSensors.validateSensors(vehicleSensors));
            System.out.println("analogOrSpeedSensors = " + analogOrSpeedSensors.validateSensors(vehicleSensors));
        }
    }
    ```
- **Explanation:** `Rule` is an interface for filtering sensors. `RuleAnalog`, `RuleType`, `RuleAnd`, and `RuleOr` are concrete implementations that can be combined to create complex filtering logic.
- **Dry Run:**
    1. `analogAndSpeedSensors.validateSensors(vehicleSensors)` is called. `analog` filters to `[fuel(true), speed(true)]`. `speedSensor` filters this list to keep only `speed` sensors, resulting in `[speed(true)]`.
    2. `analogOrSpeedSensors.validateSensors(vehicleSensors)` is called. `analog` returns `[fuel(true), speed(true)]`. `speedSensor` returns `[speed(false), speed(true)]`. The union is `[fuel(true), speed(true), speed(false)]`.

#### 4.7 Flyweight Pattern

- **Motivation:** Shares objects to minimize memory usage when many objects contain the same state.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleGarage --> Vehicle
      Vehicle <|-- VehicleType
      class Vehicle {
          <<interface>>
          +move()
      }
      class VehicleType {
          -String type
          +move()
      }
      class VehicleGarage {
          -Map~String, Vehicle~ vehicleByType$
          -VehicleGarage()
          +borrow(String) Vehicle$
      }
    ```
- **Example Code:**
    ```java
    // 1. Flyweight Interface
    interface Vehicle {
        void move();
    }

    // 2. Concrete Flyweight
    class VehicleType implements Vehicle {
        private final String type;

        VehicleType(String type) {
            this.type = type;
        }

        @Override
        public void move() {
            System.out.println("Vehicle, type:'" + type + "', confirmed");
        }
    }

    // 3. Flyweight Factory
    class VehicleGarage {
        private static final Map<String, Vehicle> vehicleByType = new HashMap<>();

        static {
            vehicleByType.put("common", new VehicleType("common-car"));
            vehicleByType.put("sport", new VehicleType("sport-car"));
        }

        private VehicleGarage() {}

        static Vehicle borrow(String type) {
            Vehicle v = vehicleByType.get(type);
            if (v == null) {
                v = new VehicleType(type);
                vehicleByType.put(type, v);
            }
            System.out.println("VehicleGarage, borrowed type:" + type);
            return v;
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Flyweight, sharing vehicles");
            Vehicle car1 = VehicleGarage.borrow("sport");
            car1.move();
            Vehicle car2 = VehicleGarage.borrow("sport");
            System.out.println("Similar template:" + (car1.equals(car2)));
        }
    }
    ```
- **Explanation:** `VehicleGarage` caches `Vehicle` objects in a `Map`. When a client requests a vehicle of a certain type, the factory returns the existing instance if it's already created, or creates a new one and adds it to the cache.
- **Dry Run:**
    1. `VehicleGarage.borrow("sport")` is called. The map contains "sport", so the existing `VehicleType` object is returned.
    2. `car1.move()` prints "Vehicle, type:'sport-car', confirmed".
    3. `VehicleGarage.borrow("sport")` is called again. The same object is returned.
    4. `car1.equals(car2)` returns `true` because they are the same object.

#### 4.8 Front-Controller Pattern

- **Motivation:** Provides a centralized entry point for handling requests, consolidating common functionality like authentication, logging, and routing.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleController --> RequestDispatcher
      RequestDispatcher <|-- BrakesUnit
      RequestDispatcher <|-- EngineUnit
      class VehicleController {
          -RequestDispatcher dispatcher
          +processRequest(String)
          +authorize()
      }
      class RequestDispatcher {
          <<interface>>
          +dispatch(String)
      }
      class BrakesUnit {
          +dispatch(String)
      }
      class EngineUnit {
          +dispatch(String)
      }
    ```
- **Example Code:**
    ```java
    // 1. Dispatcher Interface
    interface RequestDispatcher {
        void dispatch(String request);
    }

    // 2. Concrete Dispatchers
    class BrakesUnit implements RequestDispatcher {
        @Override
        public void dispatch(String request) {
            System.out.println("BrakesUnit, activated");
        }
    }

    class EngineUnit implements RequestDispatcher {
        @Override
        public void dispatch(String request) {
            System.out.println("EngineUnit, start");
        }
    }

    // 3. Front Controller
    class VehicleController {
        private final RequestDispatcher dispatcher;

        VehicleController() {
            this.dispatcher = new EngineUnit(); // Default dispatcher
        }

        void processRequest(String request) {
            System.out.println("VehicleController, log:'" + request + "'");
            if (isAuthorized()) {
                dispatcher.dispatch(request);
            } else {
                System.out.println("VehicleController, not authorized request:'" + request + "'");
                System.out.println("VehicleController, authorization");
            }
        }

        private boolean isAuthorized() {
            System.out.println("VehicleController, is authorized");
            return true;
        }

        void authorize() {
            System.out.println("VehicleController, authorization");
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern FrontController, vehicle system");
            var vehicleController = new VehicleController();
            vehicleController.processRequest("engine");
            vehicleController.authorize();
            vehicleController.processRequest("engine");
            vehicleController.processRequest("brakes");
        }
    }
    ```
- **Explanation:** `VehicleController` is the front controller. It handles all requests, performs logging and authorization checks, and delegates the actual request processing to a `RequestDispatcher`.
- **Dry Run:**
    1. `vehicleController.processRequest("engine")` is called. Logs "engine", checks authorization, and dispatches to the default `EngineUnit`, printing "EngineUnit, start".
    2. `vehicleController.authorize()` is called, simulating an authorization step.
    3. `vehicleController.processRequest("engine")` is called again. It logs, checks authorization, and dispatches to `EngineUnit`.
    4. `vehicleController.processRequest("brakes")` is called. It logs, checks authorization, but the dispatcher is still `EngineUnit`, so it prints "EngineUnit, start" even though the request is for "brakes" (in this simplified example, the dispatcher isn't dynamic).

#### 4.9 Marker Pattern

- **Motivation:** Uses an empty interface or annotation to mark a class as having a special property, so it can be handled differently at runtime.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Sensor <|.. BrakesSensor
      Sensor <|.. EngineSensor
      Sensor <|.. ConsumptionSensor
      CertifiedSensor <|.. EngineSensor
      <<interface>> CertifiedSensor
      <<annotation>> CertifiedAnnotation
      class Sensor {
          <<interface>>
          +activate()
      }
      class BrakesSensor {
          +activate()
      }
      class EngineSensor {
          +activate()
      }
      class ConsumptionSensor {
          +activate()
      }
    ```
- **Example Code:**
    ```java
    // 1. Marker Interface
    public interface CertifiedSensor extends Sensor {}

    // 2. Marker Annotation
    @Retention(RetentionPolicy.RUNTIME)
    @interface CertifiedAnnotation {}

    // 3. Base Interface
    interface Sensor {
        void activate();
    }

    // 4. Concrete Classes
    class BrakesSensor implements Sensor {
        @Override
        public void activate() {
            System.out.println("BrakesSensor activated");
        }
    }

    final class EngineSensor implements CertifiedSensor {
        @Override
        public void activate() {
            System.out.println("EngineSensor activated");
        }
    }

    @CertifiedAnnotation
    class ConsumptionSensor implements Sensor {
        @Override
        public void activate() {
            System.out.println("ConsumptionSensor activated");
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Marker, sensor identification");
            var sensors = Arrays.asList(
                new BrakesSensor(),
                new EngineSensor(),
                new ConsumptionSensor()
            );
            sensors.forEach(sensor -> {
                if (sensor.getClass().isAnnotationPresent(CertifiedAnnotation.class)) {
                    System.out.println("Sensor with Marker annotation:" + sensor);
                } else {
                    switch (sensor) {
                        case CertifiedSensor cs -> System.out.println("Sensor with Marker interface: " + cs);
                        case Sensor s -> System.out.println("Sensor without identification:" + s);
                    }
                }
            });
        }
    }
    ```
- **Explanation:** The `CertifiedSensor` interface and `CertifiedAnnotation` are markers. The client code checks for these markers to identify certified sensors.
- **Dry Run:**
    1. `sensors` list contains `BrakesSensor`, `EngineSensor`, `ConsumptionSensor`.
    2. `BrakesSensor`: Not annotated and doesn't implement `CertifiedSensor`. Prints "Sensor without identification".
    3. `EngineSensor`: Implements `CertifiedSensor`. Prints "Sensor with Marker interface".
    4. `ConsumptionSensor`: Has `CertifiedAnnotation`. Prints "Sensor with Marker annotation".

#### 4.10 Module Pattern

- **Motivation:** Encapsulates a group of related classes and packages into a module, providing a clear interface and enforcing strong encapsulation.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleModule <|-- BrakesModule
      VehicleModule <|-- EngineModule
      class VehicleModule {
          <<interface>>
          +init()
          +status()
      }
      class BrakesModule {
          -BrakesModule INSTANCE$
          +getInstance() BrakesModule$
          +init()
          +status()
      }
      class EngineModule {
          -EngineModule INSTANCE$
          +getInstance() EngineModule$
          +init()
          +status()
      }
    ```
- **Example Code:**
    ```java
    // 1. Module Interface
    interface VehicleModule {
        void init();
        void status();
    }

    // 2. Module Implementations (Singletons)
    class BrakesModule implements VehicleModule {
        private static BrakesModule INSTANCE;

        private BrakesModule() {}

        static BrakesModule getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new BrakesModule();
            }
            return INSTANCE;
        }

        @Override
        public void init() {
            System.out.println("BrakesModule, ready: true");
        }

        @Override
        public void status() {
            System.out.println("BrakesModule, ready: false");
        }
    }

    class EngineModule implements VehicleModule {
        private static EngineModule INSTANCE;

        private EngineModule() {}

        static EngineModule getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new EngineModule();
            }
            return INSTANCE;
        }

        @Override
        public void init() {
            System.out.println("EngineModule, init");
            System.out.println("EngineModule, ready: true");
        }

        @Override
        public void status() {
            System.out.println("EngineModule, ready: true");
        }
    }

    // 3. Client Code
    class ModuleMain {
        private static BrakesModule brakesModule;
        private static EngineModule engineModule;

        private static void initModules() {
            brakesModule = BrakesModule.getInstance();
            engineModule = EngineModule.getInstance();
            engineModule.init();
        }

        private static void printStatus() {
            System.out.println("BrakesModule, unit:" + brakesModule);
            System.out.println("EngineModule, unit:" + engineModule);
            brakesModule.status();
            engineModule.status();
        }

        public static void main(String[] args) {
            initModules();
            printStatus();
        }
    }
    ```
- **Explanation:** The `VehicleModule` interface defines the module contract. `BrakesModule` and `EngineModule` are singleton modules that encapsulate specific functionality. `initModules()` initializes them.
- **Dry Run:**
    1. `initModules()` is called. It gets singletons for `BrakesModule` and `EngineModule`.
    2. `engineModule.init()` is called, printing "EngineModule, init" and "EngineModule, ready: true".
    3. `printStatus()` is called. It prints the module references and their statuses. `brakesModule.status()` prints "BrakesModule, ready: false". `engineModule.status()` prints "EngineModule, ready: true".

#### 4.11 Proxy Pattern

- **Motivation:** Provides a surrogate or placeholder for another object to control access to it.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. VehicleProxy
      Vehicle <|.. VehicleReal
      VehicleProxy --> VehicleReal
      class Vehicle {
          <<interface>>
          +move()
      }
      class VehicleReal {
          +move()
      }
      class VehicleProxy {
          -Vehicle vehicleReal
          +move()
      }
    ```
- **Example Code:**
    ```java
    // 1. Subject Interface
    interface Vehicle {
        void move();
    }

    // 2. Real Subject
    class VehicleReal implements Vehicle {
        @Override
        public void move() {
            System.out.println("VehicleReal, move");
        }
    }

    // 3. Proxy
    class VehicleProxy implements Vehicle {
        private Vehicle vehicleReal;

        @Override
        public void move() {
            if (vehicleReal == null) {
                System.out.println("VehicleProxy, real vehicle connected");
                vehicleReal = new VehicleReal();
            }
            vehicleReal.move();
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Proxy, remote vehicle controller");
            Vehicle vehicle = new VehicleProxy();
            vehicle.move();
            vehicle.move();
        }
    }
    ```
- **Explanation:** `VehicleProxy` controls access to the real `VehicleReal` object. It performs lazy initialization, creating the `VehicleReal` object only when `move()` is first called.
- **Dry Run:**
    1. `vehicle.move()` is called. `vehicleReal` is `null`, so it prints "VehicleProxy, real vehicle connected" and creates a `VehicleReal` object. Then it calls `vehicleReal.move()`, printing "VehicleReal, move".
    2. `vehicle.move()` is called again. `vehicleReal` is not `null`, so it just calls `vehicleReal.move()` directly.

#### 4.12 Twin Pattern

- **Motivation:** A way to implement a form of multiple inheritance in languages that don't support it directly. Two classes are tightly coupled to provide a unified interface.
- **Class Diagram:**
    ```mermaid
    classDiagram
      AbstractVehiclePart <|-- VehicleBrakes
      AbstractVehiclePart <|-- VehicleEngine
      VehicleBrakes --> VehicleEngine
      VehicleEngine --> VehicleBrakes
      class AbstractVehiclePart {
          <<abstract>>
          -boolean ready
          +AbstractVehiclePart()
          +init()*
          +setReady()
          +isReady() boolean
      }
      class VehicleBrakes {
          -VehicleEngine twin
          +setEngine(VehicleEngine)
          +init()
      }
      class VehicleEngine {
          -VehicleBrakes twin
          +setBrakes(VehicleBrakes)
          +init()
      }
    ```
- **Example Code:**
    ```java
    // 1. Abstract Base
    abstract class AbstractVehiclePart {
        private boolean ready;

        AbstractVehiclePart() {
            System.out.println("AbstractVehiclePart, constructor");
        }

        void setReady() {
            this.ready = true;
        }

        boolean isReady() {
            return ready;
        }

        abstract void init();
    }

    // 2. Twin Classes (Tightly Coupled)
    class VehicleBrakes extends AbstractVehiclePart {
        private VehicleEngine twin;

        VehicleBrakes() {
            System.out.println("VehicleBrakes, constructor");
        }

        void setEngine(VehicleEngine engine) {
            this.twin = engine;
        }

        @Override
        void init() {
            if (twin.isReady()) {
                setReady();
            } else {
                setReady();
                twin.init();
            }
            System.out.println("VehicleBrakes, initiated");
        }
    }

    class VehicleEngine extends AbstractVehiclePart {
        private VehicleBrakes twin;

        VehicleEngine() {
            System.out.println("VehicleEngine, constructor");
        }

        void setBrakes(VehicleBrakes brakes) {
            this.twin = brakes;
        }

        @Override
        void init() {
            setReady();
            if (twin != null) {
                twin.init();
            }
            System.out.println("VehicleEngine, initiated");
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Twin, vehicle initiation sequence");
            var vehicleBrakes1 = new VehicleBrakes();
            var vehicleEngine1 = new VehicleEngine();
            vehicleBrakes1.setEngine(vehicleEngine1);
            vehicleEngine1.setBrakes(vehicleBrakes1);
            vehicleEngine1.init();
        }
    }
    ```
- **Explanation:** `VehicleBrakes` and `VehicleEngine` are tightly coupled. They know about each other and ensure that when one is initialized, the other is too.
- **Dry Run:**
    1. `vehicleBrakes1` and `vehicleEngine1` are created, printing their constructors.
    2. The twins are set: `vehicleBrakes1.setEngine(vehicleEngine1)` and `vehicleEngine1.setBrakes(vehicleBrakes1)`.
    3. `vehicleEngine1.init()` is called.
    4. `vehicleEngine1` sets its `ready` flag to `true` and calls `twin.init()`.
    5. `vehicleBrakes1.init()` is called. It checks `twin.isReady()` (which is `true`) and sets its own `ready` flag.
    6. Both print that they are initiated.

---

### Chapter 5: Behavioral Design Patterns

#### 5.1 Caching Pattern

- **Motivation:** Stores frequently used data in a cache to improve performance by avoiding expensive re-creation or re-fetching.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle --> SystemCache
      VehicleSystem <|.. BreakSystem
      VehicleSystem <|.. EngineSystem
      VehicleSystem <|.. SuspensionSystem
      class Vehicle {
          -SystemCache cache
          +init()
          +addSystem(VehicleSystem)
          +getSystemByType(String) VehicleSystem
          +systemTypes() String
      }
      class SystemCache {
          -VehicleSystem[] systems
          -int end
          +addSystem(VehicleSystem) boolean
          +getSystem(String) VehicleSystem
      }
      class VehicleSystem {
          <<sealed interface>>
          +type() String
          +init()
      }
      class BreakSystem
      class EngineSystem
      class SuspensionSystem
    ```
- **Example Code:**
    ```java
    // 1. Cacheable Items
    sealed interface VehicleSystem permits BreakSystem, EngineSystem, SuspensionSystem {
        String type();
        void init();
    }

    record BreakSystem(String type) implements VehicleSystem {
        @Override
        public void init() {
            System.out.println("BreakSystem, init");
        }
    }

    record EngineSystem(String type) implements VehicleSystem {
        @Override
        public void init() {
            System.out.println("EngineSystem, init");
        }
    }

    record SuspensionSystem(String type) implements VehicleSystem {
        @Override
        public void init() {
            System.out.println("SuspensionSystem, init");
        }
    }

    // 2. Cache
    class SystemCache {
        private final VehicleSystem[] systems;
        private int end;

        SystemCache(int size) {
            this.systems = new VehicleSystem[size];
        }

        boolean addSystem(VehicleSystem system) {
            var availableSystem = getSystem(system.type());
            if (availableSystem == null && end < systems.length) {
                systems[end++] = system;
                return true;
            }
            return false;
        }

        VehicleSystem getSystem(String type) {
            for (int i = 0; i < end; i++) {
                if (systems[i].type().equals(type)) {
                    return systems[i];
                }
            }
            return null;
        }
    }

    // 3. Client that uses the cache
    class Vehicle {
        private final SystemCache cache = new SystemCache(3);

        void init() {
            var breakSys = new BreakSystem("break");
            var engineSys = new EngineSystem("engine");
            addSystem(breakSys);
            addSystem(engineSys);
            System.out.println("Vehicle, init cache:'break':'" + cache.getSystem("break") + "', 'engine':'" + cache.getSystem("engine") + "'");
        }

        void addSystem(VehicleSystem system) {
            if (!cache.addSystem(system)) {
                System.out.println("SystemCache, not stored:" + system);
            }
        }

        VehicleSystem getSystemByType(String type) {
            return cache.getSystem(type);
        }

        String systemTypes() {
            return String.format("'break':'%s','engine':'%s','suspension':'%s'",
                    cache.getSystem("break"), cache.getSystem("engine"), cache.getSystem("suspension"));
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Caching Pattern, initiated vehicle system");
            var vehicle = new Vehicle();
            vehicle.init();
            var suspension = new SuspensionSystem("suspension");
            vehicle.addSystem(suspension);
            System.out.printf("Systems types:'%s\n", vehicle.systemTypes());
            var suspensionCache = vehicle.getSystemByType("suspension");
            System.out.printf("Is suspension equal? '%s:%s\n", suspension.equals(suspensionCache), suspensionCache);
            vehicle.addSystem(new EngineSystem("engine2"));
        }
    }
    ```
- **Explanation:** The `SystemCache` stores `VehicleSystem` objects. When a system is requested, the cache is checked first. If not present, it can be created and added to the cache.
- **Dry Run:**
    1. `vehicle.init()` creates `BreakSystem` and `EngineSystem` and adds them to the cache.
    2. A `SuspensionSystem` is created and added to the cache.
    3. `vehicle.systemTypes()` is called, showing the cached systems.
    4. `vehicle.getSystemByType("suspension")` returns the cached `SuspensionSystem`.
    5. `equals` returns `true` because they are the same object.
    6. Attempting to add another `EngineSystem` fails because it's already cached and the cache is full.

#### 5.2 Chain of Responsibility Pattern

- **Motivation:** Allows a request to be passed along a chain of handlers. Each handler decides whether to process the request or pass it to the next handler.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleSystem <|-- DriverSystem
      VehicleSystem <|-- TransmissionSystem
      VehicleSystem <|-- EngineSystem
      VehicleSystem o-- VehicleSystem
      class VehicleSystem {
          <<sealed abstract>>
          #VehicleSystem nextSystem
          #boolean active
          +setNext(VehicleSystem)
          +powerOn()
          +activate()*
      }
      class DriverSystem {
          +activate()
      }
      class TransmissionSystem {
          +activate()
      }
      class EngineSystem {
          +activate()
      }
    ```
- **Example Code:**
    ```java
    // 1. Handler Base
    sealed abstract class VehicleSystem permits DriverSystem, EngineSystem, TransmissionSystem {
        protected VehicleSystem nextSystem;
        protected boolean active;

        void setNext(VehicleSystem system) {
            this.nextSystem = system;
        }

        void powerOn() {
            if (!this.active) {
                activate();
            }
            if (nextSystem != null) {
                nextSystem.powerOn();
            }
        }

        abstract void activate();
    }

    // 2. Concrete Handlers
    final class DriverSystem extends VehicleSystem {
        @Override
        void activate() {
            System.out.println("DriverSystem: activated");
            this.active = true;
        }
    }

    final class TransmissionSystem extends VehicleSystem {
        @Override
        void activate() {
            System.out.println("TransmissionSystem: activated");
            this.active = true;
        }
    }

    final class EngineSystem extends VehicleSystem {
        @Override
        void activate() {
            System.out.println("EngineSystem, activated");
            this.active = true;
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Chain of Responsibility, vehicle system initialisation");
            var engineSystem = new EngineSystem();
            var driverSystem = new DriverSystem();
            var transmissionSystem = new TransmissionSystem();
            driverSystem.setNext(transmissionSystem);
            transmissionSystem.setNext(engineSystem);
            driverSystem.powerOn();
        }
    }
    ```
- **Explanation:** The chain is built: `DriverSystem` -> `TransmissionSystem` -> `EngineSystem`. When `driverSystem.powerOn()` is called, it triggers `activate()` in each handler in sequence.
- **Dry Run:**
    1. `driverSystem.powerOn()` is called.
    2. `driverSystem.activate()` is called, printing "DriverSystem: activated".
    3. `nextSystem.powerOn()` is called on `transmissionSystem`.
    4. `transmissionSystem.activate()` is called, printing "TransmissionSystem: activated".
    5. `nextSystem.powerOn()` is called on `engineSystem`.
    6. `engineSystem.activate()` is called, printing "EngineSystem, activated".

#### 5.3 Command Pattern

- **Motivation:** Encapsulates a request as an object, thereby allowing parameterization of clients with different requests, queuing of requests, and logging the requests.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleCommand <|.. StartCommand
      VehicleCommand <|.. StopCommand
      Driver --> VehicleCommand
      Vehicle <-- VehicleCommand
      class VehicleCommand {
          <<sealed interface>>
          +process(String)
      }
      class StartCommand {
          -Vehicle vehicle
          +process(String)
      }
      class StopCommand {
          -Vehicle vehicle
          +process(String)
      }
      class Driver {
          -List~VehicleCommand~ commands
          +addCommand(VehicleCommand)
          +executeCommands(String)
      }
      class Vehicle {
          -String type
          -boolean running
          +start()
          +stop()
          +toString() String
      }
    ```
- **Example Code:**
    ```java
    // 1. Receiver
    record Vehicle(String type, boolean running) {
        Vehicle start() {
            System.out.println("START:" + this);
            return new Vehicle(type, true);
        }

        Vehicle stop() {
            System.out.println("STOP:" + this);
            return new Vehicle(type, false);
        }
    }

    // 2. Command Interface
    sealed interface VehicleCommand permits StartCommand, StopCommand {
        void process(String command);
    }

    // 3. Concrete Commands
    record StartCommand(Vehicle vehicle) implements VehicleCommand {
        @Override
        public void process(String command) {
            if (command.contains("start")) {
                vehicle.start();
            }
        }
    }

    record StopCommand(Vehicle vehicle) implements VehicleCommand {
        @Override
        public void process(String command) {
            if (command.contains("stop")) {
                vehicle.stop();
            }
        }
    }

    // 4. Invoker
    class Driver {
        private final List<VehicleCommand> commands = new ArrayList<>();

        void addCommand(VehicleCommand command) {
            commands.add(command);
        }

        void executeCommands(String cmds) {
            for (VehicleCommand command : commands) {
                command.process(cmds);
            }
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pattern Command, turn on/off vehicle");
            var vehicle = new Vehicle("sport-car", false);
            var driver = new Driver();
            driver.addCommand(new StartCommand(vehicle));
            driver.addCommand(new StopCommand(vehicle));
            driver.addCommand(new StartCommand(vehicle));
            driver.executeCommands("start stop");
        }
    }
    ```
- **Explanation:** The `Driver` (invoker) holds a list of `VehicleCommand` objects. When `executeCommands` is called, the `process` method of each command is executed, which in turn calls the appropriate method on the `Vehicle` (receiver).
- **Dry Run:**
    1. `driver.executeCommands("start stop")` is called.
    2. `StartCommand.process("start stop")` is called. The command contains "start", so `vehicle.start()` is called, printing "START:Vehicle[type=sport-car, running=false]". The new state is `Vehicle[type=sport-car, running=true]`.
    3. `StopCommand.process("start stop")` is called. The command contains "stop", so `vehicle.stop()` is called, printing "STOP:Vehicle[type=sport-car, running=true]". The new state is `Vehicle[type=sport-car, running=false]`.
    4. The next `StartCommand` is processed similarly.

#### 5.4 Interpreter Pattern

- **Motivation:** Defines a representation for a language's grammar and an interpreter to interpret sentences in that language.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Expression <|-- IntegerExpression
      Expression <|-- AddExpression
      Expression <|-- SubExpression
      class Expression {
          <<interface>>
          +interpret() int
      }
      class IntegerExpression {
          -int value
          +interpret() int
      }
      class AddExpression {
          -Expression left
          -Expression right
          +interpret() int
      }
      class SubExpression {
          -Expression left
          -Expression right
          +interpret() int
      }
    ```
- **Example Code:**
    ```java
    // 1. Abstract Expression
    interface Expression {
        int interpret();
    }

    // 2. Terminal Expression
    record IntegerExpression(int value) implements Expression {
        @Override
        public int interpret() {
            return value;
        }
    }

    // 3. Non-terminal Expressions
    record AddExpression(Expression left, Expression right) implements Expression {
        @Override
        public int interpret() {
            return left.interpret() + right.interpret();
        }
    }

    record SubExpression(Expression left, Expression right) implements Expression {
        @Override
        public int interpret() {
            return left.interpret() - right.interpret();
        }
    }

    // 4. Client Code
    public class Main {
        private static boolean isOperator(String text) {
            return text.equals("+") || text.equals("-");
        }

        private static Expression getEvaluationExpression(String text, Expression left, Expression right) {
            return switch (text) {
                case "+" -> new AddExpression(left, right);
                case "-" -> new SubExpression(left, right);
                default -> throw new IllegalArgumentException("unknown operator");
            };
        }

        public static void main(String[] args) {
            System.out.println("Pattern Interpreter, math formula evaluation");
            var stack = new Stack<Expression>();
            var formula = "1-3+100+1";
            var parsedFormula = formula.split("");
            var index = 0;
            while (index < parsedFormula.length) {
                var text = parsedFormula[index++];
                if (isOperator(text)) {
                    var leftExp = stack.pop();
                    var rightText = parsedFormula[index++];
                    var rightExp = new IntegerExpression(Integer.parseInt(rightText));
                    var operatorExp = getEvaluationExpression(text, leftExp, rightExp);
                    stack.push(operatorExp);
                } else {
                    var exp = new IntegerExpression(Integer.parseInt(text));
                    stack.push(exp);
                }
            }
            System.out.println("Formula result:" + stack.pop().interpret());
        }
    }
    ```
- **Explanation:** The formula "1-3+100+1" is parsed. Numbers are turned into `IntegerExpression` objects. Operators (`+`, `-`) are turned into `AddExpression` or `SubExpression` objects. The stack evaluates the expressions. The final result is 99.
- **Dry Run:**
    1. Parse "1": Push `IntegerExpression(1)`.
    2. Parse "-": Pop left (1), parse "3" as right, create `SubExpression(1, 3)`, push.
    3. Parse "+": Pop left (-2), parse "100" as right, create `AddExpression(-2, 100)`, push (98).
    4. Parse "+": Pop left (98), parse "1" as right, create `AddExpression(98, 1)`, push (99).
    5. Pop and interpret the result: 99.

#### 5.5 Iterator Pattern

- **Motivation:** Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle <|.. StandardVehicle
      StandardVehicle *-- VehiclePartsIterator
      PartsIterator <|.. VehiclePartsIterator
      VehiclePart <-- PartsIterator
      class Vehicle {
          <<sealed interface>>
          +getParts() PartsIterator
      }
      class StandardVehicle {
          -String[] vehiclePartsNames
          +getParts() PartsIterator
      }
      class PartsIterator {
          <<interface>>
          +hasNext() boolean
          +next() VehiclePart
      }
      class VehiclePartsIterator {
          -int index
          +hasNext() boolean
          +next() VehiclePart
      }
      record VehiclePart(String name)
    ```
- **Example Code:**
    ```java
    // 1. Iterator Interface
    interface PartsIterator {
        boolean hasNext();
        VehiclePart next();
    }

    // 2. Element Class
    record VehiclePart(String name) {}

    // 3. Aggregate Interface
    sealed interface Vehicle permits StandardVehicle {
        PartsIterator getParts();
    }

    // 4. Concrete Aggregate
    final class StandardVehicle implements Vehicle {
        private final String[] vehiclePartsNames = {"engine", "breaks", "navigation"};

        private class VehiclePartsIterator implements PartsIterator {
            private int index;

            @Override
            public boolean hasNext() {
                return index < vehiclePartsNames.length;
            }

            @Override
            public VehiclePart next() {
                return new VehiclePart(vehiclePartsNames[index++]);
            }
        }

        @Override
        public PartsIterator getParts() {
            return new VehiclePartsIterator();
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Iterator Pattern, vehicle parts");
            var standardVehicle = new StandardVehicle();
            for (PartsIterator part = standardVehicle.getParts(); part.hasNext(); ) {
                var vehiclePart = part.next();
                System.out.println("VehiclePart name:" + vehiclePart.name());
            }
        }
    }
    ```
- **Explanation:** `StandardVehicle` provides an iterator (`VehiclePartsIterator`) that allows the client to iterate over its parts without exposing the internal array.
- **Dry Run:**
    1. `standardVehicle.getParts()` returns a `VehiclePartsIterator` with `index=0`.
    2. The `for` loop checks `part.hasNext()` (true). `part.next()` returns `VehiclePart("engine")`, prints "VehiclePart name:engine". `index` becomes 1.
    3. Iteration continues for "breaks" and "navigation". Finally, `hasNext()` returns false, and the loop ends.

#### 5.6 Mediator Pattern

- **Motivation:** Defines an object that encapsulates how a set of objects interact. Promotes loose coupling by keeping objects from referring to each other explicitly.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Sensor --> VehicleProcessor
      Sensor "1" -- "1" VehicleProcessor
      class Sensor {
          -String name
          +emitMessage(String)
      }
      class VehicleProcessor {
          -VehicleProcessor INSTANCE$
          +acceptMessage(String, String)$
      }
    ```
- **Example Code:**
    ```java
    // 1. Mediator
    final class VehicleProcessor {
        private static VehicleProcessor INSTANCE;

        private VehicleProcessor() {}

        static VehicleProcessor getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new VehicleProcessor();
            }
            return INSTANCE;
        }

        static void acceptMessage(String sensorName, String message) {
            System.out.println("Sensor:'" + sensorName + "', delivered message:'" + message + "'");
        }
    }

    // 2. Colleague
    record Sensor(String name) {
        void emitMessage(String message) {
            VehicleProcessor.acceptMessage(name, message);
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Mediator Pattern, vehicle parts");
            var engineSensor = new Sensor("engine");
            var breakSensor = new Sensor("break");
            engineSensor.emitMessage("turn on");
            breakSensor.emitMessage("init");
        }
    }
    ```
- **Explanation:** `VehicleProcessor` is the mediator. `Sensor` objects communicate through the mediator instead of directly with each other.
- **Dry Run:**
    1. `engineSensor.emitMessage("turn on")` calls `VehicleProcessor.acceptMessage("engine", "turn on")`.
    2. `VehicleProcessor.acceptMessage` prints "Sensor:'engine', delivered message:'turn on'".
    3. `breakSensor.emitMessage("init")` calls `VehicleProcessor.acceptMessage("break", "init")`, printing "Sensor:'break', delivered message:'init'".

#### 5.7 Memento Pattern

- **Motivation:** Captures and externalizes an object's internal state so that it can be restored later without violating encapsulation.
- **Class Diagram:**
    ```mermaid
    classDiagram
      AirConditionSystemOriginator --> SystemMemento
      AirConditionSystemCareTaker --> SystemMemento
      class AirConditionSystemOriginator {
          -String state
          +saveState(CareTaker) int
          +restoreState(SystemMemento)
          +getState() String
          +setState(String)
      }
      class SystemMemento {
          -String state
          +state() String
      }
      class AirConditionSystemCareTaker {
          -List~SystemMemento~ memory
          +add(SystemMemento) int
          +getMemento(int) SystemMemento
      }
    ```
- **Example Code:**
    ```java
    // 1. Memento
    record SystemMemento(String state) {}

    // 2. Originator
    final class AirConditionSystemOriginator {
        private String state;

        int saveState(AirConditionSystemCareTaker careTaker) {
            return careTaker.add(new SystemMemento(state));
        }

        void restoreState(SystemMemento m) {
            state = m.state();
        }

        String getState() {
            return state;
        }

        void setState(String state) {
            this.state = state;
        }
    }

    // 3. Caretaker
    final class AirConditionSystemCareTaker {
        private final List<SystemMemento> memory = new ArrayList<>();

        int add(SystemMemento m) {
            memory.add(m);
            return memory.size() - 1;
        }

        SystemMemento getMemento(int i) {
            return memory.get(i);
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Memento Pattern, air-condition system");
            var originator = new AirConditionSystemOriginator();
            var careTaker = new AirConditionSystemCareTaker();
            originator.setState("low");
            var stateLow = originator.saveState(careTaker);
            originator.setState("medium");
            var stateMedium = originator.saveState(careTaker);
            originator.setState("high");
            var stateHigh = originator.saveState(careTaker);
            System.out.printf("Current Air-Condition System state:'%s'%n", originator.getState());
            originator.restoreState(careTaker.getMemento(stateLow));
            System.out.printf("Restored position:'%d', Air-Condition System state:'%s'%n", stateLow, originator.getState());
        }
    }
    ```
- **Explanation:** The `AirConditionSystemOriginator` (originator) can save its state in a `SystemMemento` (memento) and restore it later. The `AirConditionSystemCareTaker` (caretaker) manages the saved states.
- **Dry Run:**
    1. States "low", "medium", "high" are saved. Each `saveState` call returns an index.
    2. Current state is "high".
    3. `originator.restoreState(careTaker.getMemento(stateLow))` is called, restoring the state to "low".
    4. The output shows the restored state.

#### 5.8 Null Object Pattern

- **Motivation:** Provides a default, do-nothing implementation of an interface to avoid null checks.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleSensor <|-- Sensor
      VehicleSensor <|-- NullSensor
      VehicleSensorsProvider --> VehicleSensor
      class VehicleSensor {
          <<interface>>
          +type()
      }
      class Sensor {
          -String type
          +type()
      }
      class NullSensor {
          -String type
          +type()
      }
      class VehicleSensorsProvider {
          -Map~String, VehicleSensor~ sensors$
          +getSensorByType(String) VehicleSensor$
      }
    ```
- **Example Code:**
    ```java
    // 1. Interface
    interface VehicleSensor {
        String type();
    }

    // 2. Real Object
    record Sensor(String type) implements VehicleSensor {}

    // 3. Null Object
    class NullSensor implements VehicleSensor {
        private final String type;

        NullSensor() {
            this.type = "not available";
        }

        @Override
        public String type() {
            return type;
        }

        @Override
        public String toString() {
            return "Sensor{type='" + type + "'}";
        }
    }

    // 4. Provider
    final class VehicleSensorsProvider {
        private static final Map<String, VehicleSensor> sensors = Map.of(
                "engine", new Sensor("engine")
        );

        static VehicleSensor getSensorByType(String type) {
            return sensors.getOrDefault(type, new NullSensor());
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Null Object Pattern, vehicle sensor");
            var engineSensor = VehicleSensorsProvider.getSensorByType("engine");
            var transmissionSensor = VehicleSensorsProvider.getSensorByType("transmission");
            System.out.println("Engine Sensor:" + engineSensor);
            System.out.println("Transmission Sensor:" + transmissionSensor);
        }
    }
    ```
- **Explanation:** `VehicleSensorsProvider` returns a `NullSensor` if the requested sensor type is not found. This prevents `NullPointerException` and allows the client to handle the "not found" case gracefully.
- **Dry Run:**
    1. `VehicleSensorsProvider.getSensorByType("engine")` returns a `Sensor` object.
    2. `VehicleSensorsProvider.getSensorByType("transmission")` returns a `NullSensor` object.
    3. The output shows the real sensor and the null object.

#### 5.9 Observer Pattern

- **Motivation:** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleSystem --> SystemObserver
      SystemObserver <|-- CockpitObserver
      SystemObserver <|-- EngineObserver
      class VehicleSystem {
          -String state
          -List~SystemObserver~ observers
          +setState(String)
          +addObserver(SystemObserver)
      }
      class SystemObserver {
          <<sealed abstract>>
          #VehicleSystem system
          +SystemObserver(VehicleSystem)
          +update()*
      }
      class CockpitObserver {
          +update()
      }
      class EngineObserver {
          +update()
      }
    ```
- **Example Code:**
    ```java
    // 1. Subject
    class VehicleSystem {
        private String state;
        private final List<SystemObserver> observers = new ArrayList<>();

        void setState(String state) {
            this.state = state;
            notifyAllObservers();
        }

        void addObserver(SystemObserver observer) {
            observers.add(observer);
        }

        private void notifyAllObservers() {
            for (var observer : observers) {
                observer.update();
            }
        }

        String getState() {
            return state;
        }
    }

    // 2. Observer Base
    sealed abstract class SystemObserver permits CockpitObserver, EngineObserver {
        protected final VehicleSystem system;

        SystemObserver(VehicleSystem system) {
            this.system = system;
            system.addObserver(this);
        }

        abstract void update();
    }

    // 3. Concrete Observers
    final class CockpitObserver extends SystemObserver {
        CockpitObserver(VehicleSystem system) {
            super(system);
        }

        @Override
        void update() {
            System.out.println("CockpitObserver, temperature:'" + system.getState() + "'");
        }
    }

    final class EngineObserver extends SystemObserver {
        EngineObserver(VehicleSystem system) {
            super(system);
        }

        @Override
        void update() {
            System.out.println("EngineObserver, temperature:'" + system.getState() + "'");
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Observer Pattern, vehicle temperature sensors");
            var temperatureControlSystem = new VehicleSystem();
            new CockpitObserver(temperatureControlSystem);
            new EngineObserver(temperatureControlSystem);
            temperatureControlSystem.setState("low");
        }
    }
    ```
- **Explanation:** `VehicleSystem` is the subject. `CockpitObserver` and `EngineObserver` are observers. When `VehicleSystem.setState()` is called, it notifies all registered observers, calling their `update()` methods.
- **Dry Run:**
    1. `CockpitObserver` and `EngineObserver` are created, registering themselves with the `VehicleSystem`.
    2. `temperatureControlSystem.setState("low")` is called.
    3. `VehicleSystem` notifies all observers.
    4. `CockpitObserver.update()` prints "CockpitObserver, temperature:'low'".
    5. `EngineObserver.update()` prints "EngineObserver, temperature:'low'".

#### 5.10 Pipeline Pattern

- **Motivation:** Processes data through a sequence of stages, where the output of one stage is the input of the next.
- **Class Diagram:**
    ```mermaid
    classDiagram
      PipeElement --> Processor
      Processor <|.. EngineProcessor
      Processor <|.. BreakProcessor
      Processor <|.. TransmissionProcessor
      Element <|.. SystemElement
      class PipeElement~E,R~ {
          -Processor~E,R~ processor
          +addProcessor(Processor) PipeElement
          +process(E) R
      }
      class Processor~I,O~ {
          <<interface>>
          +process(I) O
      }
      class EngineProcessor {
          +process(SystemElement) SystemElement
      }
      class BreakProcessor {
          +process(SystemElement) SystemElement
      }
      class TransmissionProcessor {
          +process(SystemElement) SystemElement
      }
      class Element {
          <<interface>>
          +toString() String
      }
      class SystemElement {
          -List~String~ logs
          +addLog(String)
          +logSummary() String
      }
    ```
- **Example Code:**
    ```java
    // 1. Element Interface
    interface Element {
        String toString();
    }

    // 2. Concrete Element
    class SystemElement implements Element {
        private final List<String> logs = new ArrayList<>();

        void addLog(String log) {
            logs.add(log);
        }

        String logSummary() {
            return String.join(",", logs);
        }
    }

    // 3. Processor Interface
    interface Processor<I extends Element, O extends Element> {
        O process(I inputElement);
    }

    // 4. Concrete Processors
    class EngineProcessor implements Processor<SystemElement, SystemElement> {
        @Override
        public SystemElement process(SystemElement inputElement) {
            inputElement.addLog("engine-system");
            return inputElement;
        }
    }

    class BreakProcessor implements Processor<SystemElement, SystemElement> {
        @Override
        public SystemElement process(SystemElement inputElement) {
            inputElement.addLog("break-system");
            return inputElement;
        }
    }

    class TransmissionProcessor implements Processor<SystemElement, SystemElement> {
        @Override
        public SystemElement process(SystemElement inputElement) {
            inputElement.addLog("transmission-system");
            return inputElement;
        }
    }

    // 5. Pipeline
    class PipeElement<E extends Element, R extends Element> {
        private final Processor<E, R> processor;

        PipeElement(Processor<E, R> processor) {
            this.processor = processor;
        }

        <O extends Element> PipeElement<E, O> addProcessor(Processor<R, O> p) {
            return new PipeElement<>(input -> p.process(processor.process(input)));
        }

        R process(E inputElement) {
            return processor.process(inputElement);
        }
    }

    // 6. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Pipeline Pattern, vehicle turn on states");
            var pipeline = new PipeElement<>(new EngineProcessor())
                    .addProcessor(new BreakProcessor())
                    .addProcessor(new TransmissionProcessor());
            var systemState = pipeline.process(new SystemElement());
            System.out.println(systemState.logSummary());
        }
    }
    ```
- **Explanation:** `PipeElement` chains together `Processor`s. The output of `EngineProcessor` is passed to `BreakProcessor`, and then to `TransmissionProcessor`.
- **Dry Run:**
    1. `pipeline.process(new SystemElement())` is called.
    2. `EngineProcessor` adds "engine-system" to the log.
    3. `BreakProcessor` adds "break-system".
    4. `TransmissionProcessor` adds "transmission-system".
    5. `logSummary()` returns "engine-system,break-system,transmission-system".

#### 5.11 State Pattern

- **Motivation:** Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleState <|-- InitState
      VehicleState <|-- StartState
      VehicleState <|-- StopState
      Vehicle --> VehicleState
      class Vehicle {
          -VehicleState state
          -String type
          +setState(VehicleState)
          +getState() VehicleState
      }
      class VehicleState {
          <<interface>>
          +toString() String
      }
      class InitState {
          -Vehicle vehicle
          +toString() String
      }
      class StartState {
          -Vehicle vehicle
          +toString() String
      }
      class StopState {
          -Vehicle vehicle
          +toString() String
      }
    ```
- **Example Code:**
    ```java
    // 1. State Interface
    interface VehicleState {}

    // 2. Concrete States
    record InitState(Vehicle vehicle) implements VehicleState {
        @Override
        public String toString() {
            return "InitState{vehicle=" + vehicle.type() + "}";
        }
    }

    record StartState(Vehicle vehicle) implements VehicleState {
        @Override
        public String toString() {
            return "StartState{vehicle=" + vehicle.type() + "}";
        }
    }

    record StopState(Vehicle vehicle) implements VehicleState {
        @Override
        public String toString() {
            return "StopState{vehicle=" + vehicle.type() + "}";
        }
    }

    // 3. Context
    record Vehicle(String type, VehicleState state) {
        Vehicle setState(VehicleState state) {
            return new Vehicle(type, state);
        }

        VehicleState getState() {
            return state;
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("State Pattern, vehicle turn on states");
            var initState = new InitState(null);
            var startState = new StartState(null);
            var stopState = new StopState(null);
            Vehicle vehicle = new Vehicle("truck", initState);
            System.out.println("Vehicle state2:" + vehicle.getState());
            vehicle = vehicle.setState(startState);
            System.out.println("Vehicle state3:" + vehicle.getState());
            vehicle = vehicle.setState(stopState);
            System.out.println("Vehicle state4:" + vehicle.getState());
        }
    }
    ```
- **Explanation:** The `Vehicle` object can change its state. The behavior of the `Vehicle` (in this case, its `toString` representation) depends on its current state.
- **Dry Run:**
    1. A `Vehicle` is created with `InitState`.
    2. `vehicle.getState()` prints "InitState{vehicle=truck}".
    3. The state is changed to `StartState`. `getState()` prints "StartState{vehicle=truck}".
    4. The state is changed to `StopState`. `getState()` prints "StopState{vehicle=truck}".

#### 5.12 Strategy Pattern

- **Motivation:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
- **Class Diagram:**
    ```mermaid
    classDiagram
      TransportStrategy <|.. CarStrategy
      TransportStrategy <|.. BusStrategy
      TransportStrategy <|.. TruckStrategy
      VehicleDriver --> TransportStrategy
      class TransportStrategy {
          <<interface>>
          +transport()
      }
      class CarStrategy {
          +transport()
      }
      class BusStrategy {
          +transport()
      }
      class TruckStrategy {
          +transport()
      }
      class VehicleDriver {
          -TransportStrategy strategy
          +VehicleDriver(TransportStrategy)
          +changeStrategy(TransportStrategy)
          +transport()
      }
    ```
- **Example Code:**
    ```java
    // 1. Strategy Interface
    interface TransportStrategy {
        void transport();
    }

    // 2. Concrete Strategies
    class CarStrategy implements TransportStrategy {
        @Override
        public void transport() {
            System.out.println("Car, four persons transport");
        }
    }

    class BusStrategy implements TransportStrategy {
        @Override
        public void transport() {
            System.out.println("Bus, whole crew transport");
        }
    }

    class TruckStrategy implements TransportStrategy {
        @Override
        public void transport() {
            System.out.println("Truck, transporting heavy load");
        }
    }

    // 3. Context
    class VehicleDriver {
        private TransportStrategy strategy;

        VehicleDriver(TransportStrategy strategy) {
            this.strategy = strategy;
        }

        void changeStrategy(TransportStrategy strategy) {
            this.strategy = strategy;
        }

        void transport() {
            strategy.transport();
        }
    }

    // 4. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Strategy Pattern, changing transport options");
            var driver = new VehicleDriver(new CarStrategy());
            driver.transport();
            driver.changeStrategy(new BusStrategy());
            driver.transport();
            driver.changeStrategy(new TruckStrategy());
            driver.transport();
        }
    }
    ```
- **Explanation:** The `VehicleDriver` context can change its transport behavior by setting a different `TransportStrategy` at runtime.
- **Dry Run:**
    1. `driver.transport()` with `CarStrategy` prints "Car, four persons transport".
    2. `driver.changeStrategy(new BusStrategy())` changes the strategy.
    3. `driver.transport()` prints "Bus, whole crew transport".
    4. `driver.changeStrategy(new TruckStrategy())` changes the strategy again.
    5. `driver.transport()` prints "Truck, transporting heavy load".

#### 5.13 Template Pattern

- **Motivation:** Defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Subclasses can redefine certain steps of an algorithm without changing its structure.
- **Class Diagram:**
    ```mermaid
    classDiagram
      VehicleSensor <|-- BreaksSensor
      VehicleSensor <|-- EngineSensor
      class VehicleSensor {
          <<sealed abstract>>
          +activate()
          +init()*
          +startMeasure()*
          +storeData()*
          +stopMeasure()*
      }
      class BreaksSensor {
          +init()
          +startMeasure()
          +storeData()
          +stopMeasure()
      }
      class EngineSensor {
          +init()
          +startMeasure()
          +storeData()
          +stopMeasure()
      }
    ```
- **Example Code:**
    ```java
    // 1. Abstract Template
    abstract sealed class VehicleSensor permits BreaksSensor, EngineSensor {
        abstract void init();
        abstract void startMeasure();
        abstract void storeData();
        abstract void stopMeasure();

        final void activate() {
            init();
            startMeasure();
            storeData();
            stopMeasure();
        }
    }

    // 2. Concrete Implementations
    final class BreaksSensor extends VehicleSensor {
        @Override
        void init() {
            System.out.println("BreaksSensor, initiated");
        }

        @Override
        void startMeasure() {
            System.out.println("BreaksSensor, measurement started");
        }

        @Override
        void storeData() {
            System.out.println("BreaksSensor, data stored");
        }

        @Override
        void stopMeasure() {
            System.out.println("BreaksSensor, measurement stopped");
        }
    }

    final class EngineSensor extends VehicleSensor {
        @Override
        void init() {
            System.out.println("EngineSensor, initiated");
        }

        @Override
        void startMeasure() {
            System.out.println("EngineSensor, measurement started");
        }

        @Override
        void storeData() {
            System.out.println("EngineSensor, data stored");
        }

        @Override
        void stopMeasure() {
            System.out.println("EngineSensor, measurement stopped");
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Template method Pattern, changing transport options");
            Arrays.asList(new BreaksSensor(), new EngineSensor())
                    .forEach(VehicleSensor::activate);
        }
    }
    ```
- **Explanation:** `VehicleSensor` defines the template method `activate()` which calls the abstract methods in a specific order. Subclasses provide the implementation for each step.
- **Dry Run:**
    1. `BreaksSensor.activate()` is called.
    2. `init()` prints "BreaksSensor, initiated".
    3. `startMeasure()` prints "BreaksSensor, measurement started".
    4. `storeData()` prints "BreaksSensor, data stored".
    5. `stopMeasure()` prints "BreaksSensor, measurement stopped".
    6. The same sequence is repeated for `EngineSensor`.

#### 5.14 Visitor Pattern

- **Motivation:** Represents an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.
- **Class Diagram:**
    ```mermaid
    classDiagram
      SystemCheck <|.. BreakCheck
      SystemCheck <|.. EngineCheck
      SystemCheck <|.. SuspensionCheck
      VehicleCheck <|.. VehicleSystemCheckVisitor
      VehicleCheck *-- SystemCheck
      CheckVisitor <|.. VehicleSystemCheckVisitor
      class SystemCheck {
          <<sealed interface>>
          +accept(CheckVisitor)
      }
      class BreakCheck {
          +accept(CheckVisitor)
      }
      class EngineCheck {
          +accept(CheckVisitor)
      }
      class SuspensionCheck {
          +accept(CheckVisitor)
      }
      class CheckVisitor {
          <<interface>>
          +visit(BreakCheck)
          +visit(EngineCheck)
          +visit(SuspensionCheck)
      }
      class VehicleSystemCheckVisitor {
          +visit(BreakCheck)
          +visit(EngineCheck)
          +visit(SuspensionCheck)
      }
      class VehicleCheck {
          -List~SystemCheck~ parts
          +accept(CheckVisitor)
      }
    ```
- **Example Code:**
    ```java
    // 1. Visitor Interface
    interface CheckVisitor {
        void visit(BreakCheck breakCheck);
        void visit(EngineCheck engineCheck);
        void visit(SuspensionCheck suspensionCheck);
    }

    // 2. Element Interfaces
    sealed interface SystemCheck permits BreakCheck, EngineCheck, SuspensionCheck {
        void accept(CheckVisitor visitor);
    }

    // 3. Concrete Elements
    record BreakCheck() implements SystemCheck {
        @Override
        public void accept(CheckVisitor visitor) {
            visitor.visit(this);
        }
    }

    record EngineCheck() implements SystemCheck {
        @Override
        public void accept(CheckVisitor visitor) {
            visitor.visit(this);
        }
    }

    record SuspensionCheck() implements SystemCheck {
        @Override
        public void accept(CheckVisitor visitor) {
            visitor.visit(this);
        }
    }

    // 4. Concrete Visitor
    class VehicleSystemCheckVisitor implements CheckVisitor {
        @Override
        public void visit(BreakCheck breakCheck) {
            System.out.println("BreakCheck, ready");
            visitBySwitch(breakCheck);
        }

        @Override
        public void visit(EngineCheck engineCheck) {
            System.out.println("EngineCheck, ready");
            visitBySwitch(engineCheck);
        }

        @Override
        public void visit(SuspensionCheck suspensionCheck) {
            System.out.println("SuspensionCheck, ready");
            visitBySwitch(suspensionCheck);
        }

        private void visitBySwitch(SystemCheck systemCheck) {
            switch (systemCheck) {
                case EngineCheck e -> System.out.println("EngineCheck, ready, double-check," + e);
                case BreakCheck b -> System.out.println("BreakCheck, ready, double-check," + b);
                case SuspensionCheck s -> System.out.println("SuspensionCheck, ready, double-check," + s);
                default -> System.out.println("VehicleSystemCheckVisitor, not implemented");
            }
        }
    }

    // 5. Object Structure
    class VehicleCheck {
        private final List<SystemCheck> parts = new ArrayList<>();

        VehicleCheck() {
            parts.add(new BreakCheck());
            parts.add(new EngineCheck());
            parts.add(new SuspensionCheck());
        }

        void accept(CheckVisitor visitor) {
            for (SystemCheck part : parts) {
                part.accept(visitor);
            }
            System.out.println("VehicleCheck, ready");
            System.out.println("VehicleCheck, ready, double-check," + this);
        }
    }

    // 6. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Visitor Pattern, check vehicle parts");
            var vehicleCheck = new VehicleCheck();
            vehicleCheck.accept(new VehicleSystemCheckVisitor());
        }
    }
    ```
- **Explanation:** The `VehicleCheck` class contains a list of `SystemCheck` objects. The `VehicleSystemCheckVisitor` visits each `SystemCheck` and performs an operation (`visit`). This allows adding new operations without modifying the `SystemCheck` classes.
- **Dry Run:**
    1. `vehicleCheck.accept(new VehicleSystemCheckVisitor())` is called.
    2. For each part (BreakCheck, EngineCheck, SuspensionCheck), the `accept` method is called, which in turn calls the appropriate `visit` method on the visitor.
    3. Each `visit` method prints the part's status and then calls `visitBySwitch` for a double-check.
    4. Finally, `VehicleCheck` prints its own status.

---

### Chapter 6: Concurrency Design Patterns

#### 6.1 Active Object Pattern

- **Motivation:** Decouples method execution from method invocation by having each object run in its own thread.
- **Class Diagram:**
    ```mermaid
    classDiagram
      MovingVehicle <|-- SportVehicle
      MovingVehicle --> BlockingDeque~Runnable~
      class MovingVehicle {
          -BlockingDeque~Runnable~ commands
          -String type
          -Thread thread
          -boolean active
          +MovingVehicle(String)
          +move()
          +turnOnRadio()
          +turnOffRadio()
          +stopVehicle()
          +isMoving() boolean
          -createMovementThread() Thread
      }
      class SportVehicle {
          +SportVehicle(String)
          +move()
          +turnOnRadio()
          +turnOffRadio()
          +stopVehicle()
          +isMoving() boolean
      }
    ```
- **Example Code:**
    ```java
    // 1. Active Object
    abstract class MovingVehicle {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final BlockingDeque<Runnable> commands;
        private final String type;
        private final Thread thread;
        private boolean active;

        MovingVehicle(String type) {
            this.commands = new LinkedBlockingDeque<>();
            this.type = type;
            this.thread = createMovementThread();
            this.active = true;
            thread.start();
        }

        private Thread createMovementThread() {
            var thread = new Thread(() -> {
                while (active) {
                    try {
                        var command = commands.take();
                        command.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.setName("moving-vehicle-" + COUNTER.getAndIncrement());
            return thread;
        }

        void move() throws InterruptedException {
            commands.putLast(() -> {
                System.out.println("MovingVehicle:'" + type + "', moving");
                active = true;
            });
        }

        void turnOnRadio() throws InterruptedException {
            commands.putLast(() -> System.out.println("MovingVehicle:'" + type + "', radio on"));
        }

        void turnOffRadio() throws InterruptedException {
            commands.putLast(() -> System.out.println("MovingVehicle:'" + type + "', radio off"));
        }

        void stopVehicle() throws InterruptedException {
            commands.putFirst(() -> {
                System.out.println("MovingVehicle:'" + type + "', stopping, commands_active:'" + commands.size() + "'");
                active = false;
                System.out.println("MovingVehicle:'" + type + "', stopped");
            });
        }

        boolean isMoving() {
            return active;
        }
    }

    // 2. Concrete Active Object
    class SportVehicle extends MovingVehicle {
        SportVehicle(String type) {
            super(type);
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) throws Exception {
            System.out.println("Active Object Pattern, moving vehicle");
            var sportVehicle = new SportVehicle("super_sport");
            sportVehicle.move();
            sportVehicle.turnOnRadio();
            sportVehicle.turnOffRadio();
            sportVehicle.turnOnRadio();
            sportVehicle.stopVehicle();
            sportVehicle.turnOffRadio();
            TimeUnit.MILLISECONDS.sleep(400);
            System.out.println("ActiveObjectMain, sportVehicle moving:" + sportVehicle.isMoving());
        }
    }
    ```
- **Explanation:** `MovingVehicle` has an internal thread that processes commands from a blocking deque. The `move`, `turnOnRadio`, etc. methods add commands to the deque, allowing the main thread to continue.
- **Dry Run:**
    1. `SportVehicle` is created, starting its internal thread.
    2. Commands are added to the deque: `move`, `turnOnRadio`, `turnOffRadio`, `turnOnRadio`, `stopVehicle`, `turnOffRadio`.
    3. The internal thread processes these commands in order.
    4. The `stopVehicle` command is added to the front of the deque, giving it priority.
    5. After all commands are processed, `isMoving()` returns `false`.

#### 6.2 Async Method Invocation Pattern

- **Motivation:** Allows a method to be invoked asynchronously, so the caller doesn't block waiting for the result. A callback is often used to handle the result.
- **Class Diagram:**
    ```mermaid
    classDiagram
      SensorExecutor <|.. TempSensorExecutor
      SensorCallback <|.. TempSensorCallback
      SensorResult <|.. TempSensorResult
      Callable <|.. TempSensorTask
      TempSensorExecutor --> TempSensorTask
      TempSensorExecutor --> TempSensorCallback
      TempSensorExecutor --> TempSensorResult
      class SensorExecutor~T~ {
          <<interface>>
          +measure(Callable~T~, SensorCallback~T~) SensorResult~T~
          +start()
          +stopMeasurement(SensorResult~T~) T
      }
      class TempSensorExecutor~T~ {
          +measure(Callable~T~, SensorCallback~T~) SensorResult~T~
          +start()
          +stopMeasurement(SensorResult~T~) T
      }
      class SensorCallback~T~ {
          <<interface>>
          +onResult(T)
      }
      class TempSensorCallback {
          +onResult(Integer)
      }
      class SensorResult~T~ {
          <<interface>>
          +getResult() T
          +addException(Exception)
          +setResult(T)
      }
      class TempSensorResult~T~ {
          -T result
          -SensorCallback~T~ callback
          +getResult() T
          +addException(Exception)
          +setResult(T)
      }
      class TempSensorTask {
          +call() Integer
      }
    ```
- **Example Code:**
    ```java
    // 1. Callback Interface
    interface SensorCallback<T> {
        void onResult(T result);
    }

    // 2. Result Interface
    interface SensorResult<T> {
        T getResult() throws Exception;
        void addException(Exception e);
        void setResult(T result);
    }

    // 3. Executor Interface
    interface SensorExecutor<T> {
        SensorResult<T> measure(Callable<T> sensor, SensorCallback<T> callback);
        void start();
        T stopMeasurement(SensorResult<T> result) throws Exception;
    }

    // 4. Concrete Executor
    class TempSensorExecutor<T> implements SensorExecutor<T> {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final List<Thread> threads = new ArrayList<>();
        private volatile boolean running;

        @Override
        public SensorResult<T> measure(Callable<T> sensor, SensorCallback<T> callback) {
            var result = new TempSensorResult<T>(callback);
            Runnable runnable = () -> {
                try {
                    result.setResult(sensor.call());
                } catch (Exception e) {
                    result.addException(e);
                }
            };
            var thread = new Thread(runnable, "thread-" + COUNTER.getAndIncrement());
            thread.setDaemon(true);
            threads.add(thread);
            return result;
        }

        @Override
        public void start() {
            running = true;
            for (var thread : threads) {
                thread.start();
            }
            System.out.println("SensorTaskExecutor, started:" + threads.size());
        }

        @Override
        public T stopMeasurement(SensorResult<T> result) throws Exception {
            running = false;
            return result.getResult();
        }
    }

    // 5. Concrete Result
    class TempSensorResult<T> implements SensorResult<T> {
        private T result;
        private Exception exception;
        private final SensorCallback<T> callback;

        TempSensorResult(SensorCallback<T> callback) {
            this.callback = callback;
        }

        @Override
        public synchronized T getResult() throws Exception {
            if (exception != null) {
                throw exception;
            }
            return result;
        }

        @Override
        public synchronized void addException(Exception e) {
            this.exception = e;
        }

        @Override
        public synchronized void setResult(T result) {
            this.result = result;
            callback.onResult(result);
        }
    }

    // 6. Concrete Callback
    class TempSensorCallback implements SensorCallback<Integer> {
        @Override
        public void onResult(Integer result) {
            System.out.println("TemperatureSensorCallback, recorded value:'" + result + "', thread:'" + Thread.currentThread() + "'");
        }
    }

    // 7. Concrete Task
    class TempSensorTask implements Callable<Integer> {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final int n;

        TempSensorTask() {
            this.n = COUNTER.getAndIncrement();
        }

        @Override
        public Integer call() throws Exception {
            var result = new Random().nextInt(50);
            System.out.println("TempSensorTask,n:'" + n + "' temp:'" + result + "', thread:'" + Thread.currentThread() + "'");
            return result;
        }
    }

    // 8. Client Code
    public class Main {
        public static void main(String[] args) throws Exception {
            System.out.println("Async method invocation Pattern, moving vehicle");
            var sensorTaskExecutor = new TempSensorExecutor<Integer>();
            var tempSensorCallback = new TempSensorCallback();
            var tasksNumber = 5;
            var measurements = new ArrayList<SensorResult<Integer>>();
            System.out.printf("AsyncMethodMain, tasksNumber:'%d' %n", tasksNumber);
            for (int i = 0; i < tasksNumber; i++) {
                var sensorResult = sensorTaskExecutor.measure(new TempSensorTask(), tempSensorCallback);
                measurements.add(sensorResult);
            }
            sensorTaskExecutor.start();
            for (int i = 0; i < tasksNumber; i++) {
                var temp = sensorTaskExecutor.stopMeasurement(measurements.get(i));
                System.out.printf("AsyncMethodMain, sensor:'%d' temp:'%d'%n", i, temp);
            }
        }
    }
    ```
- **Explanation:** `TempSensorExecutor` measures temperature asynchronously using multiple threads. The `TempSensorCallback` handles the result when it's available. The main thread is not blocked and continues its work.
- **Dry Run:**
    1. Five `TempSensorTask` objects are created and sent to the executor.
    2. `sensorTaskExecutor.start()` starts all threads.
    3. Each thread runs a `TempSensorTask`, which generates a random number and sets the result in `TempSensorResult`.
    4. When the result is set, the `TempSensorCallback`'s `onResult` method is called, printing the result.
    5. The main thread calls `stopMeasurement` for each task, which waits for the result (by calling `getResult`) and prints it.

#### 6.3 Balking Pattern

- **Motivation:** Prevents an action from being executed if the object is in an inappropriate state.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Vehicle --> VehicleState
      class VehicleState {
          <<enum>>
          MOVING
          STOPPED
      }
      class Vehicle {
          -VehicleState state
          -int mills
          +drive()
          +stop()
          +getState() VehicleState
          +driveWithMills(int)
          +startEngineAndMove(int)
          +init()
      }
    ```
- **Example Code:**
    ```java
    // 1. State Enum
    enum VehicleState {
        MOVING, STOPPED
    }

    // 2. Context
    class Vehicle {
        private VehicleState state = VehicleState.STOPPED;
        private int mills;

        synchronized void driveWithMills(int mills) throws InterruptedException {
            var internalState = getState();
            switch (internalState) {
                case MOVING -> System.out.printf("Vehicle state:'%s', vehicle in move, millis:'%d', thread='%s'%n",
                        state, mills, Thread.currentThread());
                case STOPPED -> startEngineAndMove(mills);
                case null -> init();
            }
        }

        synchronized void stop() {
            this.state = VehicleState.STOPPED;
            System.out.printf("Vehicle state:'%s' stopped, mills:'%d', thread='%s'%n",
                    state, mills, Thread.currentThread());
        }

        synchronized VehicleState getState() {
            return state;
        }

        private void startEngineAndMove(int mills) throws InterruptedException {
            this.state = VehicleState.MOVING;
            this.mills = mills;
            System.out.printf("Vehicle state:'%s', moving, mills:'%d', thread='%s'%n",
                    state, mills, Thread.currentThread());
            TimeUnit.MILLISECONDS.sleep(mills);
            stop();
        }

        private void init() {
            this.state = VehicleState.STOPPED;
        }

        void drive() throws InterruptedException {
            var mills = new Random().nextInt(50) + 50;
            driveWithMills(mills);
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) throws Exception {
            System.out.println("Balking pattern, vehicle move");
            var vehicle = new Vehicle();
            var numberOfDrivers = 5;
            var executors = Executors.newFixedThreadPool(2);
            for (int i = 0; i < numberOfDrivers; i++) {
                executors.submit(vehicle::drive);
            }
            TimeUnit.MILLISECONDS.sleep(1000);
            executors.shutdown();
            System.out.println("Done");
        }
    }
    ```
- **Explanation:** The `Vehicle` has a state (`MOVING` or `STOPPED`). `driveWithMills` checks the state. If it's `MOVING`, it bails out (doesn't do anything). If it's `STOPPED`, it starts the engine and moves.
- **Dry Run:**
    1. Five threads call `vehicle.drive()`. Only one can acquire the lock.
    2. The first thread starts the engine and moves (`startEngineAndMove`).
    3. Other threads are blocked because the method is `synchronized`.
    4. After the first thread stops, another thread can acquire the lock and start moving.
    5. This process repeats. The output shows the interleaving of "moving" and "stopped" states.

#### 6.4 Double-Checked Locking Pattern

- **Motivation:** A thread-safe way to implement lazy initialization of a singleton in a multi-threaded environment, minimizing synchronization overhead.
- **Class Diagram:**
    ```mermaid
    classDiagram
      class VehicleSingleton {
          -VehicleSingleton INSTANCE$
          -VehicleSingleton()
          +getInstance() VehicleSingleton$
      }
      class VehicleSingletonChecked {
          -VehicleSingletonChecked INSTANCE$
          -VehicleSingletonChecked()
          +getInstance() VehicleSingletonChecked$
      }
    ```
- **Example Code:**
    ```java
    // 1. Non-thread-safe Singleton
    class VehicleSingleton {
        private static VehicleSingleton INSTANCE;

        private VehicleSingleton() {
            System.out.println("VehicleSingleton, constructor thread:'" + Thread.currentThread() + "' hashCode:'" + this.hashCode() + "'");
        }

        public static VehicleSingleton getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new VehicleSingleton();
            }
            return INSTANCE;
        }
    }

    // 2. Thread-safe Singleton with Double-Checked Locking
    class VehicleSingletonChecked {
        private static volatile VehicleSingletonChecked INSTANCE;

        private VehicleSingletonChecked() {
            System.out.println("VehicleSingletonChecked, constructor thread:'" + Thread.currentThread() + "' hashCode:'" + this.hashCode() + "'");
        }

        public static VehicleSingletonChecked getInstance() {
            if (INSTANCE == null) {
                synchronized (VehicleSingletonChecked.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new VehicleSingletonChecked();
                    }
                }
            }
            return INSTANCE;
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) {
            System.out.println("Double checked locking pattern, only one vehicle");
            var amount = 5;
            ExecutorService executor = Executors.newFixedThreadPool(amount);
            System.out.println("Number of executors:" + amount);
            for (int i = 0; i < amount; i++) {
                executor.submit(VehicleSingleton::getInstance);
                executor.submit(VehicleSingletonChecked::getInstance);
            }
            executor.shutdown();
        }
    }
    ```
- **Explanation:** `VehicleSingleton` is not thread-safe; multiple threads can create multiple instances. `VehicleSingletonChecked` uses double-checked locking with a `volatile` variable to ensure only one instance is created in a thread-safe manner.
- **Dry Run:**
    1. Five threads call `VehicleSingleton.getInstance()`. Due to race conditions, multiple instances may be created.
    2. Five threads call `VehicleSingletonChecked.getInstance()`. The double-checked locking ensures only one instance is created.

#### 6.5 Read-Write Lock Pattern

- **Motivation:** Allows multiple threads to read a shared resource concurrently, but only one thread can write at a time, and writes are exclusive.
- **Class Diagram:**
    ```mermaid
    classDiagram
      Sensor --> ReadLock
      Sensor --> WriteLock
      SensorWriter --> Sensor
      SensorReader --> Sensor
      class Sensor {
          -int value
          -ReadLock readLock
          -WriteLock writeLock
          +getValue() int
          +writeValue(int)
      }
      class SensorWriter {
          -String type
          -Sensor sensor
          +run()
      }
      class SensorReader {
          -String type
          -Sensor sensor
          -int cycles
          +run()
      }
    ```
- **Example Code:**
    ```java
    // 1. Shared Resource
    class Sensor {
        private int value;
        private final ReadLock readLock;
        private final WriteLock writeLock;

        Sensor(ReadLock readLock, WriteLock writeLock) {
            this.readLock = readLock;
            this.writeLock = writeLock;
        }

        int getValue() {
            readLock.lock();
            try {
                return value;
            } finally {
                readLock.unlock();
            }
        }

        void writeValue(int v) {
            writeLock.lock();
            try {
                this.value = v;
                System.out.println("SensorWriter write, value:'" + v + "', thread:'" + Thread.currentThread() + "'");
            } finally {
                writeLock.unlock();
            }
        }
    }

    // 2. Reader Task
    class SensorReader implements Runnable {
        private final String type;
        private final Sensor sensor;
        private final int cycles;

        SensorReader(String type, Sensor sensor, int cycles) {
            this.type = type;
            this.sensor = sensor;
            this.cycles = cycles;
        }

        @Override
        public void run() {
            for (int i = 0; i < cycles; i++) {
                var value = sensor.getValue();
                System.out.println("SensorReader read, type:'" + type + "', value:'" + value + "', thread:'" + Thread.currentThread() + "'");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 3. Writer Task
    class SensorWriter implements Runnable {
        private final String type;
        private final Sensor sensor;

        SensorWriter(String type, Sensor sensor) {
            this.type = type;
            this.sensor = sensor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                var newValue = new Random().nextInt(100);
                sensor.writeValue(newValue);
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 4. Client Code
    public class Main {
        private static final int NUMBER_READERS = 3;
        private static final int CYCLES_READER = 10;

        public static void main(String[] args) throws Exception {
            System.out.println("Read-Write Lock pattern, writing and reading sensor values");
            ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            var sensor = new Sensor(readWriteLock.readLock(), readWriteLock.writeLock());
            var sensorWriter = new SensorWriter("writer-1", sensor);
            var writerThread = new Thread(sensorWriter, "pool-2-writer-1");
            ExecutorService executor = Executors.newFixedThreadPool(NUMBER_READERS);
            var readers = IntStream.range(0, NUMBER_READERS)
                    .boxed().map(i -> new SensorReader("reader-" + i, sensor, CYCLES_READER)).toList();
            readers.forEach(executor::submit);
            writerThread.start();
            executor.shutdown();
        }
    }
    ```
- **Explanation:** `Sensor` uses a `ReadWriteLock`. Multiple readers can read concurrently. The writer has exclusive access when writing, blocking all readers.
- **Dry Run:**
    1. Three reader threads start reading the sensor value.
    2. The writer thread starts.
    3. When the writer acquires the write lock, all readers are blocked.
    4. After the writer releases the lock, the readers can continue.

#### 6.6 Producer-Consumer Pattern

- **Motivation:** Decouples the production of data from its consumption. Producers add data to a buffer, and consumers take data from the buffer. This allows producers and consumers to operate at different rates.
- **Class Diagram:**
    ```mermaid
    classDiagram
      EventsContainer --> Event
      EventProducer --> EventsContainer
      EventConsumer --> EventsContainer
      class EventsContainer {
          -BlockingQueue~Event~ events
          +add(Event) boolean
          +get() Event
          +size() int
      }
      class Event {
          -int number
          -String source
      }
      class EventProducer {
          -EventsContainer container
          +run()
      }
      class EventConsumer {
          -int number
          -EventsContainer container
          +run()
      }
    ```
- **Example Code:**
    ```java
    // 1. Event
    record Event(int number, String source) {}

    // 2. Buffer
    class EventsContainer {
        private final BlockingQueue<Event> events;

        EventsContainer(int capacity) {
            this.events = new ArrayBlockingQueue<>(capacity);
        }

        boolean add(Event event) throws InterruptedException {
            return events.offer(event, 10, TimeUnit.MILLISECONDS);
        }

        Event get() throws InterruptedException {
            return events.poll(10, TimeUnit.MILLISECONDS);
        }

        int size() {
            return events.size();
        }
    }

    // 3. Producer
    class EventProducer implements Runnable {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final EventsContainer container;

        EventProducer(EventsContainer container) {
            this.container = container;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    var event = new Event(COUNTER.getAndIncrement(), Thread.currentThread().getName());
                    container.add(event);
                    System.out.println("EventProducer, event created:" + event);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 4. Consumer
    class EventConsumer implements Runnable {
        private final int number;
        private final EventsContainer container;

        EventConsumer(int number, EventsContainer container) {
            this.number = number;
            this.container = container;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    var event = container.get();
                    if (event == null) break;
                    System.out.println("VehicleSecurityConsumer,event:'" + event + "', number:'" + number + "', thread:'" + Thread.currentThread() + "'");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 5. Client Code
    public class Main {
        public static void main(String[] args) throws Exception {
            System.out.println("Producer-Consumer pattern, decoupling receivers and emitters");
            var producersNumber = 12;
            var consumersNumber = 10;
            var container = new EventsContainer(3);
            ExecutorService producerExecutor = Executors.newFixedThreadPool(4, new ProdConThreadFactory("prod"));
            ExecutorService consumersExecutor = Executors.newFixedThreadPool(2, new ProdConThreadFactory("con"));
            IntStream.range(0, producersNumber)
                    .boxed().map(i -> new EventProducer(container))
                    .forEach(producerExecutor::submit);
            IntStream.range(0, consumersNumber)
                    .boxed().map(i -> new EventConsumer(i, container))
                    .forEach(consumersExecutor::submit);
            TimeUnit.MILLISECONDS.sleep(200);
            producerExecutor.shutdown();
            consumersExecutor.shutdown();
        }
    }
    ```
- **Explanation:** `EventProducer` adds `Event` objects to the `EventsContainer` (a bounded queue). `EventConsumer` takes events from the queue and processes them. The queue decouples the producers and consumers.
- **Dry Run:**
    1. Producers are submitted to their executor. They generate events and add them to the queue.
    2. Consumers are submitted to their executor. They take events from the queue and process them.
    3. The output shows events being produced and consumed concurrently.

#### 6.7 Scheduler Pattern

- **Motivation:** Schedules tasks to be executed at a specific time or at regular intervals.
- **Class Diagram:**
    ```mermaid
    classDiagram
      CustomScheduler --> SensorTask
      CustomScheduledThreadPoolExecutor --> SensorTask
      class CustomScheduler {
          -int intervalMills
          -BlockingQueue~Runnable~ queue
          -Thread thread
          -boolean active
          +CustomScheduler(int)
          +run()
          +addTask(Runnable)
          +stop()
      }
      class SensorTask {
          -String type
          -long activeTime
          +run()
      }
      class CustomScheduledThreadPoolExecutor {
          +CustomScheduledThreadPoolExecutor(int)
          +scheduleAtFixedRate(Runnable, long, long, TimeUnit)
          +shutdown()
      }
    ```
- **Example Code:**
    ```java
    // 1. Custom Scheduler
    class CustomScheduler {
        private final int intervalMills;
        private final BlockingQueue<Runnable> queue;
        private final Thread thread;
        private boolean active;

        CustomScheduler(int intervalMillis) {
            this.intervalMills = intervalMillis;
            this.queue = new ArrayBlockingQueue<>(10);
            this.thread = new Thread(() -> {
                while (active) {
                    try {
                        var runnable = queue.poll(intervalMillis, TimeUnit.MILLISECONDS);
                        if (runnable != null) {
                            runnable.run();
                            var delay = intervalMillis - ((SensorTask) runnable).activeTime();
                            TimeUnit.MILLISECONDS.sleep(delay);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("CustomScheduler, stopped");
            }, "scheduler-1");
            this.active = true;
        }

        void run() {
            thread.start();
        }

        void addTask(Runnable task) {
            queue.add(task);
        }

        void stop() {
            this.active = false;
        }
    }

    // 2. Task
    class SensorTask implements Runnable {
        private final String type;
        private long activeTime;

        SensorTask(String type) {
            this.type = type;
        }

        @Override
        public void run() {
            this.activeTime = System.currentTimeMillis() % 100;
            System.out.println("SensorTask, type:'" + type + "', activeTime:'" + activeTime + "', thread:'" + Thread.currentThread() + "'");
        }

        long activeTime() {
            return activeTime;
        }
    }

    // 3. Client Code (Custom Scheduler)
    public class MainCustom {
        public static void main(String[] args) throws Exception {
            System.out.println("Scheduler pattern, providing sensor values");
            var scheduler = new CustomScheduler(100);
            scheduler.run();
            for (int i = 0; i < 15; i++) {
                scheduler.addTask(new SensorTask("temperature-" + i));
            }
            TimeUnit.SECONDS.sleep(1);
            scheduler.stop();
        }
    }

    // 4. Client Code (JDK Scheduled Thread Pool)
    public class MainPooled {
        public static void main(String[] args) throws Exception {
            System.out.println("Pooled scheduler pattern, providing sensor values");
            var pool = new CustomScheduledThreadPoolExecutor(2);
            for (int i = 0; i < 4; i++) {
                pool.scheduleAtFixedRate(new SensorTask("temperature-" + i), 0, 50, TimeUnit.MILLISECONDS);
            }
            TimeUnit.MILLISECONDS.sleep(200);
            pool.shutdown();
        }
    }
    ```
- **Explanation:** Two scheduler implementations are shown. `CustomScheduler` uses a single thread and a `BlockingQueue` to run tasks at a fixed interval. `CustomScheduledThreadPoolExecutor` uses the JDK's `ScheduledThreadPoolExecutor` to schedule tasks at a fixed rate.
- **Dry Run (Custom Scheduler):**
    1. The scheduler starts its internal thread.
    2. Tasks are added to the queue.
    3. The internal thread polls for tasks, runs them, and then sleeps for the remaining time in the interval.
    4. The scheduler stops after 1 second.
- **Dry Run (Pooled Scheduler):**
    1. A `CustomScheduledThreadPoolExecutor` with 2 threads is created.
    2. Four tasks are scheduled to run every 50 ms.
    3. After 200 ms, the pool is shut down.

#### 6.8 Thread-Pool Pattern

- **Motivation:** Uses a pool of threads to execute tasks, avoiding the overhead of creating a new thread for each task.
- **Class Diagram:**
    ```mermaid
    classDiagram
      SensorWorker --> TemperatureTask
      TemperatureTask <|.. SensorWorker
      class SensorWorker {
          -TemperatureTask task
          +run()
      }
      class TemperatureTask {
          -String type
          -int temp
          +measure()
      }
    ```
- **Example Code:**
    ```java
    // 1. Task
    class TemperatureTask {
        private final String type;
        private int temp;

        TemperatureTask(String type) {
            this.type = type;
        }

        void measure() throws InterruptedException {
            this.temp = new Random().nextInt(50);
            System.out.println("TemperatureTask, type:'" + type + "', temp:'" + temp + "', thread:'" + Thread.currentThread() + "'");
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    // 2. Worker
    class SensorWorker implements Runnable {
        private final TemperatureTask task;

        SensorWorker(TemperatureTask task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                task.measure();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 3. Client Code
    public class Main {
        public static void main(String[] args) throws Exception {
            System.out.println("Thread-Pool pattern, providing sensor values");
            var executor = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 15; i++) {
                var task = new TemperatureTask("temp" + i);
                var worker = new SensorWorker(task);
                executor.submit(worker);
            }
            executor.shutdown();
        }
    }
    ```
- **Explanation:** A fixed thread pool of size 5 is created using `Executors.newFixedThreadPool(5)`. Fifteen `SensorWorker` tasks are submitted. The pool executes these tasks using its 5 threads, reusing them for multiple tasks.
- **Dry Run:**
    1. A thread pool with 5 threads is created.
    2. 15 `SensorWorker` tasks are submitted.
    3. The pool assigns tasks to its threads. A thread executes one task, then moves to the next.
    4. The output shows tasks being executed by the 5 threads.

---

### Chapter 7: Understanding Common Anti-Patterns

This chapter focuses on identifying and avoiding bad practices. The "examples" are illustrations of these anti-patterns.

#### 7.1 Code Smell Anti-Patterns

**Example 7.1: Autoboxing Issue**

- **Description:** Shows how autoboxing can cause excessive garbage collection.
- **Code:**
    ```java
    record Sensor(int value) {}

    class SensorAlarmWorker implements Runnable {
        private boolean active = true;
        private final Map<Integer, Sensor> provider;

        SensorAlarmWorker(Map<Integer, Sensor> provider) {
            this.provider = provider;
        }

        @Override
        public void run() {
            while (active) {
                Collection<Sensor> set = provider.values();
                for (Sensor e : set) {
                    SensorAlarmSystemUtil.evaluateAlarm(provider, e.value(), System.currentTimeMillis());
                }
            }
        }
    }

    class SensorAlarmSystemUtil {
        static void evaluateAlarm(Map<Integer, Sensor> storage, Integer criticalValue, long measurementNumber) {
            // ...
        }
    }
    ```
- **Explanation:** The `Sensor` record has a primitive `int` value. The `evaluateAlarm` method expects an `Integer`. This causes autoboxing of `e.value()` to `Integer` for each call, creating many short-lived objects and causing GC pressure.

**Example 7.2: Fix for Autoboxing**

- **Description:** The fix is to use the wrapper type in the record.
- **Code:**
    ```java
    record Sensor(Integer value) {}
    ```
- **Explanation:** No autoboxing occurs.

**Example 7.3: Spaghetti Code**

- **Description:** A method with too many lines and complex conditional logic.
- **Code:**
    ```java
    class VehicleSpaghetti {
        void drive(){
            // around 100 lines of code
            // heavily using the if-else construct
            // ...
        }
    }
    ```
- **Explanation:** This is a classic code smell. The method does too much and is hard to understand, test, and maintain.

**Example 7.4: Blob (God Class)**

- **Description:** A class that accumulates many unrelated responsibilities.
- **Code:**
    ```java
    class VehicleBlob {
        void drive(){}
        void initEngine(){}
        void alarmOilLevel(){}
        void runCylinder() {}
        void checkCylinderHead(){}
        void checkWaterPump(){}
        // ... many more methods
    }
    ```
- **Explanation:** This class tries to do everything, violating the Single Responsibility Principle.

**Example 7.5: Continuous Obsolescence**

- **Description:** An interface with outdated, unused methods.
- **Code:**
    ```java
    interface VehicleCO {
        void checkEngine();
        void initSystem();
        void initRadio(); /* never used */
        void initCassettePlayer(); /* never used */
        void initMediaSystem(); /* actual logic */
    }
    ```
- **Explanation:** Unused methods clutter the interface, making it harder to understand and maintain. The presence of `initRadio` and `initCassettePlayer` while the actual logic is in `initMediaSystem` indicates continuous obsolescence.

This comprehensive extraction and explanation covers all the examples in the book, providing clarity on each design pattern and its application in Java.

# Practical Design Patterns for Java Developers - Interview Preparation Guide

## Part 1: Core Concepts

### 1. Object-Oriented Programming Pillars (APIE)

**Interview Focus:**
- **Encapsulation**: Hiding internal state and exposing only necessary methods. Keep fields private and provide public getters/setters or behavior methods.
- **Inheritance**: Creating parent-child class hierarchies for code reuse. **Potential interview trap**: Prefer composition over inheritance.
- **Polymorphism**: 
  - **Method Overloading (Static)**: Multiple methods with same name but different parameters - resolved at compile time.
  - **Method Overriding (Dynamic)**: Subclass provides specific implementation of parent method - resolved at runtime.
- **Abstraction**: Using abstract classes and interfaces to define contracts without implementation details.

**Key Interview Question**: "Explain polymorphism with examples of overloading vs overriding."

### 2. SOLID Principles

| Principle | Meaning | Interview Focus |
|-----------|---------|-----------------|
| **S**ingle Responsibility | One class = one responsibility | "What's wrong with a God class?" |
| **O**pen-Closed | Open for extension, closed for modification | "How do you add new features without changing existing code?" |
| **L**iskov Substitution | Subtypes must be substitutable for base types | "Can a Square inherit from Rectangle?" (No - violates LSP) |
| **I**nterface Segregation | Don't force clients to depend on unused methods | "Why are fat interfaces bad?" |
| **D**ependency Inversion | Depend on abstractions, not concretions | "What's the difference between DIP and Dependency Injection?" |

**Key Interview Question**: "How does the Singleton pattern violate the Single Responsibility Principle?"

---

## Part 2: Creational Design Patterns

### Factory Method Pattern

**Definition**: Defines an interface for creating an object, but lets subclasses decide which class to instantiate.

**When to use**: When a class can't anticipate the type of objects it needs to create.

**Key implementation details**:
- Single factory method (usually static)
- Returns product interface type
- Subclasses determine concrete type

**Interview Question**: "Difference between Factory Method and Abstract Factory?"

**Answer**: Factory Method creates one type of product; Abstract Factory creates families of related products.

---

### Abstract Factory Pattern

**Definition**: Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

**When to use**: When the system needs to be independent of how its products are created.

**Key implementation details**:
- Factory interface with multiple creation methods
- Concrete factories for each product family
- Client uses factory interface

**Interview Question**: "When would you use Abstract Factory over Factory Method?"

---

### Builder Pattern

**Definition**: Separates construction of a complex object from its representation.

**When to use**: When an object has many optional parameters or complex construction logic.

**Key implementation details**:
- Inner static Builder class
- Fluent interface (method chaining)
- Build() method returns final product

**Interview Question**: "How does the Builder pattern differ from using multiple constructors?"

**Answer**: Builder handles many optional parameters without telescoping constructors and is more readable.

---

### Prototype Pattern

**Definition**: Creates new objects by copying an existing object (prototype).

**When to use**: When object creation is expensive and you need many similar objects.

**Key implementation details**:
- Implement Cloneable interface
- Override clone() method
- Use shallow vs deep copy carefully

**Interview Question**: "What's the difference between shallow and deep copy? Which does clone() use?"

---

### Singleton Pattern

**Definition**: Ensures only one instance of a class exists.

**Thread-safety considerations**:
1. **Lazy initialization** (not thread-safe without synchronization)
2. **Eager initialization** (thread-safe but creates instance upfront)
3. **Double-checked locking** (thread-safe with volatile)
4. **Enum Singleton** (most robust)

**Interview Question**: "Implement a thread-safe Singleton without using synchronized keyword."

**Answer**: Use Enum or static inner class approach.

---

### Object Pool Pattern

**Definition**: Maintains a pool of reusable objects to avoid expensive creation/destruction.

**When to use**: For objects that are expensive to create (database connections, threads).

**Key implementation details**:
- Pool of reusable objects
- acquire() and release() methods
- Synchronization for thread-safety

**Interview Question**: "How does Object Pool differ from Connection Pool?"

---

### Lazy Initialization Pattern

**Definition**: Defers object creation until first use.

**When to use**: When object creation is expensive and might not be needed.

**Interview Question**: "Is Lazy Initialization the same as Singleton?"

**Answer**: No, Lazy Initialization is a technique; Singleton ensures one instance.

---

### Dependency Injection Pattern

**Definition**: Injects dependencies from outside rather than having objects create them.

**Types**:
- Constructor injection (preferred)
- Setter injection
- Field injection

**Benefits**:
- Loose coupling
- Testability
- Single responsibility

**Interview Question**: "What's the difference between Dependency Injection and Inversion of Control?"

**Answer**: Inversion of Control is a principle; Dependency Injection is a way to achieve it.

---

## Part 3: Structural Design Patterns

### Adapter Pattern

**Definition**: Allows incompatible interfaces to work together.

**When to use**: When you need to use an existing class with an incompatible interface.

**Key implementation details**:
- Target interface
- Adaptee (existing class)
- Adapter implements Target and uses Adaptee

**Interview Question**: "Difference between Adapter and Bridge patterns?"

---

### Bridge Pattern

**Definition**: Decouples abstraction from implementation so both can vary independently.

**When to use**: When you want to avoid permanent binding between abstraction and implementation.

**Key implementation details**:
- Abstraction with reference to Implementor
- RefinedAbstraction extends Abstraction
- ConcreteImplementor implements Implementor

**Interview Question**: "Why use Bridge over inheritance?"

---

### Composite Pattern

**Definition**: Composes objects into tree structures to represent part-whole hierarchies.

**When to use**: When you need to treat individual objects and compositions uniformly.

**Key implementation details**:
- Component interface
- Leaf (individual object)
- Composite (contains children)

**Interview Question**: "Real-world example of Composite pattern?"

**Answer**: File system directories (Composite) and files (Leaf).

---

### Decorator Pattern

**Definition**: Attaches additional responsibilities dynamically.

**When to use**: When you need to add functionality without subclassing.

**Key implementation details**:
- Component interface
- ConcreteComponent (original)
- Decorator (abstract wrapper)
- ConcreteDecorator (adds functionality)

**Interview Question**: "Difference between Decorator and Proxy patterns?"

**Answer**: Decorator adds behavior; Proxy controls access.

---

### Facade Pattern

**Definition**: Provides a simplified interface to a complex subsystem.

**When to use**: When you need a simple interface to a complex system.

**Interview Question**: "Is Facade just about simplifying or does it have other benefits?"

**Answer**: Also reduces coupling and improves maintainability.

---

### Proxy Pattern

**Definition**: Provides a placeholder or surrogate for another object.

**Types**:
- Virtual Proxy (lazy initialization)
- Remote Proxy (local representation of remote object)
- Protection Proxy (access control)

**Interview Question**: "When would you use a Proxy vs a Decorator?"

---

### Flyweight Pattern

**Definition**: Shares objects to minimize memory usage.

**When to use**: Many objects with shared intrinsic state.

**Key implementation details**:
- Intrinsic state (shared)
- Extrinsic state (context-specific)
- Flyweight Factory

**Interview Question**: "How does String Pool use the Flyweight pattern?"

---

### Front-Controller Pattern

**Definition**: Centralizes request handling.

**When to use**: Web applications need centralized control.

**Benefits**:
- Centralized logging
- Authentication/authorization
- Request routing

**Interview Question**: "How does Front Controller relate to MVC?"

---

### Marker Pattern

**Definition**: Uses empty interface to mark classes.

**Examples in JDK**:
- Serializable
- Cloneable
- Remote

**Interview Question**: "What's the difference between Marker Interface and Annotation?"

**Answer**: Annotations can have values; marker interfaces are type-level.

---

### Module Pattern

**Definition**: Encapsulates groups of related classes into modules.

**When to use**: Large applications needing strong encapsulation.

**JPMS features**:
- module-info.java
- exports
- requires
- provides/uses

**Interview Question**: "What problems does JPMS solve over classpath?"

**Answer**: Strong encapsulation, explicit dependencies, no classpath collisions.

---

### Twin Pattern

**Definition**: Simulates multiple inheritance.

**When to use**: When you need functionality from multiple parents.

**Interview Question**: "Why doesn't Java support multiple inheritance?"

**Answer**: Diamond problem with method overriding.

---

## Part 4: Behavioral Design Patterns

### Caching Pattern

**Definition**: Stores frequently used data for faster access.

**Considerations**:
- Cache invalidation
- Cache size limits
- Eviction policies (LRU, FIFO)

**Interview Question**: "What's the difference between Cache and Object Pool?"

**Answer**: Cache stores data; Object Pool stores objects for reuse.

---

### Chain of Responsibility Pattern

**Definition**: Passes request along chain of handlers.

**When to use**: When multiple handlers can process a request.

**Key implementation details**:
- Handler interface
- Concrete handlers with next reference
- Each handler decides to process or pass

**Interview Question**: "How is CoR different from Command pattern?"

---

### Command Pattern

**Definition**: Encapsulates a request as an object.

**When to use**: When you need to parameterize clients with operations.

**Key implementation details**:
- Command interface
- ConcreteCommand (couples Receiver and action)
- Invoker (stores command)
- Receiver (performs action)

**Interview Question**: "How does Command pattern support Undo operations?"

**Answer**: Store inverse operations in history.

---

### Interpreter Pattern

**Definition**: Defines a language grammar and interpreter.

**When to use**: When you need to parse and evaluate expressions.

**Key implementation details**:
- AbstractExpression
- TerminalExpression
- NonterminalExpression

**Interview Question**: "When would you use Interpreter over writing custom parser?"

---

### Iterator Pattern

**Definition**: Provides sequential access to elements without exposing internal structure.

**When to use**: When you need to traverse a collection.

**JDK Example**: Iterator interface, for-each loop.

**Interview Question**: "What's the difference between Iterator and ListIterator?"

---

### Mediator Pattern

**Definition**: Encapsulates how objects interact.

**When to use**: When objects have complex, direct communication.

**Benefits**:
- Loose coupling
- Centralized control
- Reduced complexity

**Interview Question**: "How is Mediator different from Observer?"

**Answer**: Mediator centralizes communication; Observer is one-to-many.

---

### Memento Pattern

**Definition**: Captures and restores object state.

**When to use**: Need undo/redo functionality.

**Key implementation details**:
- Originator (creates memento)
- Memento (stores state)
- Caretaker (manages mementos)

**Interview Question**: "How does Memento maintain encapsulation?"

**Answer**: Memento is only accessible to Originator.

---

### Null Object Pattern

**Definition**: Provides do-nothing implementation.

**Benefits**:
- Avoids null checks
- Prevents NullPointerException
- Cleaner code

**Interview Question**: "When should you use Null Object vs Optional?"

**Answer**: Null Object is an actual instance; Optional is a container.

---

### Observer Pattern

**Definition**: One-to-many dependency where state changes notify dependents.

**When to use**: When one object's state changes affect many others.

**Key implementation details**:
- Subject (maintains observers)
- Observer (update method)
- ConcreteSubject and ConcreteObserver

**Interview Question**: "How is Observer different from Publish-Subscribe?"

---

### Pipeline Pattern

**Definition**: Processes data through sequential stages.

**When to use**: When data undergoes multiple transformations.

**Interview Question**: "How does Pipeline relate to Java Stream API?"

**Answer**: Stream operations (filter, map, etc.) form a pipeline.

---

### State Pattern

**Definition**: Allows object to change behavior when state changes.

**When to use**: When object behavior depends on its state.

**Key implementation details**:
- Context (maintains state)
- State interface
- ConcreteState implementations

**Interview Question**: "Difference between State and Strategy patterns?"

**Answer**: State changes behavior based on internal state; Strategy is externally selected.

---

### Strategy Pattern

**Definition**: Defines family of interchangeable algorithms.

**When to use**: When you need different algorithms for the same task.

**Key implementation details**:
- Strategy interface
- ConcreteStrategy implementations
- Context (uses strategy)

**Interview Question**: "How is Strategy different from State?"

---

### Template Method Pattern

**Definition**: Defines skeleton of algorithm, deferring steps to subclasses.

**When to use**: When you have invariant algorithm parts.

**Key implementation details**:
- Abstract class with template method (final)
- Abstract methods (overridden by subclasses)
- Hook methods (optional overrides)

**Interview Question**: "What's the difference between Template Method and Strategy?"

**Answer**: Template Method uses inheritance; Strategy uses composition.

---

### Visitor Pattern

**Definition**: Adds operations to object structure without modifying classes.

**When to use**: When you need to perform operations on many object types.

**Key implementation details**:
- Visitor interface with visit methods
- Element interface with accept method
- ConcreteVisitors and ConcreteElements

**Interview Question**: "What's the main drawback of the Visitor pattern?"

**Answer**: Adding new Element types requires changing all Visitors.

---

## Part 5: Concurrency Design Patterns

### Active Object Pattern

**Definition**: Decouples method invocation from execution using separate thread.

**Key implementation details**:
- Blocking queue for commands
- Internal thread
- Commands are processed sequentially

**Interview Question**: "How does Active Object relate to Producer-Consumer?"

---

### Async Method Invocation

**Definition**: Executes method asynchronously with callback.

**Java implementations**:
- Callable/Future
- CompletableFuture
- ExecutorService

**Interview Question**: "What are the benefits of async vs blocking calls?"

---

### Balking Pattern

**Definition**: Skips action if object is in inappropriate state.

**When to use**: When state determines if action should proceed.

**Interview Question**: "How is Balking different from Guarded Suspension?"

---

### Double-Checked Locking

**Definition**: Thread-safe lazy initialization with minimal synchronization.

**Key implementation details**:
- volatile instance variable
- Two null checks
- Synchronized block only on first null

**Interview Question**: "What problem does double-checked locking solve?"

**Answer**: Prevents race conditions in lazy initialization while minimizing synchronization overhead.

---

### Read-Write Lock Pattern

**Definition**: Allows multiple readers, exclusive writer.

**When to use**: When reads are more frequent than writes.

**Java implementation**: ReentrantReadWriteLock

**Interview Question**: "Could you implement your own ReadWriteLock?"

---

### Producer-Consumer Pattern

**Definition**: Decouples production and consumption via shared buffer.

**Java implementations**:
- BlockingQueue
- ArrayBlockingQueue
- LinkedBlockingQueue

**Interview Question**: "What happens if producer is faster than consumer?"

**Answer**: Queue fills up; producer blocks or throws exception.

---

### Scheduler Pattern

**Definition**: Executes tasks at specific times.

**Java implementations**:
- ScheduledExecutorService
- Timer/TimerTask

**Interview Question**: "How does ScheduledThreadPoolExecutor work?"

---

### Thread-Pool Pattern

**Definition**: Reuses threads to execute multiple tasks.

**Java implementations**:
- Executors.newFixedThreadPool()
- Executors.newCachedThreadPool()
- Executors.newSingleThreadExecutor()

**Interview Question**: "What are the trade-offs of different thread pools?"

---

## Part 6: Anti-Patterns (What NOT to do)

### Code-Level Anti-Patterns

| Anti-Pattern | Description | Solution |
|--------------|-------------|----------|
| Spaghetti Code | Unstructured, tangled code | Refactor into clear, modular structure |
| Cut and Paste | Duplicated code | Extract common logic |
| Blob/God Class | Single class doing everything | Apply SRP, break into multiple classes |
| Lava Flow | Dead code left in place | Remove unused code |
| Boat Anchor | Unused abstractions | Remove or refactor |
| Input Kludge | Ad-hoc input handling | Design proper input validation |

### Architecture Anti-Patterns

| Anti-Pattern | Description | Solution |
|--------------|-------------|----------|
| Golden Hammer | Over-reliance on one solution | Consider alternatives |
| Continuous Obsolescence | Outdated code/design | Regular updates and refactoring |
| Ambiguous Viewpoint | Unclear system perspective | Use modeling like UML |
| Dead End | Platform/dependency lock-in | Use standard interfaces, plan migration |
| Poltergeists | Mysterious side effects | Understand class hierarchy |

---

## Key Interview Questions by Category

### OOP & SOLID
1. "Explain the four pillars of OOP."
2. "What is the Liskov Substitution Principle and why is it important?"
3. "How do you design a class following the Open-Closed Principle?"

### Creational Patterns
1. "When would you use Factory Method instead of Abstract Factory?"
2. "Implement a thread-safe Singleton in Java."
3. "What's the difference between Builder and Factory patterns?"

### Structural Patterns
1. "When would you use Decorator over inheritance?"
2. "What's the difference between Proxy and Adapter?"
3. "How would you implement a caching system using Flyweight?"

### Behavioral Patterns
1. "How does the Observer pattern differ from Mediator?"
2. "When would you use Strategy vs State?"
3. "What problem does the Visitor pattern solve?"

### Concurrency Patterns
1. "What's the difference between a Callable and a Runnable?"
2. "How do you implement a thread-safe Singleton?"
3. "When would you use ReadWriteLock over synchronized?"

### Anti-Patterns
1. "What is a God class and how do you refactor it?"
2. "How do you identify and fix Spaghetti Code?"
3. "What are common architectural anti-patterns?"

# Complete Design Patterns Guide: Why, When, Advantages, and Real-World Examples

## Part 1: OOP Fundamentals

### Encapsulation

**Definition**: Bundling data and methods that operate on that data, hiding internal state.

**Why Use It?**
- Protects data integrity
- Reduces system complexity
- Allows internal changes without affecting external code

**When to Use**: Always! It's a fundamental principle of OOP.

**Advantages**:
- Maintainability: Change internal implementation without breaking clients
- Security: Prevent unauthorized access
- Flexibility: Add validation/logic in setters

**Real-World Example**:
```java
// Bank Account with encapsulation
public class BankAccount {
    private double balance;  // Hidden from direct access
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logTransaction("DEPOSIT", amount);
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            logTransaction("WITHDRAW", amount);
        }
    }
    
    private void logTransaction(String type, double amount) {
        // Internal logging - hidden from client
    }
}
```

---

### Inheritance

**Definition**: Creating new classes based on existing ones, reusing code and establishing relationships.

**Why Use It?**
- Code reuse
- Method overriding for specific behavior
- Establishes "is-a" relationships

**When to Use**: When you have a true "is-a" relationship and need to share common behavior.

**Advantages**:
- Code reuse reduces duplication
- Polymorphism enables flexible code
- Hierarchical organization of classes

**Real-World Example**:
```java
// Vehicle hierarchy
public abstract class Vehicle {
    protected String registrationNumber;
    protected int wheels;
    
    public abstract void startEngine();
    public abstract void stopEngine();
    
    public void displayRegistration() {
        System.out.println("Registration: " + registrationNumber);
    }
}

public class Car extends Vehicle {
    private int doors;
    
    @Override
    public void startEngine() {
        System.out.println("Car engine started with key");
    }
    
    @Override
    public void stopEngine() {
        System.out.println("Car engine stopped");
    }
}

public class ElectricCar extends Car {
    private int batteryCapacity;
    
    @Override
    public void startEngine() {
        System.out.println("Electric car started silently");
    }
}
```

---

### Polymorphism

**Definition**: Ability to present the same interface for different underlying forms.

**Why Use It?**
- Write more flexible, generic code
- Reduce conditional logic
- Enable pluggable architectures

**When to Use**: When you have different types that should respond to the same message.

**Advantages**:
- Code flexibility
- Extensibility without modification
- Cleaner, more maintainable code

**Real-World Example**:
```java
// Payment processing with polymorphism
public interface PaymentMethod {
    void pay(double amount);
    String getPaymentType();
}

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card: " + amount);
        // Process credit card transaction
    }
    
    @Override
    public String getPaymentType() {
        return "CREDIT_CARD";
    }
}

public class PayPalPayment implements PaymentMethod {
    private String email;
    
    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal: " + amount);
        // Process PayPal transaction
    }
    
    @Override
    public String getPaymentType() {
        return "PAYPAL";
    }
}

public class PaymentProcessor {
    public void processPayment(PaymentMethod payment, double amount) {
        payment.pay(amount);  // Polymorphic call
        System.out.println("Payment of " + amount + " completed via " + payment.getPaymentType());
    }
}
```

---

## Part 2: Creational Patterns

### Factory Method Pattern

**Why Use It?**
- Centralizes object creation logic
- Decouples client code from concrete classes
- Enables extensibility through inheritance

**When to Use It?**
- When class can't anticipate object types it needs to create
- When subclasses should decide which object to instantiate
- When you want to localize creation logic

**Advantages**:
- Loose coupling between client and product classes
- Easy to add new product types
- Promotes single responsibility

**Real-World Implementation**:
```java
// Logging system with Factory Method
public interface Logger {
    void log(String message);
    void error(String message);
    void debug(String message);
}

// Concrete Loggers
public class FileLogger implements Logger {
    private String filePath;
    
    public FileLogger(String filePath) {
        this.filePath = filePath;
        // Open file for writing
    }
    
    @Override
    public void log(String message) {
        // Write to file
        System.out.println("FileLogger: " + message);
    }
    
    @Override
    public void error(String message) {
        // Write error to file with timestamp
        System.out.println("FileLogger ERROR: " + message);
    }
    
    @Override
    public void debug(String message) {
        // Write debug to file
        System.out.println("FileLogger DEBUG: " + message);
    }
}

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("ConsoleLogger: " + message);
    }
    
    @Override
    public void error(String message) {
        System.err.println("ConsoleLogger ERROR: " + message);
    }
    
    @Override
    public void debug(String message) {
        System.out.println("ConsoleLogger DEBUG: " + message);
    }
}

// Abstract Creator
public abstract class LoggerFactory {
    public abstract Logger createLogger();
    
    public void logMessage(String message, String level) {
        Logger logger = createLogger();
        switch (level) {
            case "ERROR":
                logger.error(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            default:
                logger.log(message);
        }
    }
}

// Concrete Creators
public class FileLoggerFactory extends LoggerFactory {
    private String filePath;
    
    public FileLoggerFactory(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public Logger createLogger() {
        return new FileLogger(filePath);
    }
}

public class ConsoleLoggerFactory extends LoggerFactory {
    @Override
    public Logger createLogger() {
        return new ConsoleLogger();
    }
}

// Usage
LoggerFactory factory = new ConsoleLoggerFactory();
factory.logMessage("Application started", "INFO");
```

---

### Abstract Factory Pattern

**Why Use It?**
- Creates families of related products
- Ensures product compatibility
- Separates product creation from usage

**When to Use It?**
- When system needs to be independent from product creation
- When products are designed to work together
- When you need to provide multiple product families

**Advantages**:
- Guarantees product compatibility
- Easy to switch entire product families
- Enforces consistency across products

**Real-World Implementation**:
```java
// UI Component Factory for different operating systems
public interface Button {
    void render();
    void onClick();
}

public interface TextBox {
    void render();
    void setText(String text);
    String getText();
}

// Windows UI Components
public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows-style button");
        // Windows-specific rendering code
    }
    
    @Override
    public void onClick() {
        System.out.println("Windows button clicked");
    }
}

public class WindowsTextBox implements TextBox {
    private String text;
    
    @Override
    public void render() {
        System.out.println("Rendering Windows-style text box");
    }
    
    @Override
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String getText() {
        return text;
    }
}

// Mac UI Components
public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Mac-style button");
    }
    
    @Override
    public void onClick() {
        System.out.println("Mac button clicked");
    }
}

public class MacTextBox implements TextBox {
    private String text;
    
    @Override
    public void render() {
        System.out.println("Rendering Mac-style text box");
    }
    
    @Override
    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String getText() {
        return text;
    }
}

// Abstract Factory
public interface UIFactory {
    Button createButton();
    TextBox createTextBox();
}

// Concrete Factories
public class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    
    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}

public class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    
    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}

// Usage
public class Application {
    private Button button;
    private TextBox textBox;
    private UIFactory factory;
    
    public Application(UIFactory factory) {
        this.factory = factory;
        this.button = factory.createButton();
        this.textBox = factory.createTextBox();
    }
    
    public void initUI() {
        button.render();
        textBox.render();
        textBox.setText("Hello World");
        System.out.println("Text: " + textBox.getText());
    }
}

// Client code
Application windowsApp = new Application(new WindowsUIFactory());
windowsApp.initUI();

Application macApp = new Application(new MacUIFactory());
macApp.initUI();
```

---

### Builder Pattern

**Why Use It?**
- Handles complex object construction
- Reduces constructor overload
- Creates immutable objects with many parameters

**When to Use It?**
- When object has many optional parameters
- When construction logic is complex
- When you need different representations of the same object

**Advantages**:
- Readable and maintainable code
- Immutable objects
- Validation can be centralized
- Fluent interface

**Real-World Implementation**:
```java
// Email builder with many optional fields
public class Email {
    private final String to;
    private final String from;
    private final String subject;
    private final String body;
    private final List<String> cc;
    private final List<String> bcc;
    private final boolean isHtml;
    private final int priority;
    private final String replyTo;
    
    private Email(Builder builder) {
        this.to = builder.to;
        this.from = builder.from;
        this.subject = builder.subject;
        this.body = builder.body;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.isHtml = builder.isHtml;
        this.priority = builder.priority;
        this.replyTo = builder.replyTo;
    }
    
    // Getters (no setters - immutable)
    
    public static class Builder {
        private final String to;      // Required
        private final String from;    // Required
        private String subject;
        private String body;
        private List<String> cc = new ArrayList<>();
        private List<String> bcc = new ArrayList<>();
        private boolean isHtml = false;
        private int priority = 1;
        private String replyTo;
        
        // Required parameters in constructor
        public Builder(String to, String from) {
            this.to = to;
            this.from = from;
        }
        
        // Optional parameters as methods
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        
        public Builder cc(List<String> cc) {
            this.cc = cc;
            return this;
        }
        
        public Builder cc(String cc) {
            this.cc.add(cc);
            return this;
        }
        
        public Builder bcc(List<String> bcc) {
            this.bcc = bcc;
            return this;
        }
        
        public Builder isHtml(boolean isHtml) {
            this.isHtml = isHtml;
            return this;
        }
        
        public Builder priority(int priority) {
            if (priority < 1 || priority > 5) {
                throw new IllegalArgumentException("Priority must be between 1 and 5");
            }
            this.priority = priority;
            return this;
        }
        
        public Builder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }
        
        public Email build() {
            if (to == null || to.isEmpty()) {
                throw new IllegalStateException("To address is required");
            }
            if (from == null || from.isEmpty()) {
                throw new IllegalStateException("From address is required");
            }
            return new Email(this);
        }
    }
}

// Usage
Email email = new Email.Builder("recipient@example.com", "sender@example.com")
    .subject("Meeting Agenda")
    .body("Here is the agenda for tomorrow's meeting...")
    .cc("team-lead@example.com")
    .cc("project-manager@example.com")
    .priority(3)
    .isHtml(true)
    .replyTo("no-reply@example.com")
    .build();
```

---

## Part 3: Structural Patterns

### Adapter Pattern

**Why Use It?**
- Makes incompatible interfaces work together
- Reuses existing components
- Wraps legacy code

**When to Use It?**
- When you need to use a class that doesn't match your interface
- When integrating with third-party libraries
- When working with legacy systems

**Advantages**:
- Reuses existing code
- Provides abstraction between client and adaptee
- Makes system more maintainable

**Real-World Implementation**:
```java
// Payment gateway integration with different APIs
// Existing PayPal integration
public class PayPalGateway {
    public boolean processPayment(String apiKey, double amount, String currency) {
        System.out.println("Processing PayPal payment: " + amount + " " + currency);
        return true;
    }
}

// Existing Stripe integration
public class StripeGateway {
    public void charge(String secretKey, PaymentRequest request) {
        System.out.println("Processing Stripe payment: " + request.getAmount());
    }
    
    public static class PaymentRequest {
        private double amount;
        private String currency;
        private String description;
        // getters and setters
    }
}

// Target interface (our application's expected interface)
public interface PaymentProcessor {
    boolean processPayment(double amount, String currency);
}

// PayPal Adapter
public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPal;
    private String apiKey;
    
    public PayPalAdapter(String apiKey) {
        this.payPal = new PayPalGateway();
        this.apiKey = apiKey;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        // Adapt the interface
        return payPal.processPayment(apiKey, amount, currency);
    }
}

// Stripe Adapter
public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;
    private String secretKey;
    
    public StripeAdapter(String secretKey) {
        this.stripe = new StripeGateway();
        this.secretKey = secretKey;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        // Adapt the interface
        StripeGateway.PaymentRequest request = new StripeGateway.PaymentRequest();
        request.setAmount(amount);
        request.setCurrency(currency);
        request.setDescription("Payment from system");
        
        stripe.charge(secretKey, request);
        return true; // For simplicity
    }
}

// Usage
public class ShoppingCart {
    private PaymentProcessor paymentProcessor;
    
    public void checkout(double amount, String currency) {
        if (paymentProcessor.processPayment(amount, currency)) {
            System.out.println("Payment successful!");
        }
    }
}

// Client code
ShoppingCart cart = new ShoppingCart();
cart.setPaymentProcessor(new PayPalAdapter("your-api-key"));
cart.checkout(99.99, "USD");

// Switch to Stripe without changing ShoppingCart
cart.setPaymentProcessor(new StripeAdapter("your-secret-key"));
cart.checkout(149.99, "EUR");
```

---

### Decorator Pattern

**Why Use It?**
- Adds behavior dynamically
- Avoids subclass explosion
- Provides flexible extension

**When to Use It?**
- When you need to add responsibilities to objects at runtime
- When subclassing would create too many combinations
- When you want to add or remove behavior

**Advantages**:
- Alternative to subclassing
- Runtime flexibility
- Single Responsibility Principle
- Open/Closed Principle

**Real-World Implementation**:
```java
// Coffee shop ordering system
public interface Coffee {
    String getDescription();
    double getCost();
    String getIngredients();
}

// Base implementation
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double getCost() {
        return 2.50;
    }
    
    @Override
    public String getIngredients() {
        return "Coffee beans, hot water";
    }
}

// Decorator base class
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
    
    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}

// Concrete Decorators
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Milk";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.75;
    }
    
    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients() + ", steamed milk";
    }
}

public class SugarDecorator extends CoffeeDecorator {
    private int sugarAmount;
    
    public SugarDecorator(Coffee coffee, int sugarAmount) {
        super(coffee);
        this.sugarAmount = sugarAmount;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + " + sugarAmount + " sugar(s)";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + (sugarAmount * 0.10);
    }
    
    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients() + ", " + sugarAmount + " sugar cubes";
    }
}

public class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + Whipped Cream";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.25;
    }
    
    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients() + ", whipped cream";
    }
}

// Usage
public class CoffeeShop {
    public static void main(String[] args) {
        // Simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " = $" + coffee.getCost());
        
        // Coffee with milk and sugar
        Coffee milkCoffee = new MilkDecorator(coffee);
        Coffee milkSugarCoffee = new SugarDecorator(milkCoffee, 2);
        System.out.println(milkSugarCoffee.getDescription() + " = $" + milkSugarCoffee.getCost());
        
        // Coffee with everything
        Coffee everythingCoffee = new WhipDecorator(
                                    new MilkDecorator(
                                        new SugarDecorator(
                                            new SimpleCoffee(), 3
                                        )
                                    )
                                );
        System.out.println(everythingCoffee.getDescription() + " = $" + everythingCoffee.getCost());
        System.out.println("Ingredients: " + everythingCoffee.getIngredients());
    }
}
```

---

### Proxy Pattern

**Why Use It?**
- Controls access to objects
- Adds security checks
- Implements lazy loading
- Provides remote access

**When to Use It?**
- When you need to control access to an object
- When you want to add functionality transparently
- When object creation is expensive

**Advantages**:
- Access control
- Lazy initialization
- Logging/Auditing
- Caching

**Real-World Implementation**:
```java
// Image viewer with lazy loading and caching
public interface Image {
    void display();
    String getName();
    long getSize();
}

// Real image - expensive to load
public class RealImage implements Image {
    private String filename;
    private long size;
    private byte[] imageData;
    private long loadTime;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }
    
    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
        // Simulate expensive disk I/O
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
        this.size = 1024 * 1024 * 5; // 5MB
        this.imageData = new byte[1024]; // Simulate data
        this.loadTime = System.currentTimeMillis();
        System.out.println("Image loaded: " + filename + " (Size: " + size + " bytes)");
    }
    
    @Override
    public void display() {
        System.out.println("Displaying: " + filename);
        // Display image data...
    }
    
    @Override
    public String getName() {
        return filename;
    }
    
    @Override
    public long getSize() {
        return size;
    }
}

// Proxy with lazy loading and caching
public class ImageProxy implements Image {
    private String filename;
    private Image realImage;
    private static Map<String, Image> cache = new HashMap<>();
    private static Set<String> loading = new HashSet<>();
    
    public ImageProxy(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        // Check cache first
        if (cache.containsKey(filename)) {
            System.out.println("Loading from cache: " + filename);
            cache.get(filename).display();
            return;
        }
        
        // Check if currently loading
        if (loading.contains(filename)) {
            System.out.println("Image " + filename + " is already loading, please wait...");
            return;
        }
        
        // Load the image
        synchronized (this) {
            if (!cache.containsKey(filename)) {
                System.out.println("First time loading image: " + filename);
                loading.add(filename);
                realImage = new RealImage(filename);
                cache.put(filename, realImage);
                loading.remove(filename);
            }
        }
        
        realImage.display();
    }
    
    @Override
    public String getName() {
        return filename;
    }
    
    @Override
    public long getSize() {
        if (realImage != null) {
            return realImage.getSize();
        } else {
            // Return placeholder size
            return 0;
        }
    }
    
    // Clear cache method
    public static void clearCache() {
        cache.clear();
        System.out.println("Cache cleared");
    }
}

// Usage
public class ImageViewer {
    public static void main(String[] args) {
        // Create image proxies
        List<Image> images = new ArrayList<>();
        images.add(new ImageProxy("photo1.jpg"));
        images.add(new ImageProxy("photo2.jpg"));
        images.add(new ImageProxy("photo3.jpg"));
        
        // Display images
        System.out.println("=== First display (loading) ===");
        for (Image image : images) {
            image.display();
        }
        
        System.out.println("\n=== Second display (cached) ===");
        for (Image image : images) {
            image.display();
        }
        
        // Access image metadata without loading
        System.out.println("\n=== Image Info (without loading) ===");
        Image proxy = new ImageProxy("photo4.jpg");
        System.out.println("Image: " + proxy.getName() + ", Size: " + proxy.getSize() + " bytes");
        
        // Proxy with additional features
        class ImageProxyWithLogging extends ImageProxy {
            public ImageProxyWithLogging(String filename) {
                super(filename);
            }
            
            @Override
            public void display() {
                System.out.println("Access control: User has access to " + getName());
                super.display();
                System.out.println("Audit log: Image " + getName() + " displayed at " + new Date());
            }
        }
        
        System.out.println("\n=== Proxy with logging ===");
        Image loggingProxy = new ImageProxyWithLogging("logging.jpg");
        loggingProxy.display();
    }
}
```

---

## Part 4: Behavioral Patterns

### Strategy Pattern

**Why Use It?**
- Defines family of interchangeable algorithms
- Avoids complex if-else/switch statements
- Makes algorithm selection easy

**When to Use It?**
- When you have multiple algorithms for the same task
- When algorithm behavior varies independently
- When you want to select algorithm at runtime

**Advantages**:
- Avoids conditional statements
- Provides pluggable algorithms
- Single Responsibility Principle
- Open/Closed Principle

**Real-World Implementation**:
```java
// Payment processing with different payment strategies
public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentType();
    boolean validate();
}

// Credit Card Strategy
public class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String nameOnCard;
    
    public CreditCardPaymentStrategy(String cardNumber, String expiryDate, String cvv, String nameOnCard) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
    }
    
    @Override
    public void pay(double amount) {
        // Validate before payment
        if (!validate()) {
            throw new IllegalStateException("Invalid credit card details");
        }
        
        System.out.println("Processing credit card payment: $" + amount);
        System.out.println("Card: " + maskCardNumber(cardNumber));
        System.out.println("Name on card: " + nameOnCard);
        
        // Actual credit card processing logic
        // Call payment gateway API
        // Handle response
    }
    
    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
    
    @Override
    public boolean validate() {
        // Simple validation - in real app, do more comprehensive checks
        return cardNumber != null && cardNumber.matches("\\d{16}") &&
               expiryDate != null && expiryDate.matches("\\d{2}/\\d{2}") &&
               cvv != null && cvv.matches("\\d{3,4}");
    }
    
    private String maskCardNumber(String cardNumber) {
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

// PayPal Strategy
public class PayPalPaymentStrategy implements PaymentStrategy {
    private String email;
    private String password;
    
    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay(double amount) {
        if (!validate()) {
            throw new IllegalStateException("Invalid PayPal credentials");
        }
        
        System.out.println("Processing PayPal payment: $" + amount);
        System.out.println("Email: " + email);
        
        // Actual PayPal processing
        // Call PayPal API
    }
    
    @Override
    public String getPaymentType() {
        return "PayPal";
    }
    
    @Override
    public boolean validate() {
        return email != null && email.contains("@") &&
               password != null && password.length() >= 8;
    }
}

// Cryptocurrency Strategy
public class CryptoPaymentStrategy implements PaymentStrategy {
    private String walletAddress;
    private String currency;
    
    public CryptoPaymentStrategy(String walletAddress, String currency) {
        this.walletAddress = walletAddress;
        this.currency = currency;
    }
    
    @Override
    public void pay(double amount) {
        if (!validate()) {
            throw new IllegalStateException("Invalid wallet address");
        }
        
        System.out.println("Processing cryptocurrency payment: $" + amount);
        System.out.println("Currency: " + currency);
        System.out.println("Wallet: " + walletAddress);
        
        // Actual crypto processing
        // Blockchain transaction
    }
    
    @Override
    public String getPaymentType() {
        return "Cryptocurrency (" + currency + ")";
    }
    
    @Override
    public boolean validate() {
        return walletAddress != null && walletAddress.length() >= 30 &&
               currency != null && !currency.isEmpty();
    }
}

// Shopping Cart that uses strategies
public class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    private PaymentStrategy paymentStrategy;
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public double calculateTotal() {
        return items.stream()
                   .mapToDouble(Item::getPrice)
                   .sum();
    }
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout() {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        
        double total = calculateTotal();
        System.out.println("\n=== CHECKOUT ===");
        System.out.println("Items: " + items.size());
        System.out.println("Total amount: $" + total);
        System.out.println("Payment method: " + paymentStrategy.getPaymentType());
        System.out.println("---");
        
        paymentStrategy.pay(total);
        System.out.println("Checkout completed successfully!");
    }
}

class Item {
    private String name;
    private double price;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
}

// Usage
public class OnlineStore {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Add items
        cart.addItem(new Item("Laptop", 999.99));
        cart.addItem(new Item("Headphones", 149.99));
        cart.addItem(new Item("Mouse", 29.99));
        
        // Choose payment strategy
        System.out.println("=== Credit Card Payment ===");
        cart.setPaymentStrategy(
            new CreditCardPaymentStrategy(
                "1234567890123456", "12/25", "123", "John Doe"
            )
        );
        cart.checkout();
        
        // Switch strategy
        System.out.println("\n=== PayPal Payment ===");
        cart.setPaymentStrategy(
            new PayPalPaymentStrategy("john.doe@example.com", "securePassword123")
        );
        cart.checkout();
        
        // Switch strategy again
        System.out.println("\n=== Cryptocurrency Payment ===");
        cart.setPaymentStrategy(
            new CryptoPaymentStrategy(
                "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P7Q8R9S0T1U2V", "BTC"
            )
        );
        cart.checkout();
    }
}
```

---

### Observer Pattern

**Why Use It?**
- Defines one-to-many dependency
- Automatically updates dependents
- Enables decoupled communication

**When to Use It?**
- When one object's state changes need to reflect in others
- When you have a publish-subscribe model
- When you need to maintain consistency between related objects

**Advantages**:
- Loose coupling
- Broadcast communication
- Easy to add/remove observers
- Open/Closed Principle

**Real-World Implementation**:
```java
// Stock market monitoring system
public interface StockObserver {
    void update(String stockSymbol, double price, double changePercent);
    String getObserverName();
}

public interface StockSubject {
    void registerObserver(StockObserver observer);
    void unregisterObserver(StockObserver observer);
    void notifyObservers();
}

// Stock data
public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private double previousPrice;
    private double changePercent;
    
    public Stock(String symbol, String companyName, double initialPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = initialPrice;
        this.previousPrice = initialPrice;
        this.changePercent = 0;
    }
    
    public void setPrice(double newPrice) {
        this.previousPrice = this.price;
        this.price = newPrice;
        this.changePercent = ((newPrice - previousPrice) / previousPrice) * 100;
    }
    
    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice() { return price; }
    public double getPreviousPrice() { return previousPrice; }
    public double getChangePercent() { return changePercent; }
}

// Stock market that tracks multiple stocks
public class StockMarket implements StockSubject {
    private Map<String, Stock> stocks = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();
    private Random random = new Random();
    private boolean isRunning = true;
    
    public StockMarket() {
        // Initialize with some stocks
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 175.30));
        stocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2850.50));
        stocks.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 330.75));
        stocks.put("TSLA", new Stock("TSLA", "Tesla Inc.", 720.80));
    }
    
    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
        System.out.println(observer.getObserverName() + " registered");
    }
    
    @Override
    public void unregisterObserver(StockObserver observer) {
        observers.remove(observer);
        System.out.println(observer.getObserverName() + " unregistered");
    }
    
    @Override
    public void notifyObservers() {
        for (StockObserver observer : observers) {
            for (Stock stock : stocks.values()) {
                observer.update(stock.getSymbol(), stock.getPrice(), stock.getChangePercent());
            }
        }
    }
    
    // Simulate price changes
    public void startSimulation() {
        isRunning = true;
        new Thread(() -> {
            while (isRunning) {
                // Update random stock prices
                List<String> symbols = new ArrayList<>(stocks.keySet());
                int numUpdates = 3 + random.nextInt(5); // 3-7 updates per cycle
                
                for (int i = 0; i < numUpdates; i++) {
                    String symbol = symbols.get(random.nextInt(symbols.size()));
                    Stock stock = stocks.get(symbol);
                    double change = (random.nextDouble() * 10) - 5; // -5 to +5
                    double newPrice = stock.getPrice() + change;
                    if (newPrice > 0) {
                        stock.setPrice(newPrice);
                    }
                }
                
                // Notify all observers
                notifyObservers();
                
                try {
                    Thread.sleep(2000); // Update every 2 seconds
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }).start();
    }
    
    public void stopSimulation() {
        isRunning = false;
    }
}

// Concrete Observers
public class MobileAlertObserver implements StockObserver {
    private String name;
    private double threshold;
    
    public MobileAlertObserver(String name, double threshold) {
        this.name = name;
        this.threshold = threshold;
    }
    
    @Override
    public void update(String stockSymbol, double price, double changePercent) {
        double absChange = Math.abs(changePercent);
        if (absChange >= threshold) {
            System.out.println("🔔 " + name + " ALERT: " + stockSymbol + 
                             " moved " + String.format("%.2f", changePercent) + 
                             "% to $" + String.format("%.2f", price));
            // In real app, send push notification
        }
    }
    
    @Override
    public String getObserverName() {
        return name;
    }
}

public class PortfolioDisplayObserver implements StockObserver {
    private String name;
    private Map<String, Double> holdings;
    
    public PortfolioDisplayObserver(String name) {
        this.name = name;
        this.holdings = new HashMap<>();
        // Simulate portfolio
        holdings.put("AAPL", 50.0);
        holdings.put("GOOGL", 10.0);
        holdings.put("MSFT", 30.0);
        holdings.put("TSLA", 5.0);
    }
    
    @Override
    public void update(String stockSymbol, double price, double changePercent) {
        if (holdings.containsKey(stockSymbol)) {
            double shares = holdings.get(stockSymbol);
            double value = shares * price;
            System.out.println("📊 " + name + " - " + stockSymbol + 
                             ": " + shares + " shares @ $" + String.format("%.2f", price) + 
                             " = $" + String.format("%.2f", value));
        }
    }
    
    @Override
    public String getObserverName() {
        return name;
    }
}

public class StatisticalDisplayObserver implements StockObserver {
    private String name;
    private Map<String, List<Double>> priceHistory = new HashMap<>();
    
    public StatisticalDisplayObserver(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String stockSymbol, double price, double changePercent) {
        priceHistory.computeIfAbsent(stockSymbol, k -> new ArrayList<>()).add(price);
        List<Double> prices = priceHistory.get(stockSymbol);
        
        if (prices.size() >= 5) {
            double average = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double min = prices.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double max = prices.stream().mapToDouble(Double::doubleValue).max().orElse(0);
            
            System.out.println("📈 " + name + " - " + stockSymbol + 
                             " Avg: $" + String.format("%.2f", average) + 
                             " | Min: $" + String.format("%.2f", min) + 
                             " | Max: $" + String.format("%.2f", max));
        }
    }
    
    @Override
    public String getObserverName() {
        return name;
    }
}

// Usage
public class StockMarketApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== STOCK MARKET MONITORING SYSTEM ===\n");
        
        // Create subject
        StockMarket market = new StockMarket();
        
        // Create observers
        StockObserver mobileAlert = new MobileAlertObserver("Mobile Alert", 2.0);
        StockObserver portfolioDisplay = new PortfolioDisplayObserver("Portfolio");
        StockObserver statsDisplay = new StatisticalDisplayObserver("Statistics");
        
        // Register observers
        market.registerObserver(mobileAlert);
        market.registerObserver(portfolioDisplay);
        market.registerObserver(statsDisplay);
        
        // Start simulation
        System.out.println("\n=== SIMULATION STARTED ===");
        market.startSimulation();
        
        // Run for 10 seconds
        Thread.sleep(10000);
        
        // Stop simulation
        market.stopSimulation();
        System.out.println("\n=== SIMULATION STOPPED ===");
    }
}
```

---

## Part 5: Concurrency Patterns

### Producer-Consumer Pattern

**Why Use It?**
- Decouples production from consumption
- Handles different processing speeds
- Manages resource contention

**When to Use It?**
- When producers and consumers work at different rates
- When you need to buffer data
- When you want to decouple tasks

**Advantages**:
- Loose coupling
- Load balancing
- Resource optimization
- Thread safety (when implemented correctly)

**Real-World Implementation**:
```java
// Order processing system with producers (order intake) and consumers (order processing)
public class Order {
    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;
    private final LocalDateTime orderTime;
    private OrderStatus status;
    
    public Order(String orderId, String customerId, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.RECEIVED;
    }
    
    // Getters and setters
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items.size() +
                ", status=" + status +
                '}';
    }
}

class OrderItem {
    private String productId;
    private int quantity;
    private double price;
    
    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    // Getters
}

enum OrderStatus {
    RECEIVED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

// Order Queue with bounded capacity
public class OrderQueue {
    private final BlockingQueue<Order> queue;
    private final int maxCapacity;
    private final AtomicInteger orderCounter = new AtomicInteger(0);
    private final Map<String, Order> orderCache = new ConcurrentHashMap<>();
    
    public OrderQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.queue = new LinkedBlockingQueue<>(maxCapacity);
    }
    
    public boolean addOrder(Order order) {
        orderCounter.incrementAndGet();
        boolean added = queue.offer(order);
        if (added) {
            orderCache.put(order.getOrderId(), order);
            System.out.println("✅ Order added to queue: " + order.getOrderId() + 
                             " | Queue size: " + queue.size());
        } else {
            System.out.println("❌ Queue is full! Cannot add order: " + order.getOrderId());
        }
        return added;
    }
    
    public Order takeOrder() throws InterruptedException {
        Order order = queue.poll(500, TimeUnit.MILLISECONDS);
        if (order != null) {
            System.out.println("📦 Order taken for processing: " + order.getOrderId() + 
                             " | Queue size: " + queue.size());
        }
        return order;
    }
    
    public Order getOrder(String orderId) {
        return orderCache.get(orderId);
    }
    
    public int getQueueSize() {
        return queue.size();
    }
    
    public int getTotalOrdersProcessed() {
        return orderCounter.get();
    }
}

// Producer - Generates orders
public class OrderProducer implements Runnable {
    private final OrderQueue orderQueue;
    private final String name;
    private final Random random = new Random();
    private boolean running = true;
    
    public OrderProducer(OrderQueue orderQueue, String name) {
        this.orderQueue = orderQueue;
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println("🛒 Producer " + name + " started");
        while (running) {
            try {
                // Create random order
                String orderId = "ORD-" + System.currentTimeMillis() + "-" + random.nextInt(100);
                String customerId = "CUST-" + (1000 + random.nextInt(1000));
                List<OrderItem> items = generateItems();
                
                Order order = new Order(orderId, customerId, items);
                orderQueue.addOrder(order);
                
                // Simulate variable production speed
                Thread.sleep(100 + random.nextInt(900));
            } catch (InterruptedException e) {
                running = false;
            }
        }
        System.out.println("🛒 Producer " + name + " stopped");
    }
    
    private List<OrderItem> generateItems() {
        List<OrderItem> items = new ArrayList<>();
        int numItems = 1 + random.nextInt(5);
        String[] products = {"LAPTOP", "PHONE", "TABLET", "HEADPHONES", "MOUSE", "KEYBOARD"};
        double[] prices = {899.99, 699.99, 349.99, 149.99, 29.99, 59.99};
        
        for (int i = 0; i < numItems; i++) {
            int productIndex = random.nextInt(products.length);
            items.add(new OrderItem(
                products[productIndex] + "-" + System.currentTimeMillis(),
                1 + random.nextInt(3),
                prices[productIndex]
            ));
        }
        return items;
    }
    
    public void stop() {
        this.running = false;
    }
}

// Consumer - Processes orders
public class OrderConsumer implements Runnable {
    private final OrderQueue orderQueue;
    private final String name;
    private final Random random = new Random();
    private boolean running = true;
    private long processingTime;
    private int ordersProcessed = 0;
    
    public OrderConsumer(OrderQueue orderQueue, String name) {
        this.orderQueue = orderQueue;
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println("📦 Consumer " + name + " started");
        while (running) {
            try {
                Order order = orderQueue.takeOrder();
                if (order == null) {
                    // No orders available
                    continue;
                }
                
                processOrder(order);
                ordersProcessed++;
                
                // Random processing time based on order complexity
                processingTime = 200 + random.nextInt(800);
                Thread.sleep(processingTime);
                
            } catch (InterruptedException e) {
                running = false;
            }
        }
        System.out.println("📦 Consumer " + name + " stopped. Processed: " + ordersProcessed + " orders");
    }
    
    private void processOrder(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        System.out.println("📝 Consumer " + name + " processing order: " + order.getOrderId());
        System.out.println("   Customer: " + order.getCustomerId());
        System.out.println("   Items: " + order.getItems().size());
        
        // Simulate complex processing
        double total = order.getItems().stream()
                           .mapToDouble(item -> item.getPrice() * item.getQuantity())
                           .sum();
        System.out.println("   Total: $" + String.format("%.2f", total));
        
        // Update order status
        order.setStatus(OrderStatus.SHIPPED);
        System.out.println("✅ Order " + order.getOrderId() + " processed by " + name);
    }
    
    public void stop() {
        this.running = false;
    }
    
    public int getOrdersProcessed() {
        return ordersProcessed;
    }
}

// Usage
public class OrderProcessingSystem {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ORDER PROCESSING SYSTEM ===\n");
        
        OrderQueue orderQueue = new OrderQueue(10);
        
        // Create producers
        OrderProducer producer1 = new OrderProducer(orderQueue, "Producer-1");
        OrderProducer producer2 = new OrderProducer(orderQueue, "Producer-2");
        OrderProducer producer3 = new OrderProducer(orderQueue, "Producer-3");
        
        // Create consumers
        OrderConsumer consumer1 = new OrderConsumer(orderQueue, "Consumer-1");
        OrderConsumer consumer2 = new OrderConsumer(orderQueue, "Consumer-2");
        OrderConsumer consumer3 = new OrderConsumer(orderQueue, "Consumer-3");
        
        // Start producers
        Thread producerThread1 = new Thread(producer1);
        Thread producerThread2 = new Thread(producer2);
        Thread producerThread3 = new Thread(producer3);
        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        
        // Start consumers
        Thread consumerThread1 = new Thread(consumer1);
        Thread consumerThread2 = new Thread(consumer2);
        Thread consumerThread3 = new Thread(consumer3);
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
        
        // Run for 10 seconds
        System.out.println("\n=== SYSTEM RUNNING ===");
        Thread.sleep(10000);
        
        // Stop everything
        System.out.println("\n=== SHUTTING DOWN ===");
        producer1.stop();
        producer2.stop();
        producer3.stop();
        consumer1.stop();
        consumer2.stop();
        consumer3.stop();
        
        producerThread1.join();
        producerThread2.join();
        producerThread3.join();
        consumerThread1.join();
        consumerThread2.join();
        consumerThread3.join();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total orders produced: " + orderQueue.getTotalOrdersProcessed());
        System.out.println("Orders processed by Consumer-1: " + consumer1.getOrdersProcessed());
        System.out.println("Orders processed by Consumer-2: " + consumer2.getOrdersProcessed());
        System.out.println("Orders processed by Consumer-3: " + consumer3.getOrdersProcessed());
        System.out.println("Orders remaining in queue: " + orderQueue.getQueueSize());
    }
}
```

---

## Summary: Decision Matrix for Design Patterns

| Pattern | Primary Use | Complexity | Common In |
|---------|------------|------------|-----------|
| **Factory Method** | Object creation | Low | JDK (Iterator, Collection) |
| **Abstract Factory** | Families of objects | Medium | UI Libraries |
| **Builder** | Complex objects | Medium | Configuration objects |
| **Prototype** | Cloning expensive objects | Low | Prototype-based systems |
| **Singleton** | Single instance | Low | Loggers, Managers |
| **Object Pool** | Reusing expensive objects | Medium | Connection Pools |
| **Adapter** | Interface compatibility | Low | Legacy integration |
| **Decorator** | Dynamic behavior addition | Medium | I/O Streams |
| **Proxy** | Access control/Lazy loading | Medium | Security, Caching |
| **Strategy** | Pluggable algorithms | Low | Payment processing |
| **Observer** | Event notification | Medium | Event handling systems |
| **Chain of Responsibility** | Request handling | Medium | Filter chains |
| **Command** | Request encapsulation | Medium | Undo/Redo operations |
| **Template Method** | Algorithm skeleton | Low | Framework base classes |

**When to NOT use patterns:**
- When the problem is simple
- When the pattern adds unnecessary complexity
- When you don't have experience with the pattern
- When requirements are unclear and changing frequently
