package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class DayTemplate {
    public abstract String solve(boolean isFirstPart, Scanner in) throws Exception;

    public List<String> readFile(Scanner in) {
        List<String> lines = new ArrayList();
        while (in.hasNext()) {
            lines.add(in.nextLine());
        }
        in.close();
        return lines;
    }

}
