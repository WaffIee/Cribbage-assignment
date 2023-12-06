package dssc.assignment.cribbage;

import java.util.*;

public class Scoring {



    public static void main(String[] args) {
        // Example hands
        String hand1 = "5H5D5SJC5C";
        String hand2 = "0DJHQSAC9D";

        // Calculate scores
        int score1 = calculateScore(hand1);
        int score2 = calculateScore(hand2);

        // Display results
        System.out.println("Score for hand1: " + score1);
        System.out.println("Score for hand2: " + score2);

//       output
//        > Task :Scoring.main()
//        Score for hand1: 20
//        Score for hand2: 0
    }


    public static int calculateScore(String hand) {
        int score = 0;

        // Parse hand cards and starter card
        List<Card> cards = parseHand(hand);
        score += calculateFifteenTwos(cards);
        score += calculateRuns(cards);
        score += calculatePairs(cards);
        score += calculateFlush(cards);

        return score;
    }

    private static List<Card> parseHand(String hand) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < hand.length(); i += 2) {
            String cardText = hand.substring(i, i + 2);
            cards.add(CardParser.parseCard(cardText));
        }
        return cards;
    }

    private static int calculateFifteenTwos(List<Card> cards) {
        int score = 0;

        // Generate all possible combinations of cards
        List<List<Card>> combinations = generateCombinations(cards, 2);
        for (List<Card> combination : combinations) {
            int sum = combination.stream().mapToInt(Card::getValue).sum();
            if (sum == 15) {
                score += 2;
            }
        }

        return score;
    }

    private static int calculateRuns(List<Card> cards) {
        int score = 0;

        // Sort cards by rank
        cards.sort(Comparator.comparingInt(Card::getRankValue));

        // Check for runs
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i + 1).getRankValue() == cards.get(i).getRankValue() + 1 &&
                    cards.get(i + 2).getRankValue() == cards.get(i).getRankValue() + 2) {
                score += 3; // Run of 3
            }
            if (i < cards.size() - 3 &&
                    cards.get(i + 3).getRankValue() == cards.get(i).getRankValue() + 3) {
                score += 4; // Run of 4
            }
            if (i < cards.size() - 4 &&
                    cards.get(i + 4).getRankValue() == cards.get(i).getRankValue() + 4) {
                score += 5; // Run of 5
            }
        }

        return score;
    }

    private static int calculatePairs(List<Card> cards) {
        int score = 0;

        // Count occurrences of each rank
        Map<Integer, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            int rankValue = card.getRankValue();
            rankCounts.put(rankValue, rankCounts.getOrDefault(rankValue, 0) + 1);
        }

        for (int count : rankCounts.values()) {
            if (count == 2) {
                score += 2; // Pair
            } else if (count == 3) {
                score += 6; // Pair royal
            } else if (count == 4) {
                score += 12; // Double pair royal
            }
        }

        return score;
    }

    private static int calculateFlush(List<Card> cards) {
        int score = 0;

        // Count occurrences of each suit
        Map<Suite, Integer> suitCounts = new HashMap<>();
        for (Card card : cards) {
            Suite suit = card.getSuite();
            suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
        }
        for (Map.Entry<Suite, Integer> entry : suitCounts.entrySet()) {
            if (entry.getValue() == 4) {
                score += 4; // Flush
                if (cards.get(cards.size() - 1).getSuite() == entry.getKey()) {
                    score += 1; // Jack of the same suit
                }
            }
        }

        return score;
    }

    private static List<List<Card>> generateCombinations(List<Card> cards, int r) {
        List<List<Card>> combinations = new ArrayList<>();
        generateCombinationsHelper(cards, 0, new ArrayList<>(), r, combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(List<Card> cards, int start, List<Card> current,
                                                   int r, List<List<Card>> combinations) {
        if (r == 0) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < cards.size(); i++) {
            current.add(cards.get(i));
            generateCombinationsHelper(cards, i + 1, current, r - 1, combinations);
            current.remove(current.size() - 1);
        }
    }
}






