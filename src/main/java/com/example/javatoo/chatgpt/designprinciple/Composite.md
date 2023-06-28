Certainly! The Composite pattern is a structural design pattern that allows you to compose objects into tree-like structures and work with them as if they were individual objects. It enables you to represent part-whole hierarchies and treat individual objects and compositions uniformly. Here's an example of implementing the Composite pattern in Java:

First, let's define the component interface that represents both the leaf and composite elements:

```java
interface Component {
    void operation();
}
```

Next, let's create the leaf class that represents the individual objects:

```java
class Leaf implements Component {
    private String name;

    Leaf(String name) {
        this.name = name;
    }

    public void operation() {
        System.out.println("Leaf " + name + " operation.");
    }
}
```

In this example, we have the `Leaf` class that implements the `Component` interface. It represents the individual objects in the composition.

Next, let's create the composite class that represents the composite objects:

```java
import java.util.ArrayList;
import java.util.List;

class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public void operation() {
        System.out.println("Composite operation:");
        for (Component component : children) {
            component.operation();
        }
    }
}
```

In this example, we have the `Composite` class that implements the `Component` interface. It represents the composite objects and contains a list of child components. The `add()` and `remove()` methods allow adding and removing child components from the composite. The `operation()` method iterates over the child components and invokes their `operation()` methods.

Finally, let's see how we can use the Composite pattern:

```java
public class Main {
    public static void main(String[] args) {
        Component leaf1 = new Leaf("Leaf 1");
        Component leaf2 = new Leaf("Leaf 2");
        Component leaf3 = new Leaf("Leaf 3");

        Composite composite = new Composite();
        composite.add(leaf1);
        composite.add(leaf2);

        Composite composite2 = new Composite();
        composite2.add(leaf3);
        composite2.add(composite);

        composite2.operation();
    }
}
```

In this example, we create instances of `Leaf` objects and a `Composite` object. We add the leaf objects to the composite object, and then add the composite object itself to another composite object. Finally, we call the `operation()` method on the top-level composite object, which recursively invokes the `operation()` method on all the child components.

When we run the program, it will output:

```
Composite operation:
Leaf Leaf 3 operation.
Composite operation:
Leaf Leaf 1 operation.
Leaf Leaf 2 operation.
```

The Composite pattern allows you to treat individual objects and compositions uniformly by representing them as a tree-like structure. It enables you to work with complex hierarchies in a consistent way, simplifies the client code, and provides flexibility in adding or removing components dynamically. This pattern is useful when you have a part-whole hierarchy and want to perform operations on the hierarchy as a whole or on individual parts without distinction.