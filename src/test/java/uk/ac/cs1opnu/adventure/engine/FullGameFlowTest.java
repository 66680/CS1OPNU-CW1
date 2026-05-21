package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FullGameFlowTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void twoPlayersCanCompleteSharedAdventure() {
        assertTrue(engine.move(Direction.NORTH).isSuccess());
        assertTrue(engine.take("note").isSuccess());
        assertTrue(engine.switchPlayer("Bob").isSuccess());
        assertTrue(engine.move(Direction.EAST).isSuccess());
        assertTrue(engine.take("brass_key").isSuccess());
        assertTrue(engine.solve("console", "ORION").isSuccess());
        assertTrue(engine.use("brass_key").isSuccess());
        assertTrue(engine.move(Direction.EAST).isSuccess());
        assertTrue(engine.move(Direction.NORTH).isSuccess());
        assertTrue(engine.take("star_crystal").isSuccess());

        assertTrue(GameState.getInstance().isGameWon());
        assertFalse(GameState.getInstance().getEventLog().getEvents().isEmpty());
        assertFalse(GameState.getInstance().getPlayer("Alice").getMessages().isEmpty());
    }
}