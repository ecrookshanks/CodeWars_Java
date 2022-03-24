package com.nokelservices;

import java.util.Stack;

public class Transpiler {
    public static String transpile (String expression) {
        if (!validateParens(expression)){
            return "";
        }
        String proc = removeSpaces(expression);
        String afterLamb = createLambdas(proc);
        return afterLamb;
    }

    private static String createLambdas(String exp) {
        int lambdaPos = -1;
        String result = "";
        int ndex = exp.indexOf('{');
        if (ndex != -1) {
            int endLamb = exp.indexOf('}');
            String first = exp.substring(0, ndex);
            String lamb = exp.substring(ndex, endLamb+1);
            String processedLamb = makeLambda(lamb.substring(1, (lamb.length()-1)));
            String rest = exp.substring(endLamb+1);
            result = first + processedLamb + rest;
        }
        else{
            result = exp;
        }

        return result;
    }

    private static String makeLambda(String lamb) {
        // incoming: {a -> a}
        // outgoing: (a){a}
        String args = "";
        String incoming = "";
        String outgoing = "";
        if(lamb.contains("->")){
            String[] argParts = lamb.split("->");
            incoming = argParts[0];
            if (argParts.length>1){
                outgoing = argParts[1] + ";";
            }
        }
        return "(" + incoming + "){" + outgoing + "}";

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
        Stack<Character> parens = new Stack<Character>();
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

