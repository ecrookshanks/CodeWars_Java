package com.nokelservices;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

}
