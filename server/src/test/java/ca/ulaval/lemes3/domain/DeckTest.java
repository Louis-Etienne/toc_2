package ca.ulaval.lemes3.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void givenNewDeck_whenCreated_thenContains52Cards() {
        Deck deck = new Deck();

        assertEquals(52, deck.size());
    }

    @Test
    void givenDeck_whenDrawingCard_thenReturnsCard() {
        Deck deck = new Deck();

        Card card = deck.drawTopCard();

        assertNotNull(card);
    }

    @Test
    void givenDeck_whenDrawingCard_thenDeckSizeDecrease() {
        Deck deck = new Deck();

        deck.drawTopCard();

        assertEquals(51, deck.size());
    }

    @Test
    void givenDeck_whenReseting_thenSizeIs52() {
        Deck deck = new Deck();

        Card card = deck.drawTopCard();
        deck.resetDeck();

        assertEquals(52, deck.size());
    }

    @Test
    void givenEmptyDeck_whenDrawingCard_thenThrowException() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.drawTopCard();
        }

        assertThrows(IllegalStateException.class, deck::drawTopCard);
    }

}