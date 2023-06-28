The Simple Factory pattern is not one of the standard Gang of Four design patterns, but it is a simplified version of the Factory Method pattern. In the Simple Factory pattern, a single factory class is responsible for creating objects based on a provided type or parameter. Here's an example of implementing the Simple Factory pattern in Java:

Let's start by defining the product interface:

```java
interface Product {
    void doSomething();
}
```

Next, let's create concrete product implementations:

```java
class ConcreteProductA implements Product {
    public void doSomething() {
        System.out.println("Doing something in ConcreteProductA.");
    }
}

class ConcreteProductB implements Product {
    public void doSomething() {
        System.out.println("Doing something in ConcreteProductB.");
    }
}
```

In this example, we have two concrete product classes: `ConcreteProductA` and `ConcreteProductB`. Each product class provides its own implementation of the `doSomething()` method.

Next, let's create the simple factory class:

```java
class SimpleFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else if (type.equals("B")) {
            return new ConcreteProductB();
        } else {
            throw new IllegalArgumentException("Invalid product type.");
        }
    }
}
```

In this example, we have a `createProduct()` method in the `SimpleFactory` class. This method takes a type parameter and based on that parameter, it creates and returns the corresponding concrete product instance.

Finally, let's see how we can use the Simple Factory pattern:

```java
public class Main {
    public static void main(String[] args) {
        Product productA = SimpleFactory.createProduct("A");
        Product productB = SimpleFactory.createProduct("B");

        productA.doSomething();
        productB.doSomething();
    }
}
```

In this example, we use the `createProduct()` method of the `SimpleFactory` class to create instances of the concrete products. We pass the desired product type as a parameter ("A" or "B"). Finally, we call the `doSomething()` method on each product instance to demonstrate their behavior.

When we run the program, it will output:

```
Doing something in ConcreteProductA.
Doing something in ConcreteProductB.
```

The Simple Factory pattern provides a centralized factory class to create objects based on a provided type or parameter. It encapsulates the object creation logic, allowing clients to obtain instances without being aware of the specific implementation classes. This pattern is useful when you have a limited set of product types and want a simplified way to create instances without the need for complex inheritance or subclassing.