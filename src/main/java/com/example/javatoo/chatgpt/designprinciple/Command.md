Certainly! The Command pattern is a behavioral design pattern that decouples the sender of a request from the receiver, allowing you to encapsulate a request as an object and parameterize clients with different requests. Here's an example of implementing the Command pattern in Java:

First, let's define the command interface:

```java
interface Command {
    void execute();
}
```

Next, let's create some concrete command classes:

```java
class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}
```

We have a `Light` class representing the receiver of the commands. The `LightOnCommand` and `LightOffCommand` classes implement the `Command` interface and encapsulate the actions of turning the light on and off, respectively.

Next, let's create the invoker class that will execute the commands:

```java
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

The `RemoteControl` class acts as the invoker. It has a `setCommand()` method to set the command to be executed and a `pressButton()` method to invoke the command.

Finally, let's see how we can use the command pattern:

```java
public class Main {
    public static void main(String[] args) {
        Light light = new Light();

        Command turnOnCommand = new LightOnCommand(light);
        Command turnOffCommand = new LightOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();
    }
}
```

In this example, we create an instance of the `Light` class. Then we create instances of the `LightOnCommand` and `LightOffCommand` and pass the `Light` object as a parameter to the commands. We set the commands in the `RemoteControl` object and invoke them by pressing the button.

When we run the program, it will output:

```
Light is on
Light is off
```

The Command pattern allows us to decouple the sender (the `RemoteControl`) from the receiver (the `Light`) by encapsulating the requests as command objects. This enables us to add new commands easily without modifying existing code and provides flexibility and extensibility to our application.