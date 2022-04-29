package com.nokelservices;

import java.util.List;
import java.util.Stack;

public class Transpiler2 {

    public static String transpile (String expression) {
        if (!validateParensAndBraces(expression)){
            return "";
        }
        String removedSpaces = removeSpaces(expression);
        if (!validateParensAndBraces(removedSpaces)){
            return "";
        }
        if ( removedSpaces.length() == 0){
            return "";
        }

        Function function = new Function(removedSpaces);

        return function.ToTargetLanguage();
    }

    private static String processLambda(String expression) {
        return null;
    }

    private static String removeSpaces(String exp) {
        StringBuffer sb = new StringBuffer();
        for (char ch : exp.toCharArray()){
            if(ch != ' ' && ch != '\n'){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static boolean validateParensAndBraces(String expression){
        Stack<Character> parens = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if(ch == '(' || ch == '{'){
                parens.push(ch);
            }
            else if (ch == ')' || ch=='}') {
                parens.pop();
            }
        }
        return parens.size() == 0;
    }

    static class Function {
        Expression exp;
        Lambda lambda;
        List<Expression> params;

        public Function(String fullExpression){
            this.ParseIncomingString(fullExpression);
        }

        public void ParseIncomingString(String expression){
            if(expression.contains("(")) {
                exp = new Expression(expression.substring(0, expression.indexOf("(")), "P");
//                parenExp = expression.substring(expression.indexOf("("));
//
//                int endParen = parenExp.indexOf(")");
//
//                if(parenExp.contains("{")){
//                    lambda = processLambda(parenExp);
//                }
            } else if(expression.contains("{")) {
                lambda = new Lambda(processLambda(expression));
            }
        }

        public String ToTargetLanguage() {
            if (exp.ExpType == "P"){
                return exp.contents + "(" + printParams() + ")";
            }
            return null;
        }

        private String printParams(){
            int len = params.size();
            if (len == 0) return "";

            String retVal = params.get(0).contents;

            return "";
        }
    }
    static class Expression{
        public String ExpType; // P or L (Plain or Lambda)
        public String contents;

        public Expression(String exp, String type){
            ExpType = type;
            contents = exp;
        }
    }

    static class Lambda{
        String contents;
        List<Expression> parameters;

        public Lambda(String expression){
            contents = expression;
        }
    }


}
