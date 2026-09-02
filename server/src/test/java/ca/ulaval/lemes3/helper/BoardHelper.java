package ca.ulaval.lemes3.helper;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.*;

import java.util.*;

public class BoardHelper {

    public static Board createBasicBoard() {
        Card card1 = new Card(Suit.HEART, Rank.EIGHT);
        Card card2 = new Card(Suit.SPADE, Rank.FIVE);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(card1, card2)));
        Player player1 = new Player(new PlayerId(UUID.randomUUID()));
        Player player2 = new Player(new PlayerId(UUID.randomUUID()));
        Marble marble1 = new Marble(new MarbleId(UUID.randomUUID()));
        Marble marble2 = new Marble(new MarbleId(UUID.randomUUID()));

        ArrayList<Marble> marbles = new ArrayList<>(Arrays.asList(marble1, marble2));
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));

        MarbleId[] track = new MarbleId[1];
        Map<PlayerId, List<MarbleId>> homes = new HashMap<>();
        Map<PlayerId, List<MarbleId>> heavens = new HashMap<>();

        BoardLayout boardLayout = new BoardLayout(track, homes, heavens);

        return new Board(UUID.randomUUID(), deck, players, marbles, boardLayout);
    }

    public static Board createBoardWithNoMarbles() {
        Card card1 = new Card(Suit.HEART, Rank.EIGHT);
        Card card2 = new Card(Suit.SPADE, Rank.FIVE);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(card1, card2)));
        Player player1 = new Player(new PlayerId(UUID.randomUUID()));
        Player player2 = new Player(new PlayerId(UUID.randomUUID()));
        ArrayList<Marble> marbles = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));

        MarbleId[] track = new MarbleId[1];
        Map<PlayerId, List<MarbleId>> homes = new HashMap<>();
        Map<PlayerId, List<MarbleId>> heavens = new HashMap<>();

        BoardLayout boardLayout = new BoardLayout(track, homes, heavens);

        return new Board(UUID.randomUUID(), deck, players, marbles, boardLayout);
    }

    public static Board createBasicBoard(PlayerId playerID) {
        Card card1 = new Card(Suit.HEART, Rank.EIGHT);
        Card card2 = new Card(Suit.SPADE, Rank.FIVE);
        Deck deck = new Deck(new ArrayList<>(Arrays.asList(card1, card2)));
        Player player1 = new Player(playerID);
        Player player2 = new Player(new PlayerId(UUID.randomUUID()));
        Marble marble1 = new Marble(new MarbleId(UUID.randomUUID()));
        Marble marble2 = new Marble(new MarbleId(UUID.randomUUID()));

        ArrayList<Marble> marbles = new ArrayList<>(Arrays.asList(marble1, marble2));
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1, player2));

        MarbleId[] track = new MarbleId[1];
        Map<PlayerId, List<MarbleId>> homes = new HashMap<>();
        Map<PlayerId, List<MarbleId>> heavens = new HashMap<>();

        BoardLayout boardLayout = new BoardLayout(track, homes, heavens);

        return new Board(UUID.randomUUID(), deck, players, marbles, boardLayout);
    }
}
