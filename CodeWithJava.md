Of course. Here is a complete and detailed extraction of every coding example from the book "The Complete Coding Interview Guide in Java", including a full description and a dry run of the output, organized by chapter.

***

### **Chapter 6: Object-Oriented Programming**

This chapter covers OOP concepts, SOLID principles, and design patterns through coding challenges and examples.

**Example 1: Abstraction via Interface**
*   **Description:** This example demonstrates the concept of abstraction by creating an interface `Car` that defines a contract for car behaviors (speed up, slow down, turn). The implementation details are hidden in the concrete classes that implement this interface, such as `ElectricCar`. The user of the `Car` interface only knows *what* the car can do, not *how* it does it.
*   **Code:**
    ```java
    public interface Car {
        public void speedUp();
        public void slowDown();
        public void turnRight();
        public void turnLeft();
        public String getCarType();
    }

    public class ElectricCar implements Car {
        private final String carType;
        public ElectricCar(String carType) {
            this.carType = carType;
        }
        @Override
        public void speedUp() {
            System.out.println("Speed up the electric car");
        }
        @Override
        public void slowDown() {
            System.out.println("Slow down the electric car");
        }
        @Override
        public void turnRight() {
            System.out.println("Turn right the electric car");
        }
        @Override
        public void turnLeft() {
            System.out.println("Turn left the electric car");
        }
        @Override
        public String getCarType() {
            return this.carType;
        }
    }

    public class Main {
        public static void main(String[] args) {
            Car electricCar = new ElectricCar("BMW");
            System.out.println("Driving the electric car: " + electricCar.getCarType() + "\n");
            electricCar.speedUp();
            electricCar.turnLeft();
            electricCar.slowDown();
        }
    }
    ```
*   **Dry Run:** The `main` method creates an instance of `ElectricCar` and assigns it to a `Car` reference. This is the abstraction—the code only knows it's a `Car`. The calls to `speedUp`, `turnLeft`, and `slowDown` are made on the `Car` interface, but the specific implementations in `ElectricCar` are executed.
*   **Output:**
    ```
    Driving the electric car: BMW
    
    Speed up the electric car
    Turn left the electric car
    Slow down the electric car
    ```

**Example 2: Encapsulation**
*   **Description:** This example demonstrates encapsulation by hiding the internal state of a `Cat` object (`mood`, `hungry`, `energy`) using the `private` access modifier. The state can only be accessed and modified through public methods (`sleep`, `play`, `feed`), ensuring controlled interaction and preventing external code from putting the object in an invalid state.
*   **Code:**
    ```java
    public class Cat {
        private int mood = 50;
        private int hungry = 50;
        private int energy = 50;

        public void sleep() {
            System.out.println("Sleep ...");
            energy++;
            hungry++;
        }
        public void play() {
            System.out.println("Play ...");
            mood++;
            energy--;
            meow();
        }
        public void feed() {
            System.out.println("Feed ...");
            hungry--;
            mood++;
            meow();
        }
        private void meow() {
            System.out.println("Meow!");
        }

        public int getMood() { return mood; }
        public int getHungry() { return hungry; }
        public int getEnergy() { return energy; }
    }

    public class Main {
        public static void main(String[] args) {
            Cat cat = new Cat();
            cat.feed();
            cat.play();
            cat.feed();
            cat.sleep();
            System.out.println("Energy: " + cat.getEnergy());
            System.out.println("Mood: " + cat.getMood());
            System.out.println("Hungry: " + cat.getHungry());
        }
    }
    ```
*   **Dry Run:** The `main` method creates a new `Cat` object. It then calls `feed()`, `play()`, `feed()`, and `sleep()`. Each of these public methods modifies the private state variables (`mood`, `hungry`, `energy`) and performs actions like printing status and calling the private `meow()` method. Finally, `getters` are used to read the new state.
*   **Output:**
    ```
    Feed ...
    Meow!
    Play ...
    Meow!
    Feed ...
    Meow!
    Sleep ...
    Energy: 50
    Mood: 53
    Hungry: 49
    ```

**Example 3: Inheritance**
*   **Description:** This example demonstrates inheritance. A base class `Employee` defines common attributes and methods (like `name`). A subclass `Programmer` extends `Employee`, inheriting its properties and adding its own specific attributes (like `team`). This promotes code reusability and establishes an "IS-A" relationship (`Programmer` IS-A `Employee`).
*   **Code:**
    ```java
    public class Employee {
        private String name;
        public Employee(String name) {
            this.name = name;
        }
        public String getName() { return name; }
    }

    public class Programmer extends Employee {
        private String team;
        public Programmer(String name, String team) {
            super(name);
            this.team = team;
        }
        public String getTeam() { return team; }
    }

    public class Main {
        public static void main(String[] args) {
            Programmer p = new Programmer("Joana Nimar", "Toronto");
            String name = p.getName();
            String team = p.getTeam();
            System.out.println(name + " is assigned to the " + team + " team");
        }
    }
    ```
*   **Dry Run:** The `main` method creates a `Programmer` object. The `Programmer` constructor calls `super(name)` to initialize the inherited `name` field from the `Employee` class. The `getName()` method, inherited from `Employee`, is called on the `Programmer` object to retrieve the name, demonstrating inheritance of behavior.
*   **Output:**
    ```
    Joana Nimar is assigned to the Toronto team
    ```

**Example 4: Polymorphism via Method Overloading (Compile-Time)**
*   **Description:** This example demonstrates compile-time polymorphism through method overloading. The `Triangle` class has multiple `draw` methods with the same name but different parameters (signatures). The compiler decides which method to call based on the arguments provided at compile time.
*   **Code:**
    ```java
    public class Triangle {
        public void draw() {
            System.out.println("Draw default triangle ...");
        }
        public void draw(String color) {
            System.out.println("Draw a triangle of color " + color);
        }
        public void draw(int size, String color) {
            System.out.println("Draw a triangle of color " + color + " and scale it up with the new size of " + size);
        }
    }

    public class Main {
        public static void main(String[] args) {
            Triangle triangle = new Triangle();
            triangle.draw();
            triangle.draw("red");
            triangle.draw(10, "blue");
        }
    }
    ```
*   **Dry Run:** The `main` method creates a `Triangle` object and calls `draw()` three times with different arguments. The compiler matches each call to the appropriate overloaded version of the `draw` method.
*   **Output:**
    ```
    Draw default triangle ...
    Draw a triangle of color red
    Draw a triangle of color blue and scale it up with the new size of 10
    ```

**Example 5: Polymorphism via Method Overriding (Runtime)**
*   **Description:** This example demonstrates runtime polymorphism through method overriding. An interface `Shape` defines a contract with a `draw()` method. Different classes (`Triangle`, `Rectangle`, `Circle`) implement this interface and provide their own specific implementations for `draw()`. At runtime, the JVM determines the actual object type and calls the correct overridden method.
*   **Code:**
    ```java
    public interface Shape {
        public void draw();
    }

    public class Triangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a triangle ...");
        }
    }
    public class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a rectangle ...");
        }
    }
    public class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a circle ...");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Shape triangle = new Triangle();
            Shape rectangle = new Rectangle();
            Shape circle = new Circle();
            triangle.draw();
            rectangle.draw();
            circle.draw();
        }
    }
    ```
*   **Dry Run:** The `main` method creates objects of `Triangle`, `Rectangle`, and `Circle` but assigns them to `Shape` references. When `draw()` is called on each reference, the JVM dynamically (at runtime) determines the actual object type and calls its overridden `draw()` method.
*   **Output:**
    ```
    Draw a triangle ...
    Draw a rectangle ...
    Draw a circle ...
    ```

**Example 6: Association**
*   **Description:** This example illustrates the concept of association. It shows a "uses-a" relationship where `Person` and `Address` objects can be related. One person can have multiple addresses, and one address can belong to multiple people. Both objects have independent lifecycles.
*   **Code:**
    ```java
    public class Person {
        private String name;
        public Person(String name) {
            this.name = name;
        }
        public String getName() { return name; }
    }

    public class Address {
        private String city;
        private String zip;
        public Address(String city, String zip) {
            this.city = city;
            this.zip = zip;
        }
        public String getCity() { return city; }
        public String getZip() { return zip; }
    }

    public class Main {
        public static void main(String[] args) {
            Person p1 = new Person("Andrei");
            Person p2 = new Person("Marin");
            Address a1 = new Address("Banesti", "107050");
            Address a2 = new Address("Bucuresti", "229344");
            
            System.out.println(p1.getName() + " lives at address " + a2.getCity() + " , " + a2.getZip() + " but it also has an address at " + a1.getCity() + " , " + a1.getZip());
            System.out.println(p2.getName() + " lives at address " + a1.getCity() + " , " + a1.getZip() + " but it also has an address at " + a2.getCity() + " , " + a2.getZip());
        }
    }
    ```
*   **Dry Run:** The `main` method creates `Person` and `Address` objects. It then demonstrates an association between them by associating `p1` with both `a1` and `a2`, and `p2` with both `a1` and `a2`. This shows they are independent but can be linked.
*   **Output:**
    ```
    Andrei lives at address Bucuresti , 229344 but it also has an address at Banesti , 107050
    Marin lives at address Banesti , 107050 but it also has an address at Bucuresti , 229344
    ```

**Example 7: Aggregation**
*   **Description:** Aggregation is a "HAS-A" relationship where one object (the whole) contains another (the part). In this example, a `TennisPlayer` has a `Racket`. The `Racket` object is created independently of the player and can exist even if the `TennisPlayer` is destroyed.
*   **Code:**
    ```java
    public class Rocket {
        private String type;
        private int size;
        private int weight;
        public Rocket(String type, int size, int weight) {
            this.type = type;
            this.size = size;
            this.weight = weight;
        }
        public String getType() { return type; }
    }

    public class TennisPlayer {
        private String name;
        private Rocket racket;
        public TennisPlayer(String name, Rocket racket) {
            this.name = name;
            this.racket = racket;
        }
        public String getName() { return name; }
        public Rocket getRacket() { return racket; }
    }

    public class Main {
        public static void main(String[] args) {
            Rocket racket = new Rocket("Babolat Pure Aero", 100, 300);
            TennisPlayer player = new TennisPlayer("Rafael Nadal", racket);
            System.out.println("Player " + player.getName() + " plays with " + player.getRacket().getType());
        }
    }
    ```
*   **Dry Run:** A `Rocket` object is created and then passed to the `TennisPlayer` constructor. The `TennisPlayer` now has a `Racket`. The `Rocket` has its own lifecycle and can exist without the `TennisPlayer`.
*   **Output:**
    ```
    Player Rafael Nadal plays with Babolat Pure Aero
    ```

**Example 8: Composition**
*   **Description:** Composition is a stronger "PART-OF" relationship. An object's lifetime is tied to its container. In this example, an `Engine` is created inside the `Car` constructor and is destroyed when the `Car` is destroyed.
*   **Code:**
    ```java
    public class Engine {
        private String type;
        private int horsepower;
        public Engine(String type, int horsepower) {
            this.type = type;
            this.horsepower = horsepower;
        }
        public int getHorsepower() { return horsepower; }
    }

    public class Car {
        private final String name;
        private final Engine engine;
        public Car(String name) {
            this.name = name;
            this.engine = new Engine("V8", 300);
        }
        public Engine getEngine() { return engine; }
    }

    public class Main {
        public static void main(String[] args) {
            Car car = new Car("Tesla");
            System.out.println("Horsepower: " + car.getEngine().getHorsepower());
        }
    }
    ```
*   **Dry Run:** When a `Car` is created, its constructor creates an `Engine` object. The `Engine`'s lifecycle is managed by and dependent on the `Car` object.
*   **Output:**
    ```
    Horsepower: 300
    ```

**Example 9: Single Responsibility Principle (SRP)**
*   **Description:** This example demonstrates the SRP by showing a class `RectangleAreaCalculator` that does two things: calculates area and converts units. This violates the principle. The corrected code separates these responsibilities into two classes: `RectangleAreaCalculator` for area calculation and `AreaConverter` for unit conversion.
*   **Code (Incorrect):**
    ```java
    public class RectangleAreaCalculator {
        private static final double INCH_TERM = 0.0254d;
        private final int width;
        private final int height;
        public RectangleAreaCalculator(int width, int height) {
            this.width = width;
            this.height = height;
        }
        public int area() {
            return width * height;
        }
        public double metersToInches(int area) {
            return area / INCH_TERM;
        }
    }
    ```
*   **Code (Correct):**
    ```java
    public class RectangleAreaCalculator {
        private final int width;
        private final int height;
        public RectangleAreaCalculator(int width, int height) {
            this.width = width;
            this.height = height;
        }
        public int area() {
            return width * height;
        }
    }

    public class AreaConverter {
        private static final double INCH_TERM = 0.0254d;
        public double metersToInches(int area) {
            return area / INCH_TERM;
        }
    }
    ```

**Example 10: Open/Closed Principle (OCP)**
*   **Description:** This example demonstrates the OCP. Initially, `AreaCalculator` had to be modified to add new shapes, violating OCP. The corrected solution uses polymorphism. Each shape (`Rectangle`, `Circle`) implements a `Shape` interface with an `area()` method. The `AreaCalculator` is now closed for modification but open for extension; new shapes can be added without changing its code.
*   **Code (Correct):**
    ```java
    public interface Shape {
        public double area();
    }

    public class Rectangle implements Shape {
        private final int width;
        private final int height;
        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }
        public double area() {
            return width * height;
        }
    }

    public class Circle implements Shape {
        private final int radius;
        public Circle(int radius) {
            this.radius = radius;
        }
        @Override
        public double area() {
            return Math.PI * Math.pow(radius, 2);
        }
    }

    public class AreaCalculator {
        private final List<Shape> shapes;
        public AreaCalculator(List<Shape> shapes) {
            this.shapes = shapes;
        }
        public double sum() {
            int sum = 0;
            for (Shape shape : shapes) {
                sum += shape.area();
            }
            return sum;
        }
    }
    ```

**Example 11: Liskov Substitution Principle (LSP)**
*   **Description:** This example illustrates LSP. A `FreeMember` class violates LSP by throwing an exception or printing an error for the `organizeTournament()` method, which it shouldn't support. The corrected solution uses separate interfaces `TournamentJoiner` and `TournamentOrganizer`. `FreeMember` only implements `TournamentJoiner` and can be substituted for any `TournamentJoiner` reference without breaking the application.
*   **Code (Correct):**
    ```java
    public interface TournamentJoiner {
        public void joinTournament();
    }
    public interface TournamentOrganizer {
        public void organizeTournament();
    }

    public abstract class Member implements TournamentJoiner, TournamentOrganizer {
        private final String name;
        public Member(String name) {
            this.name = name;
        }
    }

    public class PremiumMember extends Member {
        public PremiumMember(String name) {
            super(name);
        }
        @Override
        public void joinTournament() {
            System.out.println("Premium member joins tournament");
        }
        @Override
        public void organizeTournament() {
            System.out.println("Premium member organizes tournament");
        }
    }

    public class FreeMember implements TournamentJoiner {
        private final String name;
        public FreeMember(String name) {
            this.name = name;
        }
        @Override
        public void joinTournament() {
            System.out.println("Free member joins tournament ...");
        }
    }
    ```

**Example 12: Interface Segregation Principle (ISP)**
*   **Description:** This example demonstrates ISP. A `Connection` interface with three methods forces `WwwPingConnection` to implement an unused `socket()` method, violating ISP. The corrected solution splits the interface into `HttpConnection` and `SocketConnection`. `WwwPingConnection` only implements the relevant `HttpConnection`.
*   **Code (Correct):**
    ```java
    public interface Connection {
        public void connect();
    }
    public interface HttpConnection extends Connection {
        public void http();
    }
    public interface SocketConnection extends Connection {
        public void socket();
    }

    public class WwwPingConnection implements HttpConnection {
        private final String www;
        public WwwPingConnection(String www) {
            this.www = www;
        }
        @Override
        public void http() {
            System.out.println("Setup an HTTP connection to " + www);
        }
        @Override
        public void connect() {
            System.out.println("Connect to " + www);
        }
    }
    ```

**Example 13: Dependency Inversion Principle (DIP)**
*   **Description:** This example illustrates DIP. High-level `ConnectToDatabase` depended on a low-level concrete class `PostgreSQLJdbcUrl`. The corrected solution introduces an abstraction (`JdbcUrl` interface), making `ConnectToDatabase` depend on the abstraction. Now, it can connect to any database implementing `JdbcUrl` (e.g., `MySQLJdbcUrl`, `OracleJdbcUrl`).
*   **Code (Correct):**
    ```java
    public interface JdbcUrl {
        public String get();
    }

    public class PostgreSQLJdbcUrl implements JdbcUrl {
        private final String dbName;
        public PostgreSQLJdbcUrl(String dbName) {
            this.dbName = dbName;
        }
        @Override
        public String get() {
            return "jdbc://... " + this.dbName;
        }
    }

    public class ConnectToDatabase {
        public void connect(JdbcUrl jdbcUrl) {
            System.out.println("Connecting to " + jdbcUrl.get());
        }
    }
    ```

---

### **Chapter 8: Recursion and Dynamic Programming**

This chapter covers recursive algorithms, Dynamic Programming, and their optimizations.

**Example 1: Plain Recursive Fibonacci**
*   **Description:** This is a simple recursive implementation of the Fibonacci sequence. The `fibonacci(n)` method returns the nth Fibonacci number by calling itself for `n-1` and `n-2`.
*   **Code:**
    ```java
    int fibonacci(int k) {
        if (k <= 1) {
            return k;
        }
        return fibonacci(k - 2) + fibonacci(k - 1);
    }
    ```
*   **Dry Run (for `k=4`):**
    1.  `fibonacci(4)`: `k` is not <= 1, so it returns `fibonacci(2) + fibonacci(3)`.
    2.  `fibonacci(2)`: returns `fibonacci(0) + fibonacci(1)` = `0 + 1 = 1`.
    3.  `fibonacci(3)`: returns `fibonacci(1) + fibonacci(2)`.
    4.  `fibonacci(1)`: returns `1`.
    5.  `fibonacci(2)`: returns `1` (as computed earlier).
    6.  Therefore, `fibonacci(3) = 1 + 1 = 2`.
    7.  Therefore, `fibonacci(4) = 1 + 2 = 3`.
*   **Output:** The function would return `3`. This algorithm is highly inefficient as it recalculates the same values many times.

**Example 2: Recursive Fibonacci with Memoization (Top-Down DP)**
*   **Description:** This recursive implementation uses a `cache` array to store results for values that have already been computed. This eliminates redundant recalculations, significantly improving performance (from exponential to linear).
*   **Code:**
    ```java
    int fibonacci(int k) {
        return fibonacci(k, new int[k + 1]);
    }
    int fibonacci(int k, int[] cache) {
        if (k <= 1) {
            return k;
        } else if (cache[k] > 0) {
            return cache[k];
        }
        cache[k] = fibonacci(k - 2, cache) + fibonacci(k - 1, cache);
        return cache[k];
    }
    ```
*   **Dry Run (for `k=4`):**
    1.  `fibonacci(4, cache[5])`:
        - `k=4`. `cache[4]` is `0`.
        - Computes `fibonacci(2, cache) + fibonacci(3, cache)`.
    2.  `fibonacci(2, cache)`:
        - `k=2`. `cache[2]` is `0`.
        - Computes `fibonacci(0, cache) + fibonacci(1, cache)`.
        - `fibonacci(0)` returns `0`. `fibonacci(1)` returns `1`.
        - `cache[2] = 1`. Returns `1`.
    3.  `fibonacci(3, cache)`:
        - `k=3`. `cache[3]` is `0`.
        - Computes `fibonacci(1, cache) + fibonacci(2, cache)`.
        - `fibonacci(1)` returns `1`. `fibonacci(2)` checks `cache[2] > 0`, so it returns the cached value `1`.
        - `cache[3] = 2`. Returns `2`.
    4.  `cache[4] = 1 + 2 = 3`. Returns `3`.
*   **Output:** The function returns `3`. The time complexity is now O(n).

**Example 3: Iterative Fibonacci with Tabulation (Bottom-Up DP)**
*   **Description:** This iterative solution avoids recursion and uses a bottom-up approach. It starts from the base cases (`fib(0)`, `fib(1)`) and builds the sequence up to `n`.
*   **Code:**
    ```java
    int fibonacci(int k) {
        if (k <= 1) {
            return k;
        }
        int first = 1;
        int second = 0;
        int result = 0;
        for (int i = 1; i < k; i++) {
            result = first + second;
            second = first;
            first = result;
        }
        return result;
    }
    ```
*   **Dry Run (for `k=4`):**
    - `first = 1`, `second = 0`, `result = 0`.
    - `i=1`: `result = 1 + 0 = 1`. `second = 1`. `first = 1`.
    - `i=2`: `result = 1 + 1 = 2`. `second = 1`. `first = 2`.
    - `i=3`: `result = 2 + 1 = 3`. `second = 2`. `first = 3`.
    - Loop ends. Returns `3`.
*   **Output:** The function returns `3`. Time complexity is O(n), space complexity is O(1).

**Example 4: Robot Grid (with Memoization)**
*   **Description:** A robot needs to find a path from the top-left corner `(m, n)` to the bottom-right corner `(0, 0)` in a grid, moving only right or down, while avoiding obstacles (marked `true` in `maze[][]`).
*   **Code:**
    ```java
    public static boolean computePath(int m, int n, boolean[][] maze, Set<Point> path) {
        if (m < 0 || n < 0) {
            return false;
        }
        if (maze[m][n]) {
            return false;
        }
        if (((m == 0) && (n == 0)) || computePath(m, n - 1, maze, path) || computePath(m - 1, n, maze, path)) {
            path.add(new Point(m, n));
            return true;
        }
        return false;
    }
    ```
*   **Dry Run (conceptually):** The algorithm starts at the goal `(m, n)`. If it's a valid cell, it recursively tries to move `up` `(m-1, n)` or `left` `(m, n-1)`. It backtracks if it hits an obstacle or falls off the grid. If a path is found to `(0,0)`, all cells on that path are added to the `path` set. With memoization, cells that are found to be dead ends are cached to avoid recomputation.

**Example 5: Tower of Hanoi**
*   **Description:** A classic recursion problem. `n` disks of different sizes on one rod must be moved to another rod, following rules: one disk at a time, larger disks cannot be placed on smaller ones.
*   **Code:**
    ```java
    public static void moveDisks(int n, char origin, char target, char intermediate) {
        if (n <= 0) {
            return;
        }
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + origin + " to rod " + target);
            return;
        }
        moveDisks(n - 1, origin, intermediate, target);
        System.out.println("Move disk " + n + " from rod " + origin + " to rod " + target);
        moveDisks(n - 1, intermediate, target, origin);
    }
    ```
*   **Dry Run (for `n=2`, `origin='A'`, `target='C'`, `intermediate='B'`):**
    1.  `moveDisks(2, A, C, B)`:
        - Calls `moveDisks(1, A, B, C)`.
            - Prints "Move disk 1 from rod A to rod B".
        - Prints "Move disk 2 from rod A to rod C".
        - Calls `moveDisks(1, B, C, A)`.
            - Prints "Move disk 1 from rod B to rod C".
*   **Output:** The output would show the sequence of moves to solve the puzzle for 2 disks.

**Example 6: Josephus Problem**
*   **Description:** `n` men are arranged in a circle. Every `k-th` man is eliminated until only one survivor remains. The problem is to find the position of the survivor.
*   **Code (Recursive):**
    ```java
    public static int josephus(int n, int k) {
        if (n == 1) {
            return 1;
        } else {
            return (josephus(n - 1, k) + k - 1) % n + 1;
        }
    }
    ```
*   **Dry Run (for `n=5`, `k=3`):**
    - `josephus(5,3)`:
        - `= (josephus(4,3) + 2) % 5 + 1`.
        - `josephus(4,3)`: `= (josephus(3,3) + 2) % 4 + 1`.
        - `josephus(3,3)`: `= (josephus(2,3) + 2) % 3 + 1`.
        - `josephus(2,3)`: `= (josephus(1,3) + 2) % 2 + 1`.
        - `josephus(1,3)`: returns `1`.
        - `josephus(2,3)`: `= (1 + 2) % 2 + 1 = 3 % 2 + 1 = 1 + 1 = 2`.
        - `josephus(3,3)`: `= (2 + 2) % 3 + 1 = 4 % 3 + 1 = 1 + 1 = 2`.
        - `josephus(4,3)`: `= (2 + 2) % 4 + 1 = 4 % 4 + 1 = 1`.
        - `josephus(5,3)`: `= (1 + 2) % 5 + 1 = 3 % 5 + 1 = 4`.
*   **Output:** The function returns `4`, the position of the survivor.

**Example 7: Color Spots (Largest Connected Set)**
*   **Description:** Given a grid where each cell has a color (represented by a number), find the size and color of the largest connected region (color spot). The algorithm uses DFS to explore all connected cells of the same color.
*   **Code (Recursive Search):**
    ```java
    private void computeColorSpot(int i, int j, int cols, int rows, int a[][], int color) {
        a[i][j] = -a[i][j];
        currentColorSpot++;
        if (i > 1 && a[i - 1][j] == color) {
            computeColorSpot(i - 1, j, cols, rows, a, color);
        }
        if ((i + 1) < rows && a[i + 1][j] == color) {
            computeColorSpot(i + 1, j, cols, rows, a, color);
        }
        if (j > 1 && a[i][j - 1] == color) {
            computeColorSpot(i, j - 1, cols, rows, a, color);
        }
        if ((j + 1) < cols && a[i][j + 1] == color) {
            computeColorSpot(i, j + 1, cols, rows, a, color);
        }
    }
    ```
*   **Dry Run (conceptually):** Starting from a cell `(i, j)` with color `c`, the algorithm recursively visits all adjacent cells (up, down, left, right) that also have color `c`. It marks each visited cell as visited (by making it negative `-c`) to avoid re-counting. It increments `currentColorSpot` for each visited cell. Once the recursion completes, the size of the spot is known. This is called for every unvisited cell to find the largest spot.

**Example 8: Coins (Making Change)**
*   **Description:** Find the number of ways to make change for `n` cents using an unlimited number of coins of denominations 25, 10, 5, and 1.
*   **Code (with Memoization):**
    ```java
    public static int calculateChangeMemoization(int n) {
        int[] coins = {25, 10, 5, 1};
        int[][] cache = new int[n + 1][coins.length];
        return calculateChangeMemoization(n, coins, 0, cache);
    }
    private static int calculateChangeMemoization(int amount, int[] coins, int position, int[][] cache) {
        if (cache[amount][position] > 0) {
            return cache[amount][position];
        }
        if (position >= coins.length - 1) {
            return 1;
        }
        int coin = coins[position];
        int count = 0;
        for (int i = 0; i * coin <= amount; i++) {
            int remaining = amount - i * coin;
            count += calculateChangeMemoization(remaining, coins, position + 1, cache);
        }
        cache[amount][position] = count;
        return count;
    }
    ```
*   **Dry Run (conceptually):** The algorithm uses recursion to try every combination of coins, starting from the largest denomination. The `position` parameter keeps track of which coin type is being used. For each coin, the function iterates through all possible multiples of that coin (`i * coin`) and calculates the remaining amount. It then recursively finds ways to make change for the remaining amount using the next coin denomination. Memoization is used to store the `count` for a given `amount` and `position` to avoid recomputation.

**Example 9: String Permutations (with Duplicates)**
*   **Description:** This example finds all permutations of a string, handling duplicates efficiently. It uses a map to count the frequency of each character and then generates permutations by selecting one character at a time.
*   **Code (Main Method):**
    ```java
    private static List<String> permute(String prefix, int strlength, Map<Character, Integer> characters) {
        List<String> permutations = new ArrayList<>();
        if (strlength == 0) {
            permutations.add(prefix);
        } else {
            for (Character c : characters.keySet()) {
                int count = characters.get(c);
                if (count > 0) {
                    characters.put(c, count - 1);
                    permutations.addAll(permute(prefix + c, strlength - 1, characters));
                    characters.put(c, count);
                }
            }
        }
        return permutations;
    }
    ```
*   **Dry Run (for `str = "aab"`):**
    1.  Character count map: `{'a':2, 'b':1}`.
    2.  Loop through keys. For each key `c` with `count > 0`, it decrements the count in the map, appends `c` to `prefix`, and recursively calls itself with `strlength - 1`.
    3.  This process builds all permutations without generating duplicates, as the selection process inherently avoids reusing identical characters in the same position in a redundant way.

---

### **Chapter 9: Bit Manipulation**

This chapter focuses on bitwise operations and their applications in algorithms.

**Example 1: Get the Bit Value**
*   **Description:** This code snippet returns the value of the bit (0 or 1) at a specific position `k` in a 32-bit integer `n`.
*   **Code:**
    ```java
    public static char getValue(int n, int k) {
        int result = n & (1 << k);
        if (result == 0) {
            return '0';
        }
        return '1';
    }
    ```
*   **Dry Run (for `n=423` (110100111), `k=7`):**
    1.  `1 << k` = `1 << 7` = `10000000` (binary).
    2.  `n & (1 << k)` = `110100111 & 10000000`. Since the bit at position 7 (0-indexed) of `n` is `1`, the result is `10000000` which is non-zero.
    3.  The function returns `'1'`.
*   **Output:** `'1'`.

**Example 2: Set the Bit Value to 0/1**
*   **Description:** This code snippet sets the bit at a specific position `k` in an integer `n` to either 0 or 1.
*   **Code (Set to 0):**
    ```java
    public static int setValueTo0(int n, int k) {
        return n & ~(1 << k);
    }
    ```
*   **Dry Run (for `n=423` (110100111), `k=7` to set to 0):**
    1.  `1 << k` = `1 << 7` = `10000000`.
    2.  `~(1 << k)` = `11111111111111111111111101111111` (in 32-bit).
    3.  `n & ~(1 << k)` = `110100111 & 111...01111111`. This clears the bit at position 7.
*   **Code (Set to 1):**
    ```java
    public static int setValueTo1(int n, int k) {
        return n | (1 << k);
    }
    ```
*   **Dry Run (for `n=295` (100100111), `k=7` to set to 1):**
    1.  `1 << k` = `1 << 7` = `10000000`.
    2.  `n | (1 << k)` = `100100111 | 10000000`. This sets the bit at position 7 to `1`.

**Example 3: Clear Bits**
*   **Description:** This code snippet clears (sets to 0) all bits in an integer `n` from the Most Significant Bit (MSB) down to a given position `k`.
*   **Code (Clear from MSB to k):**
    ```java
    public static int clearFromMsb(int n, int k) {
        return n & ((1 << k) - 1);
    }
    ```
*   **Dry Run (for `n=423` (110100111), `k=6`):**
    1.  `1 << k` = `1 << 6` = `1000000`.
    2.  `(1 << k) - 1` = `1000000 - 1` = `0111111`.
    3.  `n & ((1 << k) - 1)` = `110100111 & 0111111`. This keeps only the lower `k` bits of `n`.

**Example 4: Summing Binaries in Code**
*   **Description:** This code snippet computes the sum of two integers `q` and `p` using bitwise operators (XOR, AND, and left shift). It simulates the process of binary addition by handling carries.
*   **Code:**
    ```java
    public static int sum(int q, int p) {
        int xor;
        int and;
        int t;
        and = q & p;
        xor = q ^ p;
        while (and != 0) {
            and = and << 1;
            t = xor ^ and;
            and = and & xor;
            xor = t;
        }
        return xor;
    }
    ```
*   **Dry Run (for `q=5` (101), `p=3` (011)):**
    1.  `and = 5 & 3 = 1 (001)`. `xor = 5 ^ 3 = 6 (110)`.
    2.  `while(and != 0)`:
        - `and = 1 << 1 = 2 (010)`.
        - `t = 6 ^ 2 = 4 (100)`.
        - `and = 2 & 6 = 2 (010)`.
        - `xor = 4 (100)`.
    3.  `while(and != 0)`:
        - `and = 2 << 1 = 4 (100)`.
        - `t = 4 ^ 4 = 0`.
        - `and = 4 & 4 = 4 (100)`.
        - `xor = 0`.
    4.  `while(and != 0)`:
        - `and = 4 << 1 = 8 (1000)`.
        - `t = 0 ^ 8 = 8`.
        - `and = 8 & 0 = 0`.
        - `xor = 8`.
    5.  Loop ends. Returns `8`.
*   **Output:** The function returns `8`, which is `5 + 3`.

**Example 5: Multiplying Binaries in Code**
*   **Description:** This code snippet computes the product of two integers `q` and `p` using bitwise operations and shifts. It simulates the process of binary multiplication.
*   **Code:**
    ```java
    public static int multiply(int q, int p) {
        int result = 0;
        while (p != 0) {
            if ((p & 1) != 0) {
                result = result + q;
            }
            q = q << 1;
            p = p >>> 1;
        }
        return result;
    }
    ```
*   **Dry Run (for `q=5` (101), `p=3` (011)):**
    1.  `while(p != 0)`:
        - `p & 1 = 3 & 1 = 1`, so `result = 0 + 5 = 5`.
        - `q = 5 << 1 = 10 (1010)`.
        - `p = 3 >>> 1 = 1 (001)`.
    2.  `while(p != 0)`:
        - `p & 1 = 1 & 1 = 1`, so `result = 5 + 10 = 15`.
        - `q = 10 << 1 = 20 (10100)`.
        - `p = 1 >>> 1 = 0`.
    3.  Loop ends. Returns `15`.
*   **Output:** The function returns `15`, which is `5 * 3`.

**Example 6: Subtracting Binaries in Code**
*   **Description:** This code snippet computes the subtraction `q - p` using bitwise operations (XOR, AND, NOT, and left shift). It simulates the process of borrowing in binary subtraction.
*   **Code:**
    ```java
    public static int subtract(int q, int p) {
        while (p != 0) {
            int borrow = (~q) & p;
            q = q ^ p;
            p = borrow << 1;
        }
        return q;
    }
    ```
*   **Dry Run (for `q=5` (101), `p=3` (011)):**
    1.  `while(p != 0)`:
        - `borrow = (~5) & 3`. `~5` in 2-bit representation is `...1010`. `...1010 & 0011 = 0010 (2)`.
        - `q = 5 ^ 3 = 6 (110)`.
        - `p = 2 << 1 = 4 (100)`.
    2.  `while(p != 0)`:
        - `borrow = (~6) & 4`. `~6` is `...1001`. `...1001 & 0100 = 0000 (0)`.
        - `q = 6 ^ 4 = 2 (010)`.
        - `p = 0 << 1 = 0`.
    3.  Loop ends. Returns `2`.
*   **Output:** The function returns `2`, which is `5 - 3`.

**Example 7: Replacing Bits**
*   **Description:** This code snippet replaces a set of bits in an integer `q` from position `i` to `j` with the bits from another integer `p`.
*   **Code:**
    ```java
    public static int replace(int q, int p, int i, int j) {
        int ones = ~0;
        int leftShiftJ = ones << (j + 1);
        int leftShiftI = ((1 << i) - 1);
        int mask = leftShiftJ | leftShiftI;
        int applyMaskToQ = q & mask;
        int bringPInPlace = p << i;
        return applyMaskToQ | bringPInPlace;
    }
    ```
*   **Dry Run (for `q=4914` (1001100110010), `p=63` (111111), `i=4`, `j=9`):**
    The code creates a mask that has 1s everywhere except in the `i` to `j` range. `q & mask` clears those bits. `p << i` shifts `p` into the correct position. The final `|` combines the cleared `q` and the shifted `p` to create the result.

---

### **Chapter 10: Arrays and Strings**

This chapter presents various algorithms and challenges related to processing arrays and strings.

**Example 1: Unique Characters**
*   **Description:** This code snippet checks if a string contains all unique characters using a `HashMap` to keep track of seen characters.
*   **Code:**
    ```java
    private static final int MAX_CODE = 65535;
    public static boolean isUnique(String str) {
        Map<Character, Boolean> chars = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.codePointAt(i) <= MAX_CODE) {
                char ch = str.charAt(i);
                if (!Character.isWhitespace(ch)) {
                    if (chars.put(ch, true) != null) {
                        return false;
                    }
                }
            } else {
                System.out.println("The given string contains unallowed characters");
                return false;
            }
        }
        return true;
    }
    ```
*   **Dry Run (for `str = "hello"`):** The algorithm will iterate through each character 'h', 'e', 'l', 'l', 'o'. When it encounters the second 'l', the `put` operation will return the previous value (true), as 'l' is already in the map. The condition `chars.put(ch, true) != null` will be true, and the method will return `false`.
*   **Output:** `false`.

**Example 2: Encoding Strings**
*   **Description:** This code snippet replaces all whitespace characters in a `char[]` with the sequence `%20`.
*   **Code (Main Logic):**
    ```java
    public static char[] encodeWhitespaces(char[] str) {
        int countWhitespaces = 0;
        for (int i = 0; i < str.length; i++) {
            if (Character.isWhitespace(str[i])) {
                countWhitespaces++;
            }
        }
        if (countWhitespaces > 0) {
            char[] encodedStr = new char[str.length + countWhitespaces * 2];
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                if (Character.isWhitespace(str[i])) {
                    encodedStr[index] = '0';
                    encodedStr[index + 1] = '2';
                    encodedStr[index + 2] = '%';
                    index = index + 3;
                } else {
                    encodedStr[index] = str[i];
                    index++;
                }
            }
            return encodedStr;
        }
        return str;
    }
    ```
*   **Dry Run (for `str = " String with spaces ".toCharArray()`):**
    - First, the code counts the spaces. Let's assume there are 4 spaces. The new array size will be `original_length + (4 * 2)`.
    - It then loops through the original array and copies characters. When a space is encountered, it copies `'0'`, `'2'`, and `'%'` in order and increments the index accordingly.
*   **Output:** A `char[]` containing the string `"%20String%20%20%20with%20spaces%20%20"`.

**Example 3: One Edit Away**
*   **Description:** This code checks if two strings are one edit (insert, remove, or replace a character) away from being equal.
*   **Code (Main Logic):**
    ```java
    public static boolean isOneEditAway(String q, String p) {
        if (Math.abs(q.length() - p.length()) > 1) {
            return false;
        }
        String shorter = q.length() < p.length() ? q : p;
        String longer = q.length() < p.length() ? p : q;
        int is = 0;
        int il = 0;
        boolean marker = false;
        while (is < shorter.length() && il < longer.length()) {
            if (shorter.charAt(is) != longer.charAt(il)) {
                if (marker) {
                    return false;
                }
                marker = true;
                if (shorter.length() == longer.length()) {
                    is++;
                }
            } else {
                is++;
            }
            il++;
        }
        return true;
    }
    ```
*   **Dry Run (for `q="tank"`, `p="tanc"`):**
    - Lengths are equal.
    - The `while` loop compares characters: 't'=='t', 'a'=='a', 'n'=='n'.
    - At `is=3`, `il=3`, it compares 'k' and 'c' which differ. `marker` is false, so it becomes true. The lengths are equal, so `is` is incremented.
    - The loop continues with `is=4`, `il=4`. The loop condition `is < shorter.length()` is false, and the while loop ends.
    - Returns true.
*   **Output:** `true`.

**Example 4: Shrinking a String**
*   **Description:** This code shrinks a string by replacing consecutive repeated characters with the character and its count (e.g., "aaabb" -> "a3b2"). It only copies whitespace.
*   **Code (Main Logic):**
    ```java
    public static String shrink(String str) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            count++;
            if (!Character.isWhitespace(str.charAt(i))) {
                if ((i + 1) >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                    result.append(str.charAt(i)).append(count);
                    count = 0;
                }
            } else {
                result.append(str.charAt(i));
                count = 0;
            }
        }
        return result.length() > str.length() ? str : result.toString();
    }
    ```
*   **Dry Run (for `str = "abbb vvvv s rttt rr eeee f"`):**
    - The loop iterates through each character. It increments `count` each time.
    - For 'a', the next char is 'b' (different), so it appends "a1" and resets `count`.
    - For 'b', 'b', 'b', it counts them. When the loop reaches the space after "bbb", it appends "b3".
    - It handles whitespace separately, copying them directly.
*   **Output:** `a1b3 v4 s1 r1t3 r2 e4 f1`.

**Example 5: Rotating a Matrix by 90 Degrees**
*   **Description:** This code rotates a square matrix (`n x n`) by 90 degrees counterclockwise in place.
*   **Code (Ring Rotation):**
    ```java
    public static boolean rotateRing(int[][] m) {
        int len = m.length;
        for (int i = 0; i < len / 2; i++) {
            for (int j = i; j < len - i - 1; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][len - 1 - i];
                m[j][len - 1 - i] = m[len - 1 - i][len - 1 - j];
                m[len - 1 - i][len - 1 - j] = m[len - 1 - j][i];
                m[len - 1 - j][i] = temp;
            }
        }
        return true;
    }
    ```
*   **Dry Run (conceptually):** The algorithm rotates the matrix layer by layer (from the outermost ring to the innermost). For each layer, it moves the top edge to the right edge, the right edge to the bottom edge, the bottom edge to the left edge, and the left edge to the top edge in a cyclic manner.

**Example 6: Matrix Containing Zeros**
*   **Description:** This code sets an entire row and column to zero if any element in that row/column is zero.
*   **Code (Main Logic):**
    ```java
    // Search for zeros in first row and column
    boolean firstRowHasZeros = false;
    boolean firstColumnHasZeros = false;
    // Mark zeros on first row and column
    for (int i = 1; i < m.length; i++) {
        for (int j = 1; j < m[0].length; j++) {
            if (m[i][j] == 0) {
                m[i][0] = 0;
                m[0][j] = 0;
            }
        }
    }
    // Propagate zeros from first row and column to the rest
    for (int i = 1; i < m.length; i++) {
        if (m[i][0] == 0) { setRowOfZero(m, i); }
    }
    for (int j = 1; j < m[0].length; j++) {
        if (m[0][j] == 0) { setColumnOfZero(m, j); }
    }
    // Handle the first row and column themselves
    if (firstRowHasZeros) { setRowOfZero(m, 0); }
    if (firstColumnHasZeros) { setColumnOfZero(m, 0); }
    ```
*   **Dry Run:** The algorithm first notes if the first row or column has a zero. It then uses the first row and column as a "marker" to indicate which rows/columns need to be zeroed. Finally, it zeros out the appropriate rows and columns based on these markers.

**Example 7: Implementing Three Stacks with One Array**
*   **Description:** This code implements three stacks using a single fixed-size array by interleaving their elements and managing free slots.
*   **Code (Conceptual Data Structure):**
    ```java
    public class ThreeStack {
        private static final int STACK_CAPACITY = 15;
        private final StackNode[] theArray;
        private int nextFreeSlot;
        private final int[] backLinks = {-1, -1, -1};
        // ... push, pop, etc.
    }
    ```
*   **Dry Run (Conceptually):** The array is divided into nodes. Each node has a `value` and a `backLink` to the previous node in its stack. A `nextFreeSlot` variable tracks the first available node to use. Pushing a value involves taking a free node, setting its `value`, linking it to the current top of that stack, and updating the `backLinks` array for that stack. Popping a node involves removing the top node of a stack and making it free again for reuse.

**Example 8: Find Pairs with Sum Equal to K**
*   **Description:** This code finds all pairs in an array that sum to a given target `k`.
*   **Code (Main Logic):**
    ```java
    public static List<String> pairs(int[] m, int k) {
        if (m == null || m.length < 2) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        java.util.Arrays.sort(m);
        int l = 0;
        int r = m.length - 1;
        while (l < r) {
            int sum = m[l] + m[r];
            if (sum == k) {
                result.add("(" + m[l] + " + " + m[r] + ")");
                l++;
                r--;
            } else if (sum < k) {
                l++;
            } else if (sum > k) {
                r--;
            }
        }
        return result;
    }
    ```
*   **Dry Run (for `m = [-5, -2, 5, 4, 3, 7, 2, 1, -1, -2, 15, 6, 12, -4, 3]`, `k=10`):**
    - The array is sorted.
    - Pointers `l` and `r` point to the start and end of the sorted array.
    - The algorithm calculates `sum`. If `sum == k`, it adds the pair to the result and moves both pointers. If `sum < k`, it increments `l` to increase the sum. If `sum > k`, it decrements `r` to decrease the sum.
*   **Output:** The list will contain the pairs that sum to 10.

**Example 9: Container With Most Water**
*   **Description:** This code finds the maximum area of water that can be contained between two vertical lines represented by an array of heights.
*   **Code (Main Logic):**
    ```java
    public static int maxAreaOptimized(int[] heights) {
        int maxArea = 0;
        int i = 0;
        int j = heights.length - 1;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(heights[i], heights[j]) * (j - i));
            if (heights[i] <= heights[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
    ```
*   **Dry Run:** The algorithm uses a two-pointer approach, starting from the widest possible container. It calculates the area, then moves the pointer pointing to the shorter line inwards, as the area is limited by the shorter line and moving the other pointer wouldn't help find a larger area. This continues until the pointers meet.

**Example 10: Trap Rain Water**
*   **Description:** This code calculates the total amount of rainwater that can be trapped between bars of varying heights.
*   **Code (Two-Pointers):**
    ```java
    public static int trap(int[] bars) {
        int left = 0;
        int right = bars.length - 1;
        int water = 0;
        int maxBarLeft = bars[left];
        int maxBarRight = bars[right];
        while (left < right) {
            if (bars[left] <= bars[right]) {
                left++;
                maxBarLeft = Math.max(maxBarLeft, bars[left]);
                water += (maxBarLeft - bars[left]);
            } else {
                right--;
                maxBarRight = Math.max(maxBarRight, bars[right]);
                water += (maxBarRight - bars[right]);
            }
        }
        return water;
    }
    ```
*   **Dry Run:** The algorithm uses two pointers, moving from the sides inward. It keeps track of the maximum height seen from the left and right. The water trapped at a position is the difference between the minimum of the max heights seen so far and the current bar's height, but only when that min is greater.

---

### **Chapter 11: Linked Lists and Maps**

This chapter covers algorithms and implementations specific to linked lists and hash maps.

**Example 1: Basic Map Implementation (Put, Get, Remove)**
*   **Description:** A basic implementation of a map data structure using an array of `MyEntry` objects. It provides `put`, `get`, and `remove` operations.
*   **Code (Conceptual Data Structure & Methods):**
    ```java
    private static final int DEFAULT_CAPACITY = 16;
    private MyEntry<K, V>[] entries = new MyEntry[DEFAULT_CAPACITY];
    private int size;

    public void put(K key, V value) {
        // Check if key exists, update if so. If not, add new entry, resizing array if needed.
    }
    public V get(K key) {
        // Linear search through entries to find matching key.
    }
    public void remove(K key) {
        // Linear search to find key, remove by setting to null and condensing the array.
    }
    ```
*   **Dry Run (Conceptually):** The map stores entries in an array. It checks for the key linearly. If found, it updates the value in `put` or returns it in `get`. In `remove`, it nullifies the entry and shifts subsequent entries to fill the gap. This is a simple linear-probing implementation, not production-ready.

**Example 2: Remove Duplicates from Linked List**
*   **Description:** This code removes duplicate values from an unsorted singly linked list.
*   **Code (Using a Set):**
    ```java
    public void removeDuplicates() {
        Set<Integer> dataSet = new HashSet<>();
        Node currentNode = head;
        Node prevNode = null;
        while (currentNode != null) {
            if (dataSet.contains(currentNode.data)) {
                prevNode.next = currentNode.next;
                if (currentNode == tail) {
                    tail = prevNode;
                }
                size--;
            } else {
                dataSet.add(currentNode.data);
                prevNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }
    ```
*   **Dry Run:** The algorithm iterates through the list, storing seen data in a `HashSet`. When it encounters a node whose data is already in the set, it removes that node by skipping it and linking the previous node to the next node. The `prevNode` pointer is maintained to achieve this.

**Example 3: Find Loop Start in Linked List**
*   **Description:** This code detects a loop in a linked list and finds the starting node of the loop.
*   **Code (Fast Runner/Slow Runner):**
    ```java
    public void findLoopStartNode() {
        Node slowRunner = head;
        Node fastRunner = head;
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if (slowRunner == fastRunner) { // Meeting point
                break;
            }
        }
        if (fastRunner == null || fastRunner.next == null) {
            return; // No loop
        }
        slowRunner = head;
        while (slowRunner != fastRunner) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }
        // Both pointers now point to the start of the loop.
    }
    ```
*   **Dry Run:** The algorithm uses two pointers (Fast and Slow). They are guaranteed to meet inside the loop if one exists. After they meet, the Slow pointer is moved back to the head. They then move at the same speed. The point where they meet again is the start of the loop.

**Example 4: Check if Linked List is a Palindrome**
*   **Description:** This code checks if a singly linked list is a palindrome.
*   **Code:**
    ```java
    public boolean isPalindrome() {
        Node fastRunner = head;
        Node slowRunner = head;
        Stack<Integer> firstHalf = new Stack<>();
        while (fastRunner != null && fastRunner.next != null) {
            firstHalf.push(slowRunner.data);
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        if (fastRunner != null) { // Odd number of elements
            slowRunner = slowRunner.next;
        }
        while (slowRunner != null) {
            int top = firstHalf.pop();
            if (top != slowRunner.data) {
                return false;
            }
            slowRunner = slowRunner.next;
        }
        return true;
    }
    ```
*   **Dry Run:** The Fast Runner/Slow Runner approach is used. The first half of the list's data is pushed onto a stack. Once the middle is reached, the second half is traversed, and its data is compared with the popped data from the stack. If all match, it's a palindrome.

**Example 5: Sum Two Linked Lists**
*   **Description:** This code adds two numbers represented by linked lists (where each node contains a digit).
*   **Code (Recursive Sum):**
    ```java
    private Node sum(Node node1, Node node2, int carry) {
        if (node1 == null && node2 == null && carry == 0) {
            return null;
        }
        Node resultNode = new Node();
        int value = carry;
        if (node1 != null) {
            value += node1.data;
        }
        if (node2 != null) {
            value += node2.data;
        }
        resultNode.data = value % 10;
        if (node1 != null || node2 != null) {
            Node more = sum(node1 == null ? null : node1.next,
                            node2 == null ? null : node2.next,
                            value >= 10 ? 1 : 0);
            resultNode.next = more;
        }
        return resultNode;
    }
    ```
*   **Dry Run:** The algorithm uses recursion. It starts from the least significant digit (head of the lists). It adds the current node's values from both lists and any carry from the previous step. It creates a new node with the sum's last digit (`value % 10`) and calls itself for the next pair of nodes, passing the new carry. The result is a new linked list representing the sum.

**Example 6: Merge Two Sorted Linked Lists**
*   **Description:** This code merges two sorted singly linked lists into a single sorted list without using extra space (by rearranging pointers).
*   **Code (Main Logic):**
    ```java
    public void merge(SinglyLinkedList sll) {
        Node list1 = head;
        Node list2 = sll.head;
        if (list1.data < list2.data) {
            head = list1;
        } else {
            head = list2;
            list2 = list1;
            list1 = head;
        }
        while (list1.next != null) {
            if (list1.next.data > list2.data) {
                Node auxNode = list1.next;
                list1.next = list2;
                list2 = auxNode;
            }
            list1 = list1.next;
        }
        if (list1.next == null) {
            list1.next = list2;
        }
    }
    ```
*   **Dry Run:** The algorithm selects the head of the merged list as the smaller of the two heads. It then iterates through the selected list (`list1`). If the next element in `list1` is larger than the head of `list2`, it swaps the nodes to maintain order. This process continues until one list is exhausted, at which point the rest of the other list is appended.

**Example 7: LRU Cache Implementation**
*   **Description:** This code implements a Least Recently Used (LRU) cache with a fixed capacity using a `HashMap` and a doubly linked list.
*   **Code (Conceptual Data Structure):**
    ```java
    private final class Node {
        private int key;
        private int value;
        private Node next;
        private Node prev;
    }
    private final Map<Integer, Node> hashmap;
    private Node head;
    private Node tail;
    // ...
    public void putEntry(int key, int value) {
        // If key exists, update node value and move to head.
        // If key is new, create a node. If at capacity, remove the tail (least recently used) and its key from the map.
        // Add the new node to the head.
    }
    ```
*   **Dry Run:** The cache is maintained as a doubly linked list, where the `head` is the most recently used and the `tail` is the least recently used. A `HashMap` maps a key to its corresponding `Node` in the list. When an entry is accessed (`get`), its node is moved to the `head`. When a new entry is added (`put`), it's added to the `head`. If the cache is full, the `tail` node is removed from the list and its key is removed from the map.

---

### **Chapter 12: Stacks and Queues**

This chapter covers algorithms and implementations specific to the stack and queue data structures.

**Example 1: Stack of Curly Braces**
*   **Description:** This code checks if a string containing curly braces `{` and `}` is properly matched (i.e., every opening brace has a corresponding closing brace in the correct order).
*   **Code (Main Logic):**
    ```java
    public static boolean bracesMatching(String bracesStr) {
        Stack<Character> stackBraces = new Stack<>();
        for (int i = 0; i < bracesStr.length(); i++) {
            switch (bracesStr.charAt(i)) {
                case '{':
                    stackBraces.push(bracesStr.charAt(i));
                    break;
                case '}':
                    if (stackBraces.isEmpty()) {
                        return false;
                    }
                    stackBraces.pop();
                    break;
                default:
                    return false;
            }
        }
        return stackBraces.empty();
    }
    ```
*   **Dry Run (for `"{{}}"`):**
    - `{`: Push `{`. Stack: `[`{`]`.
    - `{`: Push `{`. Stack: `[`{`, `{`]`.
    - `}`: Stack is not empty, pop `{`. Stack: `[`{`]`.
    - `}`: Stack is not empty, pop `{`. Stack: `[]`.
    - Returns `stack.empty()` = `true`.
*   **Output:** `true`.

**Example 2: Stack of Plates**
*   **Description:** This code implements a set of stacks, where a new stack is created when the current one reaches a certain capacity. It acts like a single stack for `push` and `pop` operations and also provides a `popAt` method.
*   **Code (Main Logic):**
    ```java
    private final LinkedList<Stack<Integer>> stacks = new LinkedList<>();
    private static final int STACK_SIZE = 3;

    public void push(int value) {
        if (stacks.isEmpty() || stacks.getLast().size() >= STACK_SIZE) {
            Stack<Integer> stack = new Stack<>();
            stack.push(value);
            stacks.add(stack);
        } else {
            stacks.getLast().push(value);
        }
    }
    public Integer pop() {
        Stack<Integer> lastStack = stacks.getLast();
        int value = lastStack.pop();
        if (lastStack.isEmpty()) {
            stacks.removeLast();
        }
        return value;
    }
    ```
*   **Dry Run:** The code manages a `LinkedList` of `Stack`s. `push` checks the last stack; if it's full or doesn't exist, a new one is created. `pop` removes from the last stack and removes it if it becomes empty.

**Example 3: Stock Span**
*   **Description:** This code calculates the stock span for a given array of daily prices. The span for day `i` is the number of consecutive days (including today) with a price less than or equal to today's price, going backward.
*   **Code (Main Logic):**
    ```java
    public static int[] stockSpan(int[] stockPrices) {
        Stack<Integer> dayStack = new Stack();
        int[] spanResult = new int[stockPrices.length];
        spanResult[0] = 1;
        dayStack.push(0);
        for (int i = 1; i < stockPrices.length; i++) {
            while (!dayStack.empty() && stockPrices[i] > stockPrices[dayStack.peek()]) {
                dayStack.pop();
            }
            if (dayStack.empty()) {
                spanResult[i] = i + 1;
            } else {
                spanResult[i] = i - dayStack.peek();
            }
            dayStack.push(i);
        }
        return spanResult;
    }
    ```
*   **Dry Run:** A stack stores indices of days with decreasing prices. For each day, it pops indices with a price less than the current day's price. The remaining index on top of the stack is the index of the previous greater element. The span is the difference between the current index and that previous greater element's index.

**Example 4: Stack Min (Constant Time)**
*   **Description:** This code implements a stack that supports a `min()` operation in constant time O(1), along with `push` and `pop`.
*   **Code (Using an Auxiliary Stack):**
    ```java
    public class MyStack extends Stack<Integer> {
        Stack<Integer> stackOfMin;
        public MyStack() {
            stackOfMin = new Stack<>();
        }
        public Integer push(int value) {
            if (value <= min()) {
                stackOfMin.push(value);
            }
            return super.push(value);
        }
        @Override
        public Integer pop() {
            int value = super.pop();
            if (value == min()) {
                stackOfMin.pop();
            }
            return value;
        }
        public int min() {
            if (stackOfMin.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return stackOfMin.peek();
            }
        }
    }
    ```
*   **Dry Run:** This implementation uses an auxiliary stack `stackOfMin` to keep track of the current minimum. When a new value is pushed, if it is less than or equal to the current minimum (which is the top of `stackOfMin`), it's also pushed onto `stackOfMin`. When a value is popped, if it is equal to the current minimum, it's popped from `stackOfMin` as well. This ensures the top of `stackOfMin` is always the minimum of the main stack.

**Example 5: Queue via Stacks**
*   **Description:** This code implements a queue using two stacks. It provides `enqueue`, `dequeue`, and `peek` operations.
*   **Code (Main Logic):**
    ```java
    public class MyQueueViaStack<E> {
        private final Stack<E> stackEnqueue;
        private final Stack<E> stackDequeue;
        // ...
        public void enqueue(E e) {
            stackEnqueue.push(e);
        }
        public E dequeue() {
            reverseStackEnqueue();
            return stackDequeue.pop();
        }
        public E peek() {
            reverseStackEnqueue();
            return stackDequeue.peek();
        }
        private void reverseStackEnqueue() {
            if (stackDequeue.isEmpty()) {
                while (!stackEnqueue.isEmpty()) {
                    stackDequeue.push(stackEnqueue.pop());
                }
            }
        }
    }
    ```
*   **Dry Run:** `enqueue` pushes elements onto `stackEnqueue`. `dequeue`/`peek` call `reverseStackEnqueue`, which pops all elements from `stackEnqueue` and pushes them onto `stackDequeue`, reversing their order. This makes the top of `stackDequeue` the front of the queue. Elements remain in `stackDequeue` for subsequent `pop` operations until it's empty, at which point it will be replenished from `stackEnqueue`.

**Example 6: Max Histogram Area**
*   **Description:** This code finds the largest rectangular area in a histogram where bar widths are 1.
*   **Code (Main Logic):**
    ```java
    public static int maxAreaUsingStack(int[] histogram) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int bar = 0; bar <= histogram.length; bar++) {
            int barHeight = (bar == histogram.length) ? 0 : histogram[bar];
            while (!stack.empty() && barHeight < histogram[stack.peek()]) {
                int top = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int areaRectWidth = bar - left - 1;
                int area = areaRectWidth * histogram[top];
                maxArea = Integer.max(area, maxArea);
            }
            stack.push(bar);
        }
        return maxArea;
    }
    ```
*   **Dry Run:** A stack is used to store indices of bars in increasing height order. For each bar, if the current bar is shorter than the bar at the top of the stack, the algorithm pops the top bar. It calculates the area of a rectangle with the popped bar's height. The left boundary is the new top of the stack after popping, and the right boundary is the current bar's index. This area is the maximum rectangle that can be formed using the popped bar as the minimum height.

---

### **Chapter 13: Trees and Graphs**

This chapter covers algorithms for traversing, manipulating, and solving problems involving trees and graphs.

**Example 1: Binary Tree Traversal (BFS / Level-Order)**
*   **Description:** This code prints the nodes of a binary tree in level-order (Breadth-First Search) using a queue.
*   **Code:**
    ```java
    private void printLevelOrder(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(" " + current.element);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
    ```
*   **Dry Run:** Starting with the root node, it is added to the queue. The algorithm repeatedly removes a node from the queue, prints it, and then adds its left and right children to the queue. This ensures nodes are printed level by level, from left to right.

**Example 2: Binary Tree Traversal (DFS / In-Order, Pre-Order, Post-Order)**
*   **Description:** This code prints the nodes of a binary tree in In-Order, Pre-Order, and Post-Order (Depth-First Search) using recursion.
*   **Code (Pre-Order):**
    ```java
    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.element);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }
    ```
*   **Code (In-Order):**
    ```java
    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(" " + node.element);
            printInOrder(node.right);
        }
    }
    ```
*   **Code (Post-Order):**
    ```java
    private void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(" " + node.element);
        }
    }
    ```
*   **Dry Run:** Each method recursively traverses the tree in a specific order determined by the relative placement of the `System.out.print` statements. Pre-Order visits the root, then left, then right. In-Order visits left, root, then right. Post-Order visits left, right, then root.

**Example 3: Sorted Array to Minimal BST**
*   **Description:** This code creates a height-balanced BST from a sorted array.
*   **Code (Recursive Insertion):**
    ```java
    private Node minimalBst(T m[], int start, int end) {
        if (end < start) {
            return null;
        }
        int middle = (start + end) / 2;
        Node node = new Node(m[middle]);
        nodeCount++;
        node.left = minimalBst(m, start, middle - 1);
        node.right = minimalBst(m, middle + 1, end);
        return node;
    }
    ```
*   **Dry Run:** The algorithm uses recursion. It finds the middle element of the current sub-array and makes it the root. It then recursively creates the left subtree from the left half of the array and the right subtree from the right half. This ensures the tree is balanced.

**Example 4: Check if a Binary Tree is a BST**
*   **Description:** This code verifies if a given binary tree satisfies the Binary Search Tree property.
*   **Code:**
    ```java
    private boolean isBinarySearchTree(Node node, T minElement, T maxElement) {
        if (node == null) {
            return true;
        }
        if ((minElement != null && node.element.compareTo(minElement) <= 0)
                || (maxElement != null && node.element.compareTo(maxElement) > 0)) {
            return false;
        }
        if (!isBinarySearchTree(node.left, minElement, node.element)
                || !isBinarySearchTree(node.right, node.element, maxElement)) {
            return false;
        }
        return true;
    }
    ```
*   **Dry Run:** The algorithm uses recursion with a valid range `(min, max)` for each node. Starting with `(null, null)` at the root, it checks if the current node's value is within this range. It then recursively validates the left subtree with an updated `max` and the right subtree with an updated `min`. A node is invalid if its value is ≤ `min` or > `max`.

**Example 5: Find Common Ancestor**
*   **Description:** This code finds the first common ancestor of two nodes in a binary tree.
*   **Code:**
    ```java
    private Node commonAncestor(Node root, Node n1, Node n2) {
        if (root == null) {
            return null;
        }
        if (root == n1 && root == n2) {
            return root;
        }
        Node left = commonAncestor(root.left, n1, n2);
        if (left != null && left != n1 && left != n2) {
            return left;
        }
        Node right = commonAncestor(root.right, n1, n2);
        if (right != null && right != n1 && right != n2) {
            return right;
        }
        if (left != null && right != null) {
            return root;
        } else if (root == n1 || root == n2) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }
    ```
*   **Dry Run:** The algorithm recursively searches the tree. If the node itself is `n1` or `n2`, it returns it. It searches the left and right subtrees. If both left and right searches return non-null (and not the other target node), the current node is the common ancestor. It handles cases where one node is an ancestor of the other.

**Example 6: Mirror a Binary Tree**
*   **Description:** This code creates a mirror image of a binary tree (swaps left and right children of every node).
*   **Code (In-Place Mirroring):**
    ```java
    private void mirrorTreeInPlace(Node node) {
        if (node == null) {
            return;
        }
        Node auxNode;
        mirrorTreeInPlace(node.left);
        mirrorTreeInPlace(node.right);
        auxNode = node.left;
        node.left = node.right;
        node.right = auxNode;
    }
    ```
*   **Dry Run:** The algorithm uses a post-order traversal. It recursively mirrors the left and right subtrees, and then swaps the left and right child pointers of the current node.

---

### **Chapter 14: Sorting and Searching**

This chapter covers popular sorting and searching algorithms.

**Example 1: Heap Sort**
*   **Description:** This code sorts an array using the Heap Sort algorithm.
*   **Code:**
    ```java
    public static void sort(int[] arr) {
        int n = arr.length;
        buildHeap(arr, n);
        while (n > 1) {
            swap(arr, 0, n - 1);
            n--;
            heapify(arr, n, 0);
        }
    }
    private static void buildHeap(int[] arr, int n) {
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }
    private static void heapify(int[] arr, int n, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int greater;
        if (left < n && arr[left] > arr[i]) {
            greater = left;
        } else {
            greater = i;
        }
        if (right < n && arr[right] > arr[greater]) {
            greater = right;
        }
        if (greater != i) {
            swap(arr, i, greater);
            heapify(arr, n, greater);
        }
    }
    ```
*   **Dry Run:** The algorithm first builds a max-heap from the array. Then, it repeatedly swaps the root (max element) with the last element, reduces the heap size, and heapifies the new root to maintain the max-heap property. This process results in a sorted array.

**Example 2: Merge Sort**
*   **Description:** This code sorts an array using the Merge Sort algorithm.
*   **Code:**
    ```java
    public static void sort(int[] arr) {
        if (arr.length > 1) {
            int[] left = leftHalf(arr);
            int[] right = rightHalf(arr);
            sort(left);
            sort(right);
            merge(arr, left, right);
        }
    }
    private static void merge(int[] result, int[] left, int[] right) {
        int t1 = 0;
        int t2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (t2 >= right.length || (t1 < left.length && left[t1] <= right[t2])) {
                result[i] = left[t1];
                t1++;
            } else {
                result[i] = right[t2];
                t2++;
            }
        }
    }
    ```
*   **Dry Run:** The algorithm recursively divides the array into two halves until base cases (arrays of size 1). It then merges these sorted halves back together. The `merge` function compares the smallest elements of the two sub-arrays and places the smaller one into the result array, preserving the sorted order.

**Example 3: Quick Sort**
*   **Description:** This code sorts an array using the Quick Sort algorithm.
*   **Code:**
    ```java
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int m = partition(arr, left, right);
            sort(arr, left, m - 1);
            sort(arr, m + 1, right);
        }
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int m = left;
        for (int i = m; i < right; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, m++);
            }
        }
        swap(arr, right, m);
        return m;
    }
    ```
*   **Dry Run:** A pivot is chosen (usually the last element). The `partition` function rearranges the array so that elements less than or equal to the pivot are on the left, and elements greater are on the right. The pivot is placed in its correct sorted position. The algorithm then recursively sorts the left and right sub-arrays.

**Example 4: Binary Search**
*   **Description:** This code finds the index of a target value in a sorted array using the Binary Search algorithm.
*   **Code (Iterative):**
    ```java
    public static int runIterative(int[] arr, int p) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (p == arr[mid]) {
                return mid;
            } else if (p < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    ```
*   **Dry Run:** The algorithm repeatedly divides the search interval in half. It compares the target with the middle element. If they are equal, the index is returned. If the target is smaller, the search continues on the left half. If it is larger, it continues on the right half. This continues until the element is found or the interval is empty.

---

### **Chapter 15: Mathematics and Puzzles**

This chapter presents solutions to common mathematical and puzzle-style problems.

**Example 1: FizzBuzz**
*   **Description:** This classic problem prints numbers from 1 to `n`. For multiples of 3 it prints "Fizz", for multiples of 5 it prints "Buzz", and for multiples of both it prints "FizzBuzz".
*   **Code:**
    ```java
    public static void print(int n) {
        for (int i = 1; i <= n; i++) {
            if (((i % 5) == 0) && ((i % 7) == 0)) {
                System.out.println("fizzbuzz");
            } else if ((i % 5) == 0) {
                System.out.println("fizz");
            } else if ((i % 7) == 0) {
                System.out.println("buzz");
            } else {
                System.out.println(i);
            }
        }
    }
    ```
*   **Dry Run (for `n=15`):** The loop will iterate from 1 to 15. The modulo operator `%` is used to check divisibility. The conditions are checked in order: divisibility by both, then by 5, then by 7. If none match, the number itself is printed.
*   **Output:**
    ```
    1
    2
    3
    4
    fizz
    6
    buzz
    8
    9
    fizz
    11
    12
    13
    14
    fizzbuzz
    ```

**Example 2: Roman Numerals**
*   **Description:** This code converts a positive integer into its Roman numeral representation.
*   **Code:**
    ```java
    private static final String HUNDREDTHS[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String TENS[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String ONES[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static String convert(int n) {
        String roman = "";
        while (n >= 1000) {
            roman = roman + 'M';
            n -= 1000;
        }
        roman = roman + HUNDREDTHS[n / 100];
        n = n % 100;
        roman = roman + TENS[n / 10];
        n = n % 10;
        roman = roman + ONES[n];
        return roman;
    }
    ```
*   **Dry Run (for `n=34`):**
    - `while(n >= 1000)` loop is skipped.
    - `HUNDREDTHS[34/100]` = `HUNDREDTHS[0]` = `""`.
    - `n = 34 % 100 = 34`.
    - `TENS[34/10]` = `TENS[3]` = `"XXX"`.
    - `n = 34 % 10 = 4`.
    - `ONES[4]` = `"IV"`.
    - Roman numeral = `"" + "XXX" + "IV"` = `"XXXIV"`.
*   **Output:** `XXXIV`.

**Example 3: Visiting and Toggling 100 Doors**
*   **Description:** This problem asks to find which doors are open after 100 passes, where a person toggles the state of every k-th door on the k-th pass.
*   **Code (Simulation):**
    ```java
    private static final int DOORS = 100;
    public static int[] visitToggle() {
        int[] doors = new int[DOORS];
        for (int i = 0; i <= (DOORS - 1); i++) {
            doors[i] = 0;
        }
        for (int i = 0; i <= (DOORS - 1); i++) {
            for (int j = 0; j <= (DOORS - 1); j++) {
                if ((j + 1) % (i + 1) == 0) {
                    if (doors[j] == 0) {
                        doors[j] = 1;
                    } else {
                        doors[j] = 0;
                    }
                }
            }
        }
        return doors;
    }
    ```
*   **Dry Run:** The code simulates 100 passes with nested loops. It toggles the door at index `j` if `(j+1)` is divisible by `(i+1)`. The answer is mathematically derived to be doors at perfect squares (1, 4, 9, ..., 100) being open, as they have an odd number of divisors. The simulation confirms this.

**Example 4: Clock Angle**
*   **Description:** This code calculates the smaller angle between the hour and minute hands of a clock for a given time.
*   **Code:**
    ```java
    public static float findAngle(int hour, int min) {
        float angle = (float) Math.abs(((30f * hour) + (0.5f * min)) - (6f * min));
        return angle > 180f ? (360f - angle) : angle;
    }
    ```
*   **Dry Run (for `10:10`):**
    - Hour hand angle = `30 * 10 + 0.5 * 10 = 300 + 5 = 305` degrees.
    - Minute hand angle = `6 * 10 = 60` degrees.
    - Difference = `|305 - 60| = 245` degrees.
    - Since 245 > 180, the smaller angle is `360 - 245 = 115` degrees.
*   **Output:** `115.0`.

---

### **Chapter 16: Concurrency**

This chapter covers fundamental concepts and implementations related to multithreading in Java.

**Example 1: Thread Life Cycle States**
*   **Description:** This code demonstrates the different states a Java thread can be in.
*   **Code (Conceptual Example):**
    ```java
    public class ThreadLifecycleState {
        public static void main(String[] args) {
            Thread t = new Thread(() -> { /* Some task */ });
            System.out.println("State after creation: " + t.getState()); // NEW
            t.start();
            System.out.println("State after start: " + t.getState()); // RUNNABLE
            // ... code to potentially block the thread ...
        }
    }
    ```
*   **Dry Run:** The output will show the thread's state at different points: `NEW` (created but not started), `RUNNABLE` (started and ready to run), `BLOCKED` (waiting for a lock), `WAITING` (waiting for another thread), `TIMED_WAITING` (waiting for a specified time), and `TERMINATED` (finished execution).

**Example 2: Deadlock**
*   **Description:** This code creates a deadlock scenario where two threads are waiting for locks held by each other.
*   **Code (Simplified):**
    ```java
    public class Deadlock {
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();
        public static void main(String[] args) {
            Thread t1 = new Thread(() -> {
                synchronized (lock1) {
                    System.out.println("Thread 1: Locked lock1");
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                    synchronized (lock2) {
                        System.out.println("Thread 1: Locked lock2");
                    }
                }
            });
            Thread t2 = new Thread(() -> {
                synchronized (lock2) {
                    System.out.println("Thread 2: Locked lock2");
                    synchronized (lock1) {
                        System.out.println("Thread 2: Locked lock1");
                    }
                }
            });
            t1.start(); t2.start();
        }
    }
    ```
*   **Dry Run:** Thread 1 locks `lock1`, sleeps, then tries to lock `lock2`. Thread 2 locks `lock2`, then tries to lock `lock1`. This creates a classic deadlock if the timings align, causing the program to hang indefinitely. The output will only show the initial lock messages.

**Example 3: Race Condition**
*   **Description:** This code demonstrates a race condition where two threads increment a shared counter without synchronization.
*   **Code (Conceptual):**
    ```java
    public class RaceCondition {
        private static int counter = 0;
        public static void main(String[] args) throws InterruptedException {
            Runnable task = () -> { for (int i = 0; i < 1000; i++) counter++; };
            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            t1.start(); t2.start();
            t1.join(); t2.join();
            System.out.println("Counter: " + counter);
        }
    }
    ```
*   **Dry Run:** The expected output is 2000, but due to the race condition (the increment operation is not atomic), the actual output is often less than 2000 and may vary between runs. This demonstrates the need for synchronization.

**Example 4: Producer-Consumer via `wait()` and `notify()`**
*   **Description:** This code implements the classic Producer-Consumer problem using `wait()` and `notify()` for inter-thread communication.
*   **Code (Producer/Consumer Logic):**
    ```java
    // Producer
    synchronized (queue) {
        while (!queue.isEmpty()) {
            queue.wait();
        }
        String product = "product-" + rnd.nextInt(1000);
        queue.add(product);
        logger.info(() -> "Produced: " + product);
        queue.notify();
    }
    // Consumer
    synchronized (queue) {
        while (queue.isEmpty()) {
            queue.wait();
        }
        String product = queue.remove(0);
        if (product != null) {
            logger.info(() -> "Consumed: " + product);
            queue.notify();
        }
    }
    ```
*   **Dry Run:** The producer and consumer threads both synchronize on the shared `queue` object. The producer waits if the queue is not empty, produces an item, and notifies the consumer. The consumer waits if the queue is empty, consumes an item, and notifies the producer. This ensures safe production and consumption without race conditions.

---

### **Chapter 17: Functional-Style Programming**

This chapter focuses on Java's functional programming features, including lambdas and streams.

**Example 1: Lambda Expression Parts and Characteristics**
*   **Description:** This example explains the syntax and purpose of a lambda expression.
*   **Code (Lambda Example):**
    ```java
    FilenameFilter filter = (File folder, String fileName) -> {
        return folder.canRead() && fileName.endsWith(".pdf");
    };
    ```
*   **Explanation:** A lambda expression has three parts: a parameter list (`File folder, String fileName`), an arrow (`->`), and a body (`{ return ...; }`). Lambdas are anonymous, function-like, and can be passed as arguments to methods, representing behavior parameterization. They are used where a functional interface (an interface with a single abstract method) is expected.

**Example 2: `map()` vs `flatMap()`**
*   **Description:** This demonstrates the difference between the `map()` and `flatMap()` stream operations.
*   **Code:**
    ```java
    List<List<String>> melonLists = Arrays.asList(
        Arrays.asList("Gac", "Cantaloupe"),
        Arrays.asList("Hemi", "Gac", "Apollo"),
        Arrays.asList("Gac", "Hemi", "Cantaloupe")
    );

    // Using map()
    melonLists.stream().map(Collection::stream); // returns Stream<Stream<String>>

    // Using flatMap()
    List<String> distinctNames = melonLists.stream()
        .flatMap(Collection::stream) // returns Stream<String>
        .distinct()
        .collect(Collectors.toList()); // Output: [Gac, Cantaloupe, Hemi, Apollo]
    ```
*   **Dry Run:** `map()` applies a function to each element and returns a stream of the results, which can create a nested stream structure. `flatMap()` is used to flatten this structure by applying a function that returns a stream for each element and then concatenating all those streams into a single stream.

---

### **Chapter 18: Unit Testing**

This chapter covers best practices and frameworks for unit testing, primarily using JUnit.

**Example 1: AAA (Arrange, Act, Assert) Pattern**
*   **Description:** This demonstrates the AAA pattern, a standard structure for writing unit tests.
*   **Code (JUnit 5 Example):**
    ```java
    @Test
    public void givenStreamWhenSumThenEquals6() {
        // Arrange
        Stream<Integer> theStream = Stream.of(1, 2, 3);
        // Act
        int sum = theStream.mapToInt(i -> i).sum();
        // Assert
        assertEquals(6, sum);
    }
    ```
*   **Explanation:** The "Arrange" section sets up the test data and environment. The "Act" section performs the action being tested. The "Assert" section verifies that the expected result matches the actual result.

**Example 2: Testing Exceptions in JUnit 5**
*   **Description:** This shows how to test for expected exceptions in JUnit 5 using `assertThrows()`.
*   **Code:**
    ```java
    @Test
    public void givenStreamWhenGetThenException() {
        assertThrows(NoSuchElementException.class, () -> {
            Stream<Integer> theStream = Stream.of();
            theStream.findAny().get();
        });
    }

    @Test
    public void givenStreamWhenGetThenExceptionWithMessage() {
        Throwable ex = assertThrows(NoSuchElementException.class, () -> {
            Stream<Integer> theStream = Stream.of();
            theStream.findAny().get();
        });
        assertEquals(ex.getMessage(), "No value present");
    }
    ```
*   **Dry Run:** The `assertThrows` method checks if the provided lambda expression throws the expected exception. If it does, the test passes. A reference to the thrown exception can be captured to assert its details (e.g., message).

**Example 3: Assumptions in JUnit 5**
*   **Description:** This shows the use of assumptions to conditionally execute tests.
*   **Code:**
    ```java
    @Test
    public void givenFolderWhenGetAbsolutePathThenSuccess() {
        assumingThat(File.separatorChar == '/',
            () -> {
                assertThat(new File(".").getAbsolutePath(), is("C:/SBPBP/GitHub/Chapter18/junit5"));
            });
        assertTrue(true);
    }
    ```
*   **Dry Run:** The `assumingThat` method will only execute the test inside the lambda if the condition is true. If the condition is false, that specific assertion is skipped, but the rest of the test (the `assertTrue(true)` line) still runs.

---

### **Chapter 19: System Scalability**

This chapter discusses concepts and design problems related to building scalable systems.

**Example 1: Load Balancer in Master-Slave Architecture**
*   **Description:** This illustrates the role of a load balancer in a distributed system.
*   **Diagram:** (Conceptual) A load balancer sits in front of multiple application servers (slaves) and distributes incoming requests among them, potentially with a master server for coordination.
*   **Explanation:** The load balancer distributes the workload across a cluster of servers to improve throughput, reduce response time, and provide high availability and fault tolerance.

**Example 2: Sharding**
*   **Description:** This is a description of sharding, a database scaling technique.
*   **Diagram:** (Conceptual) A database is partitioned horizontally. Data for users with even IDs is stored in one database shard, and data for users with odd IDs is stored in another shard.
*   **Explanation:** Sharding distributes a single logical database across multiple machines to improve scalability and performance. Each shard contains a subset of the data.

**Example 3: Social Network Design (Social Graph)**
*   **Description:** This is a problem where you design the data structures and algorithm to find the shortest path between two people in a social network.
*   **Explanation:** A social network is a graph. The solution uses a Bidirectional BFS, starting BFS from both the source and destination nodes. When the two BFS searches meet, a path is found. This is much more efficient than a unidirectional BFS, especially in large graphs. Data can be sharded by user ID to handle massive data across multiple machines.
