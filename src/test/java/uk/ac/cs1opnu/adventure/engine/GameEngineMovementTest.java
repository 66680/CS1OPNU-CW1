package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineMovementTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void activePlayerCanMoveThroughValidExit() {
        GameResult result = engine.move(Direction.NORTH);

        assertTrue(result.isSuccess());
        assertEquals("library", GameState.getInstance().getActivePlayer().getCurrentRoomId());
    }

    @Test
    void invalidMovementReturnsFailure() {
        GameResult result = engine.move(Direction.SOUTH);

        assertFalse(result.isSuccess());
        assertEquals("entrance", GameState.getInstance().getActivePlayer().getCurrentRoomId());
    }

    @Test
    void canSwitchActivePlayer() {
        GameResult result = engine.switchPlayer("Bob");

        assertTrue(result.isSuccess());
        assertEquals("Bob", GameState.getInstance().getActivePlayer().getName());
    }
}