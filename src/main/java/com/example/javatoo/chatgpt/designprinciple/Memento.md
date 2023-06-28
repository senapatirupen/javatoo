Certainly! The Memento pattern is a behavioral design pattern that allows capturing and restoring an object's internal state without violating encapsulation. Here's an example of implementing the Memento pattern in Java:

First, let's define the memento class:

```java
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
```

The `Memento` class represents the snapshot of the object's state. It has a `getState()` method to retrieve the saved state.

Next, let's create the originator class:

```java
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveState() {
        return new Memento(state);
    }

    public void restoreState(Memento memento) {
        state = memento.getState();
    }
}
```

The `Originator` class represents the object whose state needs to be saved and restored. It has a `setState()` method to set the state, a `getState()` method to retrieve the current state, a `saveState()` method to create a memento with the current state, and a `restoreState()` method to restore the state from a given memento.

Finally, let's create the caretaker class:

```java
class Caretaker {
    private Memento memento;

    public void saveMemento(Memento memento) {
        this.memento = memento;
    }

    public Memento retrieveMemento() {
        return memento;
    }
}
```

The `Caretaker` class is responsible for storing and retrieving mementos. It has a `saveMemento()` method to save a memento and a `retrieveMemento()` method to retrieve the saved memento.

Let's see how we can use the Memento pattern:

```java
public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State 1");
        System.out.println("Current state: " + originator.getState());

        // Save the state
        caretaker.saveMemento(originator.saveState());

        originator.setState("State 2");
        System.out.println("Current state: " + originator.getState());

        // Restore the state
        originator.restoreState(caretaker.retrieveMemento());
        System.out.println("Restored state: " + originator.getState());
    }
}
```

In this example, we create an `Originator` object to represent an object whose state needs to be saved and restored. We create a `Caretaker` object to handle the storage and retrieval of mementos. We set the state of the originator, save the state using the caretaker, change the state, and then restore the previous state using the memento.

When we run the program, it will output:

```
Current state: State 1
Current state: State 2
Restored state: State 1
```

The Memento pattern allows an object to save and restore its state without exposing the internal details. It provides a way to capture snapshots of an object's state and restore it to a previous state if needed. This pattern can be useful in scenarios where you need to implement undo/redo functionality or maintain a history of state changes.