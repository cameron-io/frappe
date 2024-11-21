package io.cameron.enums;

public class Card {
    String number;
    Suit suit;

    public Card(String number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public String getCard() {
        StringBuilder sb = new StringBuilder().append(number).append(" of ");
        switch (suit) {
            case CLUBS -> sb.append("Clubs");
            case SPADES -> sb.append("Spades");
            case HEARTS -> sb.append("Hearts");
            case DIAMONDS -> sb.append("Diamonds");
        }
        return sb.toString();
    }
}
