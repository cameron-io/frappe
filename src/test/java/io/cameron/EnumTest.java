package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.cameron.enums.Card;
import io.cameron.enums.Suit;

public class EnumTest {
    @Test
    public void enumTest() {
        Card card1 = new Card("Queen", Suit.HEARTS);
        Card card2 = new Card("3", Suit.DIAMONDS);
        assertEquals("Queen of Hearts", card1.getCard());
        assertEquals("3 of Diamonds", card2.getCard());
    }
}
