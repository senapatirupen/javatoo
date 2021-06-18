package com.example.javatoo.basic.lambdex;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
public class MainLambdaEx {
    public static void main(String[] args) {
        new MainLambdaEx().exTwo();
    }

    public void exOne() {
        //interface reference
        MyNumber myNum;
        // Here, the lambda expression is simply a constant expression.
        // When it is assigned to myNum, a class instance is constructed in which the lambda expression
        // provides an override of the getValue() method in MyNumber.
        myNum = () -> 3.141;
        // Call getValue(), which is overridden by the previously assigned lambda expression.
        log.info(myNum.getValue() + " ");
        //more complex one
        myNum = () -> Math.random() * 100;
        log.info(myNum.getValue() + " ");
    }

    public void exTwo() {
        //even odd
        NumericTest numericTest = (n) -> (n % 2) == 0;
        log.info(numericTest.test(12) + " ");

        //finding factor
        NumericTest2 numericTest2 = (n, d) -> (n % d) == 0;
        log.info(numericTest2.test(10, 2) + " ");

        //find factorial
        NumericFunc numericFunc = (n) -> {
            int result = 1;
            for (int i = 1; i < n; i++) {
                result = i * result;
            }
            return result;
        };
        log.info(numericFunc.test(3) + " ");

        //reverse characters in a string
        StringFunc stringFunc = (n) -> {
            String result = "";
            int i;
            for (i = n.length() - 1; i >= 0; i--) {
                result += n.charAt(i);
            }
            return result;
        };
        log.info(stringFunc.test("Abhijit"));

        //use of generic functional interface
        GenericFunc<String> genericFunc = (n) -> {
            return n + "ok";
        };
        log.info(genericFunc.test("Abhijit"));
        GenericFunc<Integer> genericFunc1 = (n) -> {
            return n * 5;
        };
        log.info(genericFunc1.test(5) + " ");

        //use lambda expression as an argument to a method
        String some = stringOp((n) -> n.toUpperCase(), "Abhijit");
        log.info(some);

        //remove space from a string
        String some1 = stringOp((str) -> {
            String result = "";
            int i;
            for (i = 0; i < str.length(); i++)
                if (str.charAt(i) != ' ')
                    result += str.charAt(i);
            return result;
        }, "Abhi jit");
        log.info(some1);

        // Of course, it is also possible to pass a StringFunc instance created by an earlier lambda expression.
        String some2 = stringOp(stringFunc, "Abhijit");
        log.info(some2);

        // An example of capturing a local variable from the enclosing scope.
        int val = 2;
        NumericTest numericTest1 = (n) -> {
            if (n > 10) {
                return true;
            } else {
                n = n + val;
//                val++; not allowed to change local variable
                return false;
            }
        };
        log.info(numericTest1.test(12) + " ");

        // Demonstrate a method reference for a static method.
        // MyStringOps class defines a static method called strReverse().
        // stringOp(StringFunc stringFunc, String str) method has a functional interface as the type of
        // its first parameter. Thus, it can be passed any instance of that interface, including a method reference.
        // Here, a method reference to strReverse is passed to stringOp().
        String outStr = stringOp(MyStringOps::strReverse, "Lambdas add power to Java");
        log.info(outStr);

        // Demonstrate a method reference to an instance method
        // MyStringOpers class defines an instance method called strReverse().
        // stringOp(StringFunc stringFunc, String str) method has a functional interface as the type of
        // its first parameter. Thus, it can be passed any instance of that interface, including a method reference.
        // Here, a method reference to strReverse is passed to stringOp().
        MyStringOpers strOps = new MyStringOpers();
        outStr = stringOp(strOps::strReverse, "Lambdas add power to Java");

        // Use an instance method reference with different objects.
        int count;
        // Create an array of HighTemp objects.
        HighTemp[] weekDayHighs = {new HighTemp(89), new HighTemp(82),
                new HighTemp(90), new HighTemp(89),
                new HighTemp(89), new HighTemp(91),
                new HighTemp(84), new HighTemp(83)};
        // Use counter() with arrays of the class HighTemp.
        // Notice that a reference to the instance method sameTemp() is passed as the second argument.
        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(91));
        log.info(count + "");
        count = counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(91));
        log.info(count + "");

        // Demonstrate a method reference to a generic method declared inside a non-generic class.
        // MyArrayOps class defines a method called countMatching() that returns the number of items in an array that are equal
        // to a specified value. Notice that countMatching() is generic, but MyArrayOps is not.
        Integer[] vals = {1, 2, 3, 4, 2, 3, 4, 4, 5};
        String[] strs = {"One", "Two", "Three", "Two"};
        count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
        log.info("vals contains " + count + " 4s");
        count = myOp(MyArrayOps::<String>countMatching, strs, "Two");
        log.info("strs contains " + count + " Twos");

        // Use a method reference to help find the maximum value in a collection.
        ArrayList<MyClass> al = new ArrayList<MyClass>();
        al.add(new MyClass(1));
        al.add(new MyClass(4));
        al.add(new MyClass(2));
        al.add(new MyClass(9));
        al.add(new MyClass(3));
        al.add(new MyClass(7));
        MyClass maxObjectValue = Collections.max(al, new MainLambdaEx()::compareMC);
        log.info(maxObjectValue.getVal() + "");

        // Demonstrate a Constructor reference.
        // MyFuncReturn is a functional interface whose method returns a MyClass reference.
        // Create a reference to the MyClass constructor.
        // Because func() in MyFuncReturn takes an argument, new refers to the parameterized constructor in MyClass,
        // not the default constructor.
        MyFuncReturn myClassCons = MyClass::new;
        // Create an instance of MyClass via that constructor reference.
        MyClass mc = myClassCons.func(100);
        // Use the instance of MyClass just created.
        log.info("val in mc is " + mc.getVal());

        // Demonstrate a constructor reference with a generic class.
        // MyFuncReturnGeneric is now a generic functional interface.
        // Create a reference to the MyClassGeneric<T> constructor.
        MyFuncReturnGeneric<Integer> myClassConss = MyClassGeneric<Integer>::new;
        // Create an instance of MyClassGeneric<T> via that constructor reference.
        MyClassGeneric<Integer> mcs = myClassConss.func(100);
        // Use the instance of MyClassGeneric<T> just created.
        log.info("val in mc is " + mcs.getVal( ));
    }

    private String stringOp(StringFunc stringFunc, String str) {
        return stringFunc.test(str);
    }

    // A method that returns the number of occurences of an object for which some criteria, as specified by
    // the MyFunc parameter, is true.
    private <T> int counter(T[] vals, MyFunc<T> f, T t) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if (f.func(vals[i], t)) count++;
        }
        return count;
    }

    // This method has the MyFuncOne functional interface as the type of its first parameter. The other two parameters
    // receive an array and a value, both of type T.
    private <T> int myOp(MyFuncOne<T> f, T[] vals, T v) {
        return f.func(vals, v);
    }

    private int compareMC(MyClass a, MyClass b) {
        return a.getVal() - b.getVal();
    }


}

//listing 17
//// Implement a simple class factory using a constructor reference.
//
//interface MyFunc<R, T> {
//    R func(T n);
//}
//
//// A simple generic class.
//class MyClass<T> {
//    private T val;
//
//    // A constructor that takes an argument.
//    MyClass(T v) { val = v; }
//
//    // The default constructor. This constructor
//    // is NOT used by this program.
//    MyClass() { val = null; }
//    // ...
//
//    T getVal() { return val; };
//}
//
//// A simple, non-generic class.
//class MyClass2 {
//    String  str;
//
//    // A constructor that takes an argument.
//    MyClass2(String s) { str = s; }
//
//    // The default constructor. This
//    // constructor is NOT used by this program.
//    MyClass2() { str = ""; }
//
//    // ...
//
//    String getVal() { return str; };
//}
//
//class ConstructorRefDemo3 {
//
//    // A factory method for class objects. The class must
//    // have a constructor that takes one parameter of type T.
//    // R specifies the type of object being created.
//    static <R,T> R myClassFactory(MyFunc<R, T> cons, T v) {
//        return cons.func(v);
//    }
//
//    public static void main(String args[])
//    {
//        // Create a reference to a MyClass constructor.
//        // In this case, new refers to the constructor that
//        // takes an argument.
//        MyFunc<MyClass<Double>, Double> myClassCons = MyClass<Double>::new;
//
//        // Create an instance of MyClass by use of the factory method.
//        MyClass<Double> mc = myClassFactory(myClassCons, 100.1);
//
//        // Use the instance of MyClass just created.
//        System.out.println("val in mc is " + mc.getVal( ));
//
//        // Now, create a different class by use of myClassFactory().
//        MyFunc<MyClass2, String> myClassCons2 = MyClass2::new;
//
//        // Create an instance of MyClass2 by use of the factory method.
//        MyClass2 mc2 = myClassFactory(myClassCons2, "Lambda");
//
//        // Use the instance of MyClass just created.
//        System.out.println("str in mc2 is " + mc2.getVal( ));
//    }
//}
//
//listing 18
//// Use the Function built-in functional interface.
//
//// Import the Function interface.
//        import java.util.function.Function;
//
//class UseFunctionInterfaceDemo {
//    public static void main(String args[])
//    {
//
//        // This block lambda computes the factorial of an int value.
//        // This time, Function is the functional interface.
//        Function<Integer, Integer> factorial = (n) ->  {
//            int result = 1;
//            for(int i=1; i <= n; i++)
//                result = i * result;
//            return result;
//        };
//
//        System.out.println("The factoral of 3 is " + factorial.apply(3));
//        System.out.println("The factoral of 5 is " + factorial.apply(5));
//    }
//}
//
//
