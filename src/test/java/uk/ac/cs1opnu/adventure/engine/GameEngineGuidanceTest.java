package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineGuidanceTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void invalidMovementMentionsAvailableDirections() {
        GameResult result = engine.move(Direction.SOUTH);

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("Available exits"));
        assertTrue(result.getMessage().contains("north"));
        assertTrue(result.getMessage().contains("east"));
    }

    @Test
    void takingMissingItemMentionsVisibleItems() {
        engine.move(Direction.NORTH);

        GameResult result = engine.take("brass_key");

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("Available items"));
        assertTrue(result.getMessage().contains("note"));
    }

    @Test
    void wrongPuzzleAnswerSuggestsInspectingPuzzle() {
        engine.move(Direction.EAST);

        GameResult result = engine.solve("console", "LYRA");

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("inspect console"));
    }
}