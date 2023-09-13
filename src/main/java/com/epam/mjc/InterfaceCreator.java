package com.epam.mjc;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InterfaceCreator {

    public Predicate<List<String>> isValuesStartWithUpperCase() {
        return list -> list.stream().allMatch(element -> Character.isUpperCase(element.charAt(0)));
//        List<String> list = new ArrayList<String>();
//        boolean a = false;
//        for (int i = 0; i < list.size(); i++) {
//            a = Character.isUpperCase(list.get(i).charAt(0));
//        }
    }


    public Consumer<List<Integer>> addEvenValuesAtTheEnd() {
        return elements -> {
            List<Integer> listEven = new ArrayList<>(elements);
            listEven.forEach(element -> {
                if (element % 2 == 0) {
                    elements.add(element);
                }
            });
        };
    }

    public Supplier<List<String>> filterCollection(List<String> values) {
        return () -> {
            List<String> resultList;
            resultList = values.stream().filter(e ->
                    (e.endsWith(".") && Character.isUpperCase(e.charAt(0)) && e.split(" ").length > 3)
            ).collect(Collectors.toList());

            return resultList;
        };

    }


    public Function<List<String>, Map<String, Integer>> stringSize() {
        return stringsList -> stringsList.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
    }

    public BiFunction<List<Integer>, List<Integer>, List<Integer>> concatList() {
        return (list1, list2) ->
                Stream.of(list1, list2).flatMap(Collection::stream)
                        .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        InterfaceCreator ic = new InterfaceCreator();

        //1.Create Predicate
        List<String> list = Arrays.asList("Elena", "NATASHKA", "DeLcHeVo", "skopje");
        boolean a = ic.isValuesStartWithUpperCase().test(list);
        //System.out.println(ic.isValuesStartWithUpperCase().test(list));

        //2. Create Consumer
        List<Integer> evenList = new ArrayList<>(List.of(48, 13, 100, 5, 222, 3));
        ic.addEvenValuesAtTheEnd().accept(evenList);
        //System.out.println(evenList);

        //3. Create Supplier
        List<String> filteredList = Arrays.asList("hello world in Java.", "MJC is a great school.");
        ic.filterCollection(filteredList).get();
        //System.out.println(ic.filterCollection(filteredList).get());

        //4.Create Function
        List<String> functionList = Arrays.asList("Hello", "MJC");
        ic.stringSize().apply(functionList);
        //System.out.println(ic.stringSize().apply(functionList));

        //5.Create Bi-Function
        List<Integer> list1 = Arrays.asList(2, 4, 5, 10);
        List<Integer> list2 = Arrays.asList(3, 1, 10, 5);
        ic.concatList().apply(list1, list2);
        System.out.println(ic.concatList().apply(list1, list2));
    }

}
