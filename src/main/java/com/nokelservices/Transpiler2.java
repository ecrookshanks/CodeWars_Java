package com.nokelservices;

import java.util.Stack;

public class Transpiler2 {

    public static String transpile (String expression) {
        if (!validateParens(expression)){
            return "";
        }
        String proc = removeSpaces(expression);

        return null;
    }

    private static String removeSpaces(String exp) {
        StringBuffer sb = new StringBuffer();
        for (char ch :
                exp.toCharArray()){
            if(ch != ' ' && ch != '\n'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static boolean validateParens(String expression){
        Stack<Character> parens = new Stack<>();
        for (char ch :
                expression.toCharArray()) {
            if(ch == '('){
                parens.push(ch);
            }
            else if (ch == ')') {
                parens.pop();
            }
        }
        return parens.size() == 0;
    }


}
