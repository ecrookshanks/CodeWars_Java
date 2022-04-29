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

    @Test
    public void testWhen_function_has_lambda_argument() {
        fromTo("fun {a -> a}", "fun((a){a;})");
        fromTo("fun { a, b -> a b }", "fun((a,b){a;b;})");
    }

    @Test
    public void testWhen_lambdaIsFunction_withArgument() {
        fromTo("{a -> a}(1)", "(a){a;}(1)");
    }

    @Test
    public void testWhen_emptyLambdaIsFunction_withEmptyLambdaArgument() {
        fromTo("{}{}", "(){}((){})");
    }

    @Test
    public void testExtended1() {
        fromTo ("(12,ab)c", "");
    }

    @Test
    public void testExtended1a() {
        fromTo ("f(12,ab)c", "");
    }

    @Test
    public void testExtended2() {
        fromTo ("a b c", "");
    }

    @Test
    public void testExtended3() {
        fromTo ("f( a v)", "");
    }

    @Test
    public void testExtended4() {
        fromTo ("run(a){as we can}", "run(a,(){as;we;can;})");
    }

    @Test
    public void testExtended5() {
        fromTo ("{a->a}(cde,y,z){x,y,d -> stuff}", "(a){a;}(cde,y,z,(x,y,d){stuff;})");
    }

    @Test
    public void testExtended6() {
        fromTo ("{}{}{}", "");
    }

    @Test
    public void testExtended7() {
        fromTo ("{a->a}(cde,y,z){x y d -> stuff}", "");
    }

    @Test
    public void testExtended8() {
        fromTo ("{eJDpS5vN   GTZEp    WyF3rAq } ({kp3bl  ,J4tFQ0,d1WEgp3 , 527  ->YUOAxY   Wnk pz0    58564004    5524    51159450    kSMQRD} , ts  ,QprPxOmd  ) {X7esinm,  3105,  HcKyUu0,A  , 423894  ,rT_E15  ->bYr    725 tTOz     61876214    WfcOt     43   791  }",
                "(){eJDpS5vN;GTZEp;WyF3rAq;}((kp3bl,J4tFQ0,d1WEgp3,527){YUOAxY;Wnk;pz0;58564004;5524;51159450;kSMQRD;},ts,QprPxOmd,(X7esinm,3105,HcKyUu0,A,423894,rT_E15){bYr;725;tTOz;61876214;WfcOt;43;791;})");
    }
}
