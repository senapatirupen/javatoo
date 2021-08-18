#### Threading
![Thread Life](Thread.JPG?raw=true "Thread Life")

In order to write thread-safe classes, we can consider the following techniques:
-   Have no state (classes with no instance and static variables)
-   Have state, but don't share it (for example, use instance variables via Runnable, ThreadLocal, and so on)
-   Have state, but an immutable state
-   Use message-passing (for example, as Akka framework)
-   Use synchronized blocks
-   Use volatile variables
-   Use data structures from the java.util.concurrent package
-   Use synchronizers (for example, CountDownLatch and Barrier)
-   Use locks from the java.util.concurrent.locks package.


##### Object- versus class-level locking

In Java, a block of code marked as synchronized can be executed by a single thread at a time. Since Java is a multi-threaded environment (it supports concurrency), it needs a synchronization mechanism to avoid issues specific to concurrent environments (for example, deadlocks and memory consistency).
A thread can achieve locks at the object level or at the class level.

######Locking at the object level

Locking at object level can be achieved by marking a non-static block of code or non-static method 
(the lock object for that method's object) with synchronized. In the following examples, only one thread 
at a time will be allowed to execute the synchronized method/block on the given instance of the class:
•Synchronized method case:public class ClassOll {
  public synchronized void methodOll() {
    ...
  }
}•Synchronized block of code:public class ClassOll {
  public void methodOll() {
    synchronized(this) {
      ...
    }
  }
}•Another synchronized block of code:public class ClassOll {

  private final Object ollLock = new Object();
  public void methodOll() {
    synchronized(ollLock) {
      ...
    }
  }
}


#####Lock at the class level

In order to protect static data, locking at the class level can be achieved by marking a static method/block
or acquiring a lock on the .class reference with synchronized. In the following examples, only one thread 
of one of the available instances at runtime will be allowed to execute the synchronized block at a time:
•synchronized static method:public class ClassCll {

  public synchronized static void methodCll() {
    ...
  }
}•Synchronized block and lock on .class:public class ClassCll {

  public void method() {
    synchronized(ClassCll.class) {
      ...
    }
  }
}•Synchronized block of code and lock on some other static object:public class ClassCll {

  private final static Object aLock = new Object();

  public void method() {
    synchronized(aLock) {
      ...
    }
  }
}


######Good to know


Here are some common cases that imply synchronizations:
•Two threads can execute concurrently a synchronized static method and a non-static method of the same class (see the OllAndCll class of the P200_ObjectVsClassLevelLocking app). This works because the threads acquire locks on different objects.
•Two threads cannot concurrently execute two different synchronized static methods (or the same synchronized static method) of the same class (check the TwoCll class of the  P200_ObjectVsClassLevelLocking application). This does not work because the first thread acquires a class-level lock. The following combinations will output, staticMethod1(): Thread-0, therefore, only one static synchronized method is executed by only one thread:TwoCll instance1 = new TwoCll();
TwoCll instance2 = new TwoCll();
•Two threads, two instances:
new Thread(() -> {
  instance1.staticMethod1();
}).start();

new Thread(() -> {
  instance2.staticMethod2();
}).start();
•Two threads, one instance:
new Thread(() -> {
  instance1.staticMethod1();
}).start();

new Thread(() -> {
  instance1.staticMethod2();
}).start();
•Two threads can concurrently execute non-synchronized, synchronized static, and synchronized non-static methods (check the OllCllAndNoLock class of the P200_ObjectVsClassLevelLocking application).•It is safe to call a synchronized method from another synchronized method of the same class that requires the same lock. This works because synchronized is re-entrant (as long as it is the same lock, the lock acquired for the first method is used in the second method as well). Check the TwoSyncs class of the P200_ObjectVsClassLevelLocking application.
As a rule of thumb, the synchronized keyword can be used only with static/non-static methods (not constructors)/code blocks. Avoid synchronizing non-final fields and String literals (instances of String created via new are OK).