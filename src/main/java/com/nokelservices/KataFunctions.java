package com.nokelservices;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
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


    public static String makeReadable(int seconds) {

        if ( seconds == 0){
            return "00:00:00";
        }

        Integer secs = seconds%60;
        Integer mins = (seconds/60)%60;
        Integer hrs = seconds/3600;

        String SECS = String.format("%02d", secs);
        String MINS = String.format("%02d", mins);
        String HRS = String.format("%02d", hrs);
        return HRS +":"+MINS+":" + SECS;

    }
}
