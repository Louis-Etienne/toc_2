package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Card;
import ca.ulaval.lemes3.domain.Deck;

import java.util.ArrayList;
import java.util.List;

public record DeckDto(List<CardDto> cards) {

    public Deck toDomain() {
        List<Card> cardsDomain = cards.stream().map(CardDto::toDomain).toList();
        return new Deck(cardsDomain);
    }


}
