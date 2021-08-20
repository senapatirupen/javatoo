package com.example.javatoo.streamandcollection;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Nice {
    public static void main(String[] args) {
        List<File> sourceDirectories = new ArrayList<>();
        List<File> destinationDirectories = new ArrayList<>();
        final String srcDirectory = "C:\\Users\\Rupen_PC\\Desktop\\demo\\interview\\Java-Coding-Problems-master\\Java-Coding-Problems-master\\Chapter17";
        final String destDirectory = "C:\\EDrive\\Examples\\javacodingchallenges\\chapters\\modern\\challenge\\chapter17";
        File files = new File(srcDirectory);
        String[] names = files.list();
        for (String s : names) {
            if (s.contains("_"))
                sourceDirectories.add(new File(srcDirectory + "\\" + s + "\\src\\modern\\challenge"));
        }

//        Arrays.asList(names).stream().forEach(System.out::println);

        List<String> directories = Arrays.asList(names).stream().collect(Collectors.toList());
        List<String> finalNames = new ArrayList<>();
        for (String s : directories) {
            if (s.contains("_"))
                finalNames.add(s.substring(s.lastIndexOf("_") + 1).toLowerCase());
        }
//        finalNames.stream().forEach(System.out::println);
        finalNames.stream().forEach(s -> {
            File file = new File(destDirectory + "\\" + s);
            file.mkdirs();
            destinationDirectories.add(file);
        });

//        sourceDirectories.stream().forEach(System.out::print);
//        destinationDirectories.stream().forEach(System.out::print);
        int count = 0;
        for (File source : sourceDirectories) {
            File dest = destinationDirectories.get(count);
//            File source = new File("C:\\Users\\Rupen_PC\\Desktop\\demo\\interview\\Java-Coding-Problems-master\\Java-Coding-Problems-master\\Chapter09");
//            File dest = new File("C:\\EDrive\\Examples\\javatoo\\src\\main\\java\\com\\example\\javatoo\\streamandcollection\\test");
//            if(source.getName().contains(dest.getName().substring(dest.getName().lastIndexOf("chapter01")+1))){
            System.out.println(source.getAbsolutePath() + " >>> : From To : >>> " + dest.getAbsolutePath());
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (FileNotFoundException e) {
                System.out.println("Error on reading source: ##### " + source + " ##### " + e.getLocalizedMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            }
            count++;
        }

    }
}
