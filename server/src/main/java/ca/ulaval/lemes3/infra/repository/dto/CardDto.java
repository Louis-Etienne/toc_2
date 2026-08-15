package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Card;
import ca.ulaval.lemes3.domain.Rank;
import ca.ulaval.lemes3.domain.Suit;

public record CardDto(Suit suit, Rank rank) {

    public Card toDomain() {
        return new Card(suit, rank);
    }


}
