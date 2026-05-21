package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineInventoryTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void playerCanTakeItemFromCurrentRoom() {
        engine.move(Direction.EAST);
        GameResult result = engine.take("brass_key");

        assertTrue(result.isSuccess());
        assertTrue(GameState.getInstance().getActivePlayer().hasItem("brass_key"));
        assertFalse(GameState.getInstance().getCurrentRoom().hasItem("brass_key"));
    }

    @Test
    void takingMissingItemFailsCleanly() {
        GameResult result = engine.take("brass_key");

        assertFalse(result.isSuccess());
        assertFalse(GameState.getInstance().getActivePlayer().hasItem("brass_key"));
    }
}