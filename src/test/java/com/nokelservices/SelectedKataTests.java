package com.nokelservices;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class SelectedKataTests {

    @Test
    public void findValuesInShiftedSortedArray(){
        int[] sorted =  {9,12, 15, 22, 1, 2, 4, 5, 7, 8};

        int result = Algorithms.shiftedBinarySearch(sorted, 5);

        assertEquals(7, result);
        assertEquals(5, sorted[result]);
    }

    @Test
    public void BasicAlphabetWarTest() {
        assertEquals("Right side wins!", Algorithms.alphabetWar("z"));
        assertEquals("Let's fight again!", Algorithms.alphabetWar("zdqmwpbs"));
        assertEquals("Right side wins!", Algorithms.alphabetWar("zzzzs"));
        assertEquals("Left side wins!", Algorithms.alphabetWar("wwwwwwz"));
    }

    @Test
    public void BasicAlphabetWarMapTest() {
        assertEquals("Right side wins!", Algorithms.alphabetWarMap("z"));
        assertEquals("Let's fight again!", Algorithms.alphabetWarMap("zdqmwpbs"));
        assertEquals("Right side wins!", Algorithms.alphabetWarMap("zzzzs"));
        assertEquals("Left side wins!", Algorithms.alphabetWarMap("wwwwwwz"));
    }

    @Test
    public void abcdeReturnsZero() {
        assertEquals(0, Algorithms.duplicateCount("abcde"));
    }

    @Test
    public void abcdeaReturnsOne() {
        assertEquals(1, Algorithms.duplicateCount("abcdea"));
    }

    @Test
    public void indivisibilityReturnsOne() {
        assertEquals(1, Algorithms.duplicateCount("indivisibility"));
    }

    @Test
    public void reallyLongStringContainingDuplicatesReturnsThree() {
        String testThousandA = new String(new char[1000]).replace('\0', 'a');
        String testHundredB = new String(new char[100]).replace('\0', 'b');
        String testTenC = new String(new char[10]).replace('\0', 'c');
        String test1CapitalA = new String(new char[1]).replace('\0', 'A');
        String test1d = new String(new char[1]).replace('\0', 'd');
        String test = test1d + test1CapitalA + testTenC +
                testHundredB + testThousandA;


        assertEquals(3, Algorithms.duplicateCount(test));
    }

    @Test
    public void deleteNth() throws Exception {
        assertArrayEquals(
                new int[] { 20, 37, 21 },
                KataFunctions.deleteNth( new int[] { 20, 37, 20, 21 }, 1 )
        );
        assertArrayEquals(
                new int[] { 1, 1, 3, 3, 7, 2, 2, 2 },
                KataFunctions.deleteNth( new int[] { 1, 1, 3, 3, 7, 2, 2, 2, 2 }, 3 )

        );
        assertArrayEquals(
                new int[] { 1, 2, 3, 1, 1, 2, 2, 3, 3, 4, 5 },
                KataFunctions.deleteNth( new int[] { 1, 2, 3, 1, 1, 2, 1, 2, 3, 3, 2, 4, 5, 3, 1 }, 3 )
        );
        assertArrayEquals(
                new int[] { 1, 1, 1, 1, 1 },
                KataFunctions.deleteNth( new int[] { 1, 1, 1, 1, 1 }, 5 )
        );
        assertArrayEquals(
                new int[] { },
                KataFunctions.deleteNth( new int[] { }, 5 )
        );

    }


}
