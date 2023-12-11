package day7;

import util.DayTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day7 extends DayTemplate {

    static final String ORDER = "23456789TJQKA";

    static List<String> fiveOfAKindList = new ArrayList<>();
    static List<String> fourOfAKindList = new ArrayList<>();
    static List<String> fullHouseList = new ArrayList<>();
    static List<String> threeOfAKindList = new ArrayList<>();
    static List<String> twoPairList = new ArrayList<>();
    static List<String> onePairList = new ArrayList<>();
    static List<String> highCardList = new ArrayList<>();

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        long sum = 0L;
        Map<String, Long> handToBid = new HashMap<>();

        for (String line : lines) {
            String hand = line.split("\s")[0];
            Long bid = Long.parseLong(line.split("\s")[1]);
            handToBid.put(hand, bid);
        }

        for (String hand : handToBid.keySet())
            calculateHandStrength(hand);

        int rank = lines.size();

        fiveOfAKindList.sort(comparator);
        fourOfAKindList.sort(comparator);
        fullHouseList.sort(comparator);
        threeOfAKindList.sort(comparator);
        twoPairList.sort(comparator);
        onePairList.sort(comparator);
        highCardList.sort(comparator);

        for (String hand: fiveOfAKindList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: fourOfAKindList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: fullHouseList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: threeOfAKindList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: twoPairList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: onePairList)
            sum += handToBid.get(hand) * rank--;
        for (String hand: highCardList)
            sum += handToBid.get(hand) * rank--;

        return sum + "";
    }

    private String solveSecondPart(List<String> lines) {
        return "";
    }

    private static void calculateHandStrength(String hand) {
        Map<Character, Integer> cardToCount = new HashMap<>();
        int fiveCount = 0;
        int fourCount = 0;
        int threeCount = 0;
        int twoCount = 0;

        for (char card : hand.toCharArray())
            cardToCount.put(card, cardToCount.getOrDefault(card, 0) + 1);
        for (Map.Entry<Character, Integer> entry : cardToCount.entrySet())
            switch (entry.getValue()) {
                case 5 -> fiveCount++;
                case 4 -> fourCount++;
                case 3 -> threeCount++;
                case 2 -> twoCount++;
                default -> { }
            }

        boolean res = (fiveCount > 0)
                ? fiveOfAKindList.add(hand)
                : (fourCount > 0)
                ? fourOfAKindList.add(hand)
                : (threeCount > 0 && twoCount > 0)
                ? fullHouseList.add(hand)
                : (threeCount > 0)
                ? threeOfAKindList.add(hand)
                : (twoCount > 1)
                ? twoPairList.add(hand)
                : (twoCount > 0)
                ? onePairList.add(hand)
                : highCardList.add(hand);
    }

    static Comparator<String> comparator = (o1, o2) -> {
        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < o1.length() && pos1 == pos2; i++) {
            pos1 = ORDER.indexOf(o1.charAt(i));
            pos2 = ORDER.indexOf(o2.charAt(i));
        }
        return pos2 - pos1;
    };

}