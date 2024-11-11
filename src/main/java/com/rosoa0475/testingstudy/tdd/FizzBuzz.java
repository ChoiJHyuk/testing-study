package com.rosoa0475.testingstudy.tdd;

public class FizzBuzz {

    /*public static String compute(int i) {
        if(i%3 ==0){
            if(i%5 == 0){
                return "FizzBuzz";
            }
            return "Fizz";
        }else if(i%5 ==0){
            return "Buzz";
        }
        return Integer.toString(i);
    }*/

    //리팩토링 ver.
    public static String compute(int i) {
        StringBuilder result = new StringBuilder();

        if (i % 3 == 0) {
            result.append("Fizz");
        }
        if (i % 5 == 0) {
            result.append("Buzz");
        }
        if (result.isEmpty()) {
            result.append(i);
        }
        return result.toString();
    }
}
