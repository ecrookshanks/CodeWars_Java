package com.nokelservices;

import java.util.*;

public class Algorithms {
    // given a sorted and shifted array, find the index of the target value
    public static int shiftedBinarySearch(int[] array, int target)
    {
        int left = 0, right = array.length - 1;
        while(left <= right)
        {
            int middle = left + (right - left) / 2;
            if(array[middle] == target)
            {
                return middle;
            }
            else if(array[middle] >= array[left])
            {
                if(target < array[middle] && target >= array[left])
                {
                    right = middle - 1;
                }
                else
                {
                    left = middle + 1;
                }
            }
            else
            {
                if(target > array[middle] && target <= array[right])
                {
                    left = middle + 1;
                }
                else
                {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }

    // Hashmap.put returns the previous value, or null if the element
    // does not already exist in the list.
    public static Character findFirstRepeatedChar(String testStr) {
        HashMap<Character, Integer> numCount = new HashMap<>();
        for( Character c: testStr.toCharArray()){
            if ( numCount.put(c, 1) != null){
                return c;
            }
        }
        return null;

    }

    // Return a double array of the running median
    public static double[] createRunningMedianArray(int[] input) {
        int[] temp = new int[input.length];
        double[] resultArray = new double[input.length];
        int counter = 0;

        for (int i = 0; i < input.length; i++) {
            temp[i] = input[i];
            counter++;

            if (i == 0) {
                resultArray[i] = (double) temp[i];
            }
            else {

                Arrays.sort(temp, 0, i + 1);

                int val = counter / 2;

                if (counter % 2 == 0)
                    resultArray[i] = (temp[val] + temp[val - 1]) / 2.0;
                else
                    resultArray[i] = temp[val];
            }
        }
        return resultArray;
    }

    public static long sumOfEvenFibs(int max){

        long evenSum = 0;
        long num1 = 0, num2 = 1;
        long sumOfPrevTwo=0;

        for (int i = 1; sumOfPrevTwo < max; i++)
        {
            sumOfPrevTwo = num1 + num2;
            num1 = num2;
            num2 = sumOfPrevTwo;
            if ( sumOfPrevTwo%2==0){
                evenSum += sumOfPrevTwo;
            }
        }
        return evenSum;
    }

    public static boolean isPrime(long value) {
        for(int i = 2; i < value; i++) {
            if(value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long largestPrimeFactor(long num){
        ArrayList<Long> primes = new ArrayList<>();
        ArrayList<Long> factors = new ArrayList<>();

        long max = (long)Math.sqrt(num);

        for (long n = 2; n<max; n++){
            if (num%n==0){
                if ( Algorithms.isPrime(n)) {
                    factors.add(n);
                }
            }
        }
//        for (long l :factors) {
//            if (Algorithms.isPrime(l)){
//                primes.add(l);
//            }
//        }
        int bigndex = factors.size();

        long bigPrime = factors.get(bigndex-1).longValue();

        return bigPrime;

    }

    public static int largestPalindromeOf3DigitProducts(){
        int num1 = 999, num2 = 999;
        int bigNum = num1 * num2;  // 998001

        int largest = 0;
        for(int i = 999; i >= 500; i--)
        {
            for(int j=999; j >= 500; j--)
            {
                int N = i * j;
                if(isNumberPalindrome(N) && N > largest)
                largest = N;
            }
        }
        return largest;

    }

    public static boolean isNumberPalindrome(int number){
        String num = Integer.toString(number);

        int n = num.length();

        // create a character array of same size as that of string
        char[] temp = new char[n];

        // fill character array backwards with characters of the string
        for (int i = 0; i < n; i++)
            temp[n - i - 1] = num.charAt(i);

        int newNum = Integer.parseInt(String.copyValueOf(temp));

        return number == newNum;


    }

    public static String alphabetWar(String fight) {
        /* Left side
         w - 4
         p - 3
         b - 2
         s - 1
        */

        /* Right side
         m - 4
         q - 3
         d - 2
         z - 1
        */
        int score = 0;
        for(Character ch: fight.toLowerCase().toCharArray()){
            switch (ch) {
                case 'w':
                    score += 4;
                    break;
                case 'p':
                    score += 3;
                    break;
                case 'b':
                    score += 2;
                    break;
                case 's':
                    score += 1;
                    break;
                case 'm':
                    score -= 4;
                    break;
                case 'q':
                    score -= 3;
                    break;
                case 'd':
                    score -= 2;
                    break;
                case 'z':
                    score -= 1;
                    break;
                default:
                    continue;

            }
        }
        if (score > 0){
            return "Left side wins!";
        }
        else if (score < 0){
            return "Right side wins!";
        }

        return "Let's fight again!";
    }

    public static String alphabetWarMap(String fight){
        HashMap<Character, Integer> letters = new HashMap<>();
        letters.put('w', 4);
        letters.put('p', 3);
        letters.put('b', 2);
        letters.put('s', 1);
        letters.put('m', -4);
        letters.put('q', -3);
        letters.put('d', -2);
        letters.put('z', -1);

        int total = 0;

        for (Character letter : fight.toCharArray()){
            if (letters.containsKey(letter))
                total += letters.get(letter);
        }

        if (total == 0) return "Let's fight again!";
        return total > 0 ? "Left side wins!" : "Right side wins!";
    }

    public static int duplicateCount(String text) {
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();

        for(Character ch: text.toLowerCase().toCharArray()){
            if(countMap.containsKey(ch)){
                Integer val = countMap.get(ch);
                countMap.put(ch, ++val );
            }
            else{
                countMap.put(ch, 1);
            }
        }

        int count = (int)(countMap.values().stream().filter(c -> (c>1)).count());

        return count;
    }
}
