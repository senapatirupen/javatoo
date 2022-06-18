
166. Writing functional interfaces


In this solution, we will highlight the purpose and usability of a functional interface in comparison with several alternatives. We will look at how to evolve the code from its basic and rigid implementation to a flexible implementation based on a functional interface. For this, let's consider the following Melon class:public class Melon {

  private final String type;
  private final int weight;
  private final String origin;

  public Melon(String type, int weight, String origin) {
    this.type = type;
    this.weight = weight;
    this.origin = origin;
  }

  // getters, toString(), and so on omitted for brevity
}
Let's assume that we have a client – let's call him Mark – who wants to start up a melon-selling business. We shaped the preceding class based on his description. His main goal is to have an inventory application that will sustain his ideas and decisions, so an application needs to be created that must grow based on business requirements and evolution. We'll take a look at the time that's needed to develop this application on a daily basis in the following sections.


##### Day 1 (filtering melons by their type)


One day, Mark asked us to provide a feature for filtering melons by their type. As a result, we created a utility class named Filters and implemented a static method that takes a list of melons and the type to filter on as arguments.
The resulting method is pretty straightforward:public static List<Melon> filterByType(
    List<Melon> melons, String type) {

  List<Melon> result = new ArrayList<>();

  for (Melon melon: melons) {
    if (melon != null && type.equalsIgnoreCase(melon.getType())) {
      result.add(melon);
    }
  }

  return result;
}
Done! Now, we can easily filter melons by type, as shown in the following example:List<Melon> bailans = Filters.filterByType(melons, "Bailan");


##### Day 2 (filtering melons of a certain weight)


While Mark was satisfied with the result, he requested another filter to obtain melons of a certain weight (for example, all the melons that are 1,200 grams). We've just implemented a filter like this for melon types, and so we can come up with a new static method for melons of a certain weight, as follows:public static List<Melon> filterByWeight(
    List<Melon> melons, int weight) {

  List<Melon> result = new ArrayList<>();

  for (Melon melon: melons) {
    if (melon != null && melon.getWeight() == weight) {
      result.add(melon);
    }
  }

  return result;
}
This is similar to filterByType(), except it has a different condition/filter. As developers, we are starting to understand that, if we continue like this, then the Filters class will end up with a lot of methods that simply repeat the code and use a different condition. We are very close to a boilerplate code case here.


##### Day 3 (filtering melons by type and weight)


Things are getting even worse. Mark has now asked us to add a new filter that filters melons by type and weight, and he needs this quickly. However, the quickest implementation is the ugliest. Check it out:public static List<Melon> filterByTypeAndWeight(
    List<Melon> melons, String type, int weight) {
  List<Melon> result = new ArrayList<>();

  for (Melon melon: melons) {
    if (melon != null && type.equalsIgnoreCase(melon.getType()) 
        && melon.getWeight() == weight) {
      result.add(melon);
    }
  }

  return result;
}
In our context, this is unacceptable. If we add a new filter criterion here, the code will become hard to maintain as well as prone to errors.


##### Day 4 (pushing the behavior as a parameter)


Meeting time! We cannot continue to add more filters like this; filtering with every attribute we can think of will end up in a huge Filters class that has big, complex methods with too many parameters and tons of boilerplate code.
The main problem is that we have different behaviors wrapped in boilerplate code. So, it will be nice to write the boilerplate code only once and push the behavior as a parameter. This way, we can shape any selection condition/criteria as behavior and juggle them as desired. The code will become more clear, flexible, easy to maintain, and have fewer parameters.
This is known as Behavior Parameterization, which is illustrated in the following diagram (the left-hand side shows what we have now; the right-hand side shows what we want):

If we think of each selection condition/criteria as a behavior, then it is pretty intuitive to think of each behavior as an implementation of an interface. Basically, all these behaviors have something in common – a selection condition/criteria and a return of the boolean type (this is known as a predicate). In the context of an interface, this is a contract that can be written as follows:public interface MelonPredicate {
  boolean test(Melon melon);
}
Furthermore, we can write different implementations of MelonPredicate. For example, filtering the Gac melons can be written like this:public class GacMelonPredicate implements MelonPredicate {
  @Override
  public boolean test(Melon melon) {
    return "gac".equalsIgnoreCase(melon.getType());
  }
}
Alternatively, filtering all the melons that are heavier than 5,000g can be written:public class HugeMelonPredicate implements MelonPredicate {
  @Override
  public boolean test(Melon melon) {
    return melon.getWeight() > 5000;
  }
}
This technique has a name – the Strategy design pattern. According to GoF, this can "Define a family of algorithms, encapsulate each one, and make them interchangeable. The strategy pattern lets the algorithm vary independently from client to client."
So, the main idea is to dynamically select the behavior of an algorithm at runtime. The MelonPredicate interface unifies all the algorithms dedicated to selecting melons, and each implementation of it is a strategy.
At the moment, we have the strategies, but we don't have any method that receives a MelonPredicate parameter. We need a filterMelons() method, as shown in the following diagram:

So, we need a single parameter and multiple behaviors. Let's look at the source code for filterMelons():public static List<Melon> filterMelons(
    List<Melon> melons, MelonPredicate predicate) {

  List<Melon> result = new ArrayList<>();

  for (Melon melon: melons) {
    if (melon != null && predicate.test(melon)) {
      result.add(melon);
    }
  }

  return result;
}
This is much better! We can reuse this method with different behaviors as follows (here, we pass GacMelonPredicate and HugeMelonPredicate):List<Melon> gacs = Filters.filterMelons(
  melons, new GacMelonPredicate());

List<Melon> huge = Filters.filterMelons(
  melons, new HugeMelonPredicate());
  
  
##### Day 5 (implementing another 100 filters)


Mark has asked us to implement another 100 filters. This time, we have the flexibility and the support to accomplish this task, but we still need to write 100 strategies or classes for implementing the MelonPredicate for each selection criteria. Moreover, we have to create instances of these strategies and pass them to the filterMelons() method.
This means a lot of code and time. In order to save both, we can rely on Java anonymous classes. In other words, having classes with no names that are declared and instantiated at the same time will result in something like this:List<Melon> europeans = Filters.filterMelons(
    melons, new MelonPredicate() {
    @Override
    public boolean test(Melon melon) {
      return "europe".equalsIgnoreCase(melon.getOrigin());
    }
});
There is some progress being made here, but this is not very significant because we still need to write a lot of code. Check the highlighted code in the following diagram (this code repeats for each implemented behavior):

Here, the code is not friendly. Anonymous classes seem complex and they somehow look incomplete and weird, especially to novices.


##### Day 6 (anonymous classes can be written as lambdas)


A new day, a new idea! Any smart IDE can show us the road ahead. For example, the NetBeans IDE will discretely warn us that this anonymous class can be written as a lambda expression.
This is shown in the following screenshot:

The message is crystal clear – This anonymous inner class creation can be turned into a lambda expression. Here, make the transformation by hand or let the IDE do it for us.
The result will look like this:List<Melon> europeansLambda = Filters.filterMelons(
  melons, m -> "europe".equalsIgnoreCase(m.getOrigin()));
This is much better! Java 8 lambda expressions did a great job this time. Now, we can write Mark's filters in a more flexible, fast, clean, readable, and maintainable manner.


##### Day 7 (abstracting the List type)


Mark comes up with some good news the next day – he will extend his business and sell other fruits as well as melons. This is cool, but our predicate only supports Melon instances.
So, how should we proceed to support other fruits too? How many other fruits? What if Mark decides to start selling another category of products, such as vegetables? We cannot simply create a predicate for each of them. This will take us back to the start.
The obvious solution is to abstract the List type. We start this by defining a new interface, and this time name it Predicate (remove Melon from the name):@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}
Next, we rewrite the filterMelons() method and rename it as filter():public static <T> List<T> filter(
    List<T> list, Predicate<T> predicate) {

  List<T> result = new ArrayList<>();

  for (T t: list) {
    if (t != null && predicate.test(t)) {
      result.add(t);
    }
  }

  return result;
}
Now, we can write filters for Melon:List<Melon> watermelons = Filters.filter(
  melons, (Melon m) -> "Watermelon".equalsIgnoreCase(m.getType()));
We can also do the same for numbers:List<Integer> numbers = Arrays.asList(1, 13, 15, 2, 67);
List<Integer> smallThan10 = Filters
  .filter(numbers, (Integer i) -> i < 10);
Take a step back and look at where we started and where we are now. The difference is huge thanks to Java 8 functional interfaces and lambda expressions. Have you noticed the @FunctionalInterface annotation on the Predicate interface? Well, that is an informative annotation type that's used to mark a functional interface. It is useful for an error to occur if the marked interface is not functional.
Conceptually, a functional interface has exactly one abstract method. Moreover, the Predicate interface that we've defined already exists in Java 8 as the java.util.function.Predicate interface. The java.util.function package contains 40+ such interfaces. Consequently, before defining a new one, it is advisable to check this package's content. Most of the time, the six standard built-in functional interfaces will do the job. These are listed as follows:•Predicate<T>•Consumer<T>•Supplier<T>•Function<T, R>•UnaryOperator<T>•BinaryOperator<T>
Functional interfaces and lambda expressions make a great team. Lambda expressions support the implementation of the abstract method of a functional interface directly inline. Basically, the entire expression is perceived as an instance of a concrete implementation of the functional interface, as demonstrated in the following code:Predicate<Melon> predicate = (Melon m) 
  -> "Watermelon".equalsIgnoreCase(m.getType());
  
  
167. Lambdas in a nutshell


Dissecting a lambda expression will reveal three main parts, as shown in the following diagram:

The following is a description of each part of a lambda expression:•On the left-hand side of the arrow, we have the parameters of this lambda that are used in the lambda body. These are the parameters of the FilenameFilter.accept​(File folder, String fileName) method.•On the right-hand of the arrow, we have the lambda body, which in this case checks if the folder in which the file was found can be read and if the file name ends with the .pdf suffix.•The arrow is just a separator of the lambda's parameters and body.
The anonymous class version of this lambda is as follows:FilenameFilter filter = new FilenameFilter() {
  @Override
  public boolean accept(File folder, String fileName) {
    return folder.canRead() && fileName.endsWith(".pdf");
  }
};
Now, if we look at the lambda and the anonymous version of it, then we can conclude that a lambda expression is a concise anonymous function that can be passed as an argument to a method or kept in a variable. We can conclude that a lambda expression can be described according to the four words shown in the following diagram:

Lambdas sustain Behavior Parameterization and that is a big plus (check the previous problem for a detailed explanation of this). Finally, keep in mind that lambdas can be used only in the context of a functional interface.