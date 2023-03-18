package org.example;

import java.text.Collator;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonMain {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

        }

        long resultAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println(resultAge);

        List<String> resultFamiliesConscript = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println(resultFamiliesConscript);

        List<Person> employableEducatedPeoples = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && person.getAge() < (person.getSex() == Sex.MAN ? 65 : 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(employableEducatedPeoples);
    }
}
