package com.rros.logmeinbasicdeck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    // XXX 2020-12-15 rosr with this seed, deck1, deck2, deck3, deck4, deck5 are shuffled so that they do not end up in the same order
    private static final long SEED = 123L;
    private static final Random RANDOM = new Random(SEED);

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void shuffle_nominal_flow() {
        Deck deck1 = addCard();
        Deck deck2 = addCard();
        Deck deck3 = addCard();
        Deck deck4 = addCard();
        Deck deck5 = addCard();
        game.shuffleGameDeck(RANDOM);
        assertThat(game.getGameDeck()).map(Card::deck)
                .containsExactlyInAnyOrder(deck1, deck2, deck3, deck4, deck5)
                .as("The order is expected to have changed")
                .doesNotContainSequence(deck1, deck2, deck3, deck4, deck5);
    }

    /**
     * Add a card (from a new single-card deck).
     *
     * <p>We use this to facilitate checking that the result is shuffled (we only check that the provenance deck is shuffled).
     */
    private Deck addCard() {
        Deck deck = new Deck(EnumSet.of(StandardSuit.HEARTS), EnumSet.of(StandardCardValue.SEVEN));
        game.addDeck(deck);
        return deck;
    }
}