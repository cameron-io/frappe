package io.cameron.enums;

public class Card {
    String number;
    Suit suit;

    public Card(String number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public String getCard() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.number);
        sb.append(" of ");
        switch (this.suit) {
            case CLUBS:
                sb.append("Clubs");
                break;    
            case SPADES:
                sb.append("Spades");
                break;
            case HEARTS:
                sb.append("Hearts");
                break;
            case DIAMONDS:
                sb.append("Diamonds");
        }
        return sb.toString();
    }
}
