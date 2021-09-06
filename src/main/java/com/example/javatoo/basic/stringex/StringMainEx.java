package com.example.javatoo.basic.stringex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringMainEx {
    public static void main(String[] args) {
        new StringMainEx().test();
    }

    public void test() {
        char[] chs = {'a', 's', 'd', 'f'};
        String s1 = new String(chs);
        String s2 = new String(s1);
        System.out.println(s1 + " equals " + s2 + " -> " + s1.equals(s2));
        System.out.println(s1 + " == " + s2 + " -> " + (s1 == s2));

        byte[] ascii = {65, 66, 67, 68, 69, 70};
        String s3 = new String(ascii);
        String s4 = new String(ascii, 2, 3);
        log.info("s4: " + s4);

        //from String to char[]
        char[] buff = new char[s4.length()];
        s4.getChars(0, s4.length() - 1, buff, 0);
        log.info(buff.toString());

        String s5 = new String("hello");
        String s6 = new String("hello");
        String s7 = "hello";
        String s8 = "hello";
        String s9 = s5;
        System.out.println(s5.equals(s6)); //true
        System.out.println(s5 == s6);      //false
        System.out.println(s7.equals(s8)); //true
        System.out.println(s7 == s8);      //true
        System.out.println(s5.equals(s7)); //true
        System.out.println(s5 == s7);      //false
        System.out.println(s5.equals(s9)); //true
        System.out.println(s5 == s9);      //true
    }
}
