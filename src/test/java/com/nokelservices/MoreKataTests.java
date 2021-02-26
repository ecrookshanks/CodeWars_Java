package com.nokelservices;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MoreKataTests {

    @Test
    public void Tests() {
        assertEquals( "00:00:00", KataFunctions.makeReadable(0));
        assertEquals("00:00:05", KataFunctions.makeReadable(5));
        assertEquals("00:01:00", KataFunctions.makeReadable(60));
        assertEquals( "23:59:59", KataFunctions.makeReadable(86399));
        assertEquals("99:59:59", KataFunctions.makeReadable(359999));
    }

    @Test
    public void testValid() {
        assertEquals(true, KataFunctions.isValid("()"));
        assertEquals(true, KataFunctions.isValid("(){}[]"));
        assertEquals(true, KataFunctions.isValid("([{}])"));
        // "(){}[]"   =>  True
        //"([{}])"   =>  True
    }

    @Test
    public void testInvalid() {
        assertEquals(false, KataFunctions.isValid("[(])"));
        assertEquals(false, KataFunctions.isValid("(}"));
        assertEquals(false, KataFunctions.isValid("[(])"));
        assertEquals(false, KataFunctions.isValid("[({})](]"));
        assertEquals(false, KataFunctions.isValid("]()]"));
        //"(}"       =>  False
        //"[(])"     =>  False
        //"[({})](]" =>  False
    }

    @Test
    public void testSpaceOptimization(){
        List<Integer> h = Arrays.asList(4);
        List<Integer> v = Arrays.asList(2);

        assertEquals(4, KataFunctions.storageOptimization(3, 3, h, v));

        // 3, 2, [1, 2, 3], [1, 2] should return 12
        h = Arrays.asList(1, 2, 3);
        v = Arrays.asList(1, 2);
        assertEquals(12, KataFunctions.storageOptimization(3, 3, h, v));

    }

}
