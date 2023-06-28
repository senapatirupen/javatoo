Certainly! The SOLID principles are a set of design principles aimed at improving the maintainability, flexibility, and extensibility of software systems. Let's go through each principle with an example in Java:

1. Single Responsibility Principle (SRP):
   This principle states that a class should have only one reason to change.

```java
class Employee {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void saveToDatabase() {
        // Save employee data to the database
    }
}
```

In this example, the `Employee` class has a single responsibility, which is to store and provide access to employee data. The saving of employee data to the database is a separate responsibility, so it can be delegated to another class or module.

2. Open-Closed Principle (OCP):
   This principle states that entities (classes, modules, functions) should be open for extension but closed for modification.

```java
interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }
}
```

Here, the `Shape` interface is open for extension, allowing new shapes to be added by implementing the interface, such as a `Triangle` class. Existing shapes like `Circle` and `Rectangle` can be used without modifying their implementation, promoting code stability and reducing the risk of introducing bugs.

3. Liskov Substitution Principle (LSP):
   This principle states that objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

```java
class Vehicle {
    public void startEngine() {
        // Start the engine
    }
}

class Car extends Vehicle {
    public void accelerate() {
        // Accelerate the car
    }
}

class Bicycle extends Vehicle {
    // Bicycle does not have accelerate() method
    // Violation of LSP
}
```

In this example, the `Car` and `Bicycle` classes inherit from the `Vehicle` class. The `Car` class adds additional behavior, such as the `accelerate()` method. However, the `Bicycle` class does not have this method, violating LSP. To adhere to LSP, subclasses should be able to substitute their superclass without affecting the behavior expected from the superclass.

4. Interface Segregation Principle (ISP):
   This principle states that clients should not be forced to depend on interfaces they do not use.

```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class AllInOnePrinter implements Printer, Scanner {
    public void print() {
        // Print functionality
    }

    public void scan() {
        // Scan functionality
    }
}
```

Here, the `AllInOnePrinter` class implements both the `Printer` and `Scanner` interfaces. However, not all clients may require both printing and scanning capabilities. To follow ISP, separate interfaces should be defined for printing and scanning, allowing clients to depend only on the interfaces they need.

5. Dependency Inversion Principle (DIP):
   This principle states that high-level modules should not depend on low-level modules. Both should depend on abstractions.

```java
interface Database {
    void save

(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) {
        // Save data to MySQL database
    }
}

class Application {
    private Database database;

    public Application(Database database) {
        this.database = database;
    }

    public void processData(String data) {
        // Process data
        database.save(data);
    }
}
```

In this example, the `Application` class depends on the `Database` interface instead of a specific implementation like `MySQLDatabase`. This allows different database implementations to be easily substituted without modifying the `Application` class, promoting flexibility and testability.

Applying the SOLID principles helps in creating modular, maintainable, and scalable code. By adhering to these principles, you can improve the overall design and quality of your Java applications.