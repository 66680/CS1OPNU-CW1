package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineHintTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void hintShowsShortestRouteToReachableItem() {
        GameResult result = engine.hint("brass_key");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("east"));
        assertTrue(result.getMessage().contains("workshop"));
    }

    @Test
    void hintFailsCleanlyWhenTargetIsBehindLockedDoor() {
        GameResult result = engine.hint("star_crystal");

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("No available route"));
    }

    @Test
    void hintCanFindTargetAfterProgressionUnlocksRoute() {
        engine.move(Direction.EAST);
        engine.take("brass_key");
        engine.solve("console", "ORION");
        engine.use("brass_key");

        GameResult result = engine.hint("star_crystal");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("east -> north"));
    }
}