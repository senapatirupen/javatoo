package com.example.javatoo.basic.javacollections;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

@Slf4j
public class JavaIoEx {
    public static void main(String[] args) {
        new JavaIoEx().fileReadAndWrite();
    }
    public void fileReadAndWrite() {
        Properties ps = new Properties();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name, number;
        FileInputStream fis = null;
        Boolean changed = false;
        //open phonebook.dat file and insert phone numbers to properties
        try {
            fis = new FileInputStream(ResourceUtils.getFile("classpath:phonebook.dat"));
            if (fis != null) {
                ps.load(fis);
                fis.close();
            }
        } catch (FileNotFoundException e) {
            log.error(" Error on loading file: " + e.getLocalizedMessage());
        } catch (IOException e) {
            log.error(" Error on loading data from file: " + e.getLocalizedMessage());
        }
        //add phone numbers to properties from cmd. Type "quit" to stop reading
        try {
            do {
                name = br.readLine();
                if (name.equals("quit")) continue;
                number = br.readLine();
                ps.setProperty(name, number);
                changed = true;
            } while (!name.equalsIgnoreCase("quit"));
        } catch (IOException e) {
            log.error(" Error on reading data from cmd: " + e.getLocalizedMessage());
        }
        //if data changed in properties then save it to file
        try {
            if(changed){
                FileOutputStream fops = new FileOutputStream(ResourceUtils.getFile("classpath:phonebook.dat"));
                ps.store(fops, "Telephone Book");
                fops.close();
            }
        } catch(FileNotFoundException e){
            log.error(" Error on opening file: " + e.getLocalizedMessage());
        } catch (IOException e) {
            log.error(" Error on pushing data to file: " + e.getLocalizedMessage());
        }
        //read phone number from book
        try{
            do{
                name = br.readLine();
                if (name.equals("quit")) continue;
                number = ps.getProperty(name);
                log.info(number+" : "+name);
            } while (! name.equalsIgnoreCase("quit"));
        } catch (IOException e){
            log.error(" Error on reading data from cmd: " + e.getLocalizedMessage());
        }
    }
}
