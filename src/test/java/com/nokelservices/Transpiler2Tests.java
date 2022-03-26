package com.nokelservices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Transpiler2Tests {
    public void expect (String actual, String expected) {
        assertEquals (expected, actual);
        //collector.checkThat (expected, CoreMatchers.equalTo (actual));
    }

    public void fromTo (String input, String expected) {
        expect (Transpiler2.transpile (input), expected);
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

    // TODO: Need to write tests for:
    // (12,ab)  ==> expected:<[]> but was:<[(12,ab)]>
    // {a,b,c->a  b c}(233,666,999) ==> expected:<(a,b,c){a[;b;]c;}(233,666,999)> but was:<(a,b,c){a[b]c;}(233,666,999)>
    // f(a,)  ==>  expected:<[]> but was:<[f(a,)]>
    // f(x,y){a,b->a\nb} ==> expected:<f(x,y,(a,b){a[;]b;})> but was:<f(x,y,(a,b){a[]b;})>
    // run(a){as we can} ==> expected:<run(a,(){as[;we;]can;})> but was:<run(a,(){as[we]can;})>

}
