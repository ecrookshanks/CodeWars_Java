package com.nokelservices;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JadenCaseTests {

    @Test
    public void simpleTestReturnsTrue(){
        assertTrue(true);
    }

    //
    //  String test = "How can mirrors be real if our eyes aren't real";
    //  String Desired = "How Can Mirrors Be Real If Our Eyes Aren't Real";

    @Test
    public void singleWordGetsCapitalized(){
        String nonCap = "capitol";
        String cap = "Capitol";

        char first = nonCap.charAt(0);
        char firstUpper = Character.toUpperCase(first);

        String result = firstUpper + nonCap.substring(1).toLowerCase();

        assertEquals(cap, result);
    }

    @Test
    public void entireSentenceGetsCapitalized(){
        String test = "How can mirrors be real if our eyes aren't real";
        String Desired = "How Can Mirrors Be Real If Our Eyes Aren't Real";

        String[] words = test.split(" ");

        String result =
                Arrays.stream(words).map(n -> n.toLowerCase())
                .map(w -> w.substring(0,1).toUpperCase() + w.substring(1))
                .collect(Collectors.joining(" "));

        assertEquals(Desired, result);

    }

}
