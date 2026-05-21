package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineInteractionTest {
    private GameEngine engine;

    @BeforeEach
    void setUp() {
        GameState.getInstance().resetForNewGame();
        engine = new GameEngine();
        engine.startNewGame(Arrays.asList("Alice", "Bob"));
    }

    @Test
    void activePlayerCanGiveInventoryItemToAnotherPlayer() {
        engine.move(Direction.EAST);
        engine.take("brass_key");
        engine.switchPlayer("Bob");
        engine.move(Direction.EAST);
        engine.switchPlayer("Alice");

        GameResult result = engine.give("brass_key", "Bob");

        assertTrue(result.isSuccess());
        assertFalse(GameState.getInstance().getPlayer("Alice").hasItem("brass_key"));
        assertTrue(GameState.getInstance().getPlayer("Bob").hasItem("brass_key"));
        assertTrue(GameState.getInstance().getPlayer("Bob").getMessages()
                .stream()
                .anyMatch(message -> message.contains("gave Brass Key")));
    }

    @Test
    void giveFailsWhenPlayersAreInDifferentRooms() {
        engine.move(Direction.EAST);
        engine.take("brass_key");

        GameResult result = engine.give("brass_key", "Bob");

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("same room"));
        assertTrue(GameState.getInstance().getPlayer("Alice").hasItem("brass_key"));
        assertFalse(GameState.getInstance().getPlayer("Bob").hasItem("brass_key"));
    }

    @Test
    void giveFailsWhenActivePlayerDoesNotHaveItem() {
        GameResult result = engine.give("brass_key", "Bob");

        assertFalse(result.isSuccess());
        assertFalse(GameState.getInstance().getPlayer("Bob").hasItem("brass_key"));
    }

    @Test
    void giveFailsWhenTargetPlayerDoesNotExist() {
        engine.move(Direction.EAST);
        engine.take("brass_key");

        GameResult result = engine.give("brass_key", "Charlie");

        assertFalse(result.isSuccess());
        assertTrue(GameState.getInstance().getPlayer("Alice").hasItem("brass_key"));
    }

    @Test
    void inspectDescribesCurrentRoom() {
        GameResult result = engine.inspect("room");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("Entrance Hall"));
    }

    @Test
    void inspectDescribesItemInCurrentRoom() {
        engine.move(Direction.NORTH);

        GameResult result = engine.inspect("note");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("hunter constellation"));
    }

    @Test
    void inspectDescribesInventoryItem() {
        engine.move(Direction.EAST);
        engine.take("brass_key");

        GameResult result = engine.inspect("brass_key");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("observatory symbol"));
    }

    @Test
    void inspectDescribesCurrentRoomPuzzle() {
        engine.move(Direction.EAST);

        GameResult result = engine.inspect("console");

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("hunter constellation"));
    }

    @Test
    void inspectFailsForUnknownTarget() {
        GameResult result = engine.inspect("missing");

        assertFalse(result.isSuccess());
        assertTrue(result.getMessage().contains("Cannot inspect"));
    }
}