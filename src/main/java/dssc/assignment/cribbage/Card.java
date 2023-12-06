package dssc.assignment.cribbage;

public class Card {

    private String rank;
    private Suite suite;

    public Card(String rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public int getValue() {
        // Implement your logic to get the value of the card
        // In Cribbage, Ace counts as 1, and face cards (Jack, Queen, King) count as 10
        // Number cards have their face value
        if ("A".equals(rank)) {
            return 1;
        } else if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }

    public int getRankValue() {
        // Implement your logic to get the numeric value of the rank
        // In Cribbage, Ace is low, so it has the lowest rank value
        if ("A".equals(rank)) {
            return 1;
        } else if ("J".equals(rank)) {
            return 11;
        } else if ("Q".equals(rank)) {
            return 12;
        } else if ("K".equals(rank)) {
            return 13;
        } else {
            return Integer.parseInt(rank);
        }
    }

    public Suite getSuite() {
        return suite;
    }
}
