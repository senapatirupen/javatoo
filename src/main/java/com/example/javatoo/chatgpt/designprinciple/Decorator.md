Certainly! The Decorator pattern is a structural design pattern that allows behavior to be added to an object dynamically at runtime by wrapping the object in a decorator class. It provides an alternative to subclassing for extending functionality. Here's an example of implementing the Decorator pattern in Java:

First, let's define the component interface that represents the object to be decorated:

```java
interface Component {
    void operation();
}
```

Next, let's create the concrete component class:

```java
class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("ConcreteComponent operation.");
    }
}
```

In this example, we have the `ConcreteComponent` class that implements the `Component` interface. It represents the object to be decorated.

Next, let's create the decorator class that wraps the component and adds additional behavior:

```java
class Decorator implements Component {
    private Component component;

    Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
        System.out.println("Decorator operation.");
    }
}
```

In this example, we have the `Decorator` class that implements the `Component` interface. It has a reference to the component object and wraps its behavior. The `operation()` method invokes the component's `operation()` method and adds additional behavior.

Finally, let's see how we can use the Decorator pattern:

```java
public class Main {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decorator = new Decorator(component);

        decorator.operation();
    }
}
```

In this example, we create an instance of the `ConcreteComponent` class. We then create an instance of the `Decorator` class, passing the component instance as a parameter to the decorator's constructor. Finally, we call the `operation()` method on the decorator, which internally invokes the component's `operation()` method and adds additional behavior.

When we run the program, it will output:

```
ConcreteComponent operation.
Decorator operation.
```

The Decorator pattern allows behavior to be added to an object dynamically at runtime without changing its structure. It provides a flexible and reusable way to extend the functionality of objects. This pattern is useful when you want to add responsibilities to objects dynamically and transparently, without the need for subclassing.