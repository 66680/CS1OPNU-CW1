package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineProgressionTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void lockedDoorRequiresKeyAndPuzzleBeforeProgression() {
        engine.move(Direction.EAST);

        assertFalse(engine.move(Direction.EAST).isSuccess());

        engine.take("brass_key");
        assertTrue(engine.solve("console", "ORION").isSuccess());
        assertTrue(engine.use("brass_key").isSuccess());
        assertTrue(engine.move(Direction.EAST).isSuccess());
        assertEquals("control_room", GameState.getInstance().getActivePlayer().getCurrentRoomId());
    }

    @Test
    void takingFinalTreasureWinsGame() {
        engine.move(Direction.EAST);
        engine.take("brass_key");
        engine.solve("console", "ORION");
        engine.use("brass_key");
        engine.move(Direction.EAST);
        engine.move(Direction.NORTH);

        GameResult result = engine.take("star_crystal");

        assertTrue(result.isSuccess());
        assertTrue(GameState.getInstance().isGameWon());
    }
}