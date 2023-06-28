The Broker architectural pattern, also known as the Message Broker pattern, is an architectural pattern that decouples communication between components by introducing a central communication entity called the broker. Components can publish and subscribe to topics handled by the broker, enabling asynchronous and loosely coupled communication. Here's an example of implementing the Broker pattern in Java:

First, let's define the broker interface:

```java
interface Broker {
    void publish(String topic, String message);
    void subscribe(String topic, Subscriber subscriber);
    void unsubscribe(String topic, Subscriber subscriber);
}
```

Next, let's create the subscriber interface:

```java
interface Subscriber {
    void receive(String message);
}
```

In this example, we have the `Broker` interface that defines the operations for publishing, subscribing, and unsubscribing to topics. We also have the `Subscriber` interface that defines the operation for receiving messages.

Next, let's create the concrete implementation of the broker:

```java
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

class MessageBroker implements Broker {
    private Map<String, List<Subscriber>> subscribers = new HashMap<>();

    public void publish(String topic, String message) {
        List<Subscriber> topicSubscribers = subscribers.getOrDefault(topic, new ArrayList<>());
        for (Subscriber subscriber : topicSubscribers) {
            subscriber.receive(message);
        }
    }

    public void subscribe(String topic, Subscriber subscriber) {
        List<Subscriber> topicSubscribers = subscribers.getOrDefault(topic, new ArrayList<>());
        topicSubscribers.add(subscriber);
        subscribers.put(topic, topicSubscribers);
    }

    public void unsubscribe(String topic, Subscriber subscriber) {
        List<Subscriber> topicSubscribers = subscribers.getOrDefault(topic, new ArrayList<>());
        topicSubscribers.remove(subscriber);
        subscribers.put(topic, topicSubscribers);
    }
}
```

In this example, we have the `MessageBroker` class that implements the `Broker` interface. It maintains a map of topic-to-subscribers, where each topic is associated with a list of subscribers. The `publish()` method notifies all subscribers of a specific topic with a given message. The `subscribe()` method adds a subscriber to a topic's list of subscribers, and the `unsubscribe()` method removes a subscriber from a topic's list of subscribers.

Finally, let's see how we can use the Broker pattern:

```java
public class Main {
    public static void main(String[] args) {
        Broker broker = new MessageBroker();

        Subscriber subscriber1 = message -> System.out.println("Subscriber 1 received message: " + message);
        Subscriber subscriber2 = message -> System.out.println("Subscriber 2 received message: " + message);

        broker.subscribe("topic1", subscriber1);
        broker.subscribe("topic2", subscriber1);
        broker.subscribe("topic2", subscriber2);

        broker.publish("topic1", "Hello, Topic 1!");
        broker.publish("topic2", "Hello, Topic 2!");

        broker.unsubscribe("topic2", subscriber1);

        broker.publish("topic2", "Updated message for Topic 2!");
    }
}
```

In this example, we create an instance of the `MessageBroker` class. We then create two subscribers (`subscriber1` and `subscriber2`) that define how they handle received messages. We subscribe `subscriber1` to "topic1" and "topic2" and `subscriber2` to "topic2" using the broker's `subscribe()` method. We then publish messages to the topics using the broker's `publish()` method. Finally, we unsubscribe `subscriber1` from "topic2" using the broker's `unsubscribe()` method and publish another message to "topic2".

When we run the program, it will output:



```
Subscriber 1 received message: Hello, Topic 1!
Subscriber 1 received message: Hello, Topic 2!
Subscriber 2 received message: Hello, Topic 2!
Subscriber 2 received message: Updated message for Topic 2!
```

The Broker pattern facilitates decoupled communication between components by introducing a central broker. It allows components to publish and subscribe to topics, enabling asynchronous and loosely coupled communication. This pattern is useful when components need to communicate without direct dependencies and when there is a need for broadcasting messages to multiple subscribers.