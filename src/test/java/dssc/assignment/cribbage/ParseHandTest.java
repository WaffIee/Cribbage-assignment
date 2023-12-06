package dssc.assignment.cribbage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParseHandTest {

    @Test
    void parseOneCard() {
        Card card = CardParser.parseCard("5H");
        assertAll(
                () -> assertEquals("5", card.getRank()),
                () -> assertEquals(Suite.HEART, card.getSuite())
        );
    }
}
