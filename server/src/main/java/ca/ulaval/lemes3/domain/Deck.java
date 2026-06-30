package ca.ulaval.lemes3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        resetDeck();
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public void resetDeck() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
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

}
