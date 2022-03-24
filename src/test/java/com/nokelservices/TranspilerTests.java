package com.nokelservices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.runners.JUnit4;
//import org.junit.jupiter.api.Rule;
//import org.junit.rules.ErrorCollector;
//import org.hamcrest.CoreMatchers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TranspilerTests {

//    @Rule
//    public ErrorCollector collector = new ErrorCollector();

    public void expect (String actual, String expected) {
        assertEquals (expected, actual);
        //collector.checkThat (expected, CoreMatchers.equalTo (actual));
    }

    public void fromTo (String input, String expected) {
        expect (Transpiler.transpile (input), expected);
    }

    public void shouldFail (String input) {
        fromTo (input, "");
    }

    @Test
    public void testSomething() {
        fromTo ("", "");
        fromTo ("1()", "1()");
        fromTo ("123()", "123()");
        fromTo ("a()", "a()");
        fromTo ("abc()", "abc()");
    }

    @Test
    public void testWhen_there_is_no_lambda () {
        fromTo ("call()", "call()");
        shouldFail ("%^&*(");
        shouldFail ("x9x92xb29xub29bx120()!(");
        fromTo ("invoke  (       a    ,   b   )", "invoke(a,b)");
        fromTo ("invoke(a, b)", "invoke(a,b)");
    }

    @Test
    public void testWhen_there_are_lambda_expressions () {
        fromTo ("call({})", "call((){})");
        fromTo ("f({a->})", "f((a){})");
        fromTo ("f({a->a})", "f((a){a;})");
    }

    @Test
    public void testWhen_lambda_expressions_aren_t_inside_brackets () {
        fromTo ("call(\n){}", "call((){})");
        fromTo ("invoke  (       a    ,   b   ) { } ", "invoke(a,b,(){})");
        fromTo ("f(x){a->}", "f(x,(a){})");
        fromTo ("f(a,b){a->a}", "f(a,b,(a){a;})");
        fromTo ("run{a}", "run((){a;})");
    }

    @Test
    public void testWhen_invoking_a_lambda_directly () {
        fromTo ("{}()", "(){}()");
        fromTo ("{a->a}(233)", "(a){a;}(233)");
    }

}
