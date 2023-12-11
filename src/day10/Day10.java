package day10;

import util.DayTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day10 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        int stepsCount = 1;
        Coordinate start = getStart(lines);

        List<Coordinate> ways = getWays(lines, start);

        Coordinate prevFirst = start;
        Coordinate currFirst = ways.get(0);
        Coordinate prevSecond = start;
        Coordinate currSecond = ways.get(1);

        while (!currFirst.equals(currSecond)) {
            stepsCount += 1;

            char symbol = getSymbol(lines, currFirst);
            Coordinate nextFirst = calculateNextStep(prevFirst, currFirst, symbol);
            prevFirst = currFirst;
            currFirst = nextFirst;

            symbol = getSymbol(lines, currSecond);
            Coordinate nextSecond = calculateNextStep(prevSecond, currSecond, symbol);
            prevSecond = currSecond;
            currSecond = nextSecond;
        }

        return stepsCount + "";
    }

    private String solveSecondPart(List<String> lines) {
        int sum = 0;


        return sum + "";
    }

    private Coordinate getStart(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'S') return new Coordinate(i, j);
            }
        }
        return null;
    }

    private List<Coordinate> getWays(List<String> lines, Coordinate start) {
        List<Coordinate> validWays = new ArrayList<>();
        List<Coordinate> ways = new ArrayList<>();

        if (start.y != 0)                                   ways.add(start.left());
        if (start.y != lines.get(start.x).length() - 1)     ways.add(start.right());
        if (start.x != 0)                                   ways.add(start.up());
        if (start.x != lines.size() - 1)                    ways.add(start.down());

        for (Coordinate way : ways) {
            Character symbol = lines.get(way.x).charAt(way.y);
            if (isValidStartWay(start, way, symbol)) validWays.add(way);
        }

        return validWays;
    }

    private boolean isValidStartWay(Coordinate start, Coordinate coordinate, Character symbol) {
        return (!symbol.equals('.')) && (coordinate.getFirstWay(symbol).equals(start) || coordinate.getSecondWay(symbol).equals(start));
    }

    private Coordinate calculateNextStep(Coordinate prev, Coordinate curr, char symbol) {
        Coordinate nextFirst = curr.getFirstWay(symbol);
        Coordinate nextSecond = curr.getSecondWay(symbol);

        if (!nextFirst.equals(prev))
            return nextFirst;
        else
            return nextSecond;
    }

    private char getSymbol(List<String> lines, Coordinate coordinate) {
        return lines.get(coordinate.x).charAt(coordinate.y);
    }

}