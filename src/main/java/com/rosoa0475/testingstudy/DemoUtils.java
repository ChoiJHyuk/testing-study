package com.rosoa0475.testingstudy;

import java.util.List;

public class DemoUtils {

    private String academy = "choi academy";
    private String academyDuplicate = academy;

    private String[] firstThreeLettersOfAlphabet = {"A", "B", "C"};

    private List<String> name = List.of("choi", "jae", "hyuk");

    public List<String> getName() {
        return name;
    }

    public String[] getFirstThreeLettersOfAlphabet() {
        return firstThreeLettersOfAlphabet;
    }

    public String getAcademy() {
        return academy;
    }

    public String getAcademyDuplicate() {
        return academyDuplicate;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public Object checkNull(Object obj) {
        if (obj != null) {
            return obj;
        }
        return null;
    }

    public Boolean isGreater(int n1, int n2) {
        if (n1 > n2) {
            return true;
        }
        return false;
    }

    public String throwExceptionIfLessThanZero(int n) throws Exception {
        if (n < 0) {
            throw new Exception("less than zero");
        }
        return "greater or equal zero";
    }

    public void checkTimeout() throws InterruptedException {
        System.out.println("I am going to sleep");
        Thread.sleep(2000);
        System.out.println("Sleeping over");
    }
}
