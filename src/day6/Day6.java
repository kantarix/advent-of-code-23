package day6;

import util.DayTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day6 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        long answer = 1L;
        List<String> lines = this.readFile(in);

        int[] time = Arrays.stream(lines.get(0).split(":")[1].trim().split("\s+")).mapToInt(Integer::parseInt).toArray();
        long[] distance = Arrays.stream(lines.get(1).split(":")[1].trim().split("\s+")).mapToLong(Long::parseLong).toArray();

        if (isFirstPart) {
            for (int i = 0; i < time.length; i++) {
                int currTime = time[i];
                long recordDistance = distance[i];
                answer *= getWaysCount(currTime, recordDistance);
            }
        } else {
            StringBuilder sTime = new StringBuilder();
            StringBuilder sDistance = new StringBuilder();

            for (int i : time) sTime.append(i);
            for (long i : distance) sDistance.append(i);

            int currTime = Integer.parseInt(sTime.toString());
            long recordDistance = Long.parseLong(sDistance.toString());

            answer *= getWaysCount(currTime, recordDistance);
        }

        return answer + "";
    }

    private int getWaysCount(int time, long recordDistance) {
        int minHoldTime = 0;
        int maxHoldTime = 0;

        int holdTime = 0;
        long currDistance = 0;
        while (currDistance <= recordDistance) {
            holdTime++;
            currDistance = (long) (time - holdTime) * holdTime;
        }
        minHoldTime = holdTime;

        holdTime = time + 1;
        currDistance = 0;
        while (currDistance <= recordDistance) {
            holdTime--;
            currDistance = (long) (time - holdTime) * holdTime;
        }
        maxHoldTime = holdTime;

        return maxHoldTime - minHoldTime + 1;
    }

}