package io.herald.projectSpring.Test;

import java.util.*;

public class CollectionFramework {

    public static void main(String[] args) {

        //List -> ArrayList, LinkedList, VectorList -> all are same, minor performance difference

        List<Integer> intList = new LinkedList<>();
        intList.add(1);

        //Set -> HashSet, LinkedHashSet, TreeSet -> no duplicate data

        Set<Integer> intSet = new TreeSet<>();
        intSet.add(1);

        //Map -> HashMap, LinkedHashMap, TreeMap

        Map<Integer, String>map = new HashMap<>(); //Key-Value pair
        map.put(1, "apple");
        map.put(2, "banana");

        System.out.println(map);



    }
}
