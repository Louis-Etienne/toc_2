package ca.ulaval.lemes3.helper;

import ca.ulaval.lemes3.domain.*;

import java.util.*;

public class BoardHelper {

    public static Board createBasicBoard() {
        Card card1 = new Card(Suit.HEART, Rank.EIGHT);
        Card card2 = new Card(Suit.SPADE, Rank.FIVE);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(card1, card2)));
        Player player1 = new Player(UUID.randomUUID());
        Player player2 = new Player(UUID.randomUUID());
        Marble marble1 = new Marble(UUID.randomUUID());
        Marble marble2 = new Marble(UUID.randomUUID());

        ArrayList<Marble> marbles = new ArrayList<>(Arrays.asList(marble1, marble2));
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));

        return new Board(UUID.randomUUID(), deck, players, marbles);
    }
}
