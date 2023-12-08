package day8;

import util.DayTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        int steps = 0;
        String currNode = "AAA";

        char[] instructions = lines.get(0).toCharArray();
        Map<String, String[]> ways = getNodeMap(lines.subList(2, lines.size()));

        int i = 0;
        while (!currNode.equals("ZZZ")) {
            char instruction = instructions[i];
            steps++;
            switch (instruction) {
                case 'L' -> currNode = ways.get(currNode)[0];
                case 'R' -> currNode = ways.get(currNode)[1];
            }
            i = (i + 1) % instructions.length;
        }

        return steps + "";
    }

    private String solveSecondPart(List<String> lines) {
        long steps = 0;

        char[] instructions = lines.get(0).toCharArray();
        Map<String, String[]> ways = getNodeMap(lines.subList(2, lines.size()));

        List<String> currNodes = ways.keySet().stream().filter(node -> node.endsWith("A")).toList();
        List<Long> stepsCount = new ArrayList<>();

        int i = 0;
        for (String currNode : currNodes) {
            while (!currNode.endsWith("Z")) {
                char instruction = instructions[i];
                steps++;
                switch (instruction) {
                    case 'L' -> currNode = ways.get(currNode)[0];
                    case 'R' -> currNode = ways.get(currNode)[1];
                }
                i = (i + 1) % instructions.length;
            }

            stepsCount.add(steps);
            steps = 0;
        }

        steps = stepsCount.get(0);
        for (int j = 1; j < stepsCount.size(); j++) {
            steps = lcm(steps, stepsCount.get(j));
        }

        return steps + "";
    }

    private Map<String, String[]> getNodeMap(List<String> lines) {
        Map<String, String[]> nodeMap = new TreeMap<>();
        for (String line : lines) {
            Matcher matcher = Pattern.compile("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)").matcher(line);
            matcher.find();
            String[] leftRightNodes = { matcher.group(2), matcher.group(3) };
            nodeMap.put(matcher.group(1), leftRightNodes);
        }
        return nodeMap;
    }

    private long lcm(long x, long y) {
        long max = Math.max(x, y);
        long min = Math.min(x, y);
        long lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }

}