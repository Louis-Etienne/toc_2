package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Card;
import ca.ulaval.lemes3.domain.Deck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeckDto {
    private List<CardDto> cards;

    public Deck toDomain() {
        List<Card> cardsDomain = cards.stream().map(CardDto::toDomain).toList();
        return new Deck(cardsDomain);
    }

    public DeckDto(Deck deck) {
        this(deck.getCards().stream().map(CardDto::new).toList());
    }

}
