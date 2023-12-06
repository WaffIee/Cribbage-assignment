package dssc.assignment.cribbage;

public class CardParser {
    public static Card parseCard(String cardAsText) {
        if (cardAsText.length() != 2) {
            throw new IllegalArgumentException("Invalid card format: " + cardAsText);
        }

        String rank = cardAsText.substring(0, 1);
        String suiteStr = cardAsText.substring(1, 2);

        Suite suite;
        switch (suiteStr) {
            case "H":
                suite = Suite.HEART;
                break;
            case "C":
                suite = Suite.CLUB;
                break;
            case "D":
                suite = Suite.DIAMOND;
                break;
            case "S":
                suite = Suite.SPADE;
                break;
            default:
                throw new IllegalArgumentException("Invalid suite: " + suiteStr);
        }

        return new Card(rank, suite);
    }
}
