package day13;

import util.DayTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        List<List<String>> patterns = new ArrayList<>();
        while (in.hasNext()) patterns.add(readFileUntilEmpty(in));

        int columns = 0;
        int rows = 0;

        int differenceAllowed = isFirstPart ? 0 : 1;

        for (List<String> pattern : patterns) {

            int horizontal = checkHorizontal(pattern, differenceAllowed);
            int vertical = checkHorizontal(columnsToRows(pattern), differenceAllowed);

            rows += horizontal;
            columns += vertical;
        }

        return 100 * rows + columns + "";
    }

    private int checkHorizontal(List<String> pattern, int differenceAllowed) {
        for (int row = 1; row < pattern.size(); row++) {
            int index = 1;
            int matched = 0;
            int difference = 0;

            while (row - index >= 0 && row + index - 1 < pattern.size()) {
                String row1 = pattern.get(row - index);
                String row2 = pattern.get(row + index - 1);

                for (int j = 0; j < row1.length(); j++)
                    if (row1.charAt(j) != row2.charAt(j)) difference++;

                if (difference > differenceAllowed) break;
                else matched++;

                index++;
            }

            if (
                matched > 0 &&
                difference == differenceAllowed &&
                (matched == row || matched == pattern.size() - row)
            ) return row;
        }

        return 0;
    }

    private List<String> columnsToRows(List<String> pattern) {
        List<String> columns = new ArrayList<>();
        StringBuilder column = new StringBuilder();

        for (int i = 0; i < pattern.get(0).length(); i++) {
            for (String s : pattern) column.append(s.charAt(i));
            columns.add(column.toString());
            column = new StringBuilder();
        }

        return columns;
    }

    private List<String> readFileUntilEmpty(Scanner in) {
        List<String> lines = new ArrayList<>();
        while (in.hasNext()) {
            String newLine = in.nextLine();
            if (newLine.isEmpty()) {
                return lines;
            } else {
                lines.add(newLine);
            }
        }
        return lines;
    }

}