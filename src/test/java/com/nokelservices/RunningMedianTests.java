package com.nokelservices;


import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunningMedianTests {

    @Test
    public void runningMedian(){
        int[] input = {12,4,5,3,8,7};
        double[] output = {12.0, 8.0,5.0,4.5,5.0,6.0};

        double[] result = Algorithms.createRunningMedianArray(input);

        assertArrayEquals(output, result);

    }
}
