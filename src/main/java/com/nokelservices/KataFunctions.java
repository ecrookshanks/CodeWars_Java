package com.nokelservices;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

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

    public static boolean isValid(String braces) {
        Stack<Character> items = new Stack<>();
        Character c;
        for(Character ch: braces.toCharArray()){
            switch (ch){
                case '(':
                case '{':
                case '[':
                    items.push(ch);
                    break;
                case ')':
                    if (items.size() == 0) return  false;
                    c = items.peek();
                    if ( c.equals('{') || c.equals('[')){
                        return false;
                    }
                    items.pop();
                    break;
                case '}':
                    if (items.size() == 0) return  false;
                    c = items.peek();
                    if ( c.equals('(') || c.equals('[')){
                        return false;
                    }
                    items.pop();
                    break;
                case ']':
                    if (items.size() == 0) return  false;
                    c = items.peek();
                    if ( c.equals('(') || c.equals('{')){
                        return false;
                    }
                    items.pop();
                    break;
                default:
                    break;
            }
        }
        return items.size() == 0;

    }

    // note - the below is correct, but not optimized - how to further
    // optimize it?  Maybe use toArray on the incoming List?
    public static long howManySwaps(List<Integer> arr) {
        int[] intArr = arr.stream().mapToInt(m -> m).toArray();
        long totalSwaps = 0;
        boolean didSwap = false;

        int siz = intArr.length;
        int nth, nthPlusOne, temp;
        do{
            didSwap = false;
            for(int i=0;i<siz-1;i++){
                nth = intArr[i];
                nthPlusOne = intArr[i+1];
                if ( nth > nthPlusOne){
                    temp = nth;
                    intArr[i] =  nthPlusOne;
                    intArr[i+1] = temp;
                    totalSwaps += 1;
                    didSwap = true;
                }
            }
        } while (didSwap == true);
        return totalSwaps;
    }

    public static long storageOptimization(int m, int n, List<Integer> h, List<Integer> v){
        // 3, 3, [4], [2] should return 4
        // 2, 2, [1], [1] should return 4
        // 3, 2, [1, 2, 3], [1, 2] should return 12
        long result = 0;

        long xConsec = 1, largestXConsec = 1;
        long yConsec = 1, largestYConsec = 1;

        // TODO: count the largest consecutive block in X
        for(int i=1; i<h.size(); i++){
            if(h.get(i-1) == h.get(i)-1){
                xConsec += 1;
            }
            else{
                if ( xConsec > largestXConsec){
                    largestXConsec = xConsec;
                }
                xConsec = 1;
            }
        }
        if (xConsec > largestXConsec) largestXConsec = xConsec;

        // TODO: count the largest consecutive block in Y
        for(int i=1; i<v.size(); i++){
            if(v.get(i-1) == v.get(i)-1){
                yConsec += 1;
            }
            else{
                if ( yConsec > largestYConsec){
                    largestYConsec = yConsec;
                }
                yConsec = 1;
            }
        }
        if ( yConsec > largestYConsec) largestYConsec = yConsec;

        // edge case
        if ( largestXConsec == 1 && largestYConsec == 1) largestXConsec++;

        result = (largestXConsec * largestYConsec) * 2;

        return result;
    }

}
