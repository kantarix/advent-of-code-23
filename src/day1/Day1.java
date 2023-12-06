package day1;

import util.DayTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 extends DayTemplate {

    HashMap<String, Integer> map = new HashMap<>();

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);
        fillMap();

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        Pattern pFirst = Pattern.compile("([1-9])");
        Pattern pLast = Pattern.compile(".*([1-9])");

        return getSum(lines, pFirst, pLast);
    }

    private String solveSecondPart(List<String> lines) {
        Pattern pFirst = Pattern.compile("([1-9]|one|two|three|four|five|six|seven|eight|nine)");
        Pattern pLast = Pattern.compile(".*([1-9]|one|two|three|four|five|six|seven|eight|nine)");

        return getSum(lines, pFirst, pLast);
    }

    private String getSum(List<String> lines, Pattern pFirst, Pattern pLast) {
        int sum = 0;

        for (String line : lines) {
            Matcher mFirst = pFirst.matcher(line);
            Matcher mLast = pLast.matcher(line);
            String first = mFirst.find() ? mFirst.group(1) : "";
            String last = mLast.find() ? mLast.group(1) : "";
            sum += getNumber(first, last);
        }

        return sum + "";
    }

    private int getNumber(String first, String last) {
        int firstDigit = 0;
        int lastDigit = 0;

        firstDigit = map.get(first);
        lastDigit = map.get(last);

        return firstDigit * 10 + lastDigit;
    }

    private void fillMap() {
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
    }

}
