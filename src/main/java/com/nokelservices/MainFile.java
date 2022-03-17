package com.nokelservices;

public class MainFile {

    public static void main(String[] args){
        System.out.println("Hello from the simple side!");
        long even = Algorithms.sumOfEvenFibs(4000000);
//        System.out.println("Sum of even fibs: " + even);
//        System.out.println("Largest prime factor of 600851475143 is: " +
//                Algorithms.largestPrimeFactor(600851475143L));

//        System.out.println("the number 9009 is a palindrome: " +
//                Algorithms.isNumberPalindrome(9009));

        System.out.println("Largest palindrome from 3 digit multiples: " +
                Algorithms.largestPalindromeOf3DigitProducts());

        System.out.println("Splitting a sentence");

        KataFunctions.splitSentence();

    }
}
