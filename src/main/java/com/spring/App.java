package com.spring;

import java.util.*;
import java.util.stream.Collectors;

public class App {


    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {


        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();
        employeeList.forEach(employee -> System.out.println("Employee: " + employee.getId()));

        System.out.println("====================Question 1=====================");
        //Question 1
        //List all distinct project in non-ascending order.
        employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream())  // Flatten project streams from each employee
                .distinct()                                           // Remove duplicate projects
                .sorted(Comparator.comparing(Project::getName).reversed())      // Sort projects in descending order
                .forEach(System.out::println);                        // Print each distinct project
        System.out.println("===================Question 2======================");

        //Question 2
        //Print full name of any employee whose firstName starts with ‘A’.
        List<String> testList = employeeList
                .stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .toList();
        testList.forEach(System.out::println);
        System.out.println("=================Question 3========================");

        //Question 3
        // List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)
        System.out.println("join ====" + employeeList.stream().filter(employee -> Integer.parseInt(employee.getId().substring(0, 3)) == 2023));
        List<Employee> employeeJoinList = new ArrayList<>(
                employeeList.stream()
                        .filter(employee -> Integer.parseInt(employee.getId().substring(0, 4)) == 2023)
                        .toList());
        employeeJoinList.forEach(System.out::println);

        System.out.println("=================Question 4=====================");
        //Question 4
        //Sort employees based on firstName, for same firstName sort by salary.
        List<Employee> sortedEmployees = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getSalary))
                .toList();
        sortedEmployees.forEach(System.out::println);

        System.out.println("====================Question 5====================");

        //Question 5
        //Print names of all employee with 3rd highest salary. (generalise it for nth highest salary)

        int n = 3;
        List<Employee> employeeList1 = employeeList
                .stream()
                .filter(
                        e -> e.getSalary() == employeeList
                                .stream()
                                .map(Employee::getSalary)
                                .sorted(Comparator.reverseOrder())
                                .distinct()
                                .skip(n - 1) // Skip until the n-1th highest salary
                                .findFirst() // Get the nth highest salary
                                .orElse(Integer.MIN_VALUE))
                .toList();
        employeeList1.forEach(e -> System.out.println("Name of " + n + " highest salary: " + e.getFirstName()));

        System.out.println("===============Question 6======================");

        //Question 6
        // Print min salary.
        System.out.println("Min salary: " + employeeList.stream().map(Employee::getSalary).sorted().distinct().findFirst().orElse(Integer.MIN_VALUE));
        ;

        System.out.println("================Question 7====================");
        //Question 7
        //Print list of all employee with min salary.
        List<Employee> minSalaryEmployeeList = employeeList
                .stream()
                .filter(
                        e -> e.getSalary() == employeeList
                                .stream()
                                .map(Employee::getSalary)
                                .sorted()
                                .distinct()
                                .findFirst() // Get the nth highest salary
                                .orElse(Integer.MIN_VALUE))
                .toList();
        minSalaryEmployeeList.forEach(System.out::println);

        System.out.println("================Question 8======================");
        //Question 8
        //List of people working on more than 2 projects.
        employeeList.stream().filter(employee -> (long) employee.getProjects().size() > 2).toList().forEach(System.out::println);

        System.out.println("==================Question 9========================");

        //Question 9
        //Count of total laptops assigned to the employees.
        System.out.println("total assigned laptops count : " + employeeList.stream().mapToInt(Employee::getTotalLaptopsAssigned).sum());

        System.out.println("==============Question 10 & 11======================");

        //Question 10 & 11
        //Count of all projects with Robert Downey Jr as PM.
        // System.out.println(
        long count = employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream())
                .filter(project -> project.getProjectManager().equals("Robert Downey Jr"))
                .distinct()
                .count();
        System.out.println("count : " + count);
        //  .toList()
        // .forEach(System.out::println);
        // .count());
        System.out.println("===============Question 12=======================");

        //Question 12
        // List of all people working with Robert Downey Jr.
        String pm = "Robert Downey Jr";
        employeeList
                .stream()
                .filter(employee -> employee.getProjects().stream().anyMatch(project -> pm.equalsIgnoreCase(project.getProjectManager())))
                .forEach(System.out::println);
        System.out.println("==================Question 13====================");
        //Question 13
        // Create a map based on this data, they key should be the year of joining, and value should be list of all the employees who joined the particular year
        Map<String, List<Employee>> mapOfEmployeeOnJoiningYear = employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getId().substring(0, 4), Collectors.toList()));
        for (Map.Entry<String, List<Employee>> entry : mapOfEmployeeOnJoiningYear.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::println);

        }

        System.out.println("==================Question 14====================");

        //Question 14
        //Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year.

        employeeList.stream()
                .collect(Collectors.toMap(employee -> employee.getId().substring(0, 4), employee -> 1, Integer::sum,
                        TreeMap::new))
                .forEach((key, value) -> System.out.println(key + "=" + value));



    }
}
