package com.spring;

import java.util.List;
import java.util.stream.Collectors;

public class BasicFunctionsExercise {
    public static void main(String[] args) {

        //Use of Predicate
        //filter(Predicate) -> Takes predicate(boolean value function),
        List<String> names = List.of("Aman", "Ankit", "Abhinav", "Jemy");
        List<String> newNames = names.stream().filter(n -> n.startsWith("J")).toList();
        System.out.println(newNames);

        //Use of Map
        //map(Function) -> each element operation
        List<Integer> numbers = List.of(2, 3, 6, 4, 8);
        List<Integer> squares = numbers.stream().map(i -> i * i).toList();
        System.out.println(squares);

        //use of forEach
        //here each element will be stored in e
        numbers.stream().forEach(System.out::println);
        names.stream().forEach(
                e -> {
                    System.out.println(e);
                }
        );

        //use of sorted
        numbers.stream().sorted().forEach(System.out::println);

        //use of min
        Integer integer = numbers.stream().max(Integer::compareTo).get();
        System.out.println(integer);
    }
}
