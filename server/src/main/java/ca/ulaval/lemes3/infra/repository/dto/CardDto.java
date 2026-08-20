package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Card;
import ca.ulaval.lemes3.domain.Rank;
import ca.ulaval.lemes3.domain.Suit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardDto {
    private Suit suit;
    private Rank rank;

    public Card toDomain() {
        return new Card(suit, rank);
    }

    public CardDto(Card card) {
        this(card.suit(), card.rank());
    }

}
