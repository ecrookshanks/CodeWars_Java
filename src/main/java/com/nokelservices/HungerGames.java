package com.nokelservices;

import java.util.*;
import java.util.stream.Collectors;

public class HungerGames {

    private static HashMap<String, Set<String>> mapAnimals = new HashMap<>();

    static{
        createZooAnimalList();
    }


    // This method is the single entry/exit point
    public static String[] whoEatsWho(final String things){

        List<String> incomingList = Arrays.asList(things.split(","));
        if (incomingList.size() == 1){
            return new String[] {incomingList.get(0), incomingList.get(0) };
        }

        List<String> ateList = new ArrayList<>();

        List<String> remaining = new ArrayList<>(incomingList);

        processList(ateList, remaining);

        String[] results = formatOutputMessage(
                things,
                ateList.stream().collect(Collectors.joining(",")),
                remaining.stream().collect(Collectors.joining(",")));
        System.out.println("OUTPUT: ");
        Arrays.stream(results).forEach(System.out::println);
        return results;
    }


    private static boolean processList(List<String> ateList, List<String> remaining) {
        // need to change this based on found?  remaining could legitimately have
        // more than one remaining depending on how the list is arranged.
        boolean found = false;
        for (int i = 0; i < remaining.size() && !found; i++) {
            String cur = remaining.get(i);
            Set<String> edibles = findEdibles(cur);
            if (edibles == null || edibles.size() == 0){
                continue;
            }
            // need to check +1 if == 0, +1 and -1 if > 0
            if (i>0){
                String prev = remaining.get(i-1);
                if (edibles.contains(prev)){
                    ateList.add(String.format("%s eats %s", cur, prev));
                    remaining.remove(i-1);
                    found = true;
                    break;
                }
            }
            if (i < remaining.size()-1){
                String next = remaining.get(i+1);
                if (edibles.contains(next)){
                    ateList.add(String.format("%s eats %s", cur, next));
                    remaining.remove(i+1);
                    found = true;
                }
            }
        }
        if (found) {
            found = processList(ateList, remaining);
        }
        return found;
    }

    private static String[] formatOutputMessage(String input, String processMessages, String remaining) {
        List<String> list = new ArrayList<>();
        list.add(input);
        if (processMessages != null && !processMessages.equals("")) {
            list.addAll(List.of(processMessages.split(",")));
        }
        list.add(remaining);

        return list.toArray(new String[0]);

    }

    public static Set<String> findEdibles(String animal) {
//        String[] results =  Arrays.stream(zooAnimals)
//                .filter(m -> m[0].equals(animal))
//                .map(m -> m[1])
//                .collect(Collectors.toList())
//                .toArray(String[]::new);

        Set<String> results = mapAnimals.get(animal);
        return results;
    }

    public static String[] getZooAnimals(){
        return mapAnimals.keySet().toArray(String[]::new);
    }


    //
    public static long createZooAnimalList(){

        mapAnimals.put("antelope", new HashSet<>(Arrays.asList("grass")));
        mapAnimals.put("big-fish", new HashSet<>(Arrays.asList("little-fish")));
        mapAnimals.put("bug", new HashSet<>(Arrays.asList("leaves")));
        mapAnimals.put("bear", new HashSet<>(Arrays.asList("big-fish", "bug", "chicken", "cow", "leaves", "sheep")));
        mapAnimals.put("chicken", new HashSet<String>(Arrays.asList("bug")));
        mapAnimals.put("cow", new HashSet<String>(Arrays.asList("grass")));
        mapAnimals.put("fox", new HashSet<String>(Arrays.asList("chicken", "sheep")));
        mapAnimals.put("giraffe", new HashSet<String>(Arrays.asList("leaves")));
        mapAnimals.put("lion", new HashSet<String>(Arrays.asList("antelope", "cow")));
        mapAnimals.put("panda", new HashSet<String>(Arrays.asList("leaves")));
        mapAnimals.put("sheep", new HashSet<String>(Arrays.asList("grass")));

        return mapAnimals.size();
    }


}
