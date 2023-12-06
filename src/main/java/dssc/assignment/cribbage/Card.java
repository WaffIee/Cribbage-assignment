package dssc.assignment.cribbage;

public class Card {

    private String rank;
    private Suite suite;

    public Card(String rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        // In Cribbage, Ace counts as 1, and face cards (Jack, Queen, King) count as 10
        if ("A".equals(rank)) {
            return 1;
        } else if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }

    public int getRankValue() {
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
