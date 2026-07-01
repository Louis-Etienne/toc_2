package ca.ulaval.lemes3.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void givenDeck_whenDrawingCard_thenReturnsCard() {
        Deck deck = createDeck();

        Card card = deck.drawTopCard();

        assertNotNull(card);
    }

    @Test
    void givenDeck_whenDrawingCard_thenDeckSizeDecrease() {
        Deck deck = createDeck();

        deck.drawTopCard();

        assertEquals(0, deck.size());
    }

    @Test
    void givenEmptyDeck_whenDrawingCard_thenThrowException() {
        Deck deck = new Deck(new ArrayList<>());

        assertThrows(IllegalStateException.class, deck::drawTopCard);
    }

    private Deck createDeck() {
        Card card = new Card(Suit.HEART, Rank.EIGHT);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        return new Deck(cards);
    }

}