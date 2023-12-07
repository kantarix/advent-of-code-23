package day3;

import util.DayTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        int sum = 0;
        Pattern number = Pattern.compile("\\d+");

        for (int i = 0; i < lines.size(); i++) {
            int num = 0;
            int start = 0;
            int end = 0;

            Matcher m = number.matcher(lines.get(i));
            while (m.find()) {
                num = Integer.parseInt(m.group());
                start = m.start();
                end = m.end();

                if (isNumberAdjacentToSymbol(lines, i, start, end - 1))
                    sum += num;
            }
        }

        return sum + "";
    }

    private String solveSecondPart(List<String> lines) {
        int sum = 0;
        Pattern number = Pattern.compile("\\d+");

        HashMap<Integer, Integer> starToNumber = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            int num = 0;
            int start = 0;
            int end = 0;

            Matcher m = number.matcher(lines.get(i));
            while (m.find()) {
                num = Integer.parseInt(m.group());
                start = m.start();
                end = m.end();

                int index = isNumberAdjacentToStar(lines, i, start, end - 1);
                if (index > -1) {
                    if (starToNumber.containsKey(index)) {
                        sum += starToNumber.get(index) * num;
                        starToNumber.remove(index);
                    }
                    else
                        starToNumber.put(index, num);
                }
            }
        }

        return sum + "";
    }

    private boolean isNumberAdjacentToSymbol(List<String> lines, int i, int start, int end) {
        Pattern symbols = Pattern.compile("[^\\d\\n.]");

        String prevLine = (i - 1 >= 0) ? lines.get(i - 1) : "";
        String currLine = lines.get(i);
        String nextLine = (i + 1 < lines.size()) ? lines.get(i + 1) : "";

        int left = (start - 1 >= 0) ? start - 1 : start;
        int right = (end + 1 < currLine.length()) ? end + 1 : end;

        prevLine = !prevLine.isEmpty() ? prevLine.substring(left, right + 1) : "";
        currLine = !currLine.isEmpty() ? currLine.substring(left, right + 1) : "";
        nextLine = !nextLine.isEmpty() ? nextLine.substring(left, right + 1) : "";

        Matcher m1 = symbols.matcher(prevLine);
        Matcher m2 = symbols.matcher(currLine);
        Matcher m3 = symbols.matcher(nextLine);

        return (m1.find() || m2.find() || m3.find());
    }


    private int isNumberAdjacentToStar(List<String> lines, int i, int start, int end) {
        Pattern star = Pattern.compile("\\*");

        String prevLine = (i - 1 >= 0) ? lines.get(i - 1) : "";
        String currLine = lines.get(i);
        String nextLine = (i + 1 < lines.size()) ? lines.get(i + 1) : "";

        int left = (start - 1 >= 0) ? start - 1 : start;
        int right = (end + 1 < currLine.length()) ? end + 1 : end;

        prevLine = !prevLine.isEmpty() ? prevLine.substring(left, right + 1) : "";
        currLine = !currLine.isEmpty() ? currLine.substring(left, right + 1) : "";
        nextLine = !nextLine.isEmpty() ? nextLine.substring(left, right + 1) : "";

        Matcher m1 = star.matcher(prevLine);
        Matcher m2 = star.matcher(currLine);
        Matcher m3 = star.matcher(nextLine);

        if (m1.find()) return (i - 1) * 1000 + (left + m1.start());
        if (m2.find()) return i * 1000 + (left + m2.start());
        if (m3.find()) return (i + 1) * 1000 + (left + m3.start());

        return -1;
    }

}