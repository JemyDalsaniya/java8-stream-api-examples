package com.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class W3ResourceExercise {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 4);

        //Find Odd or even numbers
        List<Integer> oddList = new ArrayList<>(numbers
                .stream()
                .filter(num -> num % 2 != 0)
                .toList());
        oddList.forEach(System.out::println);

        //1. Write a Java program to calculate the average of a list of integers using streams.
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);// Default value in case the list is empty
        System.out.println("Average: " + average);

        // 2. Write a Java program to convert a list of strings to uppercase or lowercase using streams.
        List<String> stringList = Arrays.asList("Jemy", "Jaydeep", "Brijesh");
        stringList.stream().map(String::toUpperCase).forEach(System.out::println);

        //3. Write a Java program to calculate the sum of all even, odd numbers in a list using streams.
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .filter(num -> num % 2 != 0)
                .sum();
        System.out.println("sum: " + sum);

        //4. Write a Java program to remove all duplicate elements from a list using streams.
        numbers.stream().distinct().forEach(System.out::println);

        //5. Write a Java program to count the number of strings in a list that start with a specific letter using streams.
        long number = stringList.stream().filter(word -> word.startsWith("J")).count();
        System.out.println("number : " + number);

        //6. Write a Java program to sort a list of strings in alphabetical order, ascending and descending using streams.
        System.out.println(stringList.stream().sorted(Comparator.reverseOrder()).toList());
        System.out.println(stringList.stream().sorted().toList());

        //7. Write a Java program to find the maximum and minimum values in a list of integers using streams.
        Integer max = numbers.stream().max(Integer::compare).orElse(null);
        Integer min = numbers.stream().min(Integer::compare).orElse(null);
        System.out.println("max: " + max + "min :" + min);

        //8. Write a Java program to find the second smallest and largest elements in a list of integers using streams.
        int smallest = numbers.stream().distinct().sorted().skip(1).findFirst().orElse(null);
        int largest = numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);
        System.out.println("smallest :" + smallest + " largest :" + largest);
    }
}
