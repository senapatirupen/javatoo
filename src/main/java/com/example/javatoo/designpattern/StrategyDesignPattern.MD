##### let's consider the following  Melon
class:
<code>
public class Melon {
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
</code>
Let's assume that we have a client – let's call him Mark –who
wants to start up a melon-selling business. We shaped the
preceding class based on his description. His main goal is to
have an inventory application that will sustain his ideas and
decisions, so an application needs to be created that must grow
based on business requirements and evolution. We'll take a
look at the time that's needed to develop this application on a
daily basis in the following sections.

##### filtering melons by their type
public class Filters {
    public static List<Melon> filterByType(List<Melon> melons, String type) {
        List<Melon> result = new ArrayList<>();
        for (Melon melon: melons) {
            if (melon != null && type.equalsIgnoreCase(melon.getType())) {
            result.add(melon);
            }
        }
        return result;
    }
}
##### Filtering melons of a certain weight

public static List<Melon> filterByWeight(List<Melon> melons, int weight) {
    List<Melon> result = new ArrayList<>();
    for (Melon melon: melons) {
        if (melon != null && melon.getWeight() == weight) {
        result.add(melon);
        }
    }
    return result;
}

As developers, we are starting to understand
that, if we continue like this, then the Filters class will end up
with a lot of methods that simply repeat the code and use a
different condition. We are very close to a boilerplate code case
here.

##### filtering melons by type and weight

Things are getting even worse. Mark has now asked us to add a
new filter that filters melons by type and weight, and he needs
this quickly. However, the quickest implementation is the
ugliest. Check it out:

public static List<Melon> filterByTypeAndWeight(List<Melon> melons, String type, int weight) {
    List<Melon> result = new ArrayList<>();
    for (Melon melon: melons) {
        if (melon != null && type.equalsIgnoreCase(melon.getType()) && melon.getWeight() == weight) {
            result.add(melon);
        }
    }
    return result;
}

In our context, this is unacceptable. If we add a new filter
criterion here, the code will become hard to maintain as well as
prone to errors.

##### pushing the behavior as a parameter

Meeting time! We cannot continue to add more filters like this;
filtering with every attribute we can think of will end up in a
huge Filters class that has big, complex methods with too many1
parameters and tons of boilerplate code.
The main problem is that we have different behaviors wrapped
in boilerplate code. So, it will be nice to write
the boilerplate code only once and push the behavior as a
parameter. This way, we can shape any selection
condition/criteria as behavior and juggle them as desired. The
code will become more clear, flexible, easy to maintain, and
have fewer parameters.
This is known as <b>Behavior Parameterization</b>
