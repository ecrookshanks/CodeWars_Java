package com.nokelservices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FirstRecurringLetterTest {

    @Test
    public void findFirstRecurringLetter(){
        String testStr = "ABDCBEDES";

        Character result = Algorithms.findFirstRepeatedChar(testStr);

        assertEquals('B', result);

    }

    @Test
    public void noRecurringCharacterExists(){
        String testStr = "ABCDEFGHI";

        Character result = Algorithms.findFirstRepeatedChar(testStr);

        assertNull(result);

    }
}
