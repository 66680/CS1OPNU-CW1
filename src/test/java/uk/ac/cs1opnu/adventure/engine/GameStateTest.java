package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    @Test
    void gameStateIsSingleton() {
        GameState first = GameState.getInstance();
        GameState second = GameState.getInstance();

        assertSame(first, second);
    }

    @Test
    void resetAndStartNewGamePrepareSharedState() {
        GameState state = GameState.getInstance();

        state.resetForNewGame();
        state.startNewGame(Arrays.asList("Alice", "Bob"));

        assertEquals("Alice", state.getActivePlayer().getName());
        assertEquals(2, state.getPlayers().size());
        assertFalse(state.isGameWon());
        assertTrue(state.getRooms().containsKey("entrance"));
    }
}