package ca.ulaval.lemes3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawTopCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards in Deck");
        }
        Card card = cards.get(0);
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

}
