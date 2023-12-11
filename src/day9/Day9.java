package day9;

import util.DayTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        int answer = 0;
        List<String> lines = this.readFile(in);

        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split("\s+"))
                    .mapToInt(Integer::parseInt)
                    .boxed().collect(Collectors.toList());

            if (!isFirstPart) Collections.reverse(numbers);

            while (!check(numbers)) {
                for (int i = 0; i < numbers.size() - 1; i++)
                    numbers.set(i, numbers.get(i + 1) - numbers.get(i));
                answer += numbers.remove(numbers.size() - 1);
            }

            answer += numbers.get(0);
        }

        return answer + "";
    }

    private boolean check(List<Integer> numbers) {
        int first = numbers.get(0);
        return numbers.stream().allMatch(elem -> elem == first);
    }

}