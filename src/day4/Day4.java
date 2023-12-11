package day4;

import util.DayTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day4 extends DayTemplate {

    Map<Integer, Integer> cardToCount = new HashMap<>();

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

        for (String line : lines) {
            int res = 0;
            String numbers = line.split(":")[1].trim();
            int[] numbersWin = Arrays.stream(numbers.split("\\|")[0].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();
            int[] numbersHave = Arrays.stream(numbers.split("\\|")[1].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();

            List<Integer> win = Arrays.stream(numbersWin).boxed().toList();
            List<Integer> have = Arrays.stream(numbersHave).boxed().toList();

            for (Integer num : have) {
                if (win.contains(num)) {
                    res = (res == 0) ? 1 : res * 2;
                }
            }

            sum += res;
        }

        return sum + "";
    }

    private String solveSecondPart(List<String> lines) {
        for (int i = 1; i <= lines.size(); i++)
            cardToCount.put(i, 1);

        for (String line : lines) {

            int card = Integer.parseInt(line.split(":")[0].split("\s+")[1]);
            int step = 1;

            String numbers = line.split(":")[1].trim();
            int[] numbersWin = Arrays.stream(numbers.split("\\|")[0].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();
            int[] numbersHave = Arrays.stream(numbers.split("\\|")[1].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();

            List<Integer> win = Arrays.stream(numbersWin).boxed().toList();
            List<Integer> have = Arrays.stream(numbersHave).boxed().toList();

            for (Integer num : have) {
                if (win.contains(num)) {
                    cardToCount.put(card + step, cardToCount.get(card + step) + cardToCount.get(card));
                    step++;
                }
            }
        }

        int sum = 0;

        for (int count : cardToCount.values()) {
            sum += count;
        }

        return sum + "";
    }

}