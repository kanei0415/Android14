package com.example.simplecalc;

import android.widget.Toast;

import java.util.Vector;

public class Calc {
    private String expression;
    private boolean error;
    private String[] numbers;

    private Vector<String> numberList = new Vector<String>(100);
    private Vector<Character> operatorList = new Vector<Character>(100);

    public boolean isOperator(char c) {
        return c=='+' || c=='-' || c=='*' || c=='/';
    }

    // MainActivity에서 EditText calcInput의 expression을 받음 -> 숫자와 연산자로 parsing
    public void setExpression(String expression) {
        this.expression = expression;

        // 부호 별로 숫자를 분리하여 String[] numbers에 저장
        numbers = expression.split("\\+|-|\\*|/");

        // 숫자들을 numberList에 저장
        for(String str : numbers) {
            numberList.add(str);
        }

        // 연산자들을 operatorList에 저장
        for(int i=0 ; i<expression.length() ; i++) {
            if(isOperator(expression.charAt(i))) {
                operatorList.add(expression.charAt(i));
            }
        }

        if(numberList.size()-operatorList.size() == 1) error = true;
    }

    public String getResult() {
        if(error) return "ERROR!";

        float tmpNumber1, tmpNumber2;

        for(int i=0 ; i<operatorList.size() ; i++) {
            // *인 경우
            if(operatorList.get(i)=='*') {
                // 부호의 앞의 숫자를 가져옴
                tmpNumber1 = Float.parseFloat(numberList.get(i));
                // 가져온 숫자는 삭제
                numberList.remove(i);

                // 부호의 뒤의 숫자를 가져옴
                tmpNumber2 = Float.parseFloat(numberList.get(i));
                // 가져온 숫자는 삭제
                numberList.remove(i);

                // 삭제한 자리에 곱셈 결과를 추가
                numberList.add(i,tmpNumber1*tmpNumber2+"");

                // * 부호 삭제
                operatorList.remove(i--);

                // /인 경우
            } else if(operatorList.get(i)=='/') {
                // 부호의 앞 뒤 숫자를 가져옴
                tmpNumber1 = Float.parseFloat(numberList.get(i));
                tmpNumber2 = Float.parseFloat(numberList.get(i+1));

                // 가져온 숫자는 삭제
                numberList.remove(i);
                numberList.remove(i);

                // 삭제한 자리에 나눗셈 결과를 추가
                numberList.add(i,tmpNumber1/tmpNumber2+"");

                // / 부호 삭제
                operatorList.remove(i--);
            }
        }

        // result에 처음 값을 대입
        float result = Float.parseFloat(numberList.get(0));
        for(int i=0 ; i<operatorList.size() ; i++) {
            // + 부호인 경우
            if(operatorList.get(i)=='+') {
                // 더하기
                result += Float.parseFloat(numberList.get(i+1));

                // - 부호인 경우
            } else if(operatorList.get(i)=='-') {
                // 빼기
                result -= Float.parseFloat(numberList.get(i+1));
            }
        }

        numberList.removeAllElements();
        operatorList.removeAllElements();

        return result + "";
    }
}
