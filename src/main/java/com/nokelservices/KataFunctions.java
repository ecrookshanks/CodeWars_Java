package com.nokelservices;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class KataFunctions {


    public static int[] deleteNth(int[] elements, int maxOccurences) {
        // linkedHashMap keeps the insertion order.
        ArrayList<Integer> finalList = new ArrayList<>();
        LinkedHashMap<Integer, Integer> mapCount = new LinkedHashMap<>();
        for( int n: elements){
            if ( mapCount.containsKey(n)){
                int val = mapCount.get(n);
                mapCount.put(n,++val);
            }
            else{
                mapCount.put(n, 1);
            }

            if ( mapCount.get(n) <= maxOccurences ){
                finalList.add(n);
            }

        }
        return finalList.stream().mapToInt(n -> n).toArray();

    }
}
