package com.example.javatoo.designpattern.behavioraldesignpatterns.others;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

@Slf4j
public class FileContentReader {
    public void read()  {
        try(Scanner scanner = new Scanner(Path.of("doubles.txt"), StandardCharsets.UTF_8)){
            if(scanner.hasNextDouble()){
                double value = scanner.nextDouble();
            }
        } catch (IOException e){
            log.error("File not found", e.getMessage());
        }
    }

    public void print(){
        try(Scanner scanner = new Scanner(Path.of("doubles.txt"), StandardCharsets.UTF_8)){
            if(scanner.hasNextDouble()){
                double value = scanner.nextDouble();
            }
        } catch (IOException e){
            log.error("File not found", e.getMessage());
        }
    }

    public double read(ScannerDoubleFunction scannerDoubleFunction) {
        try (Scanner scanner = new Scanner(Path.of("doubles.txt"), StandardCharsets.UTF_8)) {
            return scannerDoubleFunction.readDouble(scanner);
        } catch (IOException e) {
            log.error("File not found", e.getMessage());
        }
        return 0;
    }

    private static double getFirst(Scanner scanner) {
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        }
        return Double.NaN;
    }
    private static double sumAll(Scanner scanner) {
        double sum = 0.0d;
        while (scanner.hasNextDouble()) {
            sum += scanner.nextDouble();
        }
        return sum; }

    public static void main(String[] args) {
        double result = new FileContentReader().read((Scanner sc) -> getFirst(sc));
        log.info(String.valueOf(result));
        double result1 = new FileContentReader().read((Scanner sc) -> sumAll(sc));
        log.info(String.valueOf(result1));
    }
}
