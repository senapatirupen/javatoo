package com.example.javatoo.basic.javacollections;

import com.example.javatoo.model.Address;
import com.example.javatoo.model.LastNameComparator;
import com.example.javatoo.model.MyStringComparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
public class CollectionExTest {

    @Test
    public void addElementToArrayList(){
        ArrayList<String> listOfNames = new ArrayList<>();
        listOfNames.add("A");
        listOfNames.add("B");
        listOfNames.add("C");
        listOfNames.add("D");
        listOfNames.add("E");
        log.info("names size: " + listOfNames.size());
        log.info("display the content: " + listOfNames);
        listOfNames.remove("E");
        listOfNames.remove(2);
        log.info("display the content: " + listOfNames);
    }

    @Test
    public void arrayListToArrayTest(){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        Integer[] numbersArray = new Integer[numbers.size()];
        numbersArray = numbers.toArray(numbersArray);
        //sum of the array
        Integer sum = 0;
        for(Integer n: numbersArray) sum += n;
        log.info("display the content: " + numbers);
        log.info("sum of integers: "+ sum);
    }

    @Test
    public void linkedListTest(){
        LinkedList<String> names = new LinkedList<String>();
        names.add("B");
        names.add("C");
        names.add("D");
        names.addFirst("A");
        names.addLast("E");
        log.info("display the content: " + names);
        names.add(1, "A1");
        names.set(3, "B1");
        log.info("display the content: " + names);
        names.remove(1);
        names.removeLast();
        names.removeFirst();
        log.info("display the content: " + names);
    }

    @Test
    public void arrayDequeTest(){
        //use it like a stack
        ArrayDeque<String> names = new ArrayDeque<>();
        names.push("A");
        names.push("B");
        //get the entries out of array
        while(names.poll() != null){
            log.info(names.pop()+ " ");
        }
    }

    @Test
    public void iteratorUseOverArrayList(){
        ArrayList<String> listOfNames = new ArrayList<>();
        listOfNames.add("A");
        listOfNames.add("B");
        listOfNames.add("C");
        listOfNames.add("D");
        listOfNames.add("E");
        Iterator<String> names = listOfNames.iterator();
        while(names.hasNext()){
            log.info(names.next()+ " ");
        }
        //modify entry while iterate using listIterator
        ListIterator<String> nameList = listOfNames.listIterator();
        while (nameList.hasNext()){
            log.info(nameList.next()+" ");
            nameList.add("F");
        }
        //view elements in reverse order
        while (nameList.hasPrevious()){
            log.info(nameList.previous()+" ");
        }
        log.info("display the content: " + listOfNames);
    }

    @Test
    public void SpliteratorTest(){
        ArrayList<Double> vals = new ArrayList<>();
        vals.add(1.0);
        vals.add(2.0);
        vals.add(3.0);
        vals.add(4.0);
        vals.add(5.0);
        Spliterator<Double> numbers = vals.spliterator();
        while(numbers.tryAdvance((n)-> log.info("val: "+ n)));

        numbers = vals.spliterator();
        ArrayList<Double> sqrt = new ArrayList<>();
        while (numbers.tryAdvance((n)->sqrt.add(Math.sqrt(n))));

        Spliterator<Double> sqrts = sqrt.spliterator();
        sqrts.forEachRemaining((n)-> log.info("val: "+ n));
    }

    @Test
    public void mailListTest(){
        LinkedList<Address> ml = new LinkedList<Address>();
        // Add elements to the linked list.
        ml.add(new Address("J.W. West", "11 Oak Ave",
                "Urbana", "IL", "61801"));
        ml.add(new Address("Ralph Baker", "1142 Maple Lane",
                "Mahome", "IL", "61853"));
        ml.add(new Address("Tom Carlton", "867 Elm St",
                "Champaign", "IL", "61820"));

        // Display the mailing list.
        for(Address element : ml)
            System.out.println(element + "\n");
    }

    @Test
    public void hashMapAndTreeMapTest(){
        HashMap<String, Double> hm = new HashMap<String, Double>();
        hm.put("John Doe", 3434.34);
        hm.put("Tom Smith", 123.22);
        hm.put("Jane Baker", 1378.00);
        hm.put("Tod Hall", 99.22);
        hm.put("Ralph Smith", -19.08);
        Set<Map.Entry<String, Double>> set = hm.entrySet();
        for(Map.Entry<String, Double> entry: set){
            log.info("Entry Key: "+ entry.getKey());
            log.info("Entry Value: "+ entry.getValue());
        }
        // Deposit 1000 into John Doe's account.
        double balance = hm.get("John Doe");
        hm.put("John Doe", balance + 1000);

        HashMap<String, Double> tm = new HashMap<String, Double>();
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Jane Baker", 1378.00);
        tm.put("Tod Hall", 99.22);
        tm.put("Ralph Smith", -19.08);
        Set<Map.Entry<String, Double>> settm = tm.entrySet();
        for(Map.Entry<String, Double> entry: settm){
            log.info("Entry Key: "+ entry.getKey());
            log.info("Entry Value: "+ entry.getValue());
        }
        // Deposit 1000 into John Doe's account.
        double balances = hm.get("John Doe");
        tm.put("John Doe", balances + 1000);
    }

    @Test
    public void reverseStringComparisionTest(){
        TreeSet<String> ts = new TreeSet<String>(new MyStringComparator());
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");
        for(String element : ts)
            log.info(element + " ");
        //using lambda expression for reversing the string
        TreeSet<String> tsc = new TreeSet<String>((str1, str2) -> str2.compareTo(str1));
        tsc.add("C");
        tsc.add("A");
        tsc.add("B");
        for(String element : tsc)
            log.info(element + " ");
    }

    @Test
    public void lastNameComparisionTest(){
        TreeMap<String, Double> tm = new TreeMap<String, Double>(new LastNameComparator());
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Jane Baker", 1378.00);
        tm.put("Tod Hall", 99.22);
        tm.put("Ralph Smith", -19.08);
        Set<Map.Entry<String, Double>> set = tm.entrySet();
        for(Map.Entry<String, Double> me : set) {
            log.info(me.getKey() + ": ");
            log.info(me.getValue()+": ");
        }
    }

    @Test
    public void connectionsFunctionsTest(){
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.add(-8);
        ll.add(20);
        ll.add(-20);
        ll.add(8);
        Comparator<Integer> re = Collections.reverseOrder();
        Collections.sort(ll, re);
        Collections.shuffle(ll);
        Collections.min(ll);
        Collections.max(ll);
    }
}
