package day2;

import util.DayTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day2 extends DayTemplate {

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
        int sum = 0;

        for (String line : lines) {
            int gameNumber = Integer.parseInt(line.split(":")[0].split("\s")[1]);
            String[] tries = line.split(":")[1].split(";");
            boolean isPossible = true;

            for (String trie : tries) {
                String[] chunks = trie.trim().split(",");

                for (String chunk : chunks) {
                    String color = chunk.trim().split(" ")[1];
                    int number = Integer.parseInt(chunk.trim().split(" ")[0]);

                    if (number > map.get(color)) {
                        isPossible = false;
                        break;
                    }
                }

                if (!isPossible) break;
            }

            if (isPossible) sum += gameNumber;
        }

        return sum + "";
    }

    private String solveSecondPart(List<String> lines) {
        int sum = 0;

        for (String line : lines) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            String[] tries = line.split(":")[1].split(";");

            for (String trie : tries) {
                String[] chunks = trie.trim().split(",");
                for (String chunk : chunks) {
                    String color = chunk.trim().split(" ")[1];
                    int number = Integer.parseInt(chunk.trim().split(" ")[0]);

                    if (color.equals("red") && number > maxRed)
                        maxRed = number;
                    if (color.equals("green") && number > maxGreen)
                        maxGreen = number;
                    if (color.equals("blue") && number > maxBlue)
                        maxBlue = number;
                }
            }

            sum += maxRed * maxGreen * maxBlue;
        }

        return sum + "";
    }

    private void fillMap() {
        map.put("red", 12);
        map.put("green", 13);
        map.put("blue", 14);
    }

}